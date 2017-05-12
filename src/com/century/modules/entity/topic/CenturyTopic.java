/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyTopic.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月29日
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
 * 话题表
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年6月29日
 */
@Entity
@Table(name = "t_sjt_topic")
@SelectBeforeUpdate
@DynamicInsert(true)
// 动态插入
@DynamicUpdate(true)
// 动态更新
public class CenturyTopic extends StringEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6828716746866092526L;
	
	/**
	 * 1正常
	 */
	public static final String STATE_NORMAL = "1";
	
	private String userId;	//作者id
	
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
	
	private String state;//状态
	
	/**
	 * 作者id
	 * @author: huiyang.yu
	 * @return the userId
	 */
	@Column(length = 50)
	public String getUserId() {
		return userId;
	}

	/**
	 * 作者id
	 * @author: huiyang.yu
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 话题内容
	 * @author: huiyang.yu
	 * @return the content
	 */
	@Column(columnDefinition="TEXT")
	public String getContent() {
		return content;
	}

	/**
	 * 话题内容
	 * @author: huiyang.yu
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 主题图片1
	 * @author: huiyang.yu
	 * @return the themePic1
	 */
	@Column(length = 50)
	public String getThemePic1() {
		return themePic1;
	}

	/**
	 * 主题图片1
	 * @author: huiyang.yu
	 * @param themePic1 the themePic1 to set
	 */
	public void setThemePic1(String themePic1) {
		this.themePic1 = themePic1;
	}

	/**
	 * 主题图片2
	 * @author: huiyang.yu
	 * @return the themePic2
	 */
	@Column(length = 50)
	public String getThemePic2() {
		return themePic2;
	}

	/**
	 * 主题图片2
	 * @author: huiyang.yu
	 * @param themePic2 the themePic2 to set
	 */
	public void setThemePic2(String themePic2) {
		this.themePic2 = themePic2;
	}

	/**
	 * 主题图片3
	 * @author: huiyang.yu
	 * @return the themePic3
	 */
	@Column(length = 50)
	public String getThemePic3() {
		return themePic3;
	}

	/**
	 * 主题图片3
	 * @author: huiyang.yu
	 * @param themePic3 the themePic3 to set
	 */
	public void setThemePic3(String themePic3) {
		this.themePic3 = themePic3;
	}

	/**
	 * 主题图片4
	 * @author: huiyang.yu
	 * @return the themePic4
	 */
	@Column(length = 50)
	public String getThemePic4() {
		return themePic4;
	}

	/**
	 * 主题图片4
	 * @author: huiyang.yu
	 * @param themePic4 the themePic4 to set
	 */
	public void setThemePic4(String themePic4) {
		this.themePic4 = themePic4;
	}

	/**
	 * 主题图片5
	 * @author: huiyang.yu
	 * @return the themePic5
	 */
	@Column(length = 50)
	public String getThemePic5() {
		return themePic5;
	}

	/**
	 * 主题图片5
	 * @author: huiyang.yu
	 * @param themePic5 the themePic5 to set
	 */
	public void setThemePic5(String themePic5) {
		this.themePic5 = themePic5;
	}

	/**
	 * 主题图片6
	 * @author: huiyang.yu
	 * @return the themePic6
	 */
	@Column(length = 50)
	public String getThemePic6() {
		return themePic6;
	}

	/**
	 * 主题图片6
	 * @author: huiyang.yu
	 * @param themePic6 the themePic6 to set
	 */
	public void setThemePic6(String themePic6) {
		this.themePic6 = themePic6;
	}

	/**
	 * 主题图片7
	 * @author: huiyang.yu
	 * @return the themePic7
	 */
	@Column(length = 50)
	public String getThemePic7() {
		return themePic7;
	}

	/**
	 * 主题图片7
	 * @author: huiyang.yu
	 * @param themePic7 the themePic7 to set
	 */
	public void setThemePic7(String themePic7) {
		this.themePic7 = themePic7;
	}

	/**
	 * 主题图片8
	 * @author: huiyang.yu
	 * @return the themePic8
	 */
	@Column(length = 50)
	public String getThemePic8() {
		return themePic8;
	}

	/**
	 * 主题图片8
	 * @author: huiyang.yu
	 * @param themePic8 the themePic8 to set
	 */
	public void setThemePic8(String themePic8) {
		this.themePic8 = themePic8;
	}

	/**
	 * 主题图片9
	 * @author: huiyang.yu
	 * @return the themePic9
	 */
	@Column(length = 50)
	public String getThemePic9() {
		return themePic9;
	}

	/**
	 * 主题图片9
	 * @author: huiyang.yu
	 * @param themePic9 the themePic9 to set
	 */
	public void setThemePic9(String themePic9) {
		this.themePic9 = themePic9;
	}

	/**
	 * 点赞数
	 * @author: huiyang.yu
	 * @return the praiseNum
	 */
	public Integer getPraiseNum() {
		return praiseNum;
	}

	/**
	 * 点赞数
	 * @author: huiyang.yu
	 * @param praiseNum the praiseNum to set
	 */
	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}

	/**
	 * 评论数
	 * @author: huiyang.yu
	 * @return the commentNum
	 */
	public Integer getCommentNum() {
		return commentNum;
	}

	/**
	 * 评论数
	 * @author: huiyang.yu
	 * @param commentNum the commentNum to set
	 */
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	/**
	 * 经度字符串
	 * @author: huiyang.yu
	 * @return the lngStr
	 */
	public String getLngStr() {
		return lngStr;
	}

	/**
	 * 经度字符串
	 * @author: huiyang.yu
	 * @param lngStr the lngStr to set
	 */
	public void setLngStr(String lngStr) {
		this.lngStr = lngStr;
	}

	/**
	 * 纬度字符串
	 * @author: huiyang.yu
	 * @return the latStr
	 */
	public String getLatStr() {
		return latStr;
	}

	/**
	 * 纬度字符串
	 * @author: huiyang.yu
	 * @param latStr the latStr to set
	 */
	public void setLatStr(String latStr) {
		this.latStr = latStr;
	}

	/**
	 * 状态
	 * @author: huiyang.yu
	 * @return the state
	 */
	@Column(length = 50)
	public String getState() {
		return state;
	}

	/**
	 * 状态
	 * @author: huiyang.yu
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	

	
}
