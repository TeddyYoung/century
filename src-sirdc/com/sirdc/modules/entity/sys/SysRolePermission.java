/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysRolePermssion.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年2月2日
 */
package com.sirdc.modules.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 * 部门角色权限维护
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年2月2日
 */
@Entity
@Table(name="t_sys_role_permission")
@DynamicUpdate//动态更新
@DynamicInsert//动态插入
public class SysRolePermission extends StringEntity {

	private static final long serialVersionUID = -6528891650954658656L;
	
	private String deptId;
	
	private String roleId;
	
	private String menuId;
	
	private String func;//edit,delete,create,id,id,
	
	private String permission;//sys:role:create,sys:role:update

	private String permissionPre;//权限字符前置
	
	/**
	 * @author: weihuang.peng
	 * @return the permissionPre
	 */
	public String getPermissionPre() {
		return permissionPre;
	}

	/**
	 * @author: weihuang.peng
	 * @param permissionPre the permissionPre to set
	 */
	public void setPermissionPre(String permissionPre) {
		this.permissionPre = permissionPre;
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
	 * @return the menuId
	 */
	@Column(name="menu_sys_id")
	public String getMenuId() {
		return menuId;
	}

	/**
	 * @author: weihuang.peng
	 * @param menuId the menuId to set
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * 获取功能列表
	 * @return the func
	 */
	@Column(length = 500)
	public String getFunc() {
		return func;
	}

	/**
	 * 设置功能列表
	 * @param func the func to set
	 */
	public void setFunc(String func) {
		this.func = func;
	}

	/**
	 * 获得权限字符
	 * @return the permission
	 */
	@Column(length = 1000)
	public String getPermission() {
		return permission;
	}

	/**
	 * 设置权限字符
	 * @param permission the permission to set
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}
}