/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: DashBoardController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月20日
 */
package com.sirdc.modules.controller.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sirdc.modules.core.web.BaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.entity.sys.SysUserPermission;
import com.sirdc.modules.security.SysPrincipal;
import com.sirdc.modules.service.sys.SysLoginService;
import com.sirdc.modules.service.sys.SysRolePermissionService;
import com.sirdc.modules.service.sys.SysUserPermissionService;
import com.sirdc.modules.sys.entity.SysMenu;
import com.sirdc.modules.sys.service.SysMenuService;
import com.sirdc.modules.sys.util.UserUtils;
import com.sirdc.modules.util.SysUserUtils;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年1月20日
 */
@Controller
@RequestMapping("/")
public class DashBoardController extends BaseController {
	
	@Autowired
	private SysUserPermissionService userPermissionService;
	@Autowired
	private SysRolePermissionService rolePermissionService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysLoginService loginService; 
	
	@Override
	protected String getView(String view) {
		return "/modules/" + view;
	}
	
	@RequestMapping
	@SuppressWarnings("unchecked")
	public String index(Model model, HttpServletRequest request) {
		SysPrincipal principal = SysUserUtils.getSysPrincipal();
		//更新用户的部门和角色
		userPermissionService.updateUserPrincipal(principal, request);
		//声明数据区
		List<SysUserPermission> userPermissions = null;
		List<SysMenu> menus = null;
		//缓存中的数据区
		Map<String, Object> cacheMap = principal.getCacheMap();
		if(cacheMap.size() == 0) {
			//为用户赋值部门和角色，以及可以选择的列表
			userPermissions = userPermissionService.queryByUserId(principal.getUserId());
			//获取菜单
			List<String> menuIds = rolePermissionService.queryMenuIdsByRoleIdDeptId(principal.getDeptId(), principal.getRoleId());
			menus = sysMenuService.queryMenuByIds(menuIds);
			//放入缓存
			cacheMap.put("menus", menus);
			cacheMap.put("userPermissions", userPermissions);
		} else {
			userPermissions = ((List<SysUserPermission>) cacheMap.get("userPermissions"));
			menus = (List<SysMenu>) cacheMap.get("menus");
		}
		model.addAttribute("userPermissions", userPermissions);
		model.addAttribute("menus", menus);
		return getView("frame");
	}
	
	@RequestMapping("/mainContainer")
	public String mainContainer(HttpServletRequest request, HttpServletResponse response, Model model) {
		return getView("main");
	}
	
	/**
	 * 修改用户的部门和角色，并刷新缓存的数据
	 * @author: weihuang.peng
	 */
	@RequestMapping("/changeDeptRole")
	public String changeDeptRole(@Valid @Length(min=20, max=20) @RequestParam(required=false) String deptId, @Valid @Length(min=2, max=4) @RequestParam(required=false) String roleId) {
		SysPrincipal principal = SysUserUtils.getSysPrincipal();

		//判断用户是否拥有此权限,用户自身 + 用户权限信息
		if(userPermissionService.isExistUserPermission(principal.getUserId(), roleId, deptId)
				|| loginService.isExistUserPermission(principal.getUserId(), roleId, deptId)) {
			//更新用户的部门和角色
			userPermissionService.changeUserRoleDept(principal, deptId, roleId);
			//清空缓存信息
			UserUtils.clearCacheMap();
		}
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping("/kickout")
	public Message kickout() {
		Message message = new Message();
		message.setCode("407");//您已在异地登陆
		message.setMessage("对不起，您已在异地登陆，请重新登陆");
		return message;
	}
	
	@ResponseBody
	@RequestMapping("/timeout")
	public Message timeout() {
		Message message = new Message();
		message.setCode("408");//您已在异地登陆
		message.setMessage("对不起，您已登陆超时，请重新登陆");
		return message;
	}
}