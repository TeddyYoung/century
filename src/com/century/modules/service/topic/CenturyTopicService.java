/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyTopicService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月29日
 */
package com.century.modules.service.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.century.modules.dao.topic.CenturyTopicDao;
import com.century.modules.entity.topic.CenturyTopic;
import com.century.modules.filter.topic.CenturyTopicFilter;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.core.filter.BaseFilter;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年6月29日
 */
@Service
public class CenturyTopicService extends StringPKService<CenturyTopic> {
	
	@Autowired
	private CenturyTopicDao centuryTopicDao;
	
	@Autowired
	private SysTableService sysTableService;
	
	@Override
	protected BaseDao<CenturyTopic, String> getDao() {
		return centuryTopicDao;
	}
	
	@Override
	protected void beforeSave(CenturyTopic entity) {
		entity.setSysId(sysTableService.updateMaxSysId("t_sjt_topic", ""));
		super.beforeSave(entity);
	}

	public List<CenturyTopic> query(CenturyTopicFilter filter) {
		return centuryTopicDao.query(filter);
	}
	
	
	/**
	 * 查询附近得话题
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	public List<CenturyTopic> queryNearbyTopicList(CenturyTopicFilter filter) {
		filter.getOrder().put("createDate", BaseFilter.DESC);
		filter.getOrder().put("createTime", BaseFilter.DESC);
		return centuryTopicDao.query(filter);
	}
	

}
