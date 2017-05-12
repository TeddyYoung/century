/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyMerchantApiController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月22日
 */
package com.century.modules.controller.merchant.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.databean.merchant.ShopDataBean;
import com.century.modules.entity.merchant.CenturyMerchant;
import com.century.modules.filter.merchant.CenturyMerchantFilter;
import com.century.modules.service.merchant.CenturyMerchantService;
import com.sirdc.modules.core.filter.Paging;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.databean.sys.UserAndLoginUserDatabean;
import com.sirdc.modules.entity.sys.SysLogin;
import com.sirdc.modules.filter.sys.SysUserFilter;
import com.sirdc.modules.service.sys.SysLoginService;
import com.sirdc.modules.service.sys.SysUserService;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年9月22日
 */
@Controller
@RequestMapping(value = "/api/merchant")
public class CenturyMerchantApiController extends JsonBaseController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private CenturyMerchantService centuryMerchantService;
	
	@Autowired
	private SysLoginService loginService;
	
	@Override
	protected String getView(String view) {
		return "/api/merchant/" + view;
	}
	
	
	/**
	 * 商户列表
	 * @author: huiyang.yu
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopList")
	public Message shopList(@ModelAttribute SysUserFilter filter) {
		if (filter.getPaging() == null) {
			filter.setPaging(new Paging());
		}
		filter.setDeptId("20150130100000000040");
		filter.setRoleId("03");
		List<UserAndLoginUserDatabean> databeans = sysUserService.queryJoinUserLogin(filter);
		List<ShopDataBean> shopDataBeans = new ArrayList<ShopDataBean>();
		for (UserAndLoginUserDatabean databean : databeans) {
			ShopDataBean bean =  new ShopDataBean();
			bean.setMerchantId(databean.getSysUser().getSysId());
			bean.setImage(databean.getSysUser().getImage());
			bean.setName(databean.getSysUser().getName());
//			bean.setDemo(databean.getSysUser().getDemo());
			shopDataBeans.add(bean);
		}
		
		return coverMessage("200", "", shopDataBeans);
	}
	@ResponseBody
	@RequestMapping(value = "/merchantList")
	public Message merchantList(@ModelAttribute CenturyMerchantFilter filter) {
		if (filter.getPaging() == null) {
			filter.setPaging(new Paging());
		}
		List<CenturyMerchant> databeans = centuryMerchantService.queryJoinUser(filter);
		List<ShopDataBean> shopDataBeans = new ArrayList<ShopDataBean>();
		for (CenturyMerchant databean : databeans) {
			ShopDataBean bean =  new ShopDataBean();
			bean.setMerchantId(databean.getSysId());
			bean.setImage(StringUtils.defaultString(databean.getImage()));
			bean.setName(StringUtils.defaultString(databean.getMerchantName()));
			bean.setCity(StringUtils.defaultString(databean.getCity()));
			bean.setLogo(StringUtils.defaultString(databean.getLogo()));
			shopDataBeans.add(bean);
		}
		
		return coverMessage("200", "", shopDataBeans);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getMerchantInfo/{merchantId}")
	public Message getMerchantInfo(@PathVariable String merchantId) {
		if(StringUtils.isBlank(merchantId)){
			return coverMessage("500", "商家id不能为空");
		}
		CenturyMerchant centuryMerchant = centuryMerchantService.getById(merchantId);
		Map<String, Object> map =  new HashMap<String, Object>();
		map.put("address",StringUtils.defaultString( centuryMerchant.getAddress()));
		map.put("logo", StringUtils.defaultString(centuryMerchant.getLogo()));
		map.put("city", StringUtils.defaultString(centuryMerchant.getCity()));
		map.put("image", StringUtils.defaultString(centuryMerchant.getImage()));
		map.put("merchantName", StringUtils.defaultString(centuryMerchant.getMerchantName()));
		map.put("shortName", StringUtils.defaultString(centuryMerchant.getShortName()));
		map.put("telephone", StringUtils.defaultString(centuryMerchant.getTelephone()));
		map.put("telephoneOne", StringUtils.defaultString(centuryMerchant.getTelephoneOne()));
		return coverMessage("200", "", map);
	}
	@ResponseBody
	@RequestMapping(value = "/checkMobileMerchant")
	public boolean checkMobileMerchant(@ModelAttribute CenturyMerchantFilter filter) {
		SysLogin sysLogin = loginService.getLoginByMobile(filter.getMobile());
		if(sysLogin == null){
			return false;
		}
		List<CenturyMerchant> merchants = centuryMerchantService.queryJoinUser(filter);
		if(merchants != null && merchants.size() > 0){
			return false;
		}
		return true;
	}
	
}
