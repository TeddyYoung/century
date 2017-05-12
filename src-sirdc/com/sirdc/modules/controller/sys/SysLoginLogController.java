/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLoginLogController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月22日
 */
package com.sirdc.modules.controller.sys;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.JqGrid;
import com.sirdc.modules.entity.sys.SysLoginLog;
import com.sirdc.modules.filter.sys.SysLoginLogFilter;
import com.sirdc.modules.service.sys.SysLoginLogService;

/**
 * 登录日志表维护
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年1月22日
 */
@Controller
@RequestMapping(value = "/modules/sys/loginLog")
public class SysLoginLogController extends JsonBaseController {
	
	@Autowired
	private SysLoginLogService sysLoginLogService;
	
	@Override
	protected String getView(String view) {
		return "/modules/sys/loginLog/" + view;
	}

	/**
	 * 初始化登录日志表列表界面
	 * 
	 * @return url
	 */
	@RequiresPermissions("modules:sys:loginLog:query")
	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		return getView("query");
	}

	/**
	 * 输出json登录日志表
	 * 
	 * @param filter
	 * @return json
	 */
	@ResponseBody
	@RequiresPermissions("modules:sys:loginLog:query")
	@RequestMapping(method = RequestMethod.POST)
	public JqGrid query(@ModelAttribute SysLoginLogFilter filter) {
		List<SysLoginLog> sysLoginLogs = sysLoginLogService.query(filter);
		return coverJqGrid(filter, sysLoginLogs);
	}
}
