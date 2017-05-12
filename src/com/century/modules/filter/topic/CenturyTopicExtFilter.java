/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyTopicExtFilter.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月30日
 */
package com.century.modules.filter.topic;

import com.sirdc.modules.core.filter.BaseFilter;

/**
 * 
 * @author: chenyang.wang
 * @version Revision: 0.0.1
 * @Date: 2015年6月30日
 */
public class CenturyTopicExtFilter extends BaseFilter {

	private String sysId; // 系统ID
	private String userId; // 作者id(评论或点赞者)
	private String topicId; // 话题Id
	private String topicExtId; // 关联的话题扩展id
	private String extType; // 扩展类型
	private String content; // 扩展内容
	/**
	 * @author: chenyang.wang
	 * @return the sysId
	 */
	public String getSysId() {
		return sysId;
	}
	/**
	 * @author: chenyang.wang
	 * @param sysId the sysId to set
	 */
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	/**
	 * @author: chenyang.wang
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @author: chenyang.wang
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @author: chenyang.wang
	 * @return the topicId
	 */
	public String getTopicId() {
		return topicId;
	}
	/**
	 * @author: chenyang.wang
	 * @param topicId the topicId to set
	 */
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	/**
	 * @author: chenyang.wang
	 * @return the topicExtId
	 */
	public String getTopicExtId() {
		return topicExtId;
	}
	/**
	 * @author: chenyang.wang
	 * @param topicExtId the topicExtId to set
	 */
	public void setTopicExtId(String topicExtId) {
		this.topicExtId = topicExtId;
	}
	/**
	 * @author: chenyang.wang
	 * @return the extType
	 */
	public String getExtType() {
		return extType;
	}
	/**
	 * @author: chenyang.wang
	 * @param extType the extType to set
	 */
	public void setExtType(String extType) {
		this.extType = extType;
	}
	/**
	 * @author: chenyang.wang
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @author: chenyang.wang
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
