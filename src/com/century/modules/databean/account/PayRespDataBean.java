package com.century.modules.databean.account;


public class PayRespDataBean {
	
	private String userId;//用户Id
	/**
	 * 订单号
	 */
	private String bizOrderNo;
	
	
	public String getBizOrderNo() {
		return bizOrderNo;
	}
	public void setBizOrderNo(String bizOrderNo) {
		this.bizOrderNo = bizOrderNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
