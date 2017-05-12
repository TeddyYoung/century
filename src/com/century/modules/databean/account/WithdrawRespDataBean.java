package com.century.modules.databean.account;


public class WithdrawRespDataBean {
	
	/**
	 * 云订单号
	 */
	private String orderNo;
	/**
	 * 平台订单号
	 */
	private String bizOrderNo;
	/**
	 * 扩展信息
	 */
	private String extendInfo;
	
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(String extendInfo) {
		this.extendInfo = extendInfo;
	}
	public String getBizOrderNo() {
		return bizOrderNo;
	}
	public void setBizOrderNo(String bizOrderNo) {
		this.bizOrderNo = bizOrderNo;
	}
	
}
