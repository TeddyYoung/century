package com.century.modules.databean.account;

import java.math.BigDecimal;

public class WithdrawDataBean {
	
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
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
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
	public String getBizOrderNo() {
		return bizOrderNo;
	}
	public void setBizOrderNo(String bizOrderNo) {
		this.bizOrderNo = bizOrderNo;
	}
	
	
}
