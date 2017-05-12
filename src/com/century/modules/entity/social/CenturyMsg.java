/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: Message.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月6日
 */
package com.century.modules.entity.social;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 * 消息记录表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
@Entity
@Table(name = "t_sjt_msg")
@SelectBeforeUpdate
@DynamicInsert(true)
// 动态插入
@DynamicUpdate(true)
// 动态更新
public class CenturyMsg extends StringEntity {

	private static final long serialVersionUID = 4941642486793497740L;
	
	/**
	 * 1未读
	 */
	public static final String READ_STATE_UNREAD = "1";
	
	/**
	 * 2已读
	 */
	public static final String  READ_STATE_READ= "2";
	
	/**
	 * 系统消息
	 */
	public static final String MSG_TYPE_SYSMSG = "1";
	
	/**
	 * 好友添加
	 */
	public static final String MSG_TYPE_ADD_FRIEND = "2";
	
	/**
	 * 聊天消息-文本消息
	 */
	public static final String MSG_TYPE_CHAT_TEXT = "3";
	
	/**
	 * 聊天消息-语音消息
	 */
	public static final String MSG_TYPE_CHAT_SPEECH = "4";
	
	/**
	 * 聊天消息-图片消息
	 */
	public static final String MSG_TYPE_CHAT_PICTURE = "5";
	
	/**
	 * 聊天消息-文件消息
	 */
	public static final String MSG_TYPE_CHAT_FILE = "6";
	
	/**
	 * 指令消息-通知更新通讯录
	 */
	public static final String INSTRUCTION_PHONE_LIST = "101";
	

	private String sendUserId;// 发送者ID

	private String recvUserId;// 接收者ID

	private String content;// 消息内容

	private String readState;// 阅读状态

	private String msgType;// 消息类别

	/**
	 * 发送者ID
	 * 
	 * @author: xiaoqin.huang
	 * @return the sendUserId
	 */
	@Column(length = 50)
	public String getSendUserId() {
		return sendUserId;
	}

	/**
	 * 发送者ID
	 * 
	 * @author: xiaoqin.huang
	 * @param sendUserId
	 *            the sendUserId to set
	 */
	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}

	/**
	 * 接收者ID
	 * 
	 * @author: xiaoqin.huang
	 * @return the recvUserId
	 */
	@Column(length = 50)
	public String getRecvUserId() {
		return recvUserId;
	}

	/**
	 * 接收者ID
	 * 
	 * @author: xiaoqin.huang
	 * @param recvUserId
	 *            the recvUserId to set
	 */
	public void setRecvUserId(String recvUserId) {
		this.recvUserId = recvUserId;
	}

	/**
	 * 消息内容
	 * 
	 * @author: xiaoqin.huang
	 * @return the content
	 */
	@Column(columnDefinition="TEXT")
	public String getContent() {
		return content;
	}

	/**
	 * 消息内容
	 * 
	 * @author: xiaoqin.huang
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 阅读状态
	 * 
	 * @author: xiaoqin.huang
	 * @return the readState
	 */
	@Column(length = 50)
	public String getReadState() {
		return readState;
	}

	/**
	 * 阅读状态
	 * 
	 * @author: xiaoqin.huang
	 * @param readState
	 *            the readState to set
	 */
	public void setReadState(String readState) {
		this.readState = readState;
	}

	/**
	 * 消息类别
	 * 
	 * @author: xiaoqin.huang
	 * @return the msgType
	 */
	@Column(length = 50)
	public String getMsgType() {
		return msgType;
	}

	/**
	 * 消息类别
	 * 
	 * @author: xiaoqin.huang
	 * @param msgType
	 *            the msgType to set
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

}
