/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.sirdc.modules.security;

/**
 * 用户和密码（包含验证码）令牌类
 * 
 * @author ThinkGem
 * @version 2013-5-19
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	private String captcha;
	
	private boolean api;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	public boolean isApi() {
		return api;
	}

	public void setApi(boolean api) {
		this.api = api;
	}

	public UsernamePasswordToken() {
		super();
	}

	public UsernamePasswordToken(String username, char[] password, boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

	public UsernamePasswordToken(String username, String password, boolean rememberMe, boolean api) {
		super(username, password, rememberMe);
		this.api = api;
	}
}