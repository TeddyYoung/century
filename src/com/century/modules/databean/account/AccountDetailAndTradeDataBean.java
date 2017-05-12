package com.century.modules.databean.account;

import java.io.Serializable;

import com.century.modules.entity.account.CenturyAccountDetail;
import com.century.modules.entity.account.CenturyTrade;

public class AccountDetailAndTradeDataBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5557659159791392099L;

	private CenturyAccountDetail accountDetail;
	
	private CenturyTrade trade;
	
	public AccountDetailAndTradeDataBean(CenturyAccountDetail accountDetail, CenturyTrade trade) {
		super();
		this.accountDetail = accountDetail;
		this.trade = trade;
	}
	
	public AccountDetailAndTradeDataBean() {
		super();
	}

	public CenturyAccountDetail getAccountDetail() {
		return accountDetail;
	}

	public void setAccountDetail(CenturyAccountDetail accountDetail) {
		this.accountDetail = accountDetail;
	}

	public CenturyTrade getTrade() {
		return trade;
	}

	public void setTrade(CenturyTrade trade) {
		this.trade = trade;
	}

}
