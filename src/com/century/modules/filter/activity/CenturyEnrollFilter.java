/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: EnrollFilter.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月6日
 */
package com.century.modules.filter.activity;

import com.sirdc.modules.core.filter.BaseFilter;

/**
 * 活动报名表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
public class CenturyEnrollFilter extends BaseFilter {

	private String activityId;// 活动ID
	private String userId;// 用户ID
	private String state;// 状态
	private boolean receiced;// 领取电子币状态
	private String receicedDate;// 电子币领取日期
	private String receicedTime;// 电子币领取时间

	

	/**
	 * 活动ID
	 * @author: xiaoqin.huang
	 * @return the activtyId
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * 活动ID
	 * @author: xiaoqin.huang
	 * @param activtyId the activtyId to set
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/**
	 * 用户ID
	 * 
	 * @author: xiaoqin.huang
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 用户ID
	 * 
	 * @author: xiaoqin.huang
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * state
	 * 
	 * @author: xiaoqin.huang
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * state
	 * 
	 * @author: xiaoqin.huang
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 领取电子币状态
	 * 
	 * @author: xiaoqin.huang
	 * @return the receiced
	 */
	public boolean getReceiced() {
		return receiced;
	}

	/**
	 * 领取电子币状态
	 * 
	 * @author: xiaoqin.huang
	 * @param receiced
	 *            the receiced to set
	 */
	public void setReceiced(boolean receiced) {
		this.receiced = receiced;
	}

	/**
	 * 电子币领取日期
	 * 
	 * @author: xiaoqin.huang
	 * @return the receicedDate
	 */
	public String getReceicedDate() {
		return receicedDate;
	}

	/**
	 * 电子币领取日期
	 * 
	 * @author: xiaoqin.huang
	 * @param receicedDate
	 *            the receicedDate to set
	 */
	public void setReceicedDate(String receicedDate) {
		this.receicedDate = receicedDate;
	}

	/**
	 * 电子币领取时间
	 * 
	 * @author: xiaoqin.huang
	 * @return the receicedTime
	 */
	public String getReceicedTime() {
		return receicedTime;
	}

	/**
	 * 电子币领取时间
	 * 
	 * @author: xiaoqin.huang
	 * @param receicedTime
	 *            the receicedTime to set
	 */
	public void setReceicedTime(String receicedTime) {
		this.receicedTime = receicedTime;
	}

}
