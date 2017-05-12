/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: TopicDataBean.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月30日
 */
package com.century.modules.databean.topic;

import java.io.Serializable;

import com.century.modules.entity.topic.CenturyTopic;
import com.sirdc.modules.databean.sys.UserDataBean;
import com.sirdc.modules.utils.date.DateUtils;

/**
 * 话题DataBean
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年6月30日
 */
public class TopicDataBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4592117996908404019L;

	private UserDataBean user;
	
	private String sysId;
	
	private String content;//话题内容
	
	private String themePic1;//主题图片1
	
	private String themePic2;//主题图片2
	
	private String themePic3;//主题图片3
	
	private String themePic4;//主题图片4
	
	private String themePic5;//主题图片5
	
	private String themePic6;//主题图片6
	
	private String themePic7;//主题图片7
	
	private String themePic8;//主题图片8
	
	private String themePic9;//主题图片9
	
	private Integer praiseNum;//点赞数
	
	private Integer commentNum;//评论数
	
	private String lngStr;//经度字符串
	
	private String latStr;//纬度度字符串
	
	private String createDateTime;//创建日期时间
	
	
	
	
	/**
	 * 传入CenturyTopic对象和UserDataBean对象构造出对象
	 * @author: huiyang.yu
	 * @param centuryTopic
	 * @param userDataBean
	 */
	public TopicDataBean(CenturyTopic centuryTopic, UserDataBean  userDataBean) {
		super();
		this.user = userDataBean;
		this.sysId = centuryTopic.getSysId();
		this.content = centuryTopic.getContent();
		this.themePic1 = centuryTopic.getThemePic1();
		this.themePic2 = centuryTopic.getThemePic2();
		this.themePic3 = centuryTopic.getThemePic3();
		this.themePic4 = centuryTopic.getThemePic4();
		this.themePic5 = centuryTopic.getThemePic5();
		this.themePic6 = centuryTopic.getThemePic6();
		this.themePic7 = centuryTopic.getThemePic7();
		this.themePic8 = centuryTopic.getThemePic8();
		this.themePic9 = centuryTopic.getThemePic9();
		this.praiseNum = centuryTopic.getPraiseNum();
		this.commentNum = centuryTopic.getCommentNum();
		this.lngStr = centuryTopic.getLngStr();
		this.latStr = centuryTopic.getLatStr();
		this.createDateTime = DateUtils.getNewUpdateDateString(centuryTopic.getCreateDate(), centuryTopic.getCreateTime());
	}

	/**
	 * 
	 */
	public TopicDataBean() {
		super();
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
	 * @return the themePic1
	 */
	public String getThemePic1() {
		return themePic1;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param themePic1 the themePic1 to set
	 */
	public void setThemePic1(String themePic1) {
		this.themePic1 = themePic1;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the themePic2
	 */
	public String getThemePic2() {
		return themePic2;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param themePic2 the themePic2 to set
	 */
	public void setThemePic2(String themePic2) {
		this.themePic2 = themePic2;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the themePic3
	 */
	public String getThemePic3() {
		return themePic3;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param themePic3 the themePic3 to set
	 */
	public void setThemePic3(String themePic3) {
		this.themePic3 = themePic3;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the themePic4
	 */
	public String getThemePic4() {
		return themePic4;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param themePic4 the themePic4 to set
	 */
	public void setThemePic4(String themePic4) {
		this.themePic4 = themePic4;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the themePic5
	 */
	public String getThemePic5() {
		return themePic5;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param themePic5 the themePic5 to set
	 */
	public void setThemePic5(String themePic5) {
		this.themePic5 = themePic5;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the themePic6
	 */
	public String getThemePic6() {
		return themePic6;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param themePic6 the themePic6 to set
	 */
	public void setThemePic6(String themePic6) {
		this.themePic6 = themePic6;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the themePic7
	 */
	public String getThemePic7() {
		return themePic7;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param themePic7 the themePic7 to set
	 */
	public void setThemePic7(String themePic7) {
		this.themePic7 = themePic7;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the themePic8
	 */
	public String getThemePic8() {
		return themePic8;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param themePic8 the themePic8 to set
	 */
	public void setThemePic8(String themePic8) {
		this.themePic8 = themePic8;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the themePic9
	 */
	public String getThemePic9() {
		return themePic9;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param themePic9 the themePic9 to set
	 */
	public void setThemePic9(String themePic9) {
		this.themePic9 = themePic9;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the praiseNum
	 */
	public Integer getPraiseNum() {
		return praiseNum;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param praiseNum the praiseNum to set
	 */
	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the commentNum
	 */
	public Integer getCommentNum() {
		return commentNum;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param commentNum the commentNum to set
	 */
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the lngStr
	 */
	public String getLngStr() {
		return lngStr;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param lngStr the lngStr to set
	 */
	public void setLngStr(String lngStr) {
		this.lngStr = lngStr;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the latStr
	 */
	public String getLatStr() {
		return latStr;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param latStr the latStr to set
	 */
	public void setLatStr(String latStr) {
		this.latStr = latStr;
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
	
	
}
