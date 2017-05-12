package com.century.modules.controller.account.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.databean.account.AccountDataBean;
import com.century.modules.databean.account.AccountSumDataBean;
import com.century.modules.databean.account.CardDataBean;
import com.century.modules.databean.account.ConsumeDataBean;
import com.century.modules.databean.account.DepositDataBean;
import com.century.modules.databean.account.PayBackDataBean;
import com.century.modules.databean.account.PayDataBean;
import com.century.modules.databean.account.TradeRecordDataBean;
import com.century.modules.databean.account.TradeRecordQueryDataBean;
import com.century.modules.entity.account.CenturyAccountDetail;
import com.century.modules.entity.account.CenturyCard;
import com.century.modules.entity.account.CenturyPaymentInfo;
import com.century.modules.entity.merchant.CenturyMerchant;
import com.century.modules.filter.account.CenturyAccountDetailFilter;
import com.century.modules.filter.account.CenturyCardFilter;
import com.century.modules.filter.account.CenturyPaymentInfoFilter;
import com.century.modules.service.TlTransaction.TlTransactionService;
import com.century.modules.service.account.CallBackService;
import com.century.modules.service.account.CenturyAccountDetailService;
import com.century.modules.service.account.CenturyCardService;
import com.century.modules.service.account.CenturytPaymentInfoService;
import com.century.modules.service.merchant.CenturyMerchantService;
import com.century.modules.util.BizException;
import com.century.modules.util.BussConst;
import com.century.modules.util.DateUtils;
import com.century.modules.util.HttpUtil;
import com.century.modules.util.NumberUtils;
import com.century.modules.util.SysBasConst;
import com.sirdc.modules.core.filter.Paging;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.entity.sys.SysLogin;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.service.sys.SysLoginService;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.sys.util.RedisUtils;
import com.sirdc.modules.util.PasswordHelper;
import com.sirdc.modules.util.SysUserUtils;
import com.sirdc.modules.utils.StringUtils;
import com.sirdc.modules.utils.encrypt.Base64Utils;

/**
 * 
 */
@Controller
@RequestMapping("/app/account")
public class CenturyAccountAppController extends JsonBaseController {
	
	private static Logger log = LoggerFactory.getLogger(CenturyAccountAppController.class);

	@Autowired
	private SysUserService userService;
	@Autowired
	private SysLoginService loginService;
	@Autowired
	private CenturyCardService centuryCardService;
	@Autowired
	private CenturytPaymentInfoService centurytPaymentInfoService;
	@Autowired
	private TlTransactionService tlTransactionService;
	@Autowired
	private CenturyMerchantService centuryMerchantService;
	@Autowired
	private CenturyAccountDetailService centuryAccountDetailService;
	@Autowired
	private CallBackService callBackService;
	

