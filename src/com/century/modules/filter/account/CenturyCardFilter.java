/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyAccountFilter.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月21日
 */
package com.century.modules.filter.account;

import com.sirdc.modules.core.filter.BaseFilter;

/**
 * 
 */
public class CenturyCardFilter extends BaseFilter {
	
	private String userId;
	
	private String bindState;
	
	private String cardNo;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBindState() {
		return bindState;
	}

	public void setBindState(String bindState) {
		this.bindState = bindState;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
}
