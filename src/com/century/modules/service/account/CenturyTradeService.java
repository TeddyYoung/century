/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyAccountService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月21日
 */
package com.century.modules.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.century.modules.dao.account.CenturyTradeDao;
import com.century.modules.entity.account.CenturyTrade;
import com.century.modules.filter.account.CenturyTradeFilter;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;

/**
 * 
 * @author: yang
 * @version 
 * @Date: 
 */
@Service
public class CenturyTradeService extends StringPKService<CenturyTrade> {
	
	@Autowired
	private CenturyTradeDao dao;
	
	@Autowired
	private SysTableService sysTableService;
	
	@Override
	protected BaseDao<CenturyTrade, String> getDao() {
		return dao;
	}
	
	@Override
	protected void beforeSave(CenturyTrade entity) {
		entity.setSysId(sysTableService.updateMaxSysId("t_sjt_trade", ""));
		super.beforeSave(entity);
	}
	
	public List<CenturyTrade> query(CenturyTradeFilter filter) {
		return dao.query(filter);
	}
	
}
