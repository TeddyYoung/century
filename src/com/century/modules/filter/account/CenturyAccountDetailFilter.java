/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyAccountFilter.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月21日
 */
package com.century.modules.filter.account;

import java.util.List;

import com.sirdc.modules.core.filter.BaseFilter;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年9月21日
 */
public class CenturyAccountDetailFilter extends BaseFilter {
	private String userId;
	
	private String type;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	private List<String> txDate;

	public List<String> getTxDate() {
		return txDate;
	}

	public void setTxDate(List<String> txDate) {
		this.txDate = txDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
