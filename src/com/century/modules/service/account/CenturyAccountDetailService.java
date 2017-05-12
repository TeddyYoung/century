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
import org.springframework.transaction.annotation.Transactional;

import com.century.modules.dao.account.CenturyAccountDetailDao;
import com.century.modules.databean.account.AccountDetailAndTradeDataBean;
import com.century.modules.databean.account.AccountSumDataBean;
import com.century.modules.databean.account.TradeRecordQueryDataBean;
import com.century.modules.entity.account.CenturyAccountDetail;
import com.century.modules.filter.account.CenturyAccountDetailFilter;
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
public class CenturyAccountDetailService extends StringPKService<CenturyAccountDetail> {
	
	@Autowired
	private CenturyAccountDetailDao dao;
	
	@Autowired
	private SysTableService sysTableService;
	
	@Override
	protected BaseDao<CenturyAccountDetail, String> getDao() {
		return dao;
	}
	
	@Override
	protected void beforeSave(CenturyAccountDetail entity) {
		entity.setSysId(sysTableService.updateMaxSysId("t_sjt_account_detail", ""));
		super.beforeSave(entity);
	}
	
	public List<CenturyAccountDetail> query(CenturyAccountDetailFilter filter){
		return dao.query(filter);
	}
	
	public List<AccountDetailAndTradeDataBean> queryJoinTrade(CenturyAccountDetailFilter filter){
		return dao.queryJoinTrade(filter);
	}
	@Transactional
	public List<AccountSumDataBean> sumByTxDate(CenturyAccountDetailFilter filter){
		return dao.sumByTxDate(filter);
	}
	
	public List<TradeRecordQueryDataBean> queryTradeRecord(CenturyAccountDetailFilter filter){
		return dao.queryTradeRecord(filter);
	}


}
