/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysRolePermssionDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年2月2日
 */
package com.sirdc.modules.dao.sys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.entity.sys.SysRolePermission;

/**
 * 部门角色权限维护
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年2月2日
 */
@Repository
public class SysRolePermissionDao extends StringDao<SysRolePermission> {

	/**
	 * 根据角色和部门查询相应的权限字符
	 * @param roleId
	 * @param deptId
	 * @return
	 */
	public List<String> queryPermissionByRoleIdDeptId(String roleId, String deptId) {
		String hql = "select obj.permission from SysRolePermission obj where obj.roleId=:roleId and obj.deptId=:deptId";
		GenericQuery query = create(hql);
		query.setParam("roleId", roleId);
		query.setParam("deptId", deptId);
		return query.listResult();
	}

	/**
	 * 根据角色id和部门id进行删除
	 * @author: weihuang.peng
	 * @param roleId
	 * @param deptId
	 */
	public void deleteByRoleIdDeptId(String roleId, String deptId) {
		String hql = "delete from SysRolePermission where roleId=:roleId and deptId=:deptId";
		GenericQuery query = create(hql);
		query.setParam("roleId", roleId);
		query.setParam("deptId", deptId);
		query.executeUpdate();
	}

	/**
	 * 查询用户的菜单ID
	 * @author: weihuang.peng
	 * @param deptId
	 * @param roleId
	 * @return
	 */
	public List<String> queryMenuIdsByUserId(String deptId, String roleId) {
		String hql = "select obj.menuId from SysRolePermission obj where obj.roleId=:roleId and obj.deptId=:deptId and obj.func is not null";
		GenericQuery query = create(hql);
		query.setParam("roleId", roleId);
		query.setParam("deptId", deptId);
		return query.listResult();
	}

	/**
	 * 判断某个角色 + 部门是否拥有菜单下的某个功能
	 * @author: weihuang.peng
	 * @param fun
	 * @param roleId
	 * @param deptId
	 * @return
	 */
	public boolean isExistFun(String menuId, String func, String roleId, String deptId) {
		String hql = "from SysRolePermission obj where obj.deptId=:deptId and obj.roleId=:roleId and obj.menuId=:menuId and obj.func=:func";
		GenericQuery query = create(hql);
		query.setParam("roleId", roleId);
		query.setParam("deptId", deptId);
		query.setParam("func", func);
		query.setParam("menuId", menuId);
		return (query.count() > 0);
	}
}