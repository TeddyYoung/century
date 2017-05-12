/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: LogInterceptor.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月16日
 */
package com.sirdc.modules.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sirdc.modules.entity.sys.SysLog;
import com.sirdc.modules.security.SysPrincipal;
import com.sirdc.modules.service.sys.SysLogService;
import com.sirdc.modules.util.SysUserUtils;
import com.sirdc.modules.utils.StringUtils;

/**
 *日志拦截器
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年1月16日
 */
public class LogInterceptor implements HandlerInterceptor {

	@Autowired
	private SysLogService logService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		SysPrincipal principal = SysUserUtils.getSysPrincipal();
		if (principal != null) {
			String exception = ex == null ? "" : ex.toString();
			String type = StringUtils.isNotBlank(exception)? SysLog.TYPE_ACCESS : SysLog.TYPE_EXCEPTION;
			SysLog log = new SysLog();
			log.setType(type);
			log.setRemoteAddr(request.getRemoteAddr());
			log.setUserAgent(request.getHeader("user-agent"));
			log.setRequestUri(request.getRequestURI());
			log.setMethod(request.getMethod());
			log.setParams(request.getQueryString());
			log.setException(exception);
			logService.save(log);
		}
	}
}