/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysUserAndLoginUserDatebean.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月10日
 */
package com.sirdc.modules.databean.sys;

import java.io.Serializable;

import com.sirdc.modules.entity.sys.SysLogin;
import com.sirdc.modules.entity.sys.SysUser;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年6月10日
 */
public class UserAndLoginUserDatabean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2739604467945423528L;
	
	private SysUser sysUser;
	
	private SysLogin sysLogin;
	

	/**
	 * @param sysUser
	 * @param sysLogin
	 */
	public UserAndLoginUserDatabean(SysUser sysUser, SysLogin sysLogin) {
		super();
		this.sysUser = sysUser;
		this.sysLogin = sysLogin;
	}

	/**
	 * 
	 */
	public UserAndLoginUserDatabean() {
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

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the sysLogin
	 */
	public SysLogin getSysLogin() {
		return sysLogin;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param sysLogin the sysLogin to set
	 */
	public void setSysLogin(SysLogin sysLogin) {
		this.sysLogin = sysLogin;
	}
	
	
}
