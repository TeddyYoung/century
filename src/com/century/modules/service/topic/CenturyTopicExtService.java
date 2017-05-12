/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyTopicExtService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月30日
 */
package com.century.modules.service.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.century.modules.dao.topic.CenturyTopicDao;
import com.century.modules.dao.topic.CenturyTopicExtDao;
import com.century.modules.entity.topic.CenturyTopic;
import com.century.modules.entity.topic.CenturyTopicExt;
import com.century.modules.filter.topic.CenturyTopicExtFilter;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.core.filter.BaseFilter;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;

/**
 * 
 * @author: chenyang.wang
 * @version Revision: 0.0.1
 * @Date: 2015年6月30日
 */
@Service
public class CenturyTopicExtService extends StringPKService<CenturyTopicExt> {

	@Autowired
	private CenturyTopicExtDao centuryTopicExtDao;
	
	@Autowired
	private CenturyTopicDao centuryTopictDao;
	
	@Autowired
	private SysTableService sysTableService;
	
	@Override
	protected BaseDao<CenturyTopicExt, String> getDao() {
		// TODO Auto-generated method stub
		return centuryTopicExtDao;
	}
	@Override
	protected void beforeSave(CenturyTopicExt entity) {
		entity.setSysId(sysTableService.updateMaxSysId("t_sjt_topic_ext", ""));
		super.beforeSave(entity);
	}

	public List<CenturyTopicExt> query(CenturyTopicExtFilter filter) {
		return centuryTopicExtDao.query(filter);
	}
	
	
	/**
	 * 保存点赞操作
	 * @author: chenyang.wang
	 * @param topicExt
	 * @param topic
	 */
	public void saveTopicPraise(CenturyTopicExt topicExt,CenturyTopic topic){
		save(topicExt);
		topic.setPraiseNum(topic.getPraiseNum() + 1);
		centuryTopictDao.update(topic);
	}
	/**
	 * 保存评论操作
	 * @author: chenyang.wang
	 * @param topicExtC
	 * @param topic
	 */
	public void saveTopicComment(CenturyTopicExt topicExtC, CenturyTopic topic) {
		save(topicExtC);
		topic.setCommentNum(topic.getCommentNum()+1);
		centuryTopicExtDao.update(topic);
	}
	
	
	/**
	 * 查询评论列表
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	public List<CenturyTopicExt> querytopicCommentList(CenturyTopicExtFilter filter) {
		filter.getOrder().put("createDate", BaseFilter.DESC);
		filter.getOrder().put("createTime", BaseFilter.DESC);
		filter.setExtType(CenturyTopicExt.EXTTYPE_COMMENT);
		return centuryTopicExtDao.query(filter);
	}
	
	/**
	 * 判断用户是否点赞，如果已点赞返回 ture
	 * @author: huiyang.yu
	 * @param userId
	 * @param topicSysId
	 * @return
	 */
	public boolean queryIsPraise(String userId, String topicSysId){
		int num = centuryTopicExtDao.queryIsPraise(userId, topicSysId);
		if (num > 0) {
			return true;
		}
		
		return false;
	}
}
