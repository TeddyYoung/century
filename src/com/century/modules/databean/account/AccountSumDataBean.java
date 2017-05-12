package com.century.modules.databean.account;

import java.math.BigDecimal;

public class AccountSumDataBean {
	private String txDate;
	
	private BigDecimal txAmt;

	public String getTxDate() {
		return txDate;
	}
	
	public AccountSumDataBean(String txDate,BigDecimal txAmt){
		this.txAmt = txAmt;
		this.txDate = txDate;
	
	}

	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}

	public BigDecimal getTxAmt() {
		return txAmt;
	}

	public void setTxAmt(BigDecimal txAmt) {
		this.txAmt = txAmt;
	}
}
