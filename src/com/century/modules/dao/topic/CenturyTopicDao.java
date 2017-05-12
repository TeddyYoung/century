/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyTopicDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月29日
 */
package com.century.modules.dao.topic;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.century.modules.entity.topic.CenturyTopic;
import com.century.modules.filter.topic.CenturyTopicFilter;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.utils.StringUtils;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年6月29日
 */
@Repository
public class CenturyTopicDao extends StringDao<CenturyTopic> {
	
	/**
	 * 
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	public List<CenturyTopic> query(CenturyTopicFilter filter) {
		GenericQuery query = createQuery("obj");
		if (StringUtils.isNotBlank(filter.getUserId())) {
			query.append(" and obj.userId =:userId");
			query.setParam("userId", filter.getUserId());
			// 用户ID
		}
		
		/**经纬度范围查找 开始**/
		if (StringUtils.isNotBlank(filter.getMinLat())) {
			query.append(" and obj.latStr >= :minLat ").setParam("minLat", filter.getMinLat());
		}
		
		if (StringUtils.isNotBlank(filter.getMaxLat())) {
			query.append(" and obj.latStr <= :maxLat ").setParam("maxLat", filter.getMaxLat());
		}
		
		if (StringUtils.isNotBlank(filter.getMinLng())) {
			query.append(" and obj.lngStr >= :minLng ").setParam("minLng", filter.getMinLng());
		}
		
		if (StringUtils.isNotBlank(filter.getMaxLng())) {
			query.append(" and obj.lngStr <= :maxLng ").setParam("maxLng", filter.getMaxLng());
		}
		/**经纬度范围查找 结束**/
		return query.listResult(filter);

	}

}
