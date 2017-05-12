package com.century.modules.databean.account;

import java.math.BigDecimal;

public class AccountDataBean {
	private String userId;
	
	private BigDecimal allAmount;
	
	private BigDecimal freezenAmount;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getAllAmount() {
		return allAmount;
	}

	public void setAllAmount(BigDecimal allAmount) {
		this.allAmount = allAmount;
	}

	public BigDecimal getFreezenAmount() {
		return freezenAmount;
	}

	public void setFreezenAmount(BigDecimal freezenAmount) {
		this.freezenAmount = freezenAmount;
	}
}
