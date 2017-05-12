/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyAccountService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月21日
 */
package com.sirdc.modules.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.dao.sys.SysBankDao;
import com.sirdc.modules.entity.sys.SysBank;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;

/**
 * 
 * @author: yang
 * @version 
 * @Date: 
 */
@Service
public class SysBankService extends StringPKService<SysBank> {
	
	@Autowired
	private SysBankDao dao;
	
	@Autowired
	private SysTableService sysTableService;
	
	@Override
	protected BaseDao<SysBank, String> getDao() {
		return dao;
	}
	
	@Override
	protected void beforeSave(SysBank entity) {
		entity.setSysId(sysTableService.updateMaxSysId("t_sys_bank", ""));
		super.beforeSave(entity);
	}

	/**
	 * 
	 */
	public SysBank getByUserId(String bankCode) {
		return dao.getByBankCode(bankCode);
	}

}
