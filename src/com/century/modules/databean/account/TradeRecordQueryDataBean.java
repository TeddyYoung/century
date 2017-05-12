package com.century.modules.databean.account;

import java.math.BigDecimal;
import java.util.Date;

public class TradeRecordQueryDataBean {

	private BigDecimal txAmt;

	private Date txDate;

	private String accountingType;

	private String orderNo;
	
	private String tradeType;
	
	private String status;

	public TradeRecordQueryDataBean() {
		super();
	}


	public TradeRecordQueryDataBean(BigDecimal txAmt, Date txDate, String accountingType,
			String orderNo, String tradeType, String status) {
		super();
		this.txAmt = txAmt;
		this.txDate = txDate;
		this.accountingType = accountingType;
		this.orderNo = orderNo;
		this.tradeType = tradeType;
		this.status = status;
	}



	public BigDecimal getTxAmt() {
		return txAmt;
	}

	public void setTxAmt(BigDecimal txAmt) {
		this.txAmt = txAmt;
	}

	public Date getTxDate() {
		return txDate;
	}

	public void setTxDate(Date txDate) {
		this.txDate = txDate;
	}


	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public String getTradeType() {
		return tradeType;
	}


	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getAccountingType() {
		return accountingType;
	}


	public void setAccountingType(String accountingType) {
		this.accountingType = accountingType;
	}

}
