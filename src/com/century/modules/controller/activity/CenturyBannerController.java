/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyBannerController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月12日
 */
package com.century.modules.controller.activity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.databean.activity.CenturyBannerShowDatabean;
import com.century.modules.entity.activity.CenturyActivity;
import com.century.modules.entity.activity.CenturyBanner;
import com.century.modules.filter.activity.CenturyBannerFilter;
import com.century.modules.service.activity.CenturyActivityService;
import com.century.modules.service.activity.CenturyBannerService;
import com.century.modules.util.Constants;
import com.sirdc.modules.core.exceptions.ServiceException;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.JqGrid;
import com.sirdc.modules.core.web.model.Message;

/**
 * 活动滚屏图片控制层
 * @author: huangcheng.dong
 * @version Revision: 0.0.1
 * @Date: 2015年6月12日
 */
@Controller
@RequestMapping(value = "/modules/century/banner")
public class CenturyBannerController extends JsonBaseController{


	@Autowired
	private  CenturyBannerService centuryBannerService;
	@Autowired
	private  CenturyActivityService centuryActivityService;
	
	@Override
	protected String getView(String view) {
		return "/modules/century/banner/"+view;
	}

	/**
	 * 初始化界面
	 * 
	 * @return url
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		return getView("query");
	}

	/**
	 * 输出活动滚屏图片表
	 * 
	 * @param filter
	 * @return json
	 */
	/*@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public JqGrid query(@ModelAttribute CenturyBannerFilter filter) {
		List<CenturyBanner> centuryBanners = centuryBannerService.query(filter);
		List<CenturyBannerShowDatabean> centuryBannerShowDatabeans  =new ArrayList<CenturyBannerShowDatabean>();
		for (CenturyBanner centuryBanner : centuryBanners) {
			CenturyBannerShowDatabean databean=new CenturyBannerShowDatabean();
			databean.setActivityId(centuryBanner.getActivityId());
			databean.setOrderBy(centuryBanner.getOrderBy());
			databean.setPictureId(centuryBanner.getPictureId());
			databean.setPicType(centuryBanner.getPicType());
			databean.setState(centuryBanner.getState());
			databean.setSysId(centuryBanner.getSysId());
			CenturyActivity centuryActivity=centuryActivityService.getById(centuryBanner.getActivityId()); //获取活动ID
			databean.setCenturyActivity(centuryActivity);
			centuryBannerShowDatabeans.add(databean);
		}
		return coverJqGrid(filter, centuryBannerShowDatabeans);
	}*/
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public JqGrid query(@ModelAttribute CenturyBannerFilter filter) {
		List<CenturyBanner> centuryBanners = centuryBannerService.query(filter);
		return coverJqGrid(filter, centuryBanners);
	}
	
	/**
	 * 跳转至filter界面
	 * @param filter
	 * @return
	 */
	@RequestMapping("/filter")
	public String filter(@ModelAttribute CenturyBannerFilter filter) {
		return getView("filter");
	}
	
	/**
	 * 创建活动滚屏图片
	 * 
	 * @param CenturyBanner
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Message create(@ModelAttribute CenturyBanner centuryBanner) {
		centuryBanner.setActivityId("0");
		centuryBanner.setState(Constants.VALID); 
		centuryBannerService.save(centuryBanner);
		return coverMessage("200");
	}

	/**
	 * 初始化创建界面
	 * @param model
	 * @return url
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showAddView(@ModelAttribute  CenturyBanner centuryBanner, Model model) {
//		List<CenturyActivity> activities=centuryActivityService.query();//活动列表
//		centuryBanner = new CenturyBanner();
//		model.addAttribute("centuryBanner", centuryBanner);
		return getView("form");
	}

	/**
	 * 初始化更新活动滚屏图片表界面
	 * 
	 * @param model
	 * @param id
	 * @return url
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String showEditView(Model model, @PathVariable String id) {
		CenturyBanner centuryBanner = centuryBannerService.getById(id);
//		List<CenturyActivity> activities=centuryActivityService.query();//活动列表
//		model.addAttribute("activities", activities);
		model.addAttribute("centuryBanner",centuryBanner);
		return getView("form");
	}
	
	/**
	 * 更新活动滚屏图片表操作
	 * @param CenturyBanner
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Message update(@ModelAttribute CenturyBanner centuryBanner) {
		centuryBanner.setActivityId("0");
		centuryBanner.setState(Constants.VALID);
		centuryBannerService.update(centuryBanner);
		return coverMessage("200");
	}

	/**
	 * 查询活动滚屏图片表并跳转至详情界面
	 * @param id
	 * @param model
	 * @return url
	 */
	@RequestMapping(value = "/detail/{id}")
	public String detail(@PathVariable String id, Model model) {
		CenturyBanner centuryBanner = centuryBannerService.getById(id);

		if (centuryBanner == null) {
			throw new ServiceException("message");
		}
//		List<CenturyActivity> activities=centuryActivityService.query();//活动列表
//		model.addAttribute("activities", activities);
		model.addAttribute("centuryBanner", centuryBanner);
		return getView("detail");
	}
	
	/**
	 * 删除活动滚屏图片
	 * @param id
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/delete/{id}")
	public Message delete(@PathVariable List<String> id) {
		centuryBannerService.batchDelete(id);
		return coverMessage("200");
	}

}
