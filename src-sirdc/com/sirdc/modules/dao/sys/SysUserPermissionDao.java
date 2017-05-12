/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysUserPermissionDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月30日
 */
package com.sirdc.modules.dao.sys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.entity.sys.SysUserPermission;
import com.sirdc.modules.filter.sys.SysUserPermissionFilter;
import com.sirdc.modules.utils.StringUtils;

/**
 * 用户权限维护
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年1月30日
 */
@Repository
public class SysUserPermissionDao extends StringDao<SysUserPermission> {

	/**
	 * 查询所有的用户ID
	 * @author: weihuang.peng
	 * @param roleId
	 * @param deptId
	 * @return
	 */
	public List<String> queryByUserRoleIdDeptId(String roleId, String deptId) {
		String hql = "select obj.userId from SysUserPermission obj where obj.roleId=:roleId and obj.deptId=:deptId";
		GenericQuery query = create(hql);
		query.setParam("roleId", roleId);
		query.setParam("deptId", deptId);
		return query.listResult();
	}

	/**
	 * 根据角色id和部门id批量删除
	 * @author: weihuang.peng
	 * @param roleId
	 * @param deptId
	 */
	public void deleteByRoleIdDeptId(String roleId, String deptId) {
		String hql = "delete from SysUserPermission where roleId=:roleId and deptId=:deptId";
		GenericQuery query = create(hql);
		query.setParam("roleId", roleId);
		query.setParam("deptId", deptId);
		query.executeUpdate();
	}

	/**
	 * 根据用户id查询用户权限信息
	 * @author: weihuang.peng
	 * @param userId
	 * @return
	 */
	public List<SysUserPermission> queryByUserId(String userId) {
		String hql = "from SysUserPermission obj where obj.userId=:userId";
		GenericQuery query = create(hql);
		query.setParam("userId", userId);
		return query.listResult();
	}
	
	/**
	 * 根据用户id删除
	 * @author: weihuang.peng
	 * @param userId
	 * @return
	 */
	public void deleteByUserId(String userId) {
		String hql = "delete from SysUserPermission obj where obj.userId=:userId";
		GenericQuery query = create(hql);
		query.setParam("userId", userId);
		query.executeUpdate();
	}
	
	/**
	 * 根据用户ids删除
	 * @author: weihuang.peng
	 * @param userId
	 * @return
	 */
	public void deleteByUserIds(List<String> userIds) {
		String hql = "delete from SysUserPermission obj where obj.userId in (:userIds)";
		GenericQuery query = create(hql);
		query.setParamList("userIds", userIds);
		query.executeUpdate();
	}
	
	/**
	 * 查询用户的第一条记录
	 * @author: weihuang.peng
	 * @param userId
	 * @return
	 */
	public SysUserPermission getTopSysUserPermission(String userId) {
		String hql = "from SysUserPermission obj where obj.userId=:userId";
		GenericQuery query = create(hql);
		query.setParam("userId", userId);
		return query.firstResult();
	}

	/**
	 * 查询用户权限
	 * @author: weihuang.peng
	 * @param filter
	 * @return
	 */
	public List<SysUserPermission> query(final SysUserPermissionFilter filter) {
		GenericQuery query = createQuery("obj");
		if(StringUtils.isNotBlank(filter.getRoleId())) {
			query.append(" and obj.roleId=:roleId");
			query.setParam("roleId", filter.getRoleId());
		}
		
		if(StringUtils.isNotBlank(filter.getDeptId())) {
			query.append(" and obj.deptId=:deptId");
			query.setParam("deptId", filter.getDeptId());
		}
		return query.listResult(filter);
	}

	/**
	 * 判断用户是否存在
	 * @author: weihuang.peng
	 * @param roleId
	 * @param deptId
	 * @return
	 */
	public boolean isExistUserPermission(String userId, String roleId, String deptId) {
		StringBuffer hql = new StringBuffer("from SysUserPermission obj where obj.roleId=:roleId and obj.deptId=:deptId and obj.userId=:userId");
		GenericQuery query = create(hql.toString());
		query.setParam("roleId", roleId);
		query.setParam("deptId", deptId);
		query.setParam("userId", userId);
		return (query.count() > 0);
	}
}