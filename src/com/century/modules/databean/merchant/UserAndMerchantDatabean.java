/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysUserAndLoginUserDatebean.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月10日
 */
package com.century.modules.databean.merchant;

import java.io.Serializable;

import com.century.modules.entity.merchant.CenturyMerchant;
import com.sirdc.modules.entity.sys.SysUser;

/**
 * 
 */
public class UserAndMerchantDatabean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2739604467945423528L;
	
	private SysUser sysUser;
	
	private CenturyMerchant merchant;
	

	/**
	 * @param sysUser
	 * @param sysLogin
	 */
	public UserAndMerchantDatabean(SysUser sysUser, CenturyMerchant merchant) {
		super();
		this.sysUser = sysUser;
		this.merchant = merchant;
	}

	/**
	 * 
	 */
	public UserAndMerchantDatabean() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the sysUser
	 */
	public SysUser getSysUser() {
		return sysUser;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param sysUser the sysUser to set
	 */
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public CenturyMerchant getMerchant() {
		return merchant;
	}

	public void setMerchant(CenturyMerchant merchant) {
		this.merchant = merchant;
	}

	
	
}
