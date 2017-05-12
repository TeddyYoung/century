/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyBannerDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月6日
 */
package com.century.modules.dao.activity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.century.modules.entity.activity.CenturyBanner;
import com.century.modules.filter.activity.CenturyBannerFilter;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.utils.StringUtils;

/**
 * 滚屏图片表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
@Repository
public class CenturyBannerDao extends StringDao<CenturyBanner> {

	public List<CenturyBanner> query(final CenturyBannerFilter filter) {
		GenericQuery query = createQuery("obj");
		if (StringUtils.isNotBlank(filter.getPictureId())) {
			query.append(" and obj.pictureId =:pictureId");
			query.setParam("pictureId", filter.getPictureId());
			// 图片id
		}
		if (StringUtils.isNotBlank(filter.getPicType())) {
			query.append(" and obj.picType =:picType");
			query.setParam("picType", filter.getPicType());
			// 类别
		}
		if (StringUtils.isNotBlank(filter.getActivityId())) {
			query.append(" and obj.activityId =:activityId");
			query.setParam("activityId", filter.getActivityId());
			// 活动ID
		}
		if (StringUtils.isNotBlank(filter.getState())) {
			query.append(" and obj.state =:state");
			query.setParam("state", filter.getState());
			// 状态
		}

//		query.setOrder("orderBy", GenericQuery.DESC);
		query.setOrder("orderBy", GenericQuery.ASC);
		return query.listResult(filter);
	}

}
