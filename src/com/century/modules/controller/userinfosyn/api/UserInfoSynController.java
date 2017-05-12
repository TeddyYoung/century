/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: UserInfoSynController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年7月27日
 */
package com.century.modules.controller.userinfosyn.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.cxf.client.UserInfo;
import com.century.modules.util.BizException;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.databean.sys.SysUserDataBean;
import com.sirdc.modules.entity.sys.SysLogin;
import com.sirdc.modules.service.sys.SysLoginService;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.util.PasswordHelper;
import com.sirdc.modules.utils.StringUtils;
import com.sirdc.modules.utils.encrypt.Base64Utils;

/**
 * 用户数据同步接口
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年7月27日
 */
@Controller
@RequestMapping("/api/sys/user/")
public class UserInfoSynController extends JsonBaseController {

	@Autowired
	private SysUserService userService;
	
	@Autowired
	private SysLoginService loginService;
	
	@Autowired
	private PasswordHelper passwordHelper;

	@Override
	protected String getView(String view) {
		return "/api/sys/user/" + view;
	}

	@ResponseBody
	@RequestMapping(value = "/synUser", method = RequestMethod.POST)
	public Message synUser(@ModelAttribute UserInfo userInfo) {
		if (StringUtils.isBlank(userInfo.getOperate())) {
			return coverMessage("500", "请求参数错误");
		}
		Message msg;
		switch (userInfo.getOperate()) {
		case "1"://注册
			msg = register(userInfo);
			break;
		case "2"://修改用户信息
			msg = updateDetail(userInfo);
			break;
		case "3"://实名认证
			msg = attestation(userInfo);
			break;
//		case "4"://修改密码
//			msg = changePwd(userInfo);
//			break;
		default:
			return coverMessage("500", "请求参数错误");
		}

		return msg;
	}

	/**
	 *  修改密码
	 * @author: huiyang.yu
	 * @param userInfo
	 */
	/*private Message changePwd(UserInfo userInfo) {
		SysUserDataBean dataBean = new SysUserDataBean(userInfo);
		
		if (StringUtils.isBlank(dataBean.getPassword()) || StringUtils.isBlank(dataBean.getMobile())) {
			return coverMessage("500", "参数异常");
		}
		
		String regex = "^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";  
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);  
        Matcher m = p.matcher(dataBean.getMobile()); 
        if (!m.matches()) {
        	return coverMessage("500", "电话号码格式有误");
		}
		
		String password = "";
		try {
			password = new String(Base64Utils.decode(dataBean.getPassword()));
		} catch (Exception e) {
			return coverMessage("500", "参数异常");
		};

		SysLogin login = loginService.getLoginByMobile(dataBean.getMobile());
		if (login == null) {
			return coverMessage("500", "帐号不存在");
		}
		login.setPassword(password);
		passwordHelper.encryptPassword(login);
		loginService.update(login);
		
		return coverMessage("200", "成功");
	}*/

	/**
	 *  实名认证
	 * @author: huiyang.yu
	 * @param userInfo
	 */
	private Message attestation(UserInfo userInfo) {
		SysUserDataBean dataBean = new SysUserDataBean(userInfo);
		SysLogin login = loginService.getLoginByMobile(dataBean.getMobile());
		if (login == null) {
			return coverMessage("500", "帐号不存在");
		}
		dataBean.setSysId(login.getSysId());
		userService.updateDetail(dataBean);
		return coverMessage("200", "成功");
	}

	/**
	 *  修改用户信息
	 * @author: huiyang.yu
	 * @param userInfo
	 */
	private Message updateDetail(UserInfo userInfo) {
		SysUserDataBean dataBean = new SysUserDataBean(userInfo);
		
		SysLogin login = loginService.getLoginByMobile(dataBean.getMobile());
		if (login == null) {
			return coverMessage("500", "帐号不存在");
		}
		dataBean.setSysId(login.getSysId());
		userService.updateDetail(dataBean);
		
		return coverMessage("200", "成功");
	}

	/**
	 *  注册
	 * @author: huiyang.yu
	 * @param userInfo
	 */
	private Message register(UserInfo userInfo) {
		SysUserDataBean dataBean = new SysUserDataBean(userInfo);
		
		if (StringUtils.isBlank(dataBean.getMobile()) || StringUtils.isBlank(dataBean.getPassword()) || StringUtils.isBlank(dataBean.getNickName())) {
			return coverMessage("500", "参数有误");
		}
		
		String regex = "^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";  
        Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);  
        Matcher m = p.matcher(dataBean.getMobile()); 
        if (!m.matches()) {
        	return coverMessage("500", "电话号码格式有误");
		}
		
		String password = "";
		try {
			password = new String(Base64Utils.decode(dataBean.getPassword()));
		} catch (Exception e) {
			return coverMessage("500", "参数有误");
		}
		dataBean.setPassword(password);
		
		SysLogin login = loginService.getLoginByMobile(dataBean.getMobile());
		if (login != null) {
			return coverMessage("500", "用户已存在");
		}
		try {
			userService.saveUserLogin(dataBean);
		} catch (BizException e) {
			return coverMessage("500", e.getMessage());
		}
		
		return coverMessage("200", "成功");
	}


}
