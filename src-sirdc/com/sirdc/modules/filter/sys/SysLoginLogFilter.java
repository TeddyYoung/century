/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLoginLogFilter.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月22日
 */
package com.sirdc.modules.filter.sys;

import com.sirdc.modules.core.filter.BaseFilter;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年1月22日
 */
public class SysLoginLogFilter extends BaseFilter {
	
	private String userName;//用户名称
	
	private String deptName;//部门名称
	
	private String userTitlName;//职称
	
	private String startLoginDate;//开始登入日期
	
	private String startLoginTime;//开始登录时间 
	
	private String endLoginDate;//结束登入日期
	
	private String endLoginTime;//结束登录时间 
	
	private String startLogoutDate;//开始登出日期
	
	private String startLogoutTime;//开始登出时间
	
	public String getStartLoginDate() {
		return startLoginDate;
	}

	public void setStartLoginDate(String startLoginDate) {
		this.startLoginDate = startLoginDate;
	}

	public String getStartLoginTime() {
		return startLoginTime;
	}

	public void setStartLoginTime(String startLoginTime) {
		this.startLoginTime = startLoginTime;
	}

	public String getEndLoginDate() {
		return endLoginDate;
	}

	public void setEndLoginDate(String endLoginDate) {
		this.endLoginDate = endLoginDate;
	}

	public String getEndLoginTime() {
		return endLoginTime;
	}

	public void setEndLoginTime(String endLoginTime) {
		this.endLoginTime = endLoginTime;
	}

	public String getStartLogoutDate() {
		return startLogoutDate;
	}

	public void setStartLogoutDate(String startLogoutDate) {
		this.startLogoutDate = startLogoutDate;
	}

	public String getStartLogoutTime() {
		return startLogoutTime;
	}

	public void setStartLogoutTime(String startLogoutTime) {
		this.startLogoutTime = startLogoutTime;
	}

	public String getEndLogoutDate() {
		return endLogoutDate;
	}

	public void setEndLogoutDate(String endLogoutDate) {
		this.endLogoutDate = endLogoutDate;
	}

	public String getEndLogoutTime() {
		return endLogoutTime;
	}

	public void setEndLogoutTime(String endLogoutTime) {
		this.endLogoutTime = endLogoutTime;
	}

	private String endLogoutDate;//结束登出日期
	
	private String endLogoutTime;//结束登出时间

	/**
	 * 获取用户名称
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户名称
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取部门名称
	 * @return
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * 设置部门名称
	 * @param deptName
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * 获取职称
	 * @return
	 */
	public String getUserTitlName() {
		return userTitlName;
	}

	/**
	 * 设置职称
	 * @param userTitlName
	 */
	public void setUserTitlName(String userTitlName) {
		this.userTitlName = userTitlName;
	}
	
	
}
