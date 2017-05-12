/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyTopicExt.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月30日
 */
package com.century.modules.entity.topic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 * 话题扩展表
 * @author: chenyang.wang
 * @version Revision: 0.0.1
 * @Date: 2015年6月30日
 */
@Entity
@Table(name = "t_sjt_topic_ext")
@SelectBeforeUpdate
@DynamicInsert(true)
// 动态插入
@DynamicUpdate(true)
// 动态更新
public class CenturyTopicExt extends StringEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 979626521981635372L;
	
	/**
	 * 点赞
	 */
	public static final String EXTTYPE_PRAISE = "2";
	
	
	/**
	 * 评论
	 */
	public static final String EXTTYPE_COMMENT = "1";

	private String userId; // 作者id(评论或点赞者)
	private String topicId; // 话题Id
	private String topicExtId; // 关联的话题扩展id
	private String extType; // 扩展类型
	private String content; // 扩展内容
	
	/**
	 * 作者id(评论或点赞者)
	 * @author: chenyang.wang
	 * @return the userId
	 */
	@Column(length = 50)
	public String getUserId() {
		return userId;
	}
	/**
	 * 作者id(评论或点赞者)
	 * @author: chenyang.wang
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 话题Id
	 * @author: chenyang.wang
	 * @return the topicId
	 */
	@Column(length = 50)
	public String getTopicId() {
		return topicId;
	}
	/**
	 * 话题Id
	 * @author: chenyang.wang
	 * @param topicId the topicId to set
	 */
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	/**
	 * 关联的话题扩展id
	 * @author: chenyang.wang
	 * @return the topicExtId
	 */
	@Column(length = 50)
	public String getTopicExtId() {
		return topicExtId;
	}
	/**
	 * 关联的话题扩展id
	 * @author: chenyang.wang
	 * @param topicExtId the topicExtId to set
	 */
	public void setTopicExtId(String topicExtId) {
		this.topicExtId = topicExtId;
	}
	/**
	 * 扩展类型
	 * @author: chenyang.wang
	 * @return the extType
	 */
	@Column(length = 50)
	public String getExtType() {
		return extType;
	}
	/**
	 * 扩展类型
	 * @author: chenyang.wang
	 * @param extType the extType to set
	 */
	public void setExtType(String extType) {
		this.extType = extType;
	}
	/**
	 * 扩展内容
	 * @author: chenyang.wang
	 * @return the content
	 */
	@Column(columnDefinition="TEXT")
	public String getContent() {
		return content;
	}
	/**
	 * 扩展内容
	 * @author: chenyang.wang
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	
}
