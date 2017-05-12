package com.sirdc.modules.databean.sys;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.sirdc.modules.entity.sys.SysRolePermission;


public class SysRolePermissionDataBean {

	List<SysRolePermission> rolePermissions;

	@NotEmpty
	private String roleId;
	@NotEmpty
	private String deptId;
	
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
	 * @return the rolePermissions
	 */
	public List<SysRolePermission> getRolePermissions() {
		return rolePermissions;
	}

	/**
	 * @author: weihuang.peng
	 * @param rolePermissions the rolePermissions to set
	 */
	public void setRolePermissions(List<SysRolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}
}
