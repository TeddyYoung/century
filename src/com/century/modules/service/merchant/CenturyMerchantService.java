/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyConsumeService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年7月8日
 */
package com.century.modules.service.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.century.modules.dao.merchant.CenturyMerchantDao;
import com.century.modules.databean.merchant.UserAndMerchantDatabean;
import com.century.modules.entity.merchant.CenturyMerchant;
import com.century.modules.filter.merchant.CenturyMerchantFilter;
import com.century.modules.util.BizException;
import com.century.modules.util.Constants;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;

/**
 * 
 */
@Service
public class CenturyMerchantService extends StringPKService<CenturyMerchant> {
	
	@Autowired
	private CenturyMerchantDao centuryMerchantDao;
	
	@Autowired
	private SysTableService sysTableService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Override
	protected BaseDao<CenturyMerchant, String> getDao() {
		return centuryMerchantDao;
	}
	
//	@Override
//	protected void beforeSave(CenturyMerchant entity) {
//		entity.setSysId(sysTableService.updateMaxSysId("t_sjt_merchant", ""));
//		super.beforeSave(entity);
//	}
	
	public List<CenturyMerchant> query(CenturyMerchantFilter filter){
		return centuryMerchantDao.query(filter);
	}
	
	public CenturyMerchant getById(String sysId) {
		return centuryMerchantDao.getById(sysId);
	}
	public List<CenturyMerchant> queryJoinUser(CenturyMerchantFilter filter) {
		return centuryMerchantDao.queryJoinUser(filter);
	}
	
	public List<UserAndMerchantDatabean> queryUserAndMerchant(CenturyMerchantFilter filter) {
		return centuryMerchantDao.queryUserAndMerchant(filter);
	}
	
	public void createMerchant(CenturyMerchant merchant)throws BizException{
		SysUser sysUser = sysUserService.getByMobile(merchant.getMobile());
		if(sysUser == null){
			throw new BizException("用户信息有误");
		}
		merchant.setSysId(sysUser.getSysId());
		save(merchant);
		sysUser.setIsMerchant(Constants.YES_FLAG);
	}
	
	public void deleteMerchant(List<String> ids)throws BizException{
		batchDelete(ids);
		for(String id : ids){
			SysUser sysUser = sysUserService.getById(id);
			sysUser.setIsMerchant(Constants.NO_FLAG);
			sysUserService.update(sysUser);
		}
		
	}

}
