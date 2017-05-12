/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyNewscastController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月12日
 */
package com.century.modules.controller.newscast;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.entity.newscast.CenturyNewscast;
import com.century.modules.filter.newscast.CenturyNewscastFilter;
import com.century.modules.service.newscast.CenturyNewscastService;
import com.century.modules.service.social.CenturyPushService;
import com.sirdc.modules.core.exceptions.ServiceException;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.JqGrid;
import com.sirdc.modules.core.web.model.Message;

/**
 * 广播管理
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月12日
 */
@Controller
@RequestMapping(value = "/modules/century/broadcast")
public class CenturyBroadcastController extends JsonBaseController{


	@Autowired
	private CenturyNewscastService centuryNewscastService; 
	
	@Autowired
	private CenturyPushService centuryPushService;

	@Override
	protected String getView(String view) {
		return "/modules/century/broadcast/"+view;
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
	 * 输出文章表
	 * 
	 * @param filter
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public JqGrid query(@ModelAttribute CenturyNewscastFilter filter) {
		filter.setNewsType("2");
		List<CenturyNewscast> centuryNewscasts = centuryNewscastService.query(filter);
		return coverJqGrid(filter, centuryNewscasts);
	}
	
	/**
	 * 跳转至filter界面
	 * @param filter
	 * @return
	 */
	@RequestMapping("/filter")
	public String filter(@ModelAttribute CenturyNewscastFilter filter) {
		return getView("filter");
	}
	
	/**
	 * 创建文章表字段
	 * 
	 * @param CenturyNewscast
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Message create(@ModelAttribute CenturyNewscast centuryNewscast) {
		centuryNewscast.setNewsType("2");
		centuryNewscastService.saveNewscastAndNotice(centuryNewscast);
		return coverMessage("200");
	}

	/**
	 * 初始化创建界面
	 * @param model
	 * @return url
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showAddView(@ModelAttribute  CenturyNewscast centuryNewscast, Model model) {
		return getView("form");
	}

	/**
	 * 初始化更新文章表界面
	 * 
	 * @param model
	 * @param id
	 * @return url
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String showEditView(Model model, @PathVariable String id) {
		CenturyNewscast centuryNewscast = centuryNewscastService.getById(id);
		centuryNewscast.setOrderBy(0);
		model.addAttribute("centuryNewscast",centuryNewscast);
		return getView("edit");
	}
	
	/**
	 * 更新文章表操作
	 * @param CenturyNewscast
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Message update(@ModelAttribute CenturyNewscast centuryNewscast) {
		centuryNewscastService.update(centuryNewscast);
		return coverMessage("200");
	}

	/**
	 * 查询文章表并跳转至详情界面
	 * @param id
	 * @param model
	 * @return url
	 */
	@RequestMapping(value = "/detail/{id}")
	public String detail(@PathVariable String id, Model model) {
		CenturyNewscast centuryNewscast = centuryNewscastService.getById(id);

		if (centuryNewscast == null) {
			throw new ServiceException("message");
		}

		model.addAttribute("centuryNewscast", centuryNewscast);
		return getView("detail");
	}
	
	/**
	 * 删除文章
	 * @param id
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/delete/{id}")
	public Message delete(@PathVariable List<String> id) {
		centuryNewscastService.batchDelete(id);
		return coverMessage("200");
	}
	
	
	
}
