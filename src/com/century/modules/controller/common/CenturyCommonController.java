/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyCommonController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月13日
 */
package com.century.modules.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.entity.common.CenturyInformation;
import com.century.modules.service.common.CenturyInformationService;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.utils.ObjectUtils;

/**
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1 
 * @Date: 2015年6月13日
 */
@Controller
@RequestMapping("/modules/century/aboutus")
public class CenturyCommonController extends JsonBaseController{


	@Autowired
	private CenturyInformationService centuryInformationService;
	
	@Override
	protected String getView(String view) {
		return "/modules/century/aboutus/"+view;
	}

	/**
	 * 初始化界面
	 * 
	 * @return url
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initPage(Model model) {
		CenturyInformation common=centuryInformationService.getById("aboutus");	
		if(ObjectUtils.isBlank(common)){
			common.setSysId("aboutus");
			centuryInformationService.save(common);
			model.addAttribute("CenturyInformation",common);
			return getView("form");			
		}
		model.addAttribute("CenturyInformation",common);
		return getView("form");
	}
	
	/**
	 * 更新文章表操作
	 * @param CenturyNewscast
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Message update(@ModelAttribute CenturyInformation centuryInformation) {
		centuryInformationService.update(centuryInformation);
		return coverMessage("200");
	}
	
}
