/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CommonApiController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月10日
 */
package com.century.modules.controller.common.api;


import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.century.modules.entity.common.CenturyInformation;
import com.century.modules.filter.common.CenturyInformationFilter;
import com.century.modules.service.common.CenturyInformationService;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.utils.ObjectUtils;


/**
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月10日
 */
@Controller
@RequestMapping("/api/common")
public class CenturyCommonApiController extends JsonBaseController {

	@Autowired
	private CenturyInformationService centuryInformationService;
	
	@Override
	protected String getView(String view) {
		return "/api/common/" + view;
	}

	@RequestMapping(value = "/aboutus")
	public String aboutus(@ModelAttribute CenturyInformationFilter filter,Model model) {
		CenturyInformation aboutus = centuryInformationService.getById("aboutus");
		if(ObjectUtils.isBlank(aboutus)){
			 return "/erroe/404";
		}
		String content = StringEscapeUtils.unescapeHtml4(aboutus.getContent());
		model.addAttribute("content", content);
		return getView("aboutus");
	}
}
