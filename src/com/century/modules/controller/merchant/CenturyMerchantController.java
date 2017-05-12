/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyMerchantController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年7月8日
 */
package com.century.modules.controller.merchant;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.databean.merchant.UserAndMerchantDatabean;
import com.century.modules.entity.merchant.CenturyMerchant;
import com.century.modules.filter.merchant.CenturyMerchantFilter;
import com.century.modules.service.merchant.CenturyMerchantService;
import com.century.modules.util.BizException;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.JqGrid;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.filter.sys.SysUserFilter;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.sys.service.RedisService;

/**
 * 商家管理
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年7月8日
 */
@Controller
@RequestMapping(value = "/modules/century/merchant")
public class CenturyMerchantController extends JsonBaseController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private CenturyMerchantService centuryMerchantService;
	
    @Autowired
    private RedisService redisService;

	@Override
	protected String getView(String view) {
		return "/modules/century/merchant/" + view;
	}

	/**
	 * 初始化用户表列表界面
	 * 
	 * @return url
	 */
	// @RequiresPermissions("modules:century:merchant:query")
	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		return getView("query");
	}

	/**
	 * 输出json用户表
	 * 
	 * @param filter
	 * @return json
	 */
	@ResponseBody
	// @RequiresPermissions("modules:century:merchant:query")
	@RequestMapping(method = RequestMethod.POST)
	public JqGrid query(@ModelAttribute CenturyMerchantFilter filter) {
		// filter.setDeptId("20150130100000000040");
		// filter.setRoleId("03");
		List<CenturyMerchant> merchants = centuryMerchantService.query(filter);
				
		return coverJqGrid(filter, merchants);
	}

	/**
	 * 跳转至filter界面
	 * 
	 * @param filter
	 * @return
	 */
	@RequestMapping("/filter")
	// @RequiresPermissions("modules:century:merchant:query")
	public String filter(@ModelAttribute SysUserFilter filter) {
		return getView("filter");
	}

	/**
	 * 创建商户
	 * 
	 * @param sysUser
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("modules:century:merchant:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Message create(@ModelAttribute CenturyMerchant merchant) {
//		datebean.setDeptId("20150130100000000040");
//		datebean.setRoleId("03");// 商家
		try {
			centuryMerchantService.createMerchant(merchant);
		} catch (BizException e) {
			return coverMessage("500",e.getMessage());
		}
		return coverMessage("200");
	}

	/**
	 * 初始化创建商户界面
	 * 
	 * @param model
	 * @return url
	 */
	@RequiresPermissions("modules:century:merchant:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showAddView(@ModelAttribute CenturyMerchant datebean,
			Model model) {
		model.addAttribute("merchant", datebean);
		return getView("form");
	}
	@RequiresPermissions("modules:century:merchant:create")
	@RequestMapping(value = "/mobileQuery", method = RequestMethod.GET)
	public String mobileQuery(Model model) {
		CenturyMerchant merchant = new CenturyMerchant();
		model.addAttribute("merchant", merchant);
		return getView("mobileForm");
	}

	@RequestMapping(value = { "/edit/{id}" })
	public String showEditView(Model model, @PathVariable String id) {
		CenturyMerchant merchant = centuryMerchantService.getById(id);
		model.addAttribute("merchant", merchant);
		return getView("form");
	}

	@ResponseBody
	@RequestMapping(value = { "/edit" })
	public Message update(@ModelAttribute CenturyMerchant merchant) {
		centuryMerchantService.update(merchant);
//		centuryMerchantService.updateDynamic(merchant);
		return coverMessage("200");
	}
	
	/**
	 * 删除活动滚屏图片
	 * @param id
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = {"/delete/{id}"})
	public Message delete(@PathVariable List<String> id) {
		try {
			centuryMerchantService.deleteMerchant(id);
		} catch (BizException e) {
			return coverMessage("500",e.getMessage());
		}
		return coverMessage("200");
	}

}
