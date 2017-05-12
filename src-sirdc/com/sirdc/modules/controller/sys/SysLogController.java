/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLogController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年5月10日
 */
package com.sirdc.modules.controller.sys;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.JqGrid;
import com.sirdc.modules.filter.sys.SysLogFilter;
import com.sirdc.modules.service.sys.SysLogService;
import com.sirdc.modules.utils.CollectionsUtils;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年5月10日
 */
@Controller
@RequestMapping("/modules/sys/log")
public class SysLogController extends JsonBaseController {

	@Autowired
	private SysLogService logService;
	
	@Override
	protected String getView(String view) {
		return new StringBuffer("/modules/sys/log/").append(view).toString();
	}
	
	/**
	 * 初始化表信息列表界面
	 * 
	 * @return url
	 */
	@RequiresPermissions("modules:sys:log:query")
	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		return getView("query");
	}

	/**
	 * 输出json表信息
	 * @param filter
	 * @return json
	 */
	@ResponseBody
	@RequiresPermissions("modules:sys:log:query")
	@RequestMapping(method = RequestMethod.POST)
	public JqGrid query(@ModelAttribute SysLogFilter filter) {
		List<Map<String, Object>> syslogs = logService.statLog(filter);
		return coverJqGrid(filter, syslogs);
	}
	
	/**
	 * 跳转至filter界面
	 * @param filter
	 * @return
	 */
	@RequiresPermissions("modules:sys:log:query")
	@RequestMapping("/filter")
	public String filter(@ModelAttribute SysLogFilter filter) {
		return getView("filter");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/chart")
	public String chart(@ModelAttribute SysLogFilter filter, Model model) {
		List<Map<String, Object>> syslogs = logService.statLog(filter);
		List<String> createDates = CollectionsUtils.extractToList(syslogs, "createDate");
		model.addAttribute("createDates", CollectionsUtils.convertToString(createDates, "'", "',"));
		model.addAttribute("postCounts", CollectionsUtils.extractToString(syslogs, "postCount", ","));
		model.addAttribute("getCounts", CollectionsUtils.extractToString(syslogs, "getCount", ","));
		return getView("chart");
	}
}
