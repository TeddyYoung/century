/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLoginLogService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月22日
 */
package com.sirdc.modules.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.dao.sys.SysLoginLogDao;
import com.sirdc.modules.entity.sys.SysLoginLog;
import com.sirdc.modules.filter.sys.SysLoginLogFilter;
import com.sirdc.modules.security.SysPrincipal;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;
import com.sirdc.modules.utils.StringUtils;
import com.sirdc.modules.utils.date.DateUtils;


/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年1月22日
 */
@Service
public class SysLoginLogService extends StringPKService<SysLoginLog> {
	
	@Autowired
	private SysLoginLogDao sysLoginLogDao;
	@Autowired
	private SysTableService tableService;
	
	@Override
	protected BaseDao<SysLoginLog, String> getDao() {
		return sysLoginLogDao;
	}

	public List<SysLoginLog> query(SysLoginLogFilter filter) {
		return sysLoginLogDao.query(filter);
	}
	
	@Override
	public void save(SysLoginLog entity) {
		entity.setSysId(tableService.updateMaxSysId("t_sys_login_log", null));
	}
	
	/**
	 * 更新登陆时间(登陆信息只会插入一次)
	 * @author: weihuang.peng
	 * @param entity
	 */
	public void updateLoginLog(SysPrincipal principal) {
		SysLoginLog loginLog = getByUserIdLoginDate(principal.getUserId(), DateUtils.getDate());
		if (loginLog == null) {
			//TODO 填充信息
			loginLog = new SysLoginLog();
			loginLog.setDeptId(principal.getDeptId());
			loginLog.setLoginDate(DateUtils.getDate());
			loginLog.setLoginTime(DateUtils.getTime());
			save(loginLog);
		}
	}
	
	/**
	 * 更新登出
	 * @author: weihuang.peng
	 * @param entity
	 */
	public void updateLogoutLog(SysPrincipal principal) {
		SysLoginLog loginLog = getByUserIdLoginDate(principal.getUserId(), DateUtils.getDate());
		if (loginLog != null) {
			//TODO 填充信息
			loginLog.setLogoutDate(DateUtils.getDate());
			loginLog.setLogoutTime(DateUtils.getTime());
			update(loginLog);
		}
	}
	
	/**
	 * 根据UserId获取登陆日志
	 * @author: weihuang.peng
	 * @param userId
	 * @return
	 */
	public SysLoginLog getByUserIdLoginDate(String userId, String date) {
		if (StringUtils.isNotBlank(date)) {
			date = DateUtils.getDate();
		}
		return sysLoginLogDao.getByUserIdLoginDate(userId, date);
	}
}
