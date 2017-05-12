/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyOrderAppController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年7月10日
 */
package com.century.modules.controller.merchant.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.entity.merchant.CenturyConsume;
import com.century.modules.service.merchant.CenturyConsumeService;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.util.SysUserUtils;
import com.sirdc.modules.utils.encrypt.Base64Utils;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年7月10日
 */
@Controller
@RequestMapping(value ="/app/order")
public class CenturyOrderAppController extends JsonBaseController {
	
	@Autowired
	private CenturyConsumeService centuryConsumeService;
	
	@Autowired
	private SysUserService sysUserService;

	@Override
	protected String getView(String view) {
		return  "/app/order/"+view;
	}
	
	/**
	 * 支付
	 * @param sysMenu
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/edit/{id}")
	public Message edit(@PathVariable String id) {
		String orderId = new String(Base64Utils.decode(id));
		CenturyConsume centuryConsume = centuryConsumeService.getByLockId(orderId);
		//判断余额
		SysUser user = sysUserService.getByLockId(SysUserUtils.getUserId());
		if (user.getAvaliableMoney().compareTo(centuryConsume.getBalance()) < 0 ) {
			return coverMessage("500" ,"可用余额不足");
		}
		
		centuryConsumeService.updateAndDeductMoney(centuryConsume, user);
		
		return coverMessage("200");
	}
	
	
}
