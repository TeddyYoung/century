package com.century.modules.service.TlTransaction;

import java.math.BigDecimal;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.century.modules.databean.account.AccountDataBean;
import com.century.modules.databean.account.CardBinDataBean;
import com.century.modules.databean.account.CardDataBean;
import com.century.modules.databean.account.CardRespDataBean;
import com.century.modules.databean.account.ConsumeDataBean;
import com.century.modules.databean.account.ConsumeRespDataBean;
import com.century.modules.databean.account.DepositDataBean;
import com.century.modules.databean.account.DepositRespDataBean;
import com.century.modules.databean.account.PayDataBean;
import com.century.modules.databean.account.PayRespDataBean;
import com.century.modules.databean.account.WithdrawDataBean;
import com.century.modules.databean.account.WithdrawRespDataBean;
import com.century.modules.gateway.tl.TlAPI;
import com.century.modules.util.BizException;
import com.century.modules.util.BussConst;
import com.century.modules.util.Constants;
import com.sirdc.modules.databean.sys.SysUserDataBean;
import com.sirdc.modules.util.JsonMapper;

@Service
public class TlTransactionService {

	private static Logger log = LoggerFactory
			.getLogger(TlTransactionService.class);

	private JsonMapper jsonMapper = JsonMapper.getInstance();
	
	public void sendVerificationCode(String bizUserId,String phone){
		try{
			//类型1
//			JSONObject extendParams = new JSONObject();
			
			JSONObject param = new JSONObject();
			param.put("bizUserId", bizUserId);
			param.put("phone", phone);
			param.put("verificationCodeType", BussConst.VERIFICATION_CODE_TYPE_BIND);
//			param.put("extendParams", extendParams);
			
			JSONObject response = TlAPI.memberService("sendVerificationCode", param);
			resultHandle(response);
		}catch(Exception e){
			log.error("验证码发送异常：=========" + e.getMessage());
		}
	}
	
	public void bindPhone(String bizUserId,String phone,String verificationCode)throws BizException{
		JSONObject param = new JSONObject();
		try{
			param.put("bizUserId", bizUserId);
			param.put("phone", phone);
			param.put("verificationCode", verificationCode);
			
		}catch(Exception e){
			log.error("绑定手机异常：=========" + e.getMessage());
			throw new BizException("手机绑定失败");
		}
		JSONObject response = TlAPI.memberService("bindPhone", param);
		resultHandle(response);
	}

	/**
	 * 创建会员
	 * 
	 * @param memberId
	 * @return
	 * @throws BizException
	 */
	public String createMember(String memberId, String source)
			throws BizException {
		JSONObject param = new JSONObject();
		param.put("bizUserId", memberId);
		param.put("memberType", BussConst.MEMBER_TYPE_PERSONAL);
		param.put("source", source);
		// param.put("extendParam", "");
		JSONObject response = TlAPI.memberService("createMember", param);
		JSONObject returnValue = resultHandle(response);
		return returnValue.getString("userId");
	}

	/**
	 * 实名认证
	 * 
	 * @param user
	 * @param isAuth
	 *            是否需要认证
	 * @return
	 */
	public boolean setRealName(SysUserDataBean user, boolean isAuth) {
		boolean result = false;
		try {
			JSONObject param = new JSONObject();
			param.put("bizUserId", user.getSysId());
			param.put("name", user.getName());
			param.put("identityType", BussConst.IDENTITYTYPE_IDCARD);
			param.put("identityNo", TlAPI.rsaEncrypt(user.getIdCard()));
			if (!isAuth) {
				param.put("isAuth", "false");
			}

			JSONObject response = TlAPI.memberService("setRealName", param);
			if (Constants.RESPONSE_STATUS_OK.equals(response.get("status"))) {
				result = true;
			}
			if(Constants.RESPONSE_STATUS_ERROR.equals(response.get("status"))){
				if(BussConst.TL_RSP_REAL_NAME.equals(response.get("errorCode"))){
					result = true;
				}
			}
		} catch (Exception e) {
			log.error("云实名认证异常：=========" + e.getMessage());
		}
		return result;
	}

