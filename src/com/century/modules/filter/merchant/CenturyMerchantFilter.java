/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyConsumeFilter.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年7月8日
 */
package com.century.modules.filter.merchant;

import com.sirdc.modules.core.filter.BaseFilter;

/**
 * 
 */
public class CenturyMerchantFilter extends BaseFilter {
	
	private String mobile;
	
	private String merchantName;//中文名称

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
}
