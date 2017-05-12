/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyAccountService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月21日
 */
package com.century.modules.service.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.century.modules.dao.account.CenturytPaymentInfoDao;
import com.century.modules.databean.account.AccountDataBean;
import com.century.modules.databean.account.ConsumeDataBean;
import com.century.modules.databean.account.ConsumeRespDataBean;
import com.century.modules.databean.account.DepositDataBean;
import com.century.modules.databean.account.DepositRespDataBean;
import com.century.modules.databean.account.PayDataBean;
import com.century.modules.entity.account.CenturyPaymentInfo;
import com.century.modules.filter.account.CenturyPaymentInfoFilter;
import com.century.modules.service.TlTransaction.TlTransactionService;
import com.century.modules.util.BizException;
import com.century.modules.util.BussConst;
import com.century.modules.util.Constants;
import com.century.modules.util.SysBasConst;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;
import com.sirdc.modules.sys.util.RedisUtils;

/**
 * 
 * @author: yang
 * @version 
 * @Date: 
 */
@Service
public class CenturytPaymentInfoService extends StringPKService<CenturyPaymentInfo> {
	
	@Autowired
	private CenturytPaymentInfoDao dao;
	
	@Autowired
	private SysTableService sysTableService;
	
	@Autowired
	private TlTransactionService tlTransactionService;
	
	@Override
	protected BaseDao<CenturyPaymentInfo, String> getDao() {
		return dao;
	}
	
	@Override
	protected void beforeSave(CenturyPaymentInfo entity) {
		entity.setSysId(sysTableService.updateMaxSysId("t_sjt_payment_info", ""));
		super.beforeSave(entity);
	}
	
	public List<CenturyPaymentInfo> query(CenturyPaymentInfoFilter filter){
		return dao.query(filter);
	}

	public CenturyPaymentInfo getByCloudOrderNo(String cloudOrderNo) {
		return dao.getByCloudOrderNo(cloudOrderNo);
	}
	
	public CenturyPaymentInfo getByOrderNo(String orderNo) {
		return dao.getByOrderNo(orderNo);
	}
	
