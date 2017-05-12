package com.century.modules.databean.account;

import org.json.JSONObject;


public class PayBackDataBean {
	
	/**
	 * 订单号
	 */
	private String bizOrderNo;
	
	private String orderNo;
	
	private String amount;
	
	private String payDatetime;
	
	private String buyerBizUserId;
	
	private JSONObject extendInfo;
	
	
	public String getBizOrderNo() {
		return bizOrderNo;
	}
	public void setBizOrderNo(String bizOrderNo) {
		this.bizOrderNo = bizOrderNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPayDatetime() {
		return payDatetime;
	}
	public void setPayDatetime(String payDatetime) {
		this.payDatetime = payDatetime;
	}
	public String getBuyerBizUserId() {
		return buyerBizUserId;
	}
	public void setBuyerBizUserId(String buyerBizUserId) {
		this.buyerBizUserId = buyerBizUserId;
	}
	public JSONObject getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(JSONObject extendInfo) {
		this.extendInfo = extendInfo;
	}
	
}
