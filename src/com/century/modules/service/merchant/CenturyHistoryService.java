/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyHistoryService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月21日
 */
package com.century.modules.service.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.century.modules.dao.merchant.CenturyHistoryDao;
import com.century.modules.entity.merchant.CenturyHistory;
import com.century.modules.filter.merchant.CenturyHistoryFilter;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年9月21日
 */
@Service
public class CenturyHistoryService extends StringPKService<CenturyHistory> {
	
	@Autowired
	private CenturyHistoryDao dao;
	
	@Autowired
	private SysTableService sysTableService;

	@Override
	protected BaseDao<CenturyHistory, String> getDao() {
		return dao;
	}
	
	public List<CenturyHistory> query(CenturyHistoryFilter filter){
		return dao.query(filter);
	}


	@Override
	protected void beforeSave(CenturyHistory entity) {
		entity.setSysId(sysTableService.updateMaxSysId("t_sjt_history", ""));
		super.beforeSave(entity);
	}


}