	@Transactional
	public String applyDeposit(DepositDataBean dataBean,String source)throws BizException{
		if(StringUtils.isBlank(dataBean.getUserId())){
			throw new BizException("UserId不能为空");
		}
		if(dataBean.getAmount() == null 
				|| dataBean.getAmount().compareTo(BigDecimal.ZERO) == 0){
			throw new BizException("充值金额不能为空");
		}
		if(StringUtils.isBlank(dataBean.getPayMethod())){
			throw new BizException("支付方式不能为空");
		}
		if(StringUtils.isBlank(dataBean.getBankCardNo())){
			throw new BizException("银行卡号不能为空");
		}
		if(StringUtils.isBlank(dataBean.getBankCode())){
			throw new BizException("银行代号不能为空");
		}
		//生成订单号
		String orderNo = sysTableService.updateMaxSysId(Constants.SYS_TABLE_NAME_ORDERNO, "");
		dataBean.setBizOrderNo(orderNo);
		CenturyPaymentInfo paymentInfo = new CenturyPaymentInfo();
		paymentInfo.setUserId(dataBean.getUserId());
		paymentInfo.setAccountAmount(BigDecimal.ZERO);
		paymentInfo.setBackUrl(RedisUtils.getDictVal(SysBasConst.BACK_URL,
				SysBasConst.BACK_URL_PAY,
				SysBasConst.GET_DICT_VAL_ATTR1));
		paymentInfo.setBankCardNo(dataBean.getBankCardNo());
		paymentInfo.setBankCode(dataBean.getBankCode());
		paymentInfo.setBizOrderNo(orderNo);
		paymentInfo.setCardAmount(dataBean.getAmount());
//		paymentInfo.setExtendInfo(extendInfo);
		paymentInfo.setFee(dataBean.getFee());
//		paymentInfo.setFrontUrl(frontUrl);
		paymentInfo.setPayMethod(dataBean.getPayMethod());
		paymentInfo.setOrderTime(new Date());
//		paymentInfo.setPayType(dataBean.getPayType());
//		paymentInfo.setShowUrl(showUrl);
		paymentInfo.setStatus(BussConst.PAY_STATUS_INIT);
		paymentInfo.setSummary(dataBean.getSummary());
		paymentInfo.setTotalAmount(dataBean.getAmount());
		paymentInfo.setTradeType(BussConst.TRADE_TYPE_DEPOSIT);
		paymentInfo.setAccountType(BussConst.ACCOUNT_MOVEMENTS_TYPE_INCOME);
		
		dataBean.setBackUrl(paymentInfo.getBackUrl());
		DepositRespDataBean resp = tlTransactionService.applyDeposit(dataBean , source);
		paymentInfo.setCloudTradeNo(resp.getTradeNo());
		paymentInfo.setCloudOrderNo(resp.getOrderNo());
		save(paymentInfo);
		return resp.getOrderNo();
	}
	/**
	 * 
	 * @param dataBean
	 * @param source
	 * @return
	 * @throws BizException
	 */
	@Transactional
	public String applyTransfer(ConsumeDataBean dataBean,String source,String tradeType)throws BizException{
		if(StringUtils.isBlank(dataBean.getPayerId())){
			throw new BizException("付款方不能为空");
		}
		if(StringUtils.isBlank(dataBean.getRecieverId())){
			throw new BizException("收款方不能为空");
		}
		if(dataBean.getTotalAmount() == null 
				|| dataBean.getTotalAmount().compareTo(BigDecimal.ZERO) == 0){
			throw new BizException("支付金额要大于0");
		}
		if(StringUtils.isBlank(dataBean.getPayMethod())){
			throw new BizException("支付方式不能为空");
		}
		if(BussConst.PAY_METHOD_ACCOUNT.equals(dataBean.getPayMethod())){
			if(dataBean.getAccountAmount() == null 
					|| dataBean.getAccountAmount().compareTo(BigDecimal.ZERO) == 0){
				throw new BizException("账户支付金额要大于0");
			}
		}
		if(BussConst.PAY_METHOD_QUICKPAY.equals(dataBean.getPayMethod())){
			if(StringUtils.isBlank(dataBean.getBankCardNo())){
				throw new BizException("银行卡号不能为空");
			}
			if(StringUtils.isBlank(dataBean.getBankCode())){
				throw new BizException("银行代号不能为空");
			}
			if(dataBean.getCardAmount() == null 
					|| dataBean.getCardAmount().compareTo(BigDecimal.ZERO) == 0){
				throw new BizException("银行卡支付金额要大于0");
			}
		}
		//生成订单号
		String orderNo = sysTableService.updateMaxSysId(Constants.SYS_TABLE_NAME_ORDERNO, "");
		dataBean.setBizOrderNo(orderNo);
		CenturyPaymentInfo paymentInfo = new CenturyPaymentInfo();
		paymentInfo.setUserId(dataBean.getPayerId());
		paymentInfo.setRecieverId(dataBean.getRecieverId());
		paymentInfo.setAccountAmount(dataBean.getAccountAmount());
		paymentInfo.setBackUrl(RedisUtils.getDictVal(SysBasConst.BACK_URL,
				SysBasConst.BACK_URL_PAY,
				SysBasConst.GET_DICT_VAL_ATTR1));
		paymentInfo.setBankCardNo(dataBean.getBankCardNo());
		paymentInfo.setBankCode(dataBean.getBankCode());
		paymentInfo.setBizOrderNo(orderNo);
		paymentInfo.setCardAmount(dataBean.getCardAmount());
//		paymentInfo.setExtendInfo(extendInfo);
		paymentInfo.setFee(dataBean.getFee());
//		paymentInfo.setFrontUrl(frontUrl);
		paymentInfo.setPayMethod(dataBean.getPayMethod());
		paymentInfo.setOrderTime(new Date());
//		paymentInfo.setPayType(dataBean.getPayType());
//		paymentInfo.setShowUrl(showUrl);
		paymentInfo.setStatus(BussConst.PAY_STATUS_INIT);
		paymentInfo.setSummary(dataBean.getSummary());
		paymentInfo.setTotalAmount(dataBean.getTotalAmount());
		paymentInfo.setTradeType(tradeType);
		paymentInfo.setAccountType(BussConst.ACCOUNT_MOVEMENTS_TYPE_EXPENSE);
		
		dataBean.setBackUrl(paymentInfo.getBackUrl());
		ConsumeRespDataBean resp = tlTransactionService.applyConsume(dataBean, source);
		paymentInfo.setCloudTradeNo(resp.getTradeNo());
		paymentInfo.setCloudOrderNo(resp.getOrderNo());
		save(paymentInfo);
		return resp.getOrderNo();
	}
	@Transactional
	public String pay(PayDataBean dataBean)throws BizException{
		if(StringUtils.isBlank(dataBean.getUserId())){
			throw new BizException("UserId不能为空");
		}
//		if(StringUtils.isBlank(dataBean.getBizOrderNo())){
//			throw new BizException("订单号不能为空");
//		}
		if(StringUtils.isBlank(dataBean.getVerificationCode())){
			throw new BizException("验证码不能为空");
		}
		if(StringUtils.isBlank(dataBean.getConsumerIp())){
			throw new BizException("ip不能为空");
		}
//		if(BussConst.PAY_METHOD_QUICKPAY.equals(dataBean.getPayMethod())
//				&& StringUtils.isBlank(dataBean.getTradeNo())){
//			throw new BizException("交易编号不能为空");
//		}
		
		CenturyPaymentInfo paymentInfo = getByCloudOrderNo(dataBean.getOrderNo());
		if(paymentInfo == null){
			throw new BizException("订单不存在或已过期");
		}
		if(BussConst.PAY_METHOD_QUICKPAY.equals(paymentInfo.getPayMethod())
				&& StringUtils.isBlank(paymentInfo.getCloudTradeNo())){
			throw new BizException("获取不到交易编号");
		}
		AccountDataBean accountDataBean = tlTransactionService.queryBalance(dataBean.getUserId());
		if(accountDataBean == null || accountDataBean.getAllAmount() == null){
			throw new BizException("获取不到账户信息");
		}
		if(BussConst.PAY_METHOD_ACCOUNT.equals(paymentInfo.getPayMethod())){
			if(accountDataBean.getAllAmount().compareTo(paymentInfo.getTotalAmount()) < 0){
				throw new BizException("余额不足");
			}
		}
		
		paymentInfo.setStatus(BussConst.PAY_STATUS_PAYING);
		update(paymentInfo);
		dataBean.setPayMethod(paymentInfo.getPayMethod());
		dataBean.setTradeNo(paymentInfo.getCloudTradeNo());
		dataBean.setBizOrderNo(paymentInfo.getBizOrderNo());
		tlTransactionService.pay(dataBean);
		return paymentInfo.getBizOrderNo();
	}
	
}
