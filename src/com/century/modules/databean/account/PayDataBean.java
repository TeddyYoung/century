package com.century.modules.databean.account;


public class PayDataBean {
	
	private String userId;//用户Id
	/**
	 * 订单号
	 */
	private String bizOrderNo;
	
	private String orderNo;
	/**
	 * 
	 */
	private String verificationCode;

	private String consumerIp;

	private String tradeNo;
	
	private String payMethod;
	
	private String cashPwd;


	public String getBizOrderNo() {
		return bizOrderNo;
	}

	public void setBizOrderNo(String bizOrderNo) {
		this.bizOrderNo = bizOrderNo;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getConsumerIp() {
		return consumerIp;
	}

	public void setConsumerIp(String consumerIp) {
		this.consumerIp = consumerIp;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCashPwd() {
		return cashPwd;
	}

	public void setCashPwd(String cashPwd) {
		this.cashPwd = cashPwd;
	}
	
}
