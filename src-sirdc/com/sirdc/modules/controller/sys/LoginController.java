/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: LoginController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月30日
 */
package com.sirdc.modules.controller.sys;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.security.SysPrincipal;
import com.sirdc.modules.shiro.exception.CaptchaException;
import com.sirdc.modules.util.SysUserUtils;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年1月30日
 */
@Controller
@RequestMapping("/login")
public class LoginController extends JsonBaseController {

	@Override
	protected String getView(String view) {
		return "/modules/" + view;
	}

	@RequestMapping
	public String login(Model model, HttpServletRequest req) throws Exception {
		SysPrincipal principal = SysUserUtils.getSysPrincipal();
		if (principal != null) {
			return "redirect:/";
		}
		
		String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
		String error = "";
		if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "当前用户不存在";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (CaptchaException.class.getName().equals(exceptionClassName)) {
			error = "验证码输入有误";
		} else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
			error = "您当前密码输入错误超过五次，请十分钟后再试";
		} else if (StringUtils.isNotBlank(exceptionClassName)) {
			error = "用户名/密码错误";
		}
		
		model.addAttribute("error", error);
		return getView("login");
	}
}