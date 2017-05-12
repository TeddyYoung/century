/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyActivityController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月9日
 */
package com.century.modules.controller.activity.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.databean.activity.CenturyActivityDatabean;
import com.century.modules.databean.activity.CenturyBannerDatabean;
import com.century.modules.entity.activity.CenturyActivity;
import com.century.modules.entity.activity.CenturyBanner;
import com.century.modules.filter.activity.CenturyActivityFilter;
import com.century.modules.filter.activity.CenturyBannerFilter;
import com.century.modules.service.activity.CenturyActivityService;
import com.century.modules.service.activity.CenturyBannerService;
import com.century.modules.service.activity.CenturyEnrollService;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.utils.CollectionsUtils;

/**
 * 
 * @author: huangcheng.dong
 * @version Revision: 0.0.1
 * @Date: 2015年6月10日
 */
@Controller
@RequestMapping("/api/activity")
public class CenturyActivityApiController extends JsonBaseController {

	@Autowired
	private CenturyActivityService centuryActivityService;

	@Autowired
	private CenturyBannerService centuryBannerService;

	@Autowired
	private CenturyEnrollService centuryEnrollService;

	@Override
	protected String getView(String view) {
		return "/api/activity" + view;
	}

	/**
	 * 获取活动列表
	 * @author: huangcheng.dong
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/activityList", method = RequestMethod.POST)
	public Message activityList(@ModelAttribute CenturyActivityFilter filter) {
		List<CenturyActivity> centuryActivities = centuryActivityService.queryShow(filter);
		List<CenturyActivityDatabean> list = new ArrayList<CenturyActivityDatabean>();
		if (CollectionsUtils.isNotEmpty(centuryActivities)) {
			for (CenturyActivity CenturyActivity : centuryActivities) {
				CenturyActivityDatabean databean = new CenturyActivityDatabean();
				databean.setSysId(CenturyActivity.getSysId());// 活动ID
				databean.setTitle(CenturyActivity.getTitle());// 标题
				//列表不需要返回content databean.setContent(CenturyActivity.getContent());
				databean.setPictureId(CenturyActivity.getPictureId());
				databean.setActivityCast(CenturyActivity.getActivityCast());
				databean.setReward(CenturyActivity.getReward());// 活动奖励
				databean.setStartDate(CenturyActivity.getStartDate());
				databean.setStartTime(CenturyActivity.getStartTime());
				databean.setEndDate(CenturyActivity.getEndDate());
				databean.setEndTime(CenturyActivity.getEndTime());
				databean.setCreateDate(CenturyActivity.getCreateDate());
				databean.setCteateTime(CenturyActivity.getCreateTime());
				list.add(databean);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityList", list);
		return coverMessage("200", "", map);

	}

	/**
	 * 获取活动详情
	 * @author: huangcheng.dong
	 * @param activityId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/activityDetail/{activityId}", method = RequestMethod.POST)
	public Message activityDetail(@PathVariable String activityId) {
		CenturyActivity activity = centuryActivityService.getById(activityId);
		CenturyActivityDatabean databean = new CenturyActivityDatabean();
		if (activity == null) {
			return coverMessage("500", "数据加载失败");
		}
		databean.setSysId(activity.getSysId());// 活动ID
		databean.setTitle(activity.getTitle());// 标题
		databean.setContent(StringEscapeUtils.unescapeHtml4(activity.getContent()));
		databean.setPictureId(activity.getPictureId());
		databean.setActivityCast(activity.getActivityCast());
		databean.setReward(activity.getReward());// 活动奖励
		databean.setStartDate(activity.getStartDate());
		databean.setStartTime(activity.getStartTime());
		databean.setEndDate(activity.getEndDate());
		databean.setEndTime(activity.getEndTime());
		databean.setCreateDate(activity.getCreateDate());
		databean.setCteateTime(activity.getCreateTime());
		return coverMessage("200", "", databean);
	}

	/**
	 * 获取活动滚图片列表
	 * @author: huangcheng.dong
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/activityBannerList", method = RequestMethod.POST)
	public Message activityBannerList(@ModelAttribute CenturyBannerFilter filter) {
		filter.setPicType("2");
		List<CenturyBanner> centuryBanners = centuryBannerService.query(filter);
		List<CenturyBannerDatabean> list = null;
		if (CollectionsUtils.isNotEmpty(centuryBanners)) {
			list = new ArrayList<CenturyBannerDatabean>();
			for (CenturyBanner centuryBanner : centuryBanners) {
				CenturyBannerDatabean databean = new CenturyBannerDatabean();
				databean.setActivityId(centuryBanner.getActivityId());// 活动ID
				databean.setOrderBy(centuryBanner.getOrderBy());// 排序
				databean.setPictureId(centuryBanner.getPictureId());// 图片id
				databean.setPicType(centuryBanner.getPicType());// 类别
				databean.setState(centuryBanner.getState());// 状态
				databean.setSysId(centuryBanner.getSysId());// 系统id
				databean.setUrl(centuryBanner.getUrl());// 链接
				list.add(databean);
			}

		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("centuryBannerList", list);
		return coverMessage("200", "", map);

	}
}
