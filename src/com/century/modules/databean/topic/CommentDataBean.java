/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CommentDataBean.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年7月1日
 */
package com.century.modules.databean.topic;

import com.sirdc.modules.databean.sys.UserDataBean;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年7月1日
 */
public class CommentDataBean {
	
	private String sysId;//评论的sysId
	
	private UserDataBean user;//评论用户对象
	
	private String content;//评论内容
	
	private String createDateTime;//创建日期时间
	
	private String topicExtId;//被评论的评论sysId
	
	private UserDataBean extuser;//评论用户对象
	
	private String extcontent;//评论内容
	
	private String extCreateDateTime;//创建日期时间

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
		this.sysId = sysId;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the user
	 */
	public UserDataBean getUser() {
		return user;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param user the user to set
	 */
	public void setUser(UserDataBean user) {
		this.user = user;
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
		this.content = content;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the createDateTime
	 */
	public String getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the topicExtId
	 */
	public String getTopicExtId() {
		return topicExtId;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param topicExtId the topicExtId to set
	 */
	public void setTopicExtId(String topicExtId) {
		this.topicExtId = topicExtId;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the extuser
	 */
	public UserDataBean getExtuser() {
		return extuser;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param extuser the extuser to set
	 */
	public void setExtuser(UserDataBean extuser) {
		this.extuser = extuser;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the extcontent
	 */
	public String getExtcontent() {
		return extcontent;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param extcontent the extcontent to set
	 */
	public void setExtcontent(String extcontent) {
		this.extcontent = extcontent;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the extCreateDateTime
	 */
	public String getExtCreateDateTime() {
		return extCreateDateTime;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param extCreateDateTime the extCreateDateTime to set
	 */
	public void setExtCreateDateTime(String extCreateDateTime) {
		this.extCreateDateTime = extCreateDateTime;
	}
	
	
}
