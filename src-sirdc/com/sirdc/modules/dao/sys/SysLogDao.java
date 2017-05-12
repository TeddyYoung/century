/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLogDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年2月9日
 */
package com.sirdc.modules.dao.sys;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.entity.sys.SysLog;
import com.sirdc.modules.filter.sys.SysLogFilter;
import com.sirdc.modules.utils.StringUtils;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年2月9日
 */
@Repository
public class SysLogDao extends StringDao<SysLog> {

	/**
	 * 统计数据
	 * @author: weihuang.peng
	 * @return
	 */
	public List<Map<String, Object>> statLog(SysLogFilter logFilter) {
		GenericQuery query = create("select new map(log.createDate as createDate, "
				+ "count(case when log.method='POST' then 1 else null end) as postCount, "
				+ "count(case when log.method='GET' then 1 else null end) as getCount) from SysLog log where 1=1");
		if(StringUtils.isNotBlank(logFilter.getStartDate())) {
			query.append(" and log.createDate>=:startDate").setParam("startDate", logFilter.getStartDate());
		}
		if(StringUtils.isNotBlank(logFilter.getEndDate())) {
			query.append(" and log.createDate<=:endDate").setParam("endDate", logFilter.getEndDate());
		}
		return query.append(" group by log.createDate").listResult(logFilter);
	}
}