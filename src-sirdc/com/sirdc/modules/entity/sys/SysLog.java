/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLog.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年2月9日
 */
package com.sirdc.modules.entity.sys;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年2月9日
 */
@Entity
@Table(name = "t_sys_log")
@DynamicUpdate//动态更新
@DynamicInsert//动态插入
public class SysLog extends StringEntity {

	private static final long serialVersionUID = 6430906294145462310L;
	
	public static final String TYPE_ACCESS = "1";
	
	public static final String TYPE_EXCEPTION = "2";

	private String type;// 日志类型（1：接入日志；2：错误日志）
	
	private String remoteAddr;// 操作用户的IP地址
	
	private String requestUri;// 操作的URI
	
	private String method;// 操作的方式
	
	private String params;// 操作提交的数据
	
	private String userAgent;// 操作用户代理信息
	
	private String exception;// 异常信息

	/**
	 * @author: weihuang.peng
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @author: weihuang.peng
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @author: weihuang.peng
	 * @return the remoteAddr
	 */
	public String getRemoteAddr() {
		return remoteAddr;
	}

	/**
	 * @author: weihuang.peng
	 * @param remoteAddr the remoteAddr to set
	 */
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	/**
	 * @author: weihuang.peng
	 * @return the requestUri
	 */
	public String getRequestUri() {
		return requestUri;
	}

	/**
	 * @author: weihuang.peng
	 * @param requestUri the requestUri to set
	 */
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	/**
	 * @author: weihuang.peng
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @author: weihuang.peng
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @author: weihuang.peng
	 * @return the params
	 */
	public String getParams() {
		return params;
	}

	/**
	 * @author: weihuang.peng
	 * @param params the params to set
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * @author: weihuang.peng
	 * @return the userAgent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * @author: weihuang.peng
	 * @param userAgent the userAgent to set
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * @author: weihuang.peng
	 * @return the exception
	 */
	public String getException() {
		return exception;
	}

	/**
	 * @author: weihuang.peng
	 * @param exception the exception to set
	 */
	public void setException(String exception) {
		this.exception = exception;
	}
}
