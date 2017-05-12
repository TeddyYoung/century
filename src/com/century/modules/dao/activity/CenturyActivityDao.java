/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyActivityDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月6日
 */
package com.century.modules.dao.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.century.modules.entity.activity.CenturyActivity;
import com.century.modules.filter.activity.CenturyActivityFilter;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.utils.StringUtils;

/**
 * 活动信息表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
@Repository
public class CenturyActivityDao extends StringDao<CenturyActivity> {

	public List<CenturyActivity> query(final CenturyActivityFilter filter) {
		GenericQuery query = createQuery("obj");
		if (StringUtils.isNotBlank(filter.getTitle())) {
			query.append(" and obj.title like :title");
			query.setParam("title", StringUtils.getLikewords(filter.getTitle()));
			// 活动标题
		}
		if (StringUtils.isNotBlank(filter.getState())) {
			query.append(" and obj.state =:state");
			query.setParam("state", filter.getState());
			// 活动状态
		}

		query.setOrder("orderBy", GenericQuery.DESC);
		query.setOrder("createDate", GenericQuery.DESC);
		query.setOrder("createTime", GenericQuery.DESC);
		return query.listResult(filter);
	}
	
	/**
	 * 首页展示
	 * @author: huangcheng.dong
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("static-access")
	public List<CenturyActivity> queryShow(final CenturyActivityFilter filter) {
		GenericQuery query = createQuery("obj");
		Map<String, String> orders=new HashMap<String, String>();
		orders.put("startDate",query.DESC);
		orders.put("startTime",query.DESC);
		orders.put("orderBy",query.DESC);
		query.setOrders(orders);
		return query.listResult(filter);
	}
}
