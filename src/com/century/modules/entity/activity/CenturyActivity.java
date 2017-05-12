/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: Activity.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月6日
 */
package com.century.modules.entity.activity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 * 活动表
 * @author: huangcheng.dong
 * @version Revision: 0.0.1
 * @Date: 2015年6月11日
 */
@Entity
@Table(name = "t_sjt_activity")
@SelectBeforeUpdate
@DynamicInsert(true)
// 动态插入
@DynamicUpdate(true)
// 动态更新
public class CenturyActivity extends StringEntity {


	private static final long serialVersionUID = -813128973826457935L;
	
	/**
	 * 打开
	 */
	public static final String ACTIVITY_STATE_OPEN = "1";
	
	/**
	 * 关闭
	 */
	public static final String ACTIVITY_STATE_CLOSE = "2";
	
	
	private String title;// 活动标题
	private String content;// 活动内容
	private String state;// 活动状态
	private String pictureId;// 活动图ID
	private BigDecimal activityCast;// 活动经费
	private BigDecimal reward;// 活动奖励
	private String startDate;// 活动开始日期
	private String startTime;// 活动开始时间
	private String endDate;// 活动结束日期
	private String endTime;// 活动结束时间
	private Integer    orderBy;//排序

	/**
	 * 活动标题
	 * 
	 * @author: xiaoqin.huang
	 * @return the title
	 */

	@Column(length = 200)
	public String getTitle() {
		return title;
	}

	/**
	 * 活动标题
	 * 
	 * @author: xiaoqin.huang
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 活动内容
	 * 
	 * @author: xiaoqin.huang
	 * @return the content
	 */
	@Column(columnDefinition="TEXT")
	public String getContent() {
		return content;
	}

	/**
	 * 活动内容
	 * 
	 * @author: xiaoqin.huang
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 活动状态
	 * 
	 * @author: xiaoqin.huang
	 * @return the state
	 */
	@Column(length = 50)
	public String getState() {
		return state;
	}

	/**
	 * 活动状态
	 * 
	 * @author: xiaoqin.huang
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 活动图ID
	 * 
	 * @author: xiaoqin.huang
	 * @return the pictureId
	 */
	@Column(length = 500)
	public String getPictureId() {
		return pictureId;
	}

	/**
	 * 活动图ID
	 * 
	 * @author: xiaoqin.huang
	 * @param pictureId
	 *            the pictureId to set
	 */
	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	/**
	 *  活动经费
	 * @author: huangcheng.dong
	 * @return
	 */
	public BigDecimal getActivityCast() {
		return activityCast;
	}

	/**
	 *  活动经费
	 * @author: huangcheng.dong
	 * @param activityCast
	 */
	public void setActivityCast(BigDecimal activityCast) {
		this.activityCast = activityCast;
	}

	/**
	 * 活动奖励
	 * 
	 * @author: xiaoqin.huang
	 * @return the reward
	 */
	public BigDecimal getReward() {
		return reward;
	}

	/**
	 * 活动奖励
	 * 
	 * @author: xiaoqin.huang
	 * @param reward
	 *            the reward to set
	 */
	public void setReward(BigDecimal reward) {
		this.reward = reward;
	}

	/**
	 * 活动开始日期
	 * 
	 * @author: xiaoqin.huang
	 * @return the startDate
	 */
	@Column(length = 10)
	public String getStartDate() {
		return startDate;
	}

	/**
	 * 活动开始日期
	 * 
	 * @author: xiaoqin.huang
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * 活动开始时间
	 * 
	 * @author: xiaoqin.huang
	 * @return the startTime
	 */
	@Column(length = 8)
	public String getStartTime() {
		return startTime;
	}

	/**
	 * 活动开始时间
	 * 
	 * @author: xiaoqin.huang
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 活动结束日期
	 * 
	 * @author: xiaoqin.huang
	 * @return the endDate
	 */
	@Column(length = 10)
	public String getEndDate() {
		return endDate;
	}

	/**
	 * 活动结束日期
	 * 
	 * @author: xiaoqin.huang
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 活动结束时间
	 * 
	 * @author: xiaoqin.huang
	 * @return the endTime
	 */
	@Column(length = 8)
	public String getEndTime() {
		return endTime;
	}

	/**
	 * 活动结束时间
	 * 
	 * @author: xiaoqin.huang
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 排序
	 * @author: xiaoqin.huang
	 * @return the orderBy
	 */
	public Integer getOrderBy() {
		return orderBy;
	}

	/**
	 * 排序
	 * @author: xiaoqin.huang
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	
	
	

}
