/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyEnrollService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月6日
 */
package com.century.modules.service.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.century.modules.dao.activity.CenturyEnrollDao;
import com.century.modules.entity.activity.CenturyEnroll;
import com.century.modules.filter.activity.CenturyEnrollFilter;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;

/**
 * 活动报名表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
@Service
public class CenturyEnrollService extends StringPKService<CenturyEnroll> {

	@Autowired
	private SysTableService sysTableService;

	@Autowired
	private CenturyEnrollDao enrollDao;

	@Override
	protected BaseDao<CenturyEnroll, String> getDao() {
		return enrollDao;
	}

	@Override
	protected void beforeSave(CenturyEnroll entity) {
		entity.setSysId(sysTableService.updateMaxSysId("t_sjt_enroll", ""));
	}

	public List<CenturyEnroll> query(CenturyEnrollFilter filter) {
		return enrollDao.query(filter);
	}
	
	/**
	 * 查找用户在对应活动中的状态
	 * @author: huangcheng.dong
	 * @param userId
	 * @param activtyId
	 * @param state
	 * @return
	 */
	public CenturyEnroll queryUserId(String userId,String activityId,String state) {
		return enrollDao.checkUserId(userId,activityId,state);
	}
	
	/**
	 * 领取电子币
	 * @author: huangcheng.dong
	 * @param entity
	 */
	public void updateReceiveCurrency(CenturyEnroll entity) {
		
	}
	
	/**
	 * 扣除电子币
	 * @author: huangcheng.dong
	 * @param entity
	 */
	public void updateDeductionCurrency(CenturyEnroll entity) {
		
	}
}
