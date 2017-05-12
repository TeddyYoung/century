/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyMerchantAppController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月22日
 */
package com.century.modules.controller.merchant.app;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.entity.account.CenturyAccount;
import com.century.modules.entity.merchant.CenturyHistory;
import com.century.modules.filter.merchant.CenturyConsumeFilter;
import com.century.modules.filter.merchant.CenturyHistoryFilter;
import com.century.modules.service.account.CenturyAccountService;
import com.century.modules.service.merchant.CenturyConsumeService;
import com.century.modules.service.merchant.CenturyHistoryService;
import com.sirdc.modules.core.filter.Paging;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.sys.util.UserUtils;
import com.sirdc.modules.utils.StringUtils;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年9月22日
 */
@Controller
@RequestMapping(value = "/app/merchant")
public class CenturyMerchantAppController extends JsonBaseController {
	
	@Autowired
	private CenturyAccountService accountService;
	
	@Autowired
	private CenturyHistoryService historyService;
	
	@Autowired
	private CenturyConsumeService consumeService;
	
	@Autowired
	private SysUserService userService;
	
	@Override
	protected String getView(String view) {
		return "/app/merchant/" + view;
	}
	
	/**
	 * 账户查询（余额）
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/balance", method = RequestMethod.POST)
	public Message balance() {
		 CenturyAccount account = accountService.getByUserId(UserUtils.getUserId());
		 BigDecimal balance = new BigDecimal(0);
		if (account != null) {
			 balance =  account.getAvaliableAmt();
		 }
		Map<String, Object> map =  new HashMap<String, Object>();
		map.put("balance", balance);
		return coverMessage("200","",map);
	}
	
	/**
	 * 账户流水
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bill", method = RequestMethod.POST)
	public Message bill(@ModelAttribute CenturyHistoryFilter filter) {
		if (filter.getPaging() == null) {
			filter.setPaging(new Paging());
		}
		filter.setUserId(UserUtils.getUserId());
		List<CenturyHistory> centuryHistorys = historyService.query(filter);
		if (centuryHistorys.size() == 0) {
			return coverMessage("500","无记录");
		}
		return coverMessage("200","",centuryHistorys);
	}
	
	
	/**
	 * 支付
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public Message payment(@ModelAttribute CenturyConsumeFilter filter) {
		if (StringUtils.isBlank(filter.getShopId())) {
			return coverMessage("500","请求参数错误");
		}
		SysUser shopUser = userService.getById(filter.getShopId());
		if (shopUser == null) {
			return coverMessage("500","商户不存在");
		}
		//具体支付流程
		String state = consumeService.savePayment(filter);
		if (state.equals("1")) {
			return coverMessage("500","账户余额不足请充值后再支付");
		}
		return coverMessage("200","成功");
	}
	
	
}
