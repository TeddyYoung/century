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
 * @author: 
 * @version 
 * @Date: 
 */
public class CenturyPaymentInfoFilter extends BaseFilter {
	
	private String status;
	
	private String userId;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
