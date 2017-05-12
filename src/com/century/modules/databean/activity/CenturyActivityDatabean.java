/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyNewscastDatabean.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月9日
 */
package com.century.modules.databean.activity;

import java.math.BigDecimal;

import com.sirdc.modules.utils.StringUtils;


/**
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月9日
 */
public class CenturyActivityDatabean {

	private String sysId;//主键ID
	private String title;// 标题
	private String content;//活动内容
	private String state;// 活动状态
	private String pictureId;//活动图ID
	private BigDecimal activityCast;// 活动经费
	private BigDecimal reward;// 活动奖励
	private String createDate;//活动创建日期
	private String cteateTime;//活动创建时间
	private String startDate;// 活动开始日期
	private String startTime;// 活动开始时间
	private String endDate;// 活动结束日期
	private String endTime;// 活动结束时间
	private Integer orderBy;//排序
	/**
	 * @author: huangcheng.dong
	 * @return the sysId
	 */
	public String getSysId() {
		return sysId;
	}
	/**
	 * @author: huangcheng.dong
	 * @param sysId the sysId to set
	 */
	public void setSysId(String sysId) {
		this.sysId = StringUtils.defaultString(sysId);
	}
	/**
	 * @author: huangcheng.dong
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @author: huangcheng.dong
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = StringUtils.defaultString(title);
	}
	
	
	/**
	 * @author: huangcheng.dong
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @author: huangcheng.dong
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = StringUtils.defaultString(content);
	}
	/**
	 * @author: huangcheng.dong
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @author: huangcheng.dong
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = StringUtils.defaultString(state);
	}
	/**
	 * @author: huangcheng.dong
	 * @return the pictureId
	 */
	public String getPictureId() {
		return pictureId;
	}
	/**
	 * @author: huangcheng.dong
	 * @param pictureId the pictureId to set
	 */
	public void setPictureId(String pictureId) {
		this.pictureId = StringUtils.defaultString(pictureId);
	}


	/**
	 * @author: huangcheng.dong
	 * @return the cast
	 */
	public BigDecimal getActivityCast() {
		return activityCast;
	}
	/**
	 * @author: huangcheng.dong
	 * @param cast the cast to set
	 */
	public void setActivityCast(BigDecimal activityCast) {
		this.activityCast = activityCast;
		if (this.activityCast == null) {
			this.activityCast = new BigDecimal(0);
		}
	}
	/**
	 * @author: huangcheng.dong
	 * @return the reward
	 */
	public BigDecimal getReward() {
		return reward;
	}
	/**
	 * @author: huangcheng.dong
	 * @param reward the reward to set
	 */
	public void setReward(BigDecimal reward) {		
		this.reward = reward;
		if (this.reward == null) {
			this.reward = new BigDecimal(0);
		}
	}
	/**
	 * @author: huangcheng.dong
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @author: huangcheng.dong
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = StringUtils.defaultString(startDate);
	}
	/**
	 * @author: huangcheng.dong
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @author: huangcheng.dong
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = StringUtils.defaultString(startTime);
	}
	/**
	 * @author: huangcheng.dong
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @author: huangcheng.dong
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = StringUtils.defaultString(endDate);
	}
	/**
	 * @author: huangcheng.dong
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @author: huangcheng.dong
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = StringUtils.defaultString(endTime);
	}
	/**
	 * @author: xiaoqin.huang
	 * @return the orderBy
	 */
	public Integer getOrderBy() {
		return orderBy;
	}
	/**
	 * @author: xiaoqin.huang
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
		if(this.orderBy ==null){
			this.orderBy =1;
		}
	}
	/**
	 * @author: xiaoqin.huang
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @author: xiaoqin.huang
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = StringUtils.defaultString(createDate);
	}
	/**
	 * @author: xiaoqin.huang
	 * @return the cteateTime
	 */
	public String getCteateTime() {
		return cteateTime;
	}
	/**
	 * @author: xiaoqin.huang
	 * @param cteateTime the cteateTime to set
	 */
	public void setCteateTime(String cteateTime) {
		this.cteateTime = StringUtils.defaultString(cteateTime);
	}

	

}
