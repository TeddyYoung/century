/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysValidFilter.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	29 Jul,2015
 */
package com.century.modules.filter.sms;

import com.sirdc.modules.core.filter.BaseFilter;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 29 Jul,2015
 */
public class SysValidFilter extends BaseFilter {

	private String mobile;

	/**
	 * @author: weihuang.peng
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @author: weihuang.peng
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