	/**
	 * 申请绑定银行卡
	 * 
	 * @param card
	 * @throws BizException
	 */
	public CardRespDataBean applyBindBankCard(CardDataBean card)
			throws BizException {
		JSONObject response = new JSONObject();
		try {
			// 借记卡
			JSONObject param = new JSONObject();

			param.put("bizUserId", card.getUserId());
			param.put("cardNo", TlAPI.rsaEncrypt(card.getBankCardNo()));
			param.put("phone", card.getPhone());
			param.put("name", card.getUserName());
			param.put("cardType", card.getCardType());
			param.put("bankCode", card.getBankCode());
			param.put("identityType", BussConst.IDENTITYTYPE_IDCARD);
			param.put("identityNo", TlAPI.rsaEncrypt(card.getIdCard()));

			// 信用卡
			if (BussConst.CARD_TYPE_CREDIT.equals(card.getCardType())) {
				param.put("validate", TlAPI.rsaEncrypt(card.getValidate()));
				param.put("cvv2", TlAPI.rsaEncrypt(card.getCvv2()));
			}

			response = TlAPI.memberService("applyBindBankCard", param);

		} catch (Exception e) {
			log.error("云申请银行卡绑定异常：=======" + e.getMessage());
			throw new BizException("云申请绑定失败");
		}
		JSONObject returnValue = resultHandle(response);
		return jsonMapper.fromJson(JSONObject.valueToString(returnValue),
				CardRespDataBean.class);
	}

	/**
	 * 绑定银行卡
	 * 
	 * @param card
	 * @param resp
	 * @throws BizException
	 */
	public void bindBankCard(CardDataBean card, CardRespDataBean resp)
			throws BizException {
		JSONObject response = new JSONObject();
		try {
			JSONObject param = new JSONObject();
			param.put("bizUserId", card.getUserId());
			param.put("tranceNum", resp.getTranceNum());
			param.put("transDate", resp.getTransDate());
			param.put("phone", card.getPhone());
			param.put("verificationCode", card.getVerificationCode());

			response = TlAPI.memberService("bindBankCard", param);
		} catch (Exception e) {
			log.error("云绑定银行卡异常：=======" + e.getMessage());
			throw new BizException("云绑定银行卡失败");
		}
		resultHandle(response);
	}

	/**
	 * 查询卡bin
	 * 
	 * @param bankCardNo
	 * @return
	 */
	public CardBinDataBean getBankCardBin(String bankCardNo) {
		JSONObject response = new JSONObject();
		try {
			JSONObject param = new JSONObject();
			param.put("cardNo", TlAPI.rsaEncrypt(bankCardNo));

			response = TlAPI.memberService("getBankCardBin", param);
			JSONObject returnValue = resultHandle(response);
			return jsonMapper.fromJson(JSONObject.valueToString(returnValue
					.getJSONObject("cardBinInfo")), CardBinDataBean.class);
		} catch (Exception e) {
			log.error("云绑定银行卡异常：=======" + e.getMessage());
			return null;
		}

	}

	/**
	 * 解绑银行卡
	 * 
	 * @param userId
	 * @param cardNo
	 * @throws BizException
	 */
	public void unbindBankCard(String userId, String cardNo)
			throws BizException {
		JSONObject response = new JSONObject();
		try {
			JSONObject param = new JSONObject();
			param.put("bizUserId", userId);
			param.put("cardNo", TlAPI.rsaEncrypt(cardNo));

			response = TlAPI.memberService("unbindBankCard", param);
		} catch (Exception e) {
			log.error("云解绑银行卡异常：=======" + e.getMessage());
			throw new BizException("解绑银行卡失败");
		}
		resultHandle(response);
	}

