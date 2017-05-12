/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyEnrollController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月12日
 */
package com.century.modules.controller.activity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.databean.activity.CenturyEnrollShowDatabean;
import com.century.modules.databean.activity.UserIdAndNameDataben;
import com.century.modules.entity.activity.CenturyActivity;
import com.century.modules.entity.activity.CenturyEnroll;
import com.century.modules.filter.activity.CenturyEnrollFilter;
import com.century.modules.service.activity.CenturyActivityService;
import com.century.modules.service.activity.CenturyEnrollService;
import com.sirdc.modules.core.exceptions.ServiceException;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.JqGrid;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.service.sys.SysUserService;

/**
 * 活动报名控制层
 * @author: huangcheng.dong
 * @version Revision: 0.0.1
 * @Date: 2015年6月12日
 */
@Controller
@RequestMapping(value = "/modules/century/enroll")
public class CenturyEnrollController extends JsonBaseController{


	@Autowired
	private  CenturyEnrollService CenturyEnrollService;
	
	@Autowired
	private CenturyActivityService centuryActivityService;
	
	@Autowired
	private SysUserService sysUserService;

	@Override
	protected String getView(String view) {
		return "/modules/century/enroll/"+view;
	}

	/**
	 * 初始化界面
	 * 
	 * @return url
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		return getView("query");
	}

	/**
	 * 输出活动报名表
	 * 
	 * @param filter
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public JqGrid query(@ModelAttribute CenturyEnrollFilter filter) {
		List<CenturyEnroll> centuryEnrolls = CenturyEnrollService.query(filter);
		List<CenturyEnrollShowDatabean> enrollShowDatabeans  =new ArrayList<CenturyEnrollShowDatabean>();
		for (CenturyEnroll centuryEnroll : centuryEnrolls) {
			CenturyEnrollShowDatabean databean=new CenturyEnrollShowDatabean();
			databean.setActivityId(centuryEnroll.getActivityId());
			databean.setReceiced(centuryEnroll.getReceiced());
			databean.setReceicedDate(centuryEnroll.getReceicedDate());
			databean.setReceicedTime(centuryEnroll.getReceicedTime());
			databean.setState(centuryEnroll.getState());
			databean.setSysId(centuryEnroll.getSysId());
			databean.setUserId(centuryEnroll.getUserId());
			SysUser sysUser= sysUserService.getById(centuryEnroll.getUserId());
			databean.setUserName(sysUser.getNickName()); //获取用户姓名 
			CenturyActivity centuryActivity=centuryActivityService.getById(centuryEnroll.getActivityId()); //获取活动ID
			databean.setCenturyActivity(centuryActivity);
			enrollShowDatabeans.add(databean);
		}
		return coverJqGrid(filter, enrollShowDatabeans);
	}
	
	/**
	 * 跳转至filter界面
	 * @param filter
	 * @return
	 */
	@RequestMapping("/filter")
	public String filter(@ModelAttribute CenturyEnrollFilter filter) {
		return getView("filter");
	}
	
	/**
	 * 创建活动表字段
	 * 
	 * @param CenturyEnroll
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Message create(@ModelAttribute CenturyEnroll centuryEnroll) {
		CenturyEnrollService.save(centuryEnroll);
		return coverMessage("200");
	}

	/**
	 * 初始化创建界面
	 * @param model
	 * @return url
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showAddView(@ModelAttribute  CenturyEnroll centuryEnroll, Model model) {
		List<CenturyActivity> activities=centuryActivityService.query();//活动列表
		List<SysUser> sysUsers=sysUserService.query();//用户列表
		List<UserIdAndNameDataben> users=new ArrayList<UserIdAndNameDataben>();
		for (SysUser sysUser : sysUsers) {
			UserIdAndNameDataben databen=new UserIdAndNameDataben();
			databen.setUserId(sysUser.getSysId());
			databen.setUserName(sysUser.getName());
			users.add(databen);
		}
		model.addAttribute("activities", activities);
		model.addAttribute("users", users);
		return getView("form");
	}

	/**
	 * 初始化更新活动表界面
	 * 
	 * @param model
	 * @param id
	 * @return url
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String showEditView(Model model, @PathVariable String id) {
		CenturyEnroll centuryEnroll = CenturyEnrollService.getById(id);
		List<CenturyActivity> activities=centuryActivityService.query();//活动列表
		List<SysUser> sysUsers=sysUserService.query();//用户列表
		List<UserIdAndNameDataben> users=new ArrayList<UserIdAndNameDataben>();
		for (SysUser sysUser : sysUsers) {
			UserIdAndNameDataben databen=new UserIdAndNameDataben();
			databen.setUserId(sysUser.getSysId());
			databen.setUserName(sysUser.getName());
			users.add(databen);
		}
		model.addAttribute("activities", activities);
		model.addAttribute("users", users);
		model.addAttribute("centuryEnroll",centuryEnroll);
		return getView("form");
	}
	
	/**
	 * 更新活动表操作
	 * @param CenturyEnroll
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Message update(@ModelAttribute CenturyEnroll centuryEnroll) {
		CenturyEnrollService.update(centuryEnroll);
		return coverMessage("200");
	}

	/**
	 * 查询活动表并跳转至详情界面
	 * @param id
	 * @param model
	 * @return url
	 */
	@RequestMapping(value = "/detail/{id}")
	public String detail(@PathVariable String id, Model model) {
		CenturyEnroll centuryEnroll = CenturyEnrollService.getById(id);

		if (centuryEnroll == null) {
			throw new ServiceException("message");
		}
		List<CenturyActivity> activities=centuryActivityService.query();//活动列表
		List<SysUser> sysUsers=sysUserService.query();//用户列表
		List<UserIdAndNameDataben> users=new ArrayList<UserIdAndNameDataben>();
		for (SysUser sysUser : sysUsers) {
			UserIdAndNameDataben databen=new UserIdAndNameDataben();
			databen.setUserId(sysUser.getSysId());
			databen.setUserName(sysUser.getName());
			users.add(databen);
		}
		model.addAttribute("activities", activities);
		model.addAttribute("users", users);
		model.addAttribute("centuryEnroll", centuryEnroll);
		return getView("detail");
	}
	
	/**
	 * 删除活动
	 * @param id
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/delete/{id}")
	public Message delete(@PathVariable List<String> id) {
		CenturyEnrollService.batchDelete(id);
		return coverMessage("200");
	}
}
