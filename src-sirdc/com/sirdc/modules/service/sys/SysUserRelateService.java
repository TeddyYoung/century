/**
 * File Name: SysUserService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月20日
 */
package com.sirdc.modules.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.dao.sys.SysUserRelateDao;
import com.sirdc.modules.entity.sys.SysUserRelate;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;

/**
 * 
 */
@Service
public class SysUserRelateService extends StringPKService<SysUserRelate> {
	
	@Autowired
	private SysUserRelateDao sysUserRelateDao;
	
	@Autowired
	private SysTableService tableService;
	
	@Override
	protected BaseDao<SysUserRelate, String> getDao() {
		return sysUserRelateDao;
	}

	@Override
	protected void beforeSave(SysUserRelate entity) {
		entity.setSysId(tableService.updateMaxSysId("t_sys_user_relate", null));
	}
	
}