	/**
	 * 充值
	 * 
	 * @param payment
	 * @param source
	 * @return
	 * @throws BizException
	 */
	public DepositRespDataBean applyDeposit(DepositDataBean deposit, String source)
			throws BizException {
		JSONObject response = new JSONObject();
		try {
			JSONObject param = new JSONObject();
			Long amount = TlAPI.amountConvert(deposit.getAmount());
			// 支付方式
			// 组装支付方式
			JSONObject payMethod = new JSONObject();
			if (BussConst.PAY_METHOD_QUICKPAY.equals(deposit.getPayMethod())) {// 快捷
				JSONObject quickPay = new JSONObject();
				quickPay.put("bankCardNo",
						TlAPI.rsaEncrypt(deposit.getBankCardNo()));
				quickPay.put("amount", amount);
				payMethod.put("QUICKPAY", quickPay);
			} else if (BussConst.PAY_METHOD_GATEWAY.equals(deposit
					.getPayMethod())) {// 网关
				JSONObject gatewayPay = new JSONObject();
				// gatewayPay.put("bankCode", deposit.getBankCode()); //TODO
				gatewayPay.put("bankCode", "vbank"); // 虚拟银行，专门用于测试
				// gatewayPay.put("bankCardType", 1L);
				// gatewayPay.put("payType", deposit.getPayType());
				gatewayPay.put("payType",
						BussConst.GATEWAY_PAYTYPE_AUTHENTICATION);// 目前只支持认证支付
				gatewayPay.put("bankCardNo",
						TlAPI.rsaEncrypt(deposit.getBankCardNo()));
				gatewayPay.put("amount", amount);
				payMethod.put("GATEWAY", gatewayPay);
			}

			param.put("bizUserId", deposit.getUserId());
			param.put("bizOrderNo", deposit.getBizOrderNo());
			param.put("accountSetNo", TlAPI.ACCOUNT_NO);
			param.put("amount", TlAPI.amountConvert(deposit.getAmount()));
			param.put("fee", TlAPI.amountConvert(deposit.getFee()));
//			param.put("frontUrl", RedisUtils.getDictVal(SysBasConst.FRONT_URL,
//					SysBasConst.FRONT_URL_DEPOSIT,
//					SysBasConst.GET_DICT_VAL_ATTR1));
			param.put("backUrl", deposit.getBackUrl());
			param.put("ordErexpireDatetime", TlAPI.getOrdErexpireDatetime());
			param.put("payMethod", payMethod);
			param.put("industryCode", TlAPI.INDUSTRY_CODE);
			param.put("industryName", TlAPI.INDUSTRY_NAME);
			param.put("source", source);
			param.put("summary", deposit.getSummary());
			if(StringUtils.isNotBlank(deposit.getExtendInfo())){
				param.put("extendInfo", deposit.getExtendInfo());
			}

			response = TlAPI.orderService("depositApply", param);
		} catch (Exception e) {
			log.error("充值异常：=======" + e.getMessage());
			throw new BizException("充值失败");
		}
		JSONObject returnValue = resultHandle(response);
		return jsonMapper.fromJson(JSONObject.valueToString(returnValue),
				DepositRespDataBean.class);
	}

	/**
	 * 提现
	 * 
	 * @param withdraw
	 * @param source
	 * @return
	 * @throws BizException
	 */
	public WithdrawRespDataBean applyWithdraw(WithdrawDataBean withdraw,
			String source) throws BizException {
		JSONObject response = new JSONObject();
		try {
			JSONObject param = new JSONObject();
			param.put("bizUserId", withdraw.getUserId());
			param.put("bizOrderNo", withdraw.getBizOrderNo());
			param.put("accountSetNo", TlAPI.ACCOUNT_NO);
			param.put("amount", TlAPI.amountConvert(withdraw.getAmount()));
			param.put("fee", TlAPI.amountConvert(withdraw.getFee()));
			param.put("backUrl", withdraw.getBackUrl());
			param.put("ordErexpireDatetime", TlAPI.getOrdErexpireDatetime());
			param.put("bankCardNo", TlAPI.rsaEncrypt(withdraw.getBankCardNo()));
			param.put("industryCode", TlAPI.INDUSTRY_CODE);
			param.put("industryName", TlAPI.INDUSTRY_NAME);
			param.put("source", source);
			param.put("summary", withdraw.getSummary());
			param.put("extendInfo", withdraw.getExtendInfo());

			response = TlAPI.orderService("withdrawApply", param);
		} catch (Exception e) {
			log.error("提现异常：=======" + e.getMessage());
			throw new BizException("提现失败");
		}
		JSONObject returnValue = resultHandle(response);
		return jsonMapper.fromJson(JSONObject.valueToString(returnValue),
				WithdrawRespDataBean.class);
	}

