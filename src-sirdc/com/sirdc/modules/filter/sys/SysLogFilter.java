/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLogFilter.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年5月10日
 */
package com.sirdc.modules.filter.sys;

import com.sirdc.modules.core.filter.BaseFilter;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年5月10日
 */
public class SysLogFilter extends BaseFilter {

	private String startDate;
	
	private String endDate;

	/**
	 * @author: weihuang.peng
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @author: weihuang.peng
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @author: weihuang.peng
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @author: weihuang.peng
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}