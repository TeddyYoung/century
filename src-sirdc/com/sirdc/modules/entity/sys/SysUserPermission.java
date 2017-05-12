/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysUserPermission.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月30日
 */
package com.sirdc.modules.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年1月30日
 */
@Entity
@Table(name="t_sys_user_permission")
@DynamicUpdate//动态更新
@DynamicInsert//动态插入
public class SysUserPermission extends StringEntity {

	private static final long serialVersionUID = -8901796373865085636L;

	@NotNull
	private String userId;//用户
	
	@NotNull
	private String deptId;//部门

	@NotNull
	private String roleId;//角色
	
	private String state;//状态
	
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
	 * @author: weihuang.peng
	 * @return the userId
	 */
	@Column(name="user_sys_id")
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
}
