/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysUserPermissionService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月30日
 */
package com.sirdc.modules.service.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.dao.sys.SysUserPermissionDao;
import com.sirdc.modules.databean.sys.SysUserPermissionDataBean;
import com.sirdc.modules.entity.sys.SysUserPermission;
import com.sirdc.modules.filter.sys.SysUserPermissionFilter;
import com.sirdc.modules.security.SysPrincipal;
import com.sirdc.modules.security.SystemAuthorizingRealm;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;
import com.sirdc.modules.utils.CollectionsUtils;
import com.sirdc.modules.utils.StringUtils;

/**
 * 用户权限业务层
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年1月30日
 */
@Service
public class SysUserPermissionService extends StringPKService<SysUserPermission> {

	@Autowired
	private SysUserPermissionDao sysUserPermissionDao;
	
	@Autowired
	private SystemAuthorizingRealm realm;
	
	@Autowired
	private SysTableService tableService;
	
	@Override
	protected BaseDao<SysUserPermission, String> getDao() {
		return sysUserPermissionDao;
	}
	
	@Override
	protected void beforeSave(SysUserPermission sysUserPermission) {
		sysUserPermission.setSysId(tableService.updateMaxSysId("t_sys_user_permission", null));
	}

	/**
	 * 批量保存用户权限信息
	 * @author: weihuang.peng
	 * @param sysRolePermissionDataBean
	 */
	@Deprecated
	public void batchSave(SysUserPermissionDataBean dataBean) {
		List<SysUserPermission> userPermissions = dataBean.getUserPermissions();
		String roleId = dataBean.getRoleId();
		String deptId = dataBean.getDeptId();
		
		deleteByRoleIdDeptId(roleId, deptId);//保存之前需要把旧的数据删除
		handleSysUserPermissions(roleId, deptId, userPermissions);
	}

	/**
	 * 处理用户权限,对每一个SysUserPermission赋予部门和角色
	 * @author: weihuang.peng
	 * @param roleId
	 * @param dept
	 * @param rolePermissions
	 */
	public void handleSysUserPermissions(String roleId, String deptId, List<SysUserPermission> userPermissions) {
		if (CollectionsUtils.isNotEmpty(userPermissions)) {
			for (SysUserPermission sysUserPermission : userPermissions) {
				sysUserPermission.setDeptId(deptId);
				sysUserPermission.setRoleId(roleId);
				if (StringUtils.isNotBlank(sysUserPermission.getUserId())) {
					save(sysUserPermission);
				}
			}
			realm.clearAllCachedAuthorizationInfo();//清空所有的缓存
		}
	}
	
	/**
	 * 保存用户权限
	 * @author: weihuang.peng
	 * @param sysUserPermissionDataBean
	 */
	public void saveUserPermission(SysUserPermissionDataBean dataBean) {
		List<String> userIds = dataBean.getUserIds();
		String roleId = dataBean.getRoleId();
		String deptId = dataBean.getDeptId();
		if (CollectionsUtils.isNotEmpty(userIds)) {
			for (String userId : userIds) {
				SysUserPermission permission = new SysUserPermission();
				permission.setDeptId(deptId);
				permission.setRoleId(roleId);
				permission.setUserId(userId);
				save(permission);
			}
			realm.clearAllCachedAuthorizationInfo();//清空所有的缓存
		}
	}

	/**
	 * 查询所有的用户ID
	 * @author: weihuang.peng
	 * @param roleId
	 * @param deptId
	 * @return
	 */
	public List<String> queryUserByRoleIdDeptId(String roleId, String deptId) {
		return sysUserPermissionDao.queryByUserRoleIdDeptId(roleId, deptId);
	}
	
	/**
	 * 根据角色id和部门id进行删除用户权限信息
	 * @author: weihuang.peng
	 * @param roleId
	 * @param deptId
	 */
	public void deleteByRoleIdDeptId(String roleId, String deptId) {
		sysUserPermissionDao.deleteByRoleIdDeptId(roleId, deptId);
	}
	
	/**
	 * 通过用户信息查询用户的部门和角色
	 * @author: weihuang.peng
	 * @param userId
	 * @return
	 */
	public SysUserPermission getTopSysUserPermission(String userId) {
		return sysUserPermissionDao.getTopSysUserPermission(userId);
	}
	
	/**
	 * 根据用户的id查询用户的权限列表
	 * @author: weihuang.peng
	 * @param userId
	 * @return
	 */
	public List<SysUserPermission> queryByUserId(String userId) {
		return sysUserPermissionDao.queryByUserId(userId);
	}
	
	/**
	 * 抓取用户的信息，并根据此信息更新用户的部门和角色
	 * @author: weihuang.peng
	 * @param principal
	 */
	public void updateUserPrincipal(SysPrincipal principal, HttpServletRequest request) {
		String userId = principal.getUserId();
		if (StringUtils.isBlank(principal.getDeptId())) {
			SysUserPermission userPermission = getTopSysUserPermission(userId);
			if(userPermission != null) {
				principal.setDeptId(userPermission.getDeptId());
				principal.setRoleId(userPermission.getRoleId());
			}
		}
	}
	
	/**
	 * 更新用户的当前部门和当前角色，并清空缓存
	 * @author: weihuang.peng
	 * @param principal
	 * @param deptId
	 * @param roleId
	 */
	public void changeUserRoleDept(SysPrincipal principal, String deptId, String roleId) {
		principal.setDeptId(deptId);
		principal.setRoleId(roleId);
		realm.clearCachedAuthorizationInfo(principal);
	}
	
	/**
	 * 判断用户是否拥有此条用户权限
	 * @author: weihuang.peng
	 * @param roleId
	 * @param deptId
	 * @return
	 */
	public boolean isExistUserPermission(String userId, String roleId, String deptId) {
		return sysUserPermissionDao.isExistUserPermission(userId, roleId, deptId);
	}
	
	@Override
	public void batchDelete(List<String> userIds) {
		if (CollectionsUtils.isNotEmpty(userIds)) {
			sysUserPermissionDao.deleteByUserIds(userIds);
			realm.clearAllCachedAuthorizationInfo();//清空所有的缓存
		}
	}

	/**
	 * 分页查询
	 * @author: weihuang.peng
	 * @param filter
	 * @return
	 */
	public List<SysUserPermission> query(SysUserPermissionFilter filter) {
		filter.setOrder(null);
		filter.getPaging().setSortCol(null);
		filter.getPaging().setSortVal(null);
		return sysUserPermissionDao.query(filter);
	}
}