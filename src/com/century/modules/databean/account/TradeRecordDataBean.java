package com.century.modules.databean.account;


public class TradeRecordDataBean {
	private String txAmt;
	
	private String tradeTypeName;
	
	private String txDate;
	
	private String statusName;
	
	private String type;
	
	private String orderNo;
	
	private String colourDefine;


	public String getTradeTypeName() {
		return tradeTypeName;
	}

	public void setTradeTypeName(String tradeTypeName) {
		this.tradeTypeName = tradeTypeName;
	}


	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTxDate() {
		return txDate;
	}

	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}

	public String getTxAmt() {
		return txAmt;
	}

	public void setTxAmt(String txAmt) {
		this.txAmt = txAmt;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getColourDefine() {
		return colourDefine;
	}

	public void setColourDefine(String colourDefine) {
		this.colourDefine = colourDefine;
	}
}
