/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SystemSessionListener.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年2月9日
 */
package com.sirdc.modules.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Servlet容器监听器
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年2月9日
 */
public class ContextListener implements ServletContextAttributeListener, ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("Servlet 容器销毁完毕");
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("Servlet 容器初始化完毕");
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
		
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
		
	}
}