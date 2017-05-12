/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyActivityService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月6日
 */
package com.century.modules.service.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.century.modules.dao.activity.CenturyActivityDao;
import com.century.modules.entity.activity.CenturyActivity;
import com.century.modules.filter.activity.CenturyActivityFilter;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;

/**
 * 活动信息表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
@Service
public class CenturyActivityService extends StringPKService<CenturyActivity> {

	@Autowired
	private SysTableService sysTableService;

	@Autowired
	private CenturyActivityDao centuryActivityDao;

	@Override
	protected BaseDao<CenturyActivity, String> getDao() {
		return centuryActivityDao;
	}

	@Override
	protected void beforeSave(CenturyActivity entity) {
		entity.setSysId(sysTableService.updateMaxSysId("t_sjt_activity", ""));
	}

	public List<CenturyActivity> query(CenturyActivityFilter filter) {
		return centuryActivityDao.query(filter);
	}
	public List<CenturyActivity> queryShow(CenturyActivityFilter filter){
		return centuryActivityDao.queryShow(filter);
	}

}
