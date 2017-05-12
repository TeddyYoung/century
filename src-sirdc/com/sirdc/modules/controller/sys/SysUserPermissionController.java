/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysUserPermissionController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月30日
 */
package com.sirdc.modules.controller.sys;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.JqGrid;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.databean.sys.SysUserPermissionDataBean;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.filter.sys.SysUserFilter;
import com.sirdc.modules.filter.sys.SysUserPermissionFilter;
import com.sirdc.modules.service.sys.SysUserPermissionService;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.sys.entity.SysDept;
import com.sirdc.modules.sys.service.SysDeptService;
import com.sirdc.modules.utils.CollectionsUtils;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年1月30日
 */
@Controller
@RequestMapping("/modules/sys/userPermission")
public class SysUserPermissionController extends JsonBaseController {

	@Autowired
	private SysUserPermissionService sysUserPermissionService;
	@Autowired
	private SysDeptService deptService;
	@Autowired
	private SysUserService userService;
	
	@Override
	protected String getView(String view) {
		return "/modules/sys/userPermission/" + view;
	}

	/**
	 * 初始化系统参数维护列表界面
	 * @return url
	 */
	@RequiresPermissions("modules:sys:userPermission:query")
	@RequestMapping(method = RequestMethod.GET)
	public String initPage(Model model) {
		List<SysDept> depts = deptService.query();
		model.addAttribute("depts", depts);
		return getView("query");
	}
	
	/**
	 * 初始化更新系统参数维护界面
	 * @param model
	 * @param id
	 * @return url
	 */
	@ResponseBody
	@RequiresPermissions("modules:sys:userPermission:query")
	@RequestMapping(method = RequestMethod.POST)
	public JqGrid showEditView(Model model, @ModelAttribute SysUserFilter filter) {
		String roleId = filter.getRoleId();
		String deptId = filter.getDeptId();
		List<SysUser> sysUsers = new ArrayList<SysUser>();
		List<String> userIds = sysUserPermissionService.queryUserByRoleIdDeptId(roleId, deptId);
		if (CollectionsUtils.isNotEmpty(userIds)) {
			filter.setInIds(userIds);//从ids中查找并分页
			sysUsers = userService.query(filter);
		}
		
		return coverJqGrid(filter, sysUsers);
	}
	
	/**
	 * 跳转到创建界面
	 * @author: weihuang.peng
	 * @param model
	 * @param filter
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String showAddView(Model model, @ModelAttribute SysUserPermissionFilter filter) {
		return getView("form");
	}
	
	/**
	 * 创建界面的数据区域
	 * @author: weihuang.peng
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public JqGrid create(@ModelAttribute SysUserFilter filter) {
		String roleId = filter.getRoleId();
		String deptId = filter.getDeptId();
		List<String> userIds = sysUserPermissionService.queryUserByRoleIdDeptId(roleId, deptId);
		List<String> notInIds = userService.queryLoginByRoleDept(filter);//默认部门和角色是这一个的不需要显示
		userIds.addAll(notInIds);
		filter.setNotInIds(userIds);
		List<SysUser> sysUsers = userService.query(filter);
		return coverJqGrid(filter, sysUsers);
	}
	
	/**
	 * 保存创建的数据
	 * @author: weihuang.peng
	 * @param model
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/savePermission", method=RequestMethod.POST)
	public Message saveData(Model model, @RequestParam String roleId, @RequestParam String deptId, @RequestParam("userIds[]") List<String> userIds) {
		SysUserPermissionDataBean sysUserPermissionDataBean = new SysUserPermissionDataBean();
		sysUserPermissionDataBean.setDeptId(deptId);
		sysUserPermissionDataBean.setRoleId(roleId);
		sysUserPermissionDataBean.setUserIds(userIds);
		sysUserPermissionService.saveUserPermission(sysUserPermissionDataBean);
		return coverMessage("200");
	}

	/**
	 * 删除用户
	 * @param id
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/delete/{id}")
	public Message delete(@PathVariable List<String> id) {
		sysUserPermissionService.batchDelete(id);
		return coverMessage("200");
	}
}
