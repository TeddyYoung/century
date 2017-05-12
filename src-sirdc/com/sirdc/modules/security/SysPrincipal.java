/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysPrincipal.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年4月30日
 */
package com.sirdc.modules.security;

import com.sirdc.modules.entity.sys.SysLogin;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.sys.security.Principal;


/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年4月30日
 */
public class SysPrincipal extends Principal {

	private static final long serialVersionUID = 1L;
	
	private String email;// 邮箱
	private String mobile;// 电话号码
	private String name;// 中文名称
	private String nickName;// 昵称
	private String roleId;// 角色ID
	private String deptId;// 部门编号
	private boolean api;//是否为api调用

	private String defaultRoleId;// 默认角色id
	private String defaultDeptId;// 默认部门ID
	
	/**
	 * @param loginUser
	 * @param userInfo
	 */
	public SysPrincipal(SysLogin loginUser, SysUser userInfo, boolean isApi) {
		setUserId(loginUser.getSysId());
		setSuperAdmin(userInfo.isSuperAdmin());
		email = loginUser.getEmail();
		mobile = loginUser.getMobile();
		name = userInfo.getName();
		nickName = userInfo.getNickName();
		roleId = loginUser.getRoleId();
		deptId = loginUser.getDeptId();
		defaultRoleId = loginUser.getRoleId();
		defaultDeptId = loginUser.getDeptId();
		this.api = isApi;
	}
	
	/**
	 * @author: weihuang.peng
	 * @return the defaultRoleId
	 */
	public String getDefaultRoleId() {
		return defaultRoleId;
	}

	/**
	 * @author: weihuang.peng
	 * @param defaultRoleId
	 *            the defaultRoleId to set
	 */
	public void setDefaultRoleId(String defaultRoleId) {
		this.defaultRoleId = defaultRoleId;
	}

	/**
	 * @author: weihuang.peng
	 * @return the defaultDeptId
	 */
	public String getDefaultDeptId() {
		return defaultDeptId;
	}

	/**
	 * @author: weihuang.peng
	 * @param defaultDeptId the defaultDeptId to set
	 */
	public void setDefaultDeptId(String defaultDeptId) {
		this.defaultDeptId = defaultDeptId;
	}
	
	/**
	 * @author: weihuang.peng
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @author: weihuang.peng
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @author: weihuang.peng
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @author: weihuang.peng
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @author: weihuang.peng
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @author: weihuang.peng
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @author: weihuang.peng
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @author: weihuang.peng
	 * @param roleId
	 *            the roleId to set
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
	 * @param deptId
	 *            the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * @author: weihuang.peng
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @author: weihuang.peng
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @author: weihuang.peng
	 * @return the api
	 */
	public boolean isApi() {
		return api;
	}

	/**
	 * @author: weihuang.peng
	 * @param api the api to set
	 */
	public void setApi(boolean api) {
		this.api = api;
	}
}
