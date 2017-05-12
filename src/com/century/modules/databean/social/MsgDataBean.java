/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: MsgDataBean.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月11日
 */
package com.century.modules.databean.social;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.century.modules.entity.social.CenturyMsg;
import com.sirdc.modules.utils.StringUtils;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年6月11日
 */
public class MsgDataBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -448823501601232919L;
	
	private String sysId;//系统ID
	
	private String sendUserId;// 发送者ID

	private String recvUserId;// 接收者ID

	private String content;// 消息内容

	private String readState;// 阅读状态

	private String msgType;// 消息类别
	
	private String createDate;//创建日期
	
	private String createTime;//创建时间
	
	/**
	 * 消息实体转消息DataBean
	 * @param msg 消息实体对象
	 */
	public MsgDataBean(CenturyMsg msg) {
		super();
		this.sysId = msg.getSysId();
		this.sendUserId = msg.getSendUserId();
		this.recvUserId = msg.getRecvUserId();
		this.content = msg.getContent();
		this.readState = msg.getReadState();
		this.msgType = msg.getMsgType();
		this.createDate = msg.getCreateDate();
		this.createTime = msg.getCreateTime();
	}

	/**
	 * 
	 */
	public MsgDataBean() {
		super();
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the sysId
	 */
	public String getSysId() {
		return sysId;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param sysId the sysId to set
	 */
	public void setSysId(String sysId) {
		this.sysId = StringUtils.defaultString(sysId);
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the sendUserId
	 */
	public String getSendUserId() {
		return sendUserId;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param sendUserId the sendUserId to set
	 */
	public void setSendUserId(String sendUserId) {
		this.sendUserId = StringUtils.defaultString(sendUserId);
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the recvUserId
	 */
	public String getRecvUserId() {
		return recvUserId;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param recvUserId the recvUserId to set
	 */
	public void setRecvUserId(String recvUserId) {
		this.recvUserId = StringUtils.defaultString(recvUserId);
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = StringUtils.defaultString(content);
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the readState
	 */
	public String getReadState() {
		return readState;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param readState the readState to set
	 */
	public void setReadState(String readState) {
		this.readState = StringUtils.defaultString(readState);
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the msgType
	 */
	public String getMsgType() {
		return msgType;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param msgType the msgType to set
	 */
	public void setMsgType(String msgType) {
		this.msgType = StringUtils.defaultString(msgType);
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = StringUtils.defaultString(createDate);
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = StringUtils.defaultString(createTime);
	}
	
	
	public static Map<String, Object> toMap(CenturyMsg msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sysId",  msg.getSysId());
		map.put("sendUserId",  msg.getSendUserId());
		map.put("recvUserId",  msg.getRecvUserId());
		map.put("content",  msg.getContent());
		map.put("readState",  msg.getReadState());
		map.put("msgType",  msg.getMsgType());
		map.put("createDate",  msg.getCreateDate());
		map.put("createTime",  msg.getCreateTime());
		return map;
	}
}
