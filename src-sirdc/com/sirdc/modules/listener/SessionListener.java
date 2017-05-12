/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SessionListener.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年2月9日
 */
package com.sirdc.modules.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * HttpSession监听器和HttpSession属性监听器 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年2月9日
 */
public class SessionListener implements HttpSessionAttributeListener, HttpSessionListener {

	/**
	 * session创建事件
	 * @author: weihuang.peng
	 * @param event
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("session create");
	}

	/**
	 * session销毁事件
	 * @author: weihuang.peng
	 * @param event
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		/*SystemAuthorizingRealm realm = SpringContextHolder.getBean(SystemAuthorizingRealm.class);
		realm.invalidate();*/
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		
	}
}