	@Override
	protected String getView(String view) {
		return "/app/account/" + view;
	}
	/**
	 * 绑定银行卡发送验证码
	 * @param dataBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sendAuthCode")
	public Message sendAuthCode(@ModelAttribute CardDataBean dataBean) {
		if (StringUtils.isBlank(dataBean.getBankCardNo())) {
			return coverMessage("500", "银行卡号不能为空");
		}
		if (StringUtils.isBlank(dataBean.getPhone())) {
			return coverMessage("500", "手机号不能为空");
		}
		if (StringUtils.isBlank(dataBean.getUserName())) {
			return coverMessage("500", "姓名不能为空");
		}
		if (StringUtils.isBlank(dataBean.getIdCard())) {
			return coverMessage("500", "身份证号不能为空");
		}
		String userId = SysUserUtils.getUserId();
		dataBean.setUserId(userId);
		try {
			centuryCardService.applyBindBankCard(dataBean);
		} catch (BizException e) {
			return coverMessage("500", e.getMessage());
		}
		return coverMessage("200", "验证码发送成功");
	}

	@ResponseBody
	@RequestMapping(value = "/bindBankCard")
	public Message bindBankCard(@ModelAttribute CardDataBean dataBean) {
		if (StringUtils.isBlank(dataBean.getBankCardNo())) {
			return coverMessage("500", "银行卡号不能为空");
		}
		if (StringUtils.isBlank(dataBean.getPhone())) {
			return coverMessage("500", "手机号不能为空");
		}
		if (StringUtils.isBlank(dataBean.getUserName())) {
			return coverMessage("500", "姓名不能为空");
		}
		if (StringUtils.isBlank(dataBean.getIdCard())) {
			return coverMessage("500", "身份证号不能为空");
		}
		if (StringUtils.isBlank(dataBean.getVerificationCode())) {
			return coverMessage("500", "验证码不能为空");
		}
		String userId = SysUserUtils.getUserId();
		dataBean.setUserId(userId);
		try {
			centuryCardService.bindBankCard(dataBean);
		} catch (BizException e) {
			return coverMessage("500", e.getMessage());
		}
		return coverMessage("200", "绑定成功");
	}
	/**
	 * 解除绑定
	 * @param dataBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/unbindBankCard")
	public Message unbindBankCard(@ModelAttribute CardDataBean dataBean) {
		if (StringUtils.isBlank(dataBean.getBankCardNo())) {
			return coverMessage("500", "银行卡号不能为空");
		}
		String userId = SysUserUtils.getUserId();
		dataBean.setUserId(userId);
		try {
			centuryCardService.unbindBankCard(dataBean);
		} catch (BizException e) {
			return coverMessage("500", e.getMessage());
		}
		return coverMessage("200", "解绑成功");
	}

	/**
	 * 获取绑定的银行卡
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getBindBankCard")
	public Message getBindBankCard() {
		String userId = SysUserUtils.getUserId();
		CenturyCardFilter filter = new CenturyCardFilter();
		filter.setUserId(userId);
		filter.setBindState(BussConst.CARD_BIND_STATE_BIND);

		List<Map<String, String>> cardList = new ArrayList<Map<String, String>>();
		
		List<CenturyCard> list = centuryCardService.query(filter);
		if (list != null) {
			for (CenturyCard card : list) {
				Map<String, String> cardMap = new HashMap<String, String>();
				cardMap.put("bankCardNo",
						StringUtils.defaultString(card.getBankCardNo()));
				cardMap.put("cardType", StringUtils.defaultString(RedisUtils
						.getDictVal(SysBasConst.CARD_TYPE, card.getCardType(),
								SysBasConst.GET_DICT_VAL)));
				cardMap.put("bankName",
						StringUtils.defaultString(card.getBankName()));
				cardMap.put("phone", StringUtils.defaultString(card.getPhone()));
				cardMap.put("bankCode", StringUtils.defaultString(card.getBankCode()));
				cardList.add(cardMap);
			}
		}
		return coverMessage("200", "", cardList);
	}

	@ResponseBody
	@RequestMapping(value = "/applyDeposit")
	public Message applyDeposit(@ModelAttribute DepositDataBean dataBean) {
		if(dataBean.getAmount() == null 
				|| dataBean.getAmount().compareTo(BigDecimal.ZERO) == 0){
			return coverMessage("500", "充值金额要大于0");
		}
		if (StringUtils.isBlank(dataBean.getBankCardNo())) {
			return coverMessage("500", "银行卡号不能为空");
		}
		String userId = SysUserUtils.getUserId();
		CenturyCardFilter filter = new CenturyCardFilter();
		filter.setUserId(userId);
		filter.setBindState(BussConst.CARD_BIND_STATE_BIND);
		List<CenturyCard> cardList = centuryCardService.query(filter);
		if(cardList == null){
			return coverMessage("500", "银行卡未绑定");
		}
		CenturyCard centuryCard = cardList.get(0);
		dataBean.setBankCode(centuryCard.getBankCode());
		dataBean.setPayMethod(BussConst.PAY_METHOD_QUICKPAY);
		dataBean.setSummary("充值");
		dataBean.setUserId(userId);
		Map<String, String> map = new HashMap<String, String>();
		try {
			String orderNo = centurytPaymentInfoService.applyDeposit(dataBean, BussConst.ACCESS_SOURCE_PHONE);
			map.put("orderNo", orderNo);
		} catch (BizException e) {
			return coverMessage("500", e.getMessage());
		}
		
		return coverMessage("200","", map);
	}
	/**
	 * 消费
	 * @param dataBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/applyConsume")
	public Message applyConsume(@ModelAttribute ConsumeDataBean dataBean) {
		if(dataBean.getAccountAmount() == null 
				|| dataBean.getAccountAmount().compareTo(BigDecimal.ZERO) == 0){
			return coverMessage("500", "付款金额要大于0");
		}
		if(StringUtils.isBlank(dataBean.getRecieverId())){
			return coverMessage("500", "收款方不能为空");
		}
		if(StringUtils.isBlank(dataBean.getPayerId())){
			return coverMessage("500", "付款方不能为空");
		}
		if(dataBean.getPayerId().equals(dataBean.getRecieverId())){
			return coverMessage("500", "不能付款给自己");
		}
		dataBean.setTotalAmount(dataBean.getAccountAmount());
		dataBean.setPayMethod(BussConst.PAY_METHOD_ACCOUNT);
		CenturyMerchant merchant = centuryMerchantService.getById(dataBean.getRecieverId());
		if(merchant == null){
			return coverMessage("500", "商家信息有误");
		}
		dataBean.setSummary(merchant.getMerchantName()+"消费");
		Map<String, String> map = new HashMap<String, String>();
		try {
			String orderNo = centurytPaymentInfoService.applyTransfer(dataBean
					, BussConst.ACCESS_SOURCE_PHONE,BussConst.TRADE_TYPE_CONSUME);
			map.put("orderNo", orderNo);
		} catch (BizException e) {
			return coverMessage("500", e.getMessage());
		}
		return coverMessage("200","", map);
	}
	/**
	 * 转账
	 * @param dataBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/applyTransfer")
	public Message applyTransfer(@ModelAttribute ConsumeDataBean dataBean) {
		if(dataBean.getAccountAmount() == null 
				|| dataBean.getAccountAmount().compareTo(BigDecimal.ZERO) == 0){
			return coverMessage("500", "付款金额要大于0");
		}
		if(StringUtils.isBlank(dataBean.getRecieverId())){
			return coverMessage("500", "收款方不能为空");
		}
		if(StringUtils.isBlank(dataBean.getPayerId())){
			return coverMessage("500", "付款方不能为空");
		}
		if(dataBean.getPayerId().equals(dataBean.getRecieverId())){
			return coverMessage("500", "不能转账给自己");
		}
		String userId = SysUserUtils.getUserId();
		if(!userId.equals(dataBean.getPayerId())){
			return coverMessage("500", "非法操作");
		}
		dataBean.setTotalAmount(dataBean.getAccountAmount());
		dataBean.setPayMethod(BussConst.PAY_METHOD_ACCOUNT);
		SysUser reciever = userService.getById(dataBean.getRecieverId());
		if(reciever == null){
			return coverMessage("500", "获取不到收款方信息");
		}
		dataBean.setSummary("转账");
		Map<String, String> map = new HashMap<String, String>();
		try {
			String orderNo = centurytPaymentInfoService.applyTransfer(dataBean, 
					BussConst.ACCESS_SOURCE_PHONE,BussConst.TRADE_TYPE_TRANSFER);
			map.put("orderNo", orderNo);
		} catch (BizException e) {
			return coverMessage("500", e.getMessage());
		}
		return coverMessage("200","", map);
	}
	@ResponseBody
	@RequestMapping(value = "/applyDepositGateWay")
	public Message applyDepositGateWay(@ModelAttribute DepositDataBean dataBean) {
		String userId = SysUserUtils.getUserId();
		CenturyCardFilter filter = new CenturyCardFilter();
		filter.setUserId(userId);
		filter.setBindState(BussConst.CARD_BIND_STATE_BIND);
		
		try {
			centurytPaymentInfoService.applyDeposit(dataBean, BussConst.ACCESS_SOURCE_PHONE);
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coverMessage("200", "");
	}
	@ResponseBody
	@RequestMapping(value = "/pay")
	public Message pay(HttpServletRequest request,@ModelAttribute PayDataBean dataBean) {
		if(StringUtils.isBlank(dataBean.getOrderNo())){
			return coverMessage("500", "订单号不能为空");
		}
		if(StringUtils.isBlank(dataBean.getVerificationCode())){
			return coverMessage("500", "验证码不能为空");
		}
		if(StringUtils.isBlank(dataBean.getCashPwd())){
			return coverMessage("500", "支付密码不能为空");
		}
		
		String userId = SysUserUtils.getUserId();
		SysLogin login = loginService.getById(userId);
		if(!PasswordHelper.getMD5Str(new String(Base64Utils.decode(dataBean.getCashPwd())))
				.equals(login.getCashPassword())){
			return coverMessage("500", "密码不正确");
		}
		Map<String, String> map = new HashMap<String, String>();
		dataBean.setConsumerIp(HttpUtil.getIpAddress(request));
		dataBean.setUserId(userId);
		try {
			String orderNo = centurytPaymentInfoService.pay(dataBean);
			map.put("orderNo", orderNo);
		} catch (BizException e) {
			return coverMessage("500", e.getMessage());
		}
		
		
		return coverMessage("200", "",map);
	}
	@ResponseBody
	@RequestMapping(value = "/queryBalance")
	public Message queryBalance() {
		String userId = SysUserUtils.getUserId();
		AccountDataBean account = tlTransactionService.queryBalance(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		if(account == null){
			map.put("amount", "0.00");
		}else{
			map.put("amount", NumberUtils.format2(account.getAllAmount()));
		}
		
		return coverMessage("200", "",map);
	}
	@ResponseBody
	@RequestMapping(value = "/transactionRecord")
	public Message transactionRecord(CenturyAccountDetailFilter filter) {
		String userId = SysUserUtils.getUserId();
		if (filter.getPaging() == null) {
			filter.setPaging(new Paging());
		}
		filter.setUserId(userId);
//		List<AccountDetailAndTradeDataBean> dataList = centuryAccountDetailService.queryJoinTrade(filter);
		List<CenturyAccountDetail> accountList = centuryAccountDetailService.query(filter);
		List<Map<String,Object>> rspList = new ArrayList<Map<String,Object>>();
		List<TradeRecordDataBean> detailList = new ArrayList<TradeRecordDataBean>();
		Map<String, List<TradeRecordDataBean>> tradeRecordMap = new HashMap<String, List<TradeRecordDataBean>>();
		List<String> txDateList = new ArrayList<String>();
		
		if(accountList != null && accountList.size() > 0){
			for(CenturyAccountDetail data : accountList){
				TradeRecordDataBean record = new TradeRecordDataBean();
				if(BussConst.ACCOUNT_MOVEMENTS_TYPE_INCOME.equals(data.getAccountingType())){
					record.setStatusName("已存入余额");
				}else{
					record.setStatusName("支付成功");
				}
				record.setTradeTypeName(StringUtils.defaultString(RedisUtils
						.getDictVal(SysBasConst.TRADE_TYPE, data.getTradeType(),
								SysBasConst.GET_DICT_VAL)));
				record.setTxAmt(StringUtils.defaultString(NumberUtils.format2(data.getTxAmt())));
				record.setTxDate(StringUtils.defaultString(DateUtils.getYmdhms(data.getTxDate())));
				record.setType(data.getAccountingType());
				detailList.add(record);
				String txDate = DateUtils.getDateString("YYYY-MM", data.getTxDate());
				tradeRecordMap.put(txDate, detailList);
				txDateList.add(txDate);
			}
			filter.setTxDate(txDateList);
			filter.setType(BussConst.ACCOUNT_MOVEMENTS_TYPE_EXPENSE);
			List<AccountSumDataBean> accountSumList = centuryAccountDetailService.sumByTxDate(filter);
			if(accountSumList != null && accountSumList.size() > 0){
				for(AccountSumDataBean accountSum : accountSumList){
					Map<String, Object> monthMap = new HashMap<String, Object>();
					monthMap.put("month", accountSum.getTxDate());
					monthMap.put("detail", tradeRecordMap.get(accountSum.getTxDate()));
					monthMap.put("sum", NumberUtils.format2(accountSum.getTxAmt().negate()));
					rspList.add(monthMap);
				}
			}
		}
		
		return coverMessage("200", "",rspList);
	}
	
	@ResponseBody
//	@RequestMapping(value = "/transRecord")
	public Message transAccountDetail(CenturyAccountDetailFilter filter) {
		String userId = SysUserUtils.getUserId();
		if (filter.getPaging() == null) {
			filter.setPaging(new Paging());
		}
		filter.setUserId(userId);
		List<CenturyAccountDetail> accountList = centuryAccountDetailService.query(filter);
		List<TradeRecordDataBean> detailList = new ArrayList<TradeRecordDataBean>();
		if(accountList != null && accountList.size() > 0){
			for(CenturyAccountDetail data : accountList){
				TradeRecordDataBean record = new TradeRecordDataBean();
				if(BussConst.ACCOUNT_MOVEMENTS_TYPE_INCOME.equals(data.getAccountingType())){
					record.setStatusName("已存入余额");
				}else{
					record.setStatusName("支付成功");
				}
				record.setTradeTypeName(StringUtils.defaultString(RedisUtils
						.getDictVal(SysBasConst.TRADE_TYPE, data.getTradeType(),
								SysBasConst.GET_DICT_VAL)));
				record.setTxAmt(StringUtils.defaultString(NumberUtils.format2(data.getTxAmt())));
				record.setTxDate(StringUtils.defaultString(DateUtils.getYmdhms(data.getTxDate())));
				record.setType(StringUtils.defaultString(data.getAccountingType()));
				record.setOrderNo(StringUtils.defaultString(data.getTxNo()));
				detailList.add(record);
			}
		}
		
		return coverMessage("200", "",detailList);
	}
	@ResponseBody
	@RequestMapping(value = "/transRecord")
	public Message transRecord(CenturyAccountDetailFilter filter) {
		String userId = SysUserUtils.getUserId();
		if (filter.getPaging() == null) {
			filter.setPaging(new Paging());
		}
		filter.setUserId(userId);
		CenturyPaymentInfoFilter paymentInfoFilter = new CenturyPaymentInfoFilter();
		paymentInfoFilter.setStatus(BussConst.PAY_STATUS_PAYING);
		paymentInfoFilter.setUserId(userId);
		//查询支付中的订单
		List<CenturyPaymentInfo> paymentList = centurytPaymentInfoService.query(paymentInfoFilter);
		if(paymentList != null){
			for(CenturyPaymentInfo paymentInfo : paymentList){
				//通联查询订单实时状态
				Long status = tlTransactionService.getOrderDetail(userId, paymentInfo.getBizOrderNo());
				if(BussConst.PAY_STATUS_SUCCESS.equals(status.toString())){//支付成功处理
					PayBackDataBean payBackDataBean = new PayBackDataBean();
					try {
						payBackDataBean.setOrderNo(paymentInfo.getCloudOrderNo());
						callBackService.pay(payBackDataBean );
					} catch (BizException e) {
						
					}
				}else if(BussConst.PAY_STATUS_FAILURE.equals(status)){//支付失败处理
					paymentInfo.setStatus(BussConst.PAY_STATUS_FAILURE);
					centurytPaymentInfoService.update(paymentInfo);
				}
			}
		}
		//获取交易记录
		List<TradeRecordQueryDataBean> tradeList = centuryAccountDetailService.queryTradeRecord(filter);
		List<TradeRecordDataBean> detailList = new ArrayList<TradeRecordDataBean>();
		if(tradeList != null && tradeList.size() > 0){
			for(TradeRecordQueryDataBean data : tradeList){
				TradeRecordDataBean record = new TradeRecordDataBean();
				if(BussConst.PAY_STATUS_FAILURE.equals(data.getStatus())){
					record.setStatusName("交易退回");
					record.setColourDefine("red");
				}else if(BussConst.PAY_STATUS_PAYING.equals(data.getStatus())){
					record.setStatusName("支付中");
					record.setColourDefine("green");
				}else{
					if(BussConst.ACCOUNT_MOVEMENTS_TYPE_INCOME.equals(data.getAccountingType())){
						record.setStatusName("已存入余额");
					}else{
						record.setStatusName("支付成功");
					}
					record.setColourDefine("gray");
				}
				record.setTradeTypeName(StringUtils.defaultString(RedisUtils
						.getDictVal(SysBasConst.TRADE_TYPE, data.getTradeType(),
								SysBasConst.GET_DICT_VAL)));
				if(BussConst.ACCOUNT_MOVEMENTS_TYPE_EXPENSE.equals(data.getAccountingType())
						&& data.getTxAmt().compareTo(BigDecimal.ZERO) > 0){
					
				}else{
					record.setTxAmt(StringUtils.defaultString(NumberUtils.format2(data.getTxAmt())));
				}
				
				record.setTxDate(StringUtils.defaultString(DateUtils.getYmdhms(data.getTxDate())));
				record.setType(StringUtils.defaultString(data.getAccountingType()));
				record.setOrderNo(StringUtils.defaultString(data.getOrderNo()));
				detailList.add(record);
			}
		}
		
		return coverMessage("200", "",detailList);
	}

}
