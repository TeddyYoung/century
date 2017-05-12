/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyOrderController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年7月8日
 */
package com.century.modules.controller.merchant;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.entity.merchant.CenturyConsume;
import com.century.modules.filter.merchant.CenturyConsumeFilter;
import com.century.modules.service.merchant.CenturyConsumeService;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.JqGrid;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.util.SysUserUtils;
import com.sirdc.modules.utils.ZxingUtils;
import com.sirdc.modules.utils.encrypt.Base64Utils;

/**
 * 商家订单管理
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年7月8日
 */
@Controller
@RequestMapping(value = "/modules/century/order")
public class CenturyOrderController extends JsonBaseController {

	@Autowired
	private CenturyConsumeService centuryConsumeService;
	
	@Override
	protected String getView(String view) {
		return "/modules/century/order/" + view;
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
	 * 跳转至filter界面
	 * @param filter
	 * @return
	 */
	@RequestMapping("/filter")
	public String filter(@ModelAttribute CenturyConsumeFilter filter) {
		return getView("filter");
	}
	
	/**
	 * 输出json数据
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public JqGrid query(@ModelAttribute CenturyConsumeFilter filter) {
		
		List<CenturyConsume> centuryConsumes = centuryConsumeService.query(filter);
		return coverJqGrid(filter, centuryConsumes);
	}
	
	/**
	 * 创建订单信息
	 * 
	 * @param sysMenu
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Message create(@ModelAttribute CenturyConsume centuryConsume) {
		centuryConsume.setState(CenturyConsume.STATE_PLACE_ORDER);
		centuryConsumeService.save(centuryConsume);
		return coverMessage("200");
	}

	/**
	 * 初始化创建菜单信息界面
	 * 
	 * @param model
	 * @return url
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showAddView(@ModelAttribute CenturyConsume centuryConsume, Model model) {
		centuryConsume.setShopId(SysUserUtils.getUserId());
		return getView("formOrder");
	}
	
//	/**
//	 * 支付
//	 * @param sysMenu
//	 * @return 
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/edit", method = RequestMethod.POST)
//	public Message edit(@ModelAttribute CenturyConsume centuryConsume) {
//		centuryConsume.setState(CenturyConsume.STATE_PLACE_SECCESS);
//		centuryConsumeService.update(centuryConsume);
//		return coverMessage("200");
//	}

	/**
	 * 初始化支付界面
	 * @param model
	 * @return url
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String showEditView(Model model, @PathVariable String id) {
		CenturyConsume centuryConsume = centuryConsumeService.getById(id);
		model.addAttribute("centuryConsume", centuryConsume);
		return getView("formPay");
	}
	
	/**
	 * 生成二维码
	 * @author: huiyang.yu
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/qrcode/{id}", method = RequestMethod.GET)
	public void showQrcode(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		CenturyConsume centuryConsume = centuryConsumeService.getById(id);
		String orderId = Base64Utils.encode(centuryConsume.getSysId().getBytes());
		String url = "http://" + request.getHeader("Host") +  request.getContextPath() + "/app/order/edit/" + orderId;
		ZxingUtils.encode2(url, 300, 300, response.getOutputStream());
	}
	
}
