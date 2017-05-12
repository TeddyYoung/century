/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyConsumeDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年7月8日
 */
package com.century.modules.dao.merchant;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.century.modules.entity.merchant.CenturyConsume;
import com.century.modules.filter.merchant.CenturyConsumeFilter;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年7月8日
 */
@Repository
public class CenturyConsumeDao extends StringDao<CenturyConsume> {
	
	public List<CenturyConsume> query(CenturyConsumeFilter filter){
		GenericQuery query = createQuery("obj");
		
		return query.listResult(filter);
	}
}
