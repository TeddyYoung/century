/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: BannerFilter.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月6日
 */
package com.century.modules.filter.activity;

import com.sirdc.modules.core.filter.BaseFilter;

/**
 * 滚屏图片表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
public class CenturyBannerFilter extends BaseFilter {

	private String pictureId;// 图片ID
	private String state;// 删除标记
	private String picType;// 类别
	private String activityId;// 活动ID
	
	private String title;

	/**
	 * 图片ID
	 * 
	 * @author: xiaoqin.huang
	 * @return the pictureId
	 */
	public String getPictureId() {
		return pictureId;
	}

	/**
	 * 图片ID
	 * 
	 * @author: xiaoqin.huang
	 * @param pictureId
	 *            the pictureId to set
	 */
	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	
	/**
	 * 状态
	 * @author: xiaoqin.huang
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * 状态
	 * @author: xiaoqin.huang
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * 类别
	 * @author: xiaoqin.huang
	 * @return the picType
	 */
	public String getPicType() {
		return picType;
	}

	/**
	 * 类别
	 * @author: xiaoqin.huang
	 * @param picType the picType to set
	 */
	public void setPicType(String picType) {
		this.picType = picType;
	}

	/**
	 * 活动ID
	 * @author: xiaoqin.huang
	 * @return the activityId
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * 活动ID
	 * @author: xiaoqin.huang
	 * @param activityId the activityId to set
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
