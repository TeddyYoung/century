/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyActivityController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月12日
 */
package com.century.modules.controller.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.entity.activity.CenturyActivity;
import com.century.modules.filter.activity.CenturyActivityFilter;
import com.century.modules.service.activity.CenturyActivityService;
import com.sirdc.modules.core.exceptions.ServiceException;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.JqGrid;
import com.sirdc.modules.core.web.model.Message;

/**
 * 活动控制层
 * @author: huangcheng.dong
 * @version Revision: 0.0.1
 * @Date: 2015年6月12日
 */
@Controller
@RequestMapping(value = "/modules/century/activity")
public class CenturyActivityController extends JsonBaseController{


	@Autowired
	private  CenturyActivityService centuryActivityService; 

	@Override
	protected String getView(String view) {
		return "/modules/century/activity/"+view;
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
	 * 输出活动表
	 * 
	 * @param filter
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public JqGrid query(@ModelAttribute CenturyActivityFilter filter) {
		List<CenturyActivity> CenturyActivities = centuryActivityService.query(filter);
		return coverJqGrid(filter, CenturyActivities);
	}
	
	/**
	 * 跳转至filter界面
	 * @param filter
	 * @return
	 */
	@RequestMapping("/filter")
	public String filter(@ModelAttribute CenturyActivityFilter filter) {
		return getView("filter");
	}
	
	/**
	 * 创建活动表字段
	 * 
	 * @param CenturyActivity
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Message create(@ModelAttribute CenturyActivity CenturyActivity) {
		centuryActivityService.save(CenturyActivity);
		return coverMessage("200");
	}

	/**
	 * 初始化创建界面
	 * @param model
	 * @return url
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showAddView(@ModelAttribute  CenturyActivity CenturyActivity, Model model) {
		CenturyActivity.setOrderBy(0);
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
		CenturyActivity CenturyActivity = centuryActivityService.getById(id);
		model.addAttribute("CenturyActivity",CenturyActivity);
		return getView("form");
	}
	
	/**
	 * 更新活动表操作
	 * @param CenturyActivity
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Message update(@ModelAttribute CenturyActivity CenturyActivity) {
		centuryActivityService.update(CenturyActivity);
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
		CenturyActivity CenturyActivity = centuryActivityService.getById(id);

		if (CenturyActivity == null) {
			throw new ServiceException("message");
		}

		model.addAttribute("CenturyActivity", CenturyActivity);
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
		centuryActivityService.batchDelete(id);
		return coverMessage("200");
	}
}
