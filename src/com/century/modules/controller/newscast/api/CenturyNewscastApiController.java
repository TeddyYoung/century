/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyNewscastController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月9日
 */
package com.century.modules.controller.newscast.api;

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

import com.century.modules.databean.newscast.CenturyNewscastDatabean;
import com.century.modules.entity.newscast.CenturyNewscast;
import com.century.modules.filter.newscast.CenturyNewscastFilter;
import com.century.modules.service.newscast.CenturyNewscastService;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.utils.CollectionsUtils;
import com.sirdc.modules.utils.ObjectUtils;


/**
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月9日
 */
@Controller
@RequestMapping("/api/newscast")
public class CenturyNewscastApiController extends JsonBaseController {

	@Autowired
	private CenturyNewscastService centuryNewscastService;

	@Override
	protected String getView(String view) {
		return "/api/newscast" + view;
	}

	/**
	 * 获取新闻列表
	 * @author: xiaoqin.huang
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/newsList")
	public Message newsList(@ModelAttribute CenturyNewscastFilter filter) {
		filter.setNewsType("1");
		List<CenturyNewscast> centuryNewscasts = centuryNewscastService.query(filter);
		List<CenturyNewscastDatabean> list = new ArrayList<CenturyNewscastDatabean>();
		if (CollectionsUtils.isNotEmpty(centuryNewscasts)) {
			for (CenturyNewscast centuryNewscast : centuryNewscasts) {
				CenturyNewscastDatabean databean = new CenturyNewscastDatabean();
				databean.setSysId(centuryNewscast.getSysId());
				databean.setImage(centuryNewscast.getImage());
				databean.setTitle(centuryNewscast.getTitle());
				databean.setUrl(centuryNewscast.getUrl());
				databean.setCreateDate(centuryNewscast.getCreateDate());
				databean.setCreateTime(centuryNewscast.getCreateTime());
				databean.setCategory(centuryNewscast.getCategory());
				list.add(databean);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newsList", list);
		return coverMessage("200", "", map);
	}
	
	/**
	 * 获取新闻详情
	 * @author: xiaoqin.huang
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/newsDetail/{newsId}", method = RequestMethod.POST)
	public Message newsDetail(@PathVariable String newsId) {
		CenturyNewscast news = centuryNewscastService.getById(newsId);
		CenturyNewscastDatabean databean = new CenturyNewscastDatabean();
		if(news == null){
			return coverMessage("500", "数据加载失败");
		}	
		
		databean.setSysId(news.getSysId());
		databean.setContent(StringEscapeUtils.unescapeHtml4(news.getContent()));
		databean.setImage(news.getImage());
		databean.setNewsType(news.getNewsType());
		databean.setOrderBy(news.getOrderBy());
		databean.setTitle(news.getTitle());
		databean.setUrl(news.getUrl());
		databean.setCreateDate(news.getCreateDate());
		databean.setCreateTime(news.getCreateTime());
		databean.setCategory(news.getCategory());
		return coverMessage("200", "", databean);
	}
	
	/**
	 * 获取广播列表
	 * @author: xiaoqin.huang
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/broadcastList", method = RequestMethod.POST)
	public Message broadcastList(@ModelAttribute CenturyNewscastFilter filter) {
		filter.setNewsType("2");
		List<CenturyNewscast> centuryNewscasts = centuryNewscastService.query(filter);
		List<CenturyNewscastDatabean> list = null;
		if (CollectionsUtils.isNotEmpty(centuryNewscasts)) {
			list = new ArrayList<CenturyNewscastDatabean>();
			for (CenturyNewscast centuryNewscast : centuryNewscasts) {
				CenturyNewscastDatabean databean = new CenturyNewscastDatabean();
				databean.setSysId(centuryNewscast.getSysId());
				databean.setImage(centuryNewscast.getImage());
				databean.setTitle(centuryNewscast.getTitle());
				databean.setUrl(centuryNewscast.getUrl());
				databean.setCreateDate(centuryNewscast.getCreateDate());
				databean.setCreateTime(centuryNewscast.getCreateTime());
				databean.setCategory(centuryNewscast.getCategory());
				list.add(databean);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("broadcastList", list);
		return coverMessage("200", "", map);
	}

	/**
	 * 获取广播详情
	 * @author: xiaoqin.huang
	 * @param castId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/broadcastDetail/{castId}", method = RequestMethod.POST)
	public Message broadcastDetail(@PathVariable String castId) {
		CenturyNewscast broadcast = centuryNewscastService.getById(castId);
		CenturyNewscastDatabean databean=new CenturyNewscastDatabean();
		if(ObjectUtils.isBlank(broadcast)){
			return coverMessage("500", "数据加载失败");
		}	
		
		databean.setSysId(broadcast.getSysId());
		databean.setContent(StringEscapeUtils.unescapeHtml4(broadcast.getContent()));
		databean.setImage(broadcast.getImage());
		databean.setNewsType(broadcast.getNewsType());
		databean.setOrderBy(broadcast.getOrderBy());
		databean.setTitle(broadcast.getTitle());
		databean.setUrl(broadcast.getUrl());
		databean.setCreateDate(broadcast.getCreateDate());
		databean.setCreateTime(broadcast.getCreateTime());
		databean.setCategory(broadcast.getCategory());
		return coverMessage("200", "", databean);
	}
}
