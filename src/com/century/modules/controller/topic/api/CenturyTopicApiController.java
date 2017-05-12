/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyTopicApiController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月30日
 */
package com.century.modules.controller.topic.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.databean.topic.CommentDataBean;
import com.century.modules.databean.topic.TopicDataBean;
import com.century.modules.entity.topic.CenturyTopic;
import com.century.modules.entity.topic.CenturyTopicExt;
import com.century.modules.filter.topic.CenturyTopicExtFilter;
import com.century.modules.filter.topic.CenturyTopicFilter;
import com.century.modules.service.topic.CenturyTopicExtService;
import com.century.modules.service.topic.CenturyTopicService;
import com.century.modules.util.MapDistance;
import com.sirdc.modules.core.filter.BaseFilter;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.databean.sys.UserDataBean;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.utils.StringUtils;
import com.sirdc.modules.utils.date.DateUtils;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年6月30日
 */
@Controller
@RequestMapping(value = "/api/topic/")
public class CenturyTopicApiController extends JsonBaseController {

	@Autowired
	private CenturyTopicService topicService;
	
	@Autowired
	private CenturyTopicExtService topicExtService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysUserService userService;
	
	@Override
	protected String getView(String view) {
		return "/api/topic/" + view;
	}
	
	
	/**
	 * 2.4.	公共话题列表
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/topicList", method = RequestMethod.POST)
	public Message topicList(@ModelAttribute CenturyTopicFilter filter) {
		
		filter.getOrder().put("createDate", BaseFilter.DESC);
		filter.getOrder().put("createTime", BaseFilter.DESC);
		List<CenturyTopic> centuryTopics = topicService.query(filter);
		if (centuryTopics == null || centuryTopics.size() == 0) {
			return coverMessage("500", "无更多话题");
		}
		List<TopicDataBean> topicList = new ArrayList<TopicDataBean>();
		
		for (CenturyTopic centuryTopic : centuryTopics) {
			UserDataBean  userDataBean = userService.getUserDataBean(centuryTopic.getUserId());
			TopicDataBean topicDataBean = new TopicDataBean(centuryTopic, userDataBean);
			topicList.add(topicDataBean);
		}
		
		return coverMessage("200", "" ,topicList);
	}
	
	
	/**
	 * 2.5.	附近话题列表
	 * @author: chenyang.wang
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/nearbyTopicList", method = RequestMethod.POST)
	public Message nearbyTopicList(@ModelAttribute CenturyTopicFilter filter) {
		if (StringUtils.isBlank(filter.getLngStr()) || StringUtils.isBlank(filter.getLatStr())) {
			return coverMessage("500", "请求参数错误");
		}
		
		if(StringUtils.isBlank(filter.getRaidus())){
			filter.setRaidus("2000");//默认两公里范围
		}
		Map<String, String> map = MapDistance.getAround(filter.getLatStr(), filter.getLngStr(), filter.getRaidus());
		filter.setMinLat(map.get("minLat"));
		filter.setMaxLat(map.get("maxLat"));
		filter.setMinLng(map.get("minLng"));
		filter.setMaxLng(map.get("maxLng"));
			
		List<CenturyTopic> centuryTopics = topicService.queryNearbyTopicList(filter);
		if (centuryTopics == null || centuryTopics.size() == 0) {
			return coverMessage("500", "无更多话题");
		}
		List<TopicDataBean> topicList = new ArrayList<TopicDataBean>();
		for (CenturyTopic centuryTopic : centuryTopics) {
			UserDataBean  userDataBean = userService.getUserDataBean(centuryTopic.getUserId());
			TopicDataBean topicDataBean = new TopicDataBean(centuryTopic, userDataBean);
			topicList.add(topicDataBean);
		}
		
		return coverMessage("200", " ",topicList);
	}
	
	/**
	 * 2.7.	话题详情
	 * @author: chenyang.wang
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/topicDetail", method = RequestMethod.POST)
	public Message topicDetail(@ModelAttribute CenturyTopicFilter filter){
		
		if (StringUtils.isBlank(filter.getTopicSysId())) {
			return coverMessage("500", "请求参数错误");
		}
		CenturyTopic topic  = topicService.getById(filter.getTopicSysId());
		if (topic == null) {
			return coverMessage("500", "话题不存在");
		}
		UserDataBean userDataBean = userService.getUserDataBean(topic.getUserId());
		TopicDataBean topicDataBean = new TopicDataBean(topic, userDataBean);
		
		return coverMessage("200", "",topicDataBean);
	}
	
	/**
	 * 2.8.	话题评论列表
	 * @author: chenyang.wang
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/topicCommentList", method = RequestMethod.POST)
	public Message topicCommentList(@ModelAttribute CenturyTopicExtFilter filter){
		if (StringUtils.isBlank(filter.getTopicId())) {
			return coverMessage("500", "请求参数错误");
		}
		List<CenturyTopicExt> topicExts= topicExtService.querytopicCommentList(filter);
		if (topicExts == null  ||  topicExts.size() == 0) {
			return coverMessage("500", "无更多评论");
		}
		
		List<CommentDataBean> commentList =  new ArrayList<CommentDataBean>();
		for (CenturyTopicExt centuryTopicExt : topicExts) {
			
			String createDateTime = DateUtils.getNewUpdateDateString(centuryTopicExt.getCreateDate(), centuryTopicExt.getCreateTime());
			CommentDataBean commentDataBean =  new CommentDataBean();
			commentDataBean.setSysId(centuryTopicExt.getSysId());
			commentDataBean.setUser(sysUserService.getUserDataBean(centuryTopicExt.getUserId()));
			commentDataBean.setContent(centuryTopicExt.getContent());
			commentDataBean.setCreateDateTime(createDateTime);
			
			if (StringUtils.isNotBlank(centuryTopicExt.getTopicExtId())) {
				CenturyTopicExt  extTopicExt = topicExtService.getById(centuryTopicExt.getTopicExtId());
				String extCreateDateTime = DateUtils.getNewUpdateDateString(centuryTopicExt.getCreateDate(), centuryTopicExt.getCreateTime());
				commentDataBean.setExtuser(sysUserService.getUserDataBean(extTopicExt.getUserId()));
				commentDataBean.setTopicExtId(extTopicExt.getSysId());
				commentDataBean.setExtcontent(extTopicExt.getContent());
				commentDataBean.setExtCreateDateTime(extCreateDateTime);
			}
			commentList.add(commentDataBean);
		}
		
		return coverMessage("200", "", commentList);
	}
}
