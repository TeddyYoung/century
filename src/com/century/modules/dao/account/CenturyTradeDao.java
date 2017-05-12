/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLogDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年2月9日
 */
package com.century.modules.dao.account;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.century.modules.entity.account.CenturyTrade;
import com.century.modules.filter.account.CenturyTradeFilter;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;

/**
 * 
 */
@Repository
public class CenturyTradeDao extends StringDao<CenturyTrade> {

	public List<CenturyTrade> query(CenturyTradeFilter filter){
		GenericQuery query = createQuery("obj");
		return query.listResult(filter);
	}
	
}