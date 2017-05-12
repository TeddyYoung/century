/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyConsumeFilter.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年7月8日
 */
package com.century.modules.filter.merchant;

import java.math.BigDecimal;

import com.sirdc.modules.core.filter.BaseFilter;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年7月8日
 */
public class CenturyConsumeFilter extends BaseFilter {
	
	private String shopId;//商家ID
	
	private BigDecimal balance;//消费金额
	
	
	private String demo;

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the shopId
	 */
	public String getShopId() {
		return shopId;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param shopId the shopId to set
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param balance the balance to set
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}


	/**
	 * 
	 * @author: huiyang.yu
	 * @return the demo
	 */
	public String getDemo() {
		return demo;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param demo the demo to set
	 */
	public void setDemo(String demo) {
		this.demo = demo;
	}
	
	
}
