package com.sirdc.modules.databean.sys;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.sirdc.modules.entity.sys.SysUserPermission;

public class SysUserPermissionDataBean {

	List<SysUserPermission> userPermissions;

	private List<String> userIds;
	
	@NotNull
	private String roleId;
	
	@NotNull
	private String deptId;
	
	/**
	 * @author: weihuang.peng
	 * @return the userIds
	 */
	public List<String> getUserIds() {
		return userIds;
	}

	/**
	 * @author: weihuang.peng
	 * @param userIds the userIds to set
	 */
	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
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
	 * @return the userPermissions
	 */
	public List<SysUserPermission> getUserPermissions() {
		return userPermissions;
	}

	/**
	 * @author: weihuang.peng
	 * @param userPermissions the userPermissions to set
	 */
	public void setUserPermissions(List<SysUserPermission> userPermissions) {
		this.userPermissions = userPermissions;
	}
}
