/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyHistoryFilter.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月21日
 */
package com.century.modules.filter.merchant;

import com.sirdc.modules.core.filter.BaseFilter;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年9月21日
 */
public class CenturyHistoryFilter extends BaseFilter {
	
	private String userId;

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
