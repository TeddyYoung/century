/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLoginLog.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月22日
 */
package com.sirdc.modules.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 * 登录日志表
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年1月22日
 */
@Entity
@Table(name = "t_sys_login_log")
@DynamicUpdate//动态更新
@DynamicInsert//动态插入
public class SysLoginLog extends StringEntity {

	private static final long serialVersionUID = -3615935930043444260L;
	
	private String userId;//用户Id
	
	private String userName;//用户名称
	
	private String deptId;//部门编号
	
	private String deptName;//部门名称
	
	private String roleName;//角色名称
	
	private String roleId;//角色ID
	
	private String ip;//ip
	
	private String hostName;//主机名称
	
	private String loginDate;//登入日期
	
	private String loginTime;//登入时间 
	
	private String logoutDate;//登出日期
	
	private String logoutTime;//登出时间
	
	private String demo;//备注
	
	private String state;//状态 

	/**
	 * @author: weihuang.peng
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @author: weihuang.peng
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @author: weihuang.peng
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @author: weihuang.peng
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @author: weihuang.peng
	 * @return the deptId
	 */
	@Column(name="dept_sys_id")
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

	/**
	 * @author: weihuang.peng
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @author: weihuang.peng
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @author: weihuang.peng
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @author: weihuang.peng
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @author: weihuang.peng
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @author: weihuang.peng
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @author: weihuang.peng
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @author: weihuang.peng
	 * @param hostName the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * @author: weihuang.peng
	 * @return the loginDate
	 */
	public String getLoginDate() {
		return loginDate;
	}

	/**
	 * @author: weihuang.peng
	 * @param loginDate the loginDate to set
	 */
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	/**
	 * @author: weihuang.peng
	 * @return the loginTime
	 */
	public String getLoginTime() {
		return loginTime;
	}

	/**
	 * @author: weihuang.peng
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * @author: weihuang.peng
	 * @return the logoutDate
	 */
	public String getLogoutDate() {
		return logoutDate;
	}

	/**
	 * @author: weihuang.peng
	 * @param logoutDate the logoutDate to set
	 */
	public void setLogoutDate(String logoutDate) {
		this.logoutDate = logoutDate;
	}

	/**
	 * @author: weihuang.peng
	 * @return the logoutTime
	 */
	public String getLogoutTime() {
		return logoutTime;
	}

	/**
	 * @author: weihuang.peng
	 * @param logoutTime the logoutTime to set
	 */
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	/**
	 * @author: weihuang.peng
	 * @return the demo
	 */
	public String getDemo() {
		return demo;
	}

	/**
	 * @author: weihuang.peng
	 * @param demo the demo to set
	 */
	public void setDemo(String demo) {
		this.demo = demo;
	}

	/**
	 * @author: weihuang.peng
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @author: weihuang.peng
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
}