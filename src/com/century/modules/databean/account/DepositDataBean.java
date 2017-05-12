package com.century.modules.databean.account;

import java.math.BigDecimal;

public class DepositDataBean {
	
	private String userId;//用户Id
	/**
	 * 订单号
	 */
	private String bizOrderNo;
	/**
	 * 金额
	 */
	private BigDecimal amount;
	/**
	 * 手续费
	 */
	private BigDecimal fee;
	/**
	 * 支付方式
	 */
	private String payMethod;
	/**
	 * 摘要
	 */
	private String summary;
	/**
	 * 扩展信息
	 */
	private String extendInfo;
	/**
	 * 银行卡号
	 */
	private String bankCardNo;
	/**
	 * 前台通知地址
	 */
	private String frontUrl;
	/**
	 * 后台通知地址
	 */
	private String backUrl;
	/**
	 * 银行代码
	 */
	private String bankCode;
	/**
	 * 网关支付类型
	 */
	private String payType;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getFee() {
		if(fee == null){
			fee = BigDecimal.ZERO;
		}
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(String extendInfo) {
		this.extendInfo = extendInfo;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getFrontUrl() {
		return frontUrl;
	}
	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getBizOrderNo() {
		return bizOrderNo;
	}
	public void setBizOrderNo(String bizOrderNo) {
		this.bizOrderNo = bizOrderNo;
	}
	
	
}
