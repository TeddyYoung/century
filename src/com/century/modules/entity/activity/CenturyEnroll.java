/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: Enroll.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月6日
 */
package com.century.modules.entity.activity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 * 活动报名表
 * @author: huangcheng.dong
 * @version Revision: 0.0.1
 * @Date: 2015年6月11日
 */
@Entity
@Table(name = "t_sjt_enroll")
@SelectBeforeUpdate
@DynamicInsert(true)// 动态插入
@DynamicUpdate(true)// 动态更新
public class CenturyEnroll extends StringEntity {

	private static final long serialVersionUID = -1172911648451403333L;

	/**
	 * 报名
	 */
	public static final String ENROLL_STATE_REGISTER = "1";
	
	/**
	 * 签到
	 */
	public static final String ENROLL_STATE_SIGN = "2";
	
	
	private String activityId;// 活动ID
	private String userId;// 用户ID
	private String state;// 状态
	private boolean receiced;// 领取电子币状态
	private String receicedDate;// 电子币领取日期
	private String receicedTime;// 电子币领取时间

	


	/**
	 * 活动ID
	 * @author: huangcheng.dong
	 * @return
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * 活动ID
	 * @author: huangcheng.dong
	 * @param activityId
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
