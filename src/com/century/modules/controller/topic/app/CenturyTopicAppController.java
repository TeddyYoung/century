/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyTopicAppController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月29日
 */
package com.century.modules.controller.topic.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.databean.topic.TopicDataBean;
import com.century.modules.entity.topic.CenturyTopic;
import com.century.modules.entity.topic.CenturyTopicExt;
import com.century.modules.filter.topic.CenturyTopicFilter;
import com.century.modules.service.topic.CenturyTopicExtService;
import com.century.modules.service.topic.CenturyTopicService;
import com.sirdc.modules.core.filter.BaseFilter;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.databean.sys.UserDataBean;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.util.SysUserUtils;
import com.sirdc.modules.utils.StringUtils;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年6月29日
 */
@Controller
@RequestMapping(value = "/app/topic/")
public class CenturyTopicAppController extends JsonBaseController {

	@Autowired
	private CenturyTopicService topicService;
	
	@Autowired
	private CenturyTopicExtService topicExtService;
	
	@Autowired
	private SysUserService userService;
	
	@Override
	protected String getView(String view) {
		return "/app/topic/" + view;
	}
	
	/**
	 * 2.1.	发布话题 
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/publishTopic", method = RequestMethod.POST)
	public Message publishTopic(@ModelAttribute CenturyTopicFilter filter) {
		if (StringUtils.isBlank(filter.getContent())) {
			return coverMessage("500", "请求参数错误");
		}
		//2. 新增t_sjt_topic
		 CenturyTopic topic = new CenturyTopic();
		 topic.setUserId(SysUserUtils.getUserId());
		 topic.setContent(filter.getContent());
		 topic.setThemePic1(filter.getThemePic1());
		 topic.setThemePic2(filter.getThemePic2());
		 topic.setThemePic3(filter.getThemePic3());
		 topic.setThemePic4(filter.getThemePic4());
		 topic.setThemePic5(filter.getThemePic5());
		 topic.setThemePic6(filter.getThemePic6());
		 topic.setThemePic7(filter.getThemePic7());
		 topic.setThemePic8(filter.getThemePic8());
		 topic.setThemePic9(filter.getThemePic9());
		 topic.setLatStr(filter.getLatStr());
		 topic.setLngStr(filter.getLngStr());
		 topic.setPraiseNum(0);
		 topic.setCommentNum(0);
		 topic.setState(CenturyTopic.STATE_NORMAL);
		 try {
			topicService.save(topic);
		} catch (Exception e) {
			e.printStackTrace();
			return coverMessage("500", "发布失败");
		}
		 return coverMessage("200", "发布成功");
	}
	
	/**
	 * 2.2.	点赞操作
	 * @author: chenyang.wang
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/topicPraise", method = RequestMethod.POST)
	public Message topicPraise(@ModelAttribute CenturyTopicFilter filter){
		if(StringUtils.isBlank(filter.getTopicSysId())){
			return coverMessage("500","点赞失败");
		}
		//判断是否点赞
		if (topicExtService.queryIsPraise(SysUserUtils.getUserId(), filter.getTopicSysId())) {
			return coverMessage("500","请勿重复点赞");
		}
		//执行点赞
		CenturyTopicExt topicExt = new CenturyTopicExt();
		topicExt.setUserId(SysUserUtils.getUserId());
		topicExt.setTopicId(filter.getTopicSysId());
		topicExt.setExtType(CenturyTopicExt.EXTTYPE_PRAISE);
		
		CenturyTopic topic = topicService.getByLockId(filter.getTopicSysId());
		if (topic == null) {
			return coverMessage("500","话题不存在");
		}
		try {
			topicExtService.saveTopicPraise(topicExt, topic);
		} catch (Exception e) {
			e.printStackTrace();
			return coverMessage("500","点赞失败");
		}
		return coverMessage("200","点赞成功");
		
	}
	
	/**
	 * 2.3.	评论操作
	 * @author: chenyang.wang
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/topicComment", method = RequestMethod.POST)
	public Message topicComment(@ModelAttribute CenturyTopicFilter filter){
		if(StringUtils.isBlank(filter.getTopicSysId()) || StringUtils.isBlank(filter.getContent())){
			return coverMessage("500","请求参数错误");
		}
	
		CenturyTopicExt topicExtC =new CenturyTopicExt();
		topicExtC.setUserId(SysUserUtils.getUserId());
		topicExtC.setTopicId(filter.getTopicSysId());
		topicExtC.setTopicExtId(filter.getTopicExtId());
		topicExtC.setExtType(CenturyTopicExt.EXTTYPE_COMMENT);
		topicExtC.setContent(filter.getContent());
	
		CenturyTopic topic = topicService.getByLockId(filter.getTopicSysId());
		
		if(topic==null){
			return coverMessage("500","话题不存在");
		}
		
		try {
			topicExtService.saveTopicComment(topicExtC, topic);
		} catch (Exception e) {
			e.printStackTrace();
			return coverMessage("500","评论失败");
		}
		return coverMessage("200","评论成功");
	
}
	/**
	 * 我的话题列表
	 * @author: chenyang.wang
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myTopicList", method = RequestMethod.POST)
	public Message myTopicList(@ModelAttribute CenturyTopicFilter filter){
		filter.getOrder().put("createDate", BaseFilter.DESC);
		filter.getOrder().put("createTime", BaseFilter.DESC);
		filter.setUserId(SysUserUtils.getUserId());
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
}
