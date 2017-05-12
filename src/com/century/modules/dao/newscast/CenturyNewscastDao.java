/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyNewscastDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月6日
 */
package com.century.modules.dao.newscast;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.century.modules.entity.newscast.CenturyNewscast;
import com.century.modules.filter.newscast.CenturyNewscastFilter;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.utils.StringUtils;

/**
 * 新闻广播表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
@Repository
public class CenturyNewscastDao extends StringDao<CenturyNewscast> {
	
	public List<CenturyNewscast> query(final CenturyNewscastFilter filter) {
		GenericQuery query = createQuery("obj");
		if (StringUtils.isNotBlank(filter.getTitle())) {
			query.append(" and obj.title like :title");
			query.setParam("title",  StringUtils.getLikewords(filter.getTitle()));
			// 标题
		}
		
		if (StringUtils.isNotBlank(filter.getNewsType())) {
			query.append(" and obj.newsType = :newsType");
			query.setParam("newsType", filter.getNewsType());
			// 类型
		}

		query.setOrder("orderBy", GenericQuery.DESC);
		query.setOrder("createDate",GenericQuery.DESC);
		query.setOrder("createTime", GenericQuery.DESC);
		return query.listResult(filter);
	}
}