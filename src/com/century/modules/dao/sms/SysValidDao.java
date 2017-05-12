/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysValidDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	29 Jul,2015
 */
package com.century.modules.dao.sms;

import org.springframework.stereotype.Repository;

import com.century.modules.entity.sms.SysValid;
import com.century.modules.filter.sms.SysValidFilter;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.LongDao;
import com.sirdc.modules.utils.StringUtils;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 29 Jul,2015
 */
@Repository
public class SysValidDao extends LongDao<SysValid> {

	/**
	 * 获取最后一条记录
	 * @author: weihuang.peng
	 * @param sysValid
	 * @return
	 */
	public SysValid getLastSysValid(SysValidFilter filter) {
		GenericQuery query = createQuery("obj");
		if (StringUtils.isNotBlank(filter.getMobile())) {
			query.append(" and obj.mobile=:mobile");
			query.setParam("mobile", filter.getMobile());
		}
		query.setOrder("currentTimeMillis", GenericQuery.DESC);
		return query.firstResult();
	}
}
