/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLoginLogDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月22日
 */
package com.sirdc.modules.dao.sys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.entity.sys.SysLoginLog;
import com.sirdc.modules.filter.sys.SysLoginLogFilter;
import com.sirdc.modules.utils.StringUtils;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年1月22日
 */
@Repository
public class SysLoginLogDao extends StringDao<SysLoginLog> {
	
	private void queryCondition(SysLoginLogFilter filter, GenericQuery query){
		if (StringUtils.isNotBlank(filter.getStartLogoutDate()) && StringUtils.isBlank(filter.getEndLogoutDate())) {
			query.append(" or tb.logoutDate >= :logoutDate");
			query.setParam("logoutDate", filter.getStartLogoutDate());
		}
		
		if (StringUtils.isBlank(filter.getStartLogoutDate()) && StringUtils.isNotBlank(filter.getEndLogoutDate())) {
			query.append(" or tb.logoutDate <= :logoutDate");
			query.setParam("logoutDate", filter.getEndLogoutDate());
		}
		
		if (StringUtils.isNotBlank(filter.getStartLogoutDate()) && StringUtils.isNotBlank(filter.getEndLogoutDate())) {
			
			query.append(" or tb.logoutDate between :startLogoutDate and :endLogoutDate");
			query.setParam("startLogoutDate", filter.getStartLogoutDate());
			query.setParam("endLogoutDate", filter.getEndLogoutDate());
		}
	}
	
	public List<SysLoginLog> query(final SysLoginLogFilter filter) {
		GenericQuery query = createQuery("tb");
		
		if (StringUtils.isNotBlank(filter.getUserName())) {
			query.append(" and tb.userName like :userName");
			query.setParam("userName", StringUtils.getLikewords(filter.getUserName()));
		}
		
		if (StringUtils.isNotBlank(filter.getUserTitlName())) {
			query.append(" and tb.userTitlName like :userTitlName");
			query.setParam("userTitlName", StringUtils.getLikewords(filter.getUserTitlName()));
		}
		
		if (StringUtils.isNotBlank(filter.getDeptName())) {
			query.append(" and tb.deptName like :deptName");
			query.setParam("deptName", StringUtils.getLikewords(filter.getDeptName()));
		}
		
		//``````````````````
		if (StringUtils.isNotBlank(filter.getStartLoginDate()) && StringUtils.isBlank(filter.getEndLoginDate())) {
			
			query.append(" and ( tb.loginDate >= :loginDate");
			query.setParam("loginDate", filter.getStartLoginDate());
			queryCondition(filter, query);
			query.append(")");
		}
		
		if (StringUtils.isBlank(filter.getStartLoginDate()) && StringUtils.isNotBlank(filter.getEndLoginDate())) {
			
			query.append(" and ( tb.loginDate <= :loginDate");
			query.setParam("loginDate", filter.getEndLoginDate());
			queryCondition(filter, query);
			query.append(")");
		}
		
		if (StringUtils.isNotBlank(filter.getStartLoginDate()) && StringUtils.isNotBlank(filter.getEndLoginDate())) {
			
			query.append(" and ( tb.loginDate between :startLoginDate and :endLoginDate");
			query.setParam("startLoginDate", filter.getStartLoginDate());
			query.setParam("endLoginDate", filter.getEndLoginDate());
			queryCondition(filter, query);
			query.append(")");
		}
		return query.listResult(filter);
	}
	
	/**
	 * 根据UserId获取登陆日志
	 * @author: weihuang.peng
	 * @param userId
	 * @return
	 */
	public SysLoginLog getByUserIdLoginDate(String userId, String date) {
		String hql = "from SysLoginLog log where log.userId=:userId and log.loginDate=:loginDate";
		GenericQuery query = create(hql);
		query.setParam("userId", userId);
		query.setParam("loginDate", date);
		return query.singleResult();
	}
}