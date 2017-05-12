/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyEnrollDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月6日
 */
package com.century.modules.dao.activity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.century.modules.entity.activity.CenturyEnroll;
import com.century.modules.filter.activity.CenturyEnrollFilter;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.utils.StringUtils;

/**
 * 活动报名表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
@Repository
public class CenturyEnrollDao extends StringDao<CenturyEnroll> {
	public List<CenturyEnroll> query(final CenturyEnrollFilter filter) {
		GenericQuery query = createQuery("obj");
		if (StringUtils.isNotBlank(filter.getActivityId())) {
			query.append(" and obj.activityId =:activityId");
			query.setParam("activityId", filter.getActivityId());
			// 活动ID
		}
		if (StringUtils.isNotBlank(filter.getUserId())) {
			query.append(" and obj.userId =:userId");
			query.setParam("userId", filter.getUserId());
			// 用户ID
		}
		if (StringUtils.isNotBlank(filter.getState())) {
			query.append(" and obj.state =:state");
			query.setParam("state", filter.getState());
			// 状态
		}

		return query.listResult(filter);
	}

	
	public CenturyEnroll checkUserId(String userId,String activityId,String state) {
		String hql = "from CenturyEnroll obj where obj.userId=:userId and obj.activtyId=:activityId and obj.state=:state";
		GenericQuery query =create(hql);
		query.setParam("userId", userId);
		query.setParam("activityId", activityId);
		query.setParam("state", state);
		return query.firstResult();
	}
}