	/**
	 * 消费付款
	 * 
	 * @param consume
	 * @param source
	 * @return
	 * @throws BizException
	 */
	public ConsumeRespDataBean applyConsume(ConsumeDataBean consume,
			String source) throws BizException {
		JSONObject response = new JSONObject();
		try {
			JSONObject param = new JSONObject();
			// 组装支付方式
			JSONObject payMethod = new JSONObject();
			if (BussConst.PAY_METHOD_QUICKPAY.equals(consume.getPayMethod())) {// 快捷
				JSONObject quickPay = new JSONObject();
				quickPay.put("bankCardNo",
						TlAPI.rsaEncrypt(consume.getBankCardNo()));
				quickPay.put("amount",
						TlAPI.amountConvert(consume.getCardAmount()));
				payMethod.put("QUICKPAY", quickPay);
			} else if (BussConst.PAY_METHOD_GATEWAY.equals(consume
					.getPayMethod())) {// 网关
				JSONObject gatewayPay = new JSONObject();
				gatewayPay.put("bankCode", consume.getBankCode()); // 虚拟银行，专门用于测试
				// gatewayPay.put("bankCode", "vbank"); //虚拟银行，专门用于测试
				gatewayPay.put("payType", consume.getPayType());
				gatewayPay.put("amount", consume.getCardAmount());
				payMethod.put("GATEWAY", gatewayPay);
			} else if (BussConst.PAY_METHOD_ACCOUNT.equals(consume
					.getPayMethod())) {// 账户余额
				JSONArray accountPay = new JSONArray();
				JSONObject accountObject = new JSONObject();
				accountObject.put("accountSetNo", TlAPI.ACCOUNT_NO);
				accountObject.put("amount",
						TlAPI.amountConvert(consume.getAccountAmount()));
				accountPay.put(accountObject);
				payMethod.put("BALANCE", accountPay);
			} else {
				throw new BizException("暂不支持该支付方式");
			}
			// else
			// if(BussConst.PAY_METHOD_ACCOUNT_QUICKPAY.equals(consume.getPayMethod())){//账户余额+快捷
			//
			// }else
			// if(BussConst.PAY_METHOD_ACCOUNT_GATEWAY.equals(consume.getPayMethod())){//账户余额+网关
			//
			// }
			// 分账规则
			JSONArray splitRule = new JSONArray();

			param.put("payerId", consume.getPayerId());
			param.put("recieverId", consume.getRecieverId());
			param.put("bizOrderNo", consume.getBizOrderNo());
			param.put("amount", TlAPI.amountConvert(consume.getTotalAmount()));
			param.put("fee", TlAPI.amountConvert(consume.getFee()));
			param.put("splitRule", splitRule);
			param.put("frontUrl", consume.getFrontUrl());
			param.put("backUrl", consume.getBackUrl());
			param.put("showUrl", consume.getShowUrl());
			param.put("ordErexpireDatetime", TlAPI.getOrdErexpireDatetime());
			param.put("payMethod", payMethod);
			param.put("goodsName", consume.getGoodsName());
			param.put("goodsDesc", consume.getGoodsDesc());
			param.put("industryCode", TlAPI.INDUSTRY_CODE);
			param.put("industryName", TlAPI.INDUSTRY_NAME);
			param.put("source", source);
			param.put("summary", consume.getSummary());
			param.put("extendInfo", consume.getExtendInfo());

			response = TlAPI.orderService("consumeApply", param);
		} catch (Exception e) {
			log.error("消费异常：=======" + e.getMessage());
			throw new BizException("订单支付失败");
		}
		JSONObject returnValue = resultHandle(response);
		return jsonMapper.fromJson(JSONObject.valueToString(returnValue),
				ConsumeRespDataBean.class);
	}
	/**
	 * 确认支付（后台）
	 * @param pay
	 * @return
	 * @throws BizException
	 */
	public PayRespDataBean pay(PayDataBean pay)throws BizException {
		JSONObject response = new JSONObject();
		try{
			JSONObject param = new JSONObject();
			param.put("bizUserId", pay.getUserId());
			param.put("bizOrderNo", pay.getBizOrderNo());
			param.put("tradeNo", pay.getTradeNo());
			param.put("verificationCode", pay.getVerificationCode());
			param.put("consumerIp", pay.getConsumerIp());
			
			response = TlAPI.orderService("pay", param);
		}catch(Exception e){
			log.error("确认支付异常：=======" + e.getMessage());
			throw new BizException("支付失败");
		}
		JSONObject returnValue = resultHandle(response);
		return jsonMapper.fromJson(JSONObject.valueToString(returnValue),
				PayRespDataBean.class);
	}
	/**
	 * 确认支付（前台）
	 * @param pay
	 * @return
	 * @throws BizException
	 */
	public void payFront(PayDataBean pay)throws BizException {
		try{
			JSONObject param = new JSONObject();
			param.put("bizUserId", pay.getUserId());
			param.put("bizOrderNo", pay.getBizOrderNo());
			param.put("verificationCode", pay.getVerificationCode());
			param.put("consumerIp", pay.getConsumerIp());
			
			TlAPI.orderService("pay", param);
		}catch(Exception e){
			log.error("确认支付异常：=======" + e.getMessage());
			throw new BizException("支付失败");
		}
	}

