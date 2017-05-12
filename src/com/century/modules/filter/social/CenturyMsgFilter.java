package com.century.modules.filter.social;

import java.util.List;

import com.sirdc.modules.core.filter.BaseFilter;

/**
 * 消息记录表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
public class CenturyMsgFilter extends BaseFilter {

	private String sendUserId;// 发送者ID
	private String recvUserId;// 接收者ID
	private String content;// 消息内容
	private String readState;// 阅读状态
	private String msgType;// 消息类别
	
	private String msgSysId;//消息系统id
	
	private List<String> UnreadState4friend;
	
	private List<String> msgTypes;
	
	/**
	 * @author: weihuang.peng
	 * @return the msgTypes
	 */
	public List<String> getMsgTypes() {
		return msgTypes;
	}

	/**
	 * @author: weihuang.peng
	 * @param msgTypes the msgTypes to set
	 */
	public void setMsgTypes(List<String> msgTypes) {
		this.msgTypes = msgTypes;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the unreadState4friend
	 */
	public List<String> getUnreadState4friend() {
		return UnreadState4friend;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param unreadState4friend the unreadState4friend to set
	 */
	public void setUnreadState4friend(List<String> unreadState4friend) {
		UnreadState4friend = unreadState4friend;
	}

	/**
	 * 发送者ID
	 * 
	 * @author: xiaoqin.huang
	 * @return the sendUserId
	 */
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

	/**
	 * 消息系统Id
	 * @author: huiyang.yu
	 * @return the msgSysId
	 */
	public String getMsgSysId() {
		return msgSysId;
	}

	/**
	 * 消息系统Id
	 * @author: huiyang.yu
	 * @param msgSysId the msgSysId to set
	 */
	public void setMsgSysId(String msgSysId) {
		this.msgSysId = msgSysId;
	}
	
	
	
}
