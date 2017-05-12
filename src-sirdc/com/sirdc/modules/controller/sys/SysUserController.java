/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysUserController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月20日
 */
package com.sirdc.modules.controller.sys;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.util.BizException;
import com.sirdc.modules.core.exceptions.ServiceException;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.JqGrid;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.databean.sys.SysUserDataBean;
import com.sirdc.modules.databean.sys.UserAndLoginUserDatabean;
import com.sirdc.modules.entity.sys.SysLogin;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.filter.sys.SysUserFilter;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.sys.entity.SysDept;
import com.sirdc.modules.sys.service.SysDeptService;
import com.sirdc.modules.sys.util.UserUtils;
import com.sirdc.modules.util.SysUserUtils;
import com.sirdc.modules.utils.StringUtils;

/**
 * 系统用户表维护
 * @author: huiyang.yu
 * @Date: 2015年1月20日
 */
@Controller
@RequestMapping(value = "/modules/sys/user")
public class SysUserController  extends JsonBaseController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysDeptService sysDeptService;
	
	@Override
	protected String getView(String view) {
		return "/modules/sys/user/" + view;
	}

	/**
	 * 初始化用户表列表界面
	 * 
	 * @return url
	 */
	@RequiresPermissions("modules:sys:user:query")
	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		return getView("query");
	}

	/**
	 * 输出json用户表
	 * 
	 * @param filter
	 * @return json
	 */
	@ResponseBody
	@RequiresPermissions("modules:sys:user:query")
	@RequestMapping(method = RequestMethod.POST)
	public JqGrid query(@ModelAttribute SysUserFilter filter) {
		List<UserAndLoginUserDatabean> sysUsers = sysUserService.queryJoinUserLogin(filter);
		return coverJqGrid(filter, sysUsers);
	}
	
	/**
	 * 跳转至filter界面
	 * @param filter
	 * @return
	 */
	@RequestMapping("/filter")
	@RequiresPermissions("modules:sys:user:query")
	public String filter(@ModelAttribute SysUserFilter filter) {
		return getView("filter");
	}
	
	/**
	 * 创建用户表字段
	 * 
	 * @param sysUser
	 * @return 
	 */
	@ResponseBody
	@RequiresPermissions("modules:sys:user:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Message create(@ModelAttribute SysUserDataBean datebean) {
		try {
			sysUserService.saveUserLogin(datebean);
		} catch (BizException e) {
			return coverMessage("500",e.getMessage());
		}
		return coverMessage("200");
	}

	/**
	 * 初始化创建用户表界面
	 * 
	 * @param model
	 * @return url
	 */
	@RequiresPermissions("modules:sys:user:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showAddView(@ModelAttribute SysUserDataBean datebean, Model model) {
		//根据自己的过滤规则过滤部门，将来可以考虑是否要修改成下拉树
		List<SysDept> depts = sysDeptService.query();
		datebean = new SysUserDataBean();
		model.addAttribute("datebean", datebean);
		model.addAttribute("depts", depts);
		return getView("form");
	}

	/**
	 * 初始化更新用户表界面
	 * 
	 * @param model
	 * @param id
	 * @return url
	 */
	@RequiresPermissions("modules:sys:user:edit")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String showEditView(Model model, @PathVariable String id) {
		//根据自己的过滤规则过滤部门，将来可以考虑是否要修改成下拉树
		List<SysDept> depts = sysDeptService.query();
		SysUserDataBean datebean = sysUserService.getUserDataBeanById(id);
		model.addAttribute("datebean", datebean);
		model.addAttribute("depts", depts);
		return getView("form");
	}
	
	/**
	 * 更新用户表操作
	 * @param sysUser
	 * @return 
	 */
	@ResponseBody
	@RequiresPermissions("modules:sys:user:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Message update(@ModelAttribute SysUserDataBean datebean) {
		sysUserService.updateUserLogin(datebean);
		return coverMessage("200");
	}

	/**
	 * 查询用户表并跳转至详情界面
	 * @param id
	 * @param model
	 * @return url
	 */
	@RequiresPermissions("modules:sys:user:detail")
	@RequestMapping(value = "/detail/{id}")
	public String detail(@PathVariable String id, Model model) {
		SysUser sysUser = sysUserService.getById(id);

		if (sysUser == null) {
			throw new ServiceException("message");
		}

		model.addAttribute("sysUser", sysUser);
		return getView("detail");
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return 
	 */
	@ResponseBody
	@RequiresPermissions("modules:sys:user:delete")
	@RequestMapping(value = "/delete/{id}")
	public Message delete(@PathVariable List<String> id) {
		boolean del = id.remove(UserUtils.getUserId());
		sysUserService.batchDelete(id);
		if(del) {
			return coverMessage("500", "删除成功，但您不能删除自己");
		}
		return coverMessage("200");
	}
	
	/**
	 * 获取个人基本资料
	 * @author: weihuang.peng
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/profile")
	public String profile(Model model) {
		String userId = UserUtils.getUserId();
		SysUser user = sysUserService.getById(userId);
		model.addAttribute("user", user);
		return getView("profile");
	}
	
	/**
	 * 保存 个人基本资料
	 * @author: weihuang.peng
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveProfile")
	public Message saveProfile(@ModelAttribute SysUser user) {
		sysUserService.update(user);
		SysUserUtils.getSysPrincipal().setNickName(user.getNickName());
		SysUserUtils.getSysPrincipal().setName(user.getName());
		return coverMessage("200");
	}
	
	/**
	 * 显示修改用户个人密码界面
	 * @author: weihuang.peng
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showChangePassView")
	public String showChangePassView(Model model) {
		return getView("changePass");
	}
	
	/**
	 * 修改用户密码
	 * @author: weihuang.peng
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changePass")
	public Message changePass(@ModelAttribute SysUserDataBean databean) {
		if (StringUtils.isEmpty(databean.getPassword()) || StringUtils.isEmpty(databean.getOldPassword())) {
			return coverMessage("500", "请输入完整的信息");
		}
		String userId = UserUtils.getUserId();
		SysLogin login = sysUserService.getSysLoginById(userId);
		String encryptPass = sysUserService.getEncryPass(databean.getOldPassword(), login.getSaltKey());
		if(login.getPassword().equals(encryptPass)) {
			databean.setSysId(userId);
			sysUserService.updatePassword(databean);
			return coverMessage("200");
		}
		return coverMessage("500", "对不起，您当前输入的旧密码有误");
	}
}