	/**
	 * 查询余额
	 * 
	 * @param userId
	 * @return
	 */
	public AccountDataBean queryBalance(String userId) {
		try {
			JSONObject param = new JSONObject();
			param.put("bizUserId", userId);
			param.put("accountSetNo", TlAPI.ACCOUNT_NO);

			JSONObject response = TlAPI.orderService("queryBalance", param);

			JSONObject returnValue = resultHandle(response);
			AccountDataBean accountDataBean = jsonMapper.fromJson(JSONObject.valueToString(returnValue),
					AccountDataBean.class);
			accountDataBean.setAllAmount(accountDataBean.getAllAmount().divide(new BigDecimal(100)));
			accountDataBean.setFreezenAmount(accountDataBean.getFreezenAmount().divide(new BigDecimal(100)));
			return accountDataBean;
		} catch (Exception e) {
			log.error("查询余额异常：=======" + e.getMessage());
			return null;
		}
	}
	
	public Long getOrderDetail(String bizUserId,String bizOrderNo){
		try{
			JSONObject param = new JSONObject();
			param.put("bizUserId", bizUserId);
			param.put("bizOrderNo", bizOrderNo);
			
			JSONObject response = TlAPI.orderService("getOrderDetail", param);
			JSONObject returnValue = resultHandle(response);
			return returnValue.getLong("orderStatus");
		}catch(Exception e){
			log.error("查询订单状态异常：=======" + e.getMessage());
			return null;
		}
		
	}

	private JSONObject resultHandle(JSONObject response) throws BizException {
		log.error("通联返回-response"+response);
		if (!Constants.RESPONSE_STATUS_OK.equals(response.get("status"))) {
			if(response.has("message")){
				log.error("通联返回-error"+response.get("message"));
				throw new BizException(response.get("message").toString());
			}else{
				throw new BizException("未知异常");
			}
			
		}
		return response.getJSONObject("returnValue");

	}

	private JSONObject decode(String signedValue) throws BizException {
		try {
			String value = URLDecoder.decode(signedValue, "UTF-8");
			String decode = TlAPI.rsaDecrypt(value);
			return new JSONObject(decode);
		} catch (Exception e) {
			log.error("解密异常=======" + e.getMessage());
			throw new BizException("解密失败");
		}
	}

	private String decodeString(String signedValue) throws BizException {
		try {
			String value = URLDecoder.decode(signedValue, "UTF-8");
			return TlAPI.rsaDecrypt(value);
		} catch (Exception e) {
			log.error("解密异常=======" + e.getMessage());
			throw new BizException("解密失败");
		}
	}
}
