/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyInformationDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月9日
 */
package com.century.modules.dao.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.century.modules.entity.common.CenturyInformation;
import com.century.modules.filter.common.CenturyInformationFilter;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;

/**
 * 文章信息表
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月9日
 */
@Repository
public class CenturyInformationDao extends StringDao<CenturyInformation> {
	
	public List<CenturyInformation> query(final CenturyInformationFilter filter) {
		GenericQuery query = createQuery("obj");
		return query.listResult(filter);
	}
}
