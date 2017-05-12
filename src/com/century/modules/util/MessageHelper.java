/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: MessageHelper.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	29 Jul,2015
 */
package com.century.modules.util;

import com.sirdc.modules.core.web.model.Message;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 29 Jul,2015
 */
public class MessageHelper {

	/**
	 * 将状态码和信息输出给界面
	 * @param code
	 * @param msg
	 * @return
	 */
	public static Message coverMessage(String code, String msg, Object data) {
		Message message = new Message();
		message.setCode(code);
		message.setMessage(msg);
		message.setData(data);
		return message;
	}

	/**
	 * 将状态码和信息输出给界面
	 * @param code
	 * @param msg
	 * @return
	 */
	public static Message coverMessage(String code, String msg) {
		Message message = new Message();
		message.setCode(code);
		message.setMessage(msg);
		return message;
	}
	
	/**
	 * 将状态码和信息输出给界面
	 * @param code
	 * @return
	 */
	public static Message coverMessage(String code) {
		Message message = new Message();
		message.setCode(code);
		return message;
	}
}
