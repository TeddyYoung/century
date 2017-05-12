/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLogDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年2月9日
 */
package com.sirdc.modules.dao.cms;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.entity.cms.Article;
import com.sirdc.modules.filter.cms.ArticleFilter;
import com.sirdc.modules.utils.StringUtils;


/**
 * 
 * @author: huangcheng.dong
 * @version Revision: 0.0.1
 * @Date: 2015年6月4日
 */
@Repository
public class ArticleDao extends StringDao<Article> {

	public List<Article> query(ArticleFilter filter) {
		GenericQuery query = createQuery("obj");
		if (StringUtils.isNotBlank(filter.getTitle())) {
			query.append(" and obj.title like:title");
			query.setParam("title", StringUtils.getLikewords(filter.getTitle()));
			//标题
		}
		if (StringUtils.isNotBlank(filter.getCategory())) {
			query.append(" and obj.category =:category");
			query.setParam("category", filter.getCategory());
			//分类
		}
		return query.listResult(filter);
	}
}