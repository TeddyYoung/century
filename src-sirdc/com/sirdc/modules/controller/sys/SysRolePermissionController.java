/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysRolePermissionController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年2月2日
 */
package com.sirdc.modules.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.databean.sys.SysRolePermissionDataBean;
import com.sirdc.modules.security.SystemAuthorizingRealm;
import com.sirdc.modules.service.sys.SysRolePermissionService;
import com.sirdc.modules.sys.entity.SysBas;
import com.sirdc.modules.sys.entity.SysDept;
import com.sirdc.modules.sys.entity.SysMenu;
import com.sirdc.modules.sys.service.SysDeptService;
import com.sirdc.modules.sys.service.SysMenuService;
import com.sirdc.modules.sys.util.RedisUtils;
import com.sirdc.modules.utils.CollectionsUtils;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年2月2日
 */
@Controller
@RequestMapping("/modules/sys/rolePermission")
public class SysRolePermissionController extends JsonBaseController {

	@Autowired
	private SysRolePermissionService sysRolePermissionService;
	@Autowired
	private SystemAuthorizingRealm realm;
	@Autowired
	private SysDeptService sysDeptService;
	@Autowired
	private SysMenuService sysMenuService;
	
	@Override
	protected String getView(String view) {
		return "/modules/sys/rolePermission/" + view;
	}

	/**
	 * 初始化表信息列表界面
	 * 
	 * @return url
	 */
	@RequestMapping
	@RequiresPermissions("modules:sys:rolePermission:query")
	public String initPage(Model model) {
		List<SysDept> depts = sysDeptService.query();
		model.addAttribute("depts", depts);
		return getView("query");
	}

	/**
	 * 更新角色权限创建
	 * @author: weihuang.peng
	 * @param sysRolePermissionDataBean
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("modules:sys:rolePermission:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Message create(@ModelAttribute SysRolePermissionDataBean sysRolePermissionDataBean) {
		sysRolePermissionService.batchSave(sysRolePermissionDataBean);
		realm.clearAllCachedAuthorizationInfo();
		return coverMessage("200");
	}

	/**
	 * 初始化角色权限创建界面
	 * @author: weihuang.peng
	 * @param model
	 * @param sysRolePermissionDataBean
	 * @param roleId
	 * @param deptId
	 * @return
	 */
	@RequiresPermissions("modules:sys:rolePermission:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String showAddView(Model model, @Valid @ModelAttribute SysRolePermissionDataBean sysRolePermissionDataBean) {
		String roleId = sysRolePermissionDataBean.getRoleId();
		String deptId = sysRolePermissionDataBean.getDeptId();
		
		List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
		List<SysMenu> sysMenus = sysMenuService.queryTopMenus();
		if(sysMenus != null) {
			for (SysMenu sysMenu : sysMenus) {
				List<SysMenu> fillList = new ArrayList<SysMenu>();
				fillList.add(sysMenu);
				String relation = new StringBuffer(sysMenu.getMenuRelation()).append(sysMenu.getMenuId()).append("/").toString();
				List<SysMenu> subMenus = sysMenuService.queryWithRelation(relation);
				if(CollectionsUtils.isNotEmpty(subMenus)) {
					fillList.addAll(subMenus);
				}
				fillEditData(roleId, deptId, results, fillList);//填充父亲，儿子数据
			}
		}
		model.addAttribute("results", results);
		model.addAttribute("roleId", roleId);
		model.addAttribute("deptId", deptId);
		return getView("form");
	}
	
	/**
	 * 填充数据区
	 * @author: weihuang.peng
	 * @param roleId
	 * @param deptId
	 * @param results
	 * @param sysMenus
	 */
	public void fillEditData(String roleId, String deptId, List<Map<String, Object>> results, List<SysMenu> sysMenus) {
		for (SysMenu menu : sysMenus) {
			String menuId = menu.getSysId();
			Map<String, Object> item = new HashMap<String, Object>();
			List<Map<String, Object>> functions = new ArrayList<Map<String,Object>>();
			item.put("menuId", menu.getSysId());
			item.put("menuName", menu.getMenuName());
			item.put("menuMsgId", menu.getMsgId());
			item.put("permissionPre", menu.getPermissionPre());
			List<SysBas> funs = RedisUtils.getDictListByIds("FUN", menu.getFuns());
			if(funs != null) {
				for (SysBas sysBas : funs) {
					Map<String, Object> func = new HashMap<String, Object>();
					String fun = sysBas.getBasId();
					func.put("basId", sysBas.getBasId());
					func.put("funMsgId", sysBas.getMsgId());
					func.put("funName", sysBas.getName());
					func.put("checked", sysRolePermissionService.isExistFun(menuId, fun, roleId, deptId));
					functions.add(func);
				}
			}
			item.put("funs", functions);
			results.add(item);
		}
	}
}