/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyBannerController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月12日
 */
package com.century.modules.controller.activity.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.entity.activity.CenturyBanner;
import com.century.modules.filter.activity.CenturyBannerFilter;
import com.century.modules.service.activity.CenturyActivityService;
import com.century.modules.service.activity.CenturyBannerService;
import com.century.modules.util.Constants;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.utils.StringUtils;

/**
 */
@Controller
@RequestMapping(value = "/api/banner")
public class CenturyBannerApiController extends JsonBaseController{


	@Autowired
	private  CenturyBannerService CenturyBannerService;
	@Autowired
	private  CenturyActivityService centuryActivityService;

	@Override
	protected String getView(String view) {
		return "/api/banner/"+view;
	}


	/**
	 * 输出滚屏图片
	 * 
	 * @param filter
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/bannerList")
	public Message bannerList() {
		CenturyBannerFilter filter = new CenturyBannerFilter();
		filter.setActivityId("0");
		filter.setState(Constants.VALID);
		List<CenturyBanner> centuryBanners = CenturyBannerService.query(filter);
		
		List<Map<String,String>> pictureList  =new ArrayList<Map<String,String>>();
		for (CenturyBanner centuryBanner : centuryBanners) {
			Map<String,String> pictureMap = new HashMap<String,String>();
			pictureMap.put("pictureId", StringUtils.defaultString(centuryBanner.getPictureId()));
			pictureMap.put("image", StringUtils.defaultString(centuryBanner.getImage()));
			pictureList.add(pictureMap);
		}
		return coverMessage("200","", pictureList);
	}
	
}
