/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLogin.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月21日
 */
package com.sirdc.modules.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 * 用户登录表
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年1月21日
 */
@Entity
@Table(name = "t_sys_login")
@DynamicUpdate//动态更新
@DynamicInsert//动态插入
public class SysLogin extends StringEntity {

	private static final long serialVersionUID = -2310383761651689412L;
	
	private String username;//用户名
	
	private String password;//密码
	
	private String saltKey;//加密钥匙

	private String email;//电子邮箱
	
	private String mobile;//手机
	
	private String mailValidCode;//邮件验证码
	
	private String mailValidTime;//邮件下发时间
	
	private String state;//帐号状态
	
	private String deptId;//部门编号
	
	private String roleId;//角色编号 ->小类id
	
	private String sjctPassword;//密码
	
	private String cashPassword;//密码
	
	/**
	 * @author: weihuang.peng
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @author: weihuang.peng
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * 获取密码
	 * @return
	 */
	@Column(length = 100)
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取电子邮件
	 * @return
	 */
	@Column(length = 30)
	public String getEmail() {
		return email;
	}

	/**
	 * 设置电子邮件
	 * @param emall
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取加密钥匙
	 * @return the saltKey
	 */
	public String getSaltKey() {
		return saltKey;
	}

	/**
	 * 设置加密钥匙
	 * @param saltKey the saltKey to set
	 */
	public void setSaltKey(String saltKey) {
		this.saltKey = saltKey;
	}

	/**
	 * 获取手机
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置手机
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @author: weihuang.peng
	 * @return the mailValidCode
	 */
	public String getMailValidCode() {
		return mailValidCode;
	}

	/**
	 * @author: weihuang.peng
	 * @param mailValidCode the mailValidCode to set
	 */
	public void setMailValidCode(String mailValidCode) {
		this.mailValidCode = mailValidCode;
	}

	/**
	 * @author: weihuang.peng
	 * @return the mailValidTime
	 */
	public String getMailValidTime() {
		return mailValidTime;
	}

	/**
	 * @author: weihuang.peng
	 * @param mailValidTime the mailValidTime to set
	 */
	public void setMailValidTime(String mailValidTime) {
		this.mailValidTime = mailValidTime;
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

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the sjctPassword
	 */
	public String getSjctPassword() {
		return sjctPassword;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param sjctPassword the sjctPassword to set
	 */
	public void setSjctPassword(String sjctPassword) {
		this.sjctPassword = sjctPassword;
	}

	public String getCashPassword() {
		return cashPassword;
	}

	public void setCashPassword(String cashPassword) {
		this.cashPassword = cashPassword;
	}
	
	
}