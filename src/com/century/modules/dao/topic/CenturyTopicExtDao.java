/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyTopicExtDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月30日
 */
package com.century.modules.dao.topic;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.century.modules.entity.topic.CenturyTopicExt;
import com.century.modules.filter.topic.CenturyTopicExtFilter;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.utils.StringUtils;

/**
 * 
 * @author: chenyang.wang
 * @version Revision: 0.0.1
 * @Date: 2015年6月30日
 */
@Repository
public class CenturyTopicExtDao extends StringDao<CenturyTopicExt> {
	/**
	 * 
	 * @author: chenyang.wang
	 * @param filter
	 * @return
	 */
	public List<CenturyTopicExt> query(CenturyTopicExtFilter filter){
		GenericQuery query=createQuery("obj");
		
		if (StringUtils.isNotBlank(filter.getTopicId())) {
			query.append(" and obj.topicId = :topicId ").setParam("topicId", filter.getTopicId());
		}
		
		if (StringUtils.isNotBlank(filter.getExtType())) {
			query.append(" and obj.extType = :extType ").setParam("extType", filter.getExtType());
		}
		
		return query.listResult(filter);
	}

	/**
	 * 统计用户点赞数
	 * @author: huiyang.yu
	 * @param userId
	 * @param topicSysId
	 */
	public int queryIsPraise(String userId, String topicSysId) {
		GenericQuery query=createQuery("obj");
		query.append(" and obj.userId = :userId ").setParam("userId", userId);
		query.append(" and obj.topicId = :topicSysId ").setParam("topicSysId", topicSysId);
		return query.count();
	}

}
