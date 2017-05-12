/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysUserPermissionFilter.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月30日
 */
package com.sirdc.modules.filter.sys;

import com.sirdc.modules.core.filter.BaseFilter;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年1月30日
 */
public class SysUserPermissionFilter extends BaseFilter {

	private String roleId;
	
	private String deptId;

	/**
	 * @author: weihuang.peng
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @author: weihuang.peng
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @author: weihuang.peng
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * @author: weihuang.peng
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
}
