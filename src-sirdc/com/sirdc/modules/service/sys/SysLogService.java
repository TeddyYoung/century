/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLogService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年2月9日
 */
package com.sirdc.modules.service.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.dao.sys.SysLogDao;
import com.sirdc.modules.entity.sys.SysLog;
import com.sirdc.modules.filter.sys.SysLogFilter;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年2月9日
 */
@Service
public class SysLogService extends StringPKService<SysLog> {

	@Autowired
	private SysLogDao logDao;
	@Autowired
	private SysTableService tableService;
	
	@Override
	protected BaseDao<SysLog, String> getDao() {
		return logDao;
	}

	@Override
	protected void beforeSave(SysLog entity) {
		entity.setSysId(tableService.updateMaxSysId("t_sys_log", null));
	}
	
	//统计每日get，post数量
	//日期，get数量，post数量
	public List<Map<String, Object>> statLog(SysLogFilter logFilter) {
		return logDao.statLog(logFilter);
	}
}