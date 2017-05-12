/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyHistoryDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月21日
 */
package com.century.modules.dao.merchant;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.century.modules.entity.merchant.CenturyHistory;
import com.century.modules.filter.merchant.CenturyHistoryFilter;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.utils.StringUtils;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年9月21日
 */
@Repository
public class CenturyHistoryDao extends StringDao<CenturyHistory> {
	
	public List<CenturyHistory> query(CenturyHistoryFilter filter){
		GenericQuery query = createQuery("obj");
		if (StringUtils.isNotBlank(filter.getUserId())) {
			query.append("and obj.userId=:userId").setParam("userId", filter.getUserId());
		}
		return query.listResult(filter);
	}

}
