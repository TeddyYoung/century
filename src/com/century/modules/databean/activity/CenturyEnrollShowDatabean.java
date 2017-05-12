/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyBannerShow.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月22日
 */
package com.century.modules.databean.activity;

import javax.persistence.Column;

import com.century.modules.entity.activity.CenturyActivity;

/**
 * 
 * @author: huangcheng.dong
 * @version Revision: 0.0.1
 * @Date: 2015年6月22日
 */
public class CenturyEnrollShowDatabean {
	private CenturyActivity centuryActivity;//活动
	private String sysId;//活动报名表系统ID
	private String activityId;// 活动ID
	private String userId;// 用户ID
	private String state;// 状态
	private boolean receiced;// 领取电子币状态
	private String receicedDate;// 电子币领取日期
	private String receicedTime;// 电子币领取时间
	private String userName;
	
	/**
	 * 活动信息
	 * @author: huangcheng.dong
	 * @return the centuryActivity
	 */
	public CenturyActivity getCenturyActivity() {
		return centuryActivity;
	}

	/**
	 * 活动信息
	 * @author: huangcheng.dong
	 * @param centuryActivity the centuryActivity to set
	 */
	public void setCenturyActivity(CenturyActivity centuryActivity) {
		this.centuryActivity = centuryActivity;
	}
	
	

	/**
	 * 报名表系统ID
	 * @author: huangcheng.dong
	 * @return the sysId
	 */
	public String getSysId() {
		return sysId;
	}

	/**
	 * 报名表系统ID
	 * @author: huangcheng.dong
	 * @param sysId the sysId to set
	 */
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

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
	@Column(length = 50)
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
	 * 用户姓名
	 * @author: huangcheng.dong
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 用户姓名
	 * @author: huangcheng.dong
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * state
	 * 
	 * @author: xiaoqin.huang
	 * @return the state
	 */
	@Column(length = 50)
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
	@Column(length = 50)
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
	@Column(length = 50)
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
