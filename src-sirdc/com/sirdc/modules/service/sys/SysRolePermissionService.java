/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysRolePermssionService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年2月2日
 */
package com.sirdc.modules.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.dao.sys.SysRolePermissionDao;
import com.sirdc.modules.databean.sys.SysRolePermissionDataBean;
import com.sirdc.modules.entity.sys.SysRolePermission;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;
import com.sirdc.modules.utils.CollectionsUtils;
import com.sirdc.modules.utils.StringUtils;

/**
 * 部门角色权限维护
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年2月2日
 */
@Service
public class SysRolePermissionService extends StringPKService<SysRolePermission> {

	@Autowired
	private SysRolePermissionDao sysRolePermissionDao;
	@Autowired
	private SysTableService tableService;
	
	@Override
	protected BaseDao<SysRolePermission, String> getDao() {
		return sysRolePermissionDao;
	}
	
	@Override
	protected void beforeSave(SysRolePermission obj) {
		obj.setSysId(tableService.updateMaxSysId("t_sys_role_permission", null));
	}
	
	/**
	 * 批量保存
	 * @author: weihuang.peng
	 * @param dataBean
	 */
	public void batchSave(SysRolePermissionDataBean dataBean) {
		List<SysRolePermission> rolePermissions = dataBean.getRolePermissions();
		String roleId = dataBean.getRoleId();
		String deptId = dataBean.getDeptId();
		sysRolePermissionDao.deleteByRoleIdDeptId(roleId, deptId);//保存之前需要把旧的数据删除
		handleSysRolePermissions(roleId, deptId, rolePermissions);//对数据进行一定的处理
	}
	
	/**
	 * 处理角色权限，将菜单的id转换成权限字符
	 * @author: weihuang.peng
	 * @param roleId
	 * @param dept
	 * @param rolePermissions
	 */
	public void handleSysRolePermissions(String roleId, String deptId, List<SysRolePermission> rolePermissions) {
		if (CollectionsUtils.isNotEmpty(rolePermissions)) {
			for (SysRolePermission sysRolePermission : rolePermissions) {
				if(StringUtils.isNotBlank(sysRolePermission.getFunc())) {
					save(sysRolePermission);
				}
			}
		}
	}
	
	/**
	 * 判断某个角色 + 部门是否拥有菜单下的某个功能
	 * @author: weihuang.peng
	 * @param fun
	 * @param roleId
	 * @param deptId
	 * @return
	 */
	public boolean isExistFun(String menuId, String fun, String roleId, String deptId) {
		return sysRolePermissionDao.isExistFun(menuId, fun, roleId, deptId);
	} 
	
	/**
	 * 根据角色和部门查询相应的权限字符
	 * @param roleId
	 * @param deptId
	 * @return
	 */
	public List<String> queryPermissionByRoleIdDeptId(String roleId, String deptId) {
		return sysRolePermissionDao.queryPermissionByRoleIdDeptId(roleId, deptId);
	}
	
	/**
	 * 查询用户的菜单
	 * @author: weihuang.peng
	 * @param deptId
	 * @param roleId
	 * @return
	 */
	public List<String> queryMenuIdsByRoleIdDeptId(String deptId, String roleId) {
		return sysRolePermissionDao.queryMenuIdsByUserId(deptId, roleId);
	}
}