/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyBannerShow.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月22日
 */
package com.century.modules.databean.activity;

import com.century.modules.entity.activity.CenturyActivity;

/**
 * 活动滚屏图片控制层
 * @author: huangcheng.dong
 * @version Revision: 0.0.1
 * @Date: 2015年6月22日
 */
public class CenturyBannerShowDatabean {
	private CenturyActivity centuryActivity;//活动
	private String sysId;//滚屏图片系统ID
	private String pictureId;// 图片ID
	private String state;// 状态
	private String picType;// 类别
	private String activityId;// 活动ID
	private String url;//链接
	private Integer orderBy;//排序
	
	
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
	 * 系统ID
	 * @author: huangcheng.dong
	 * @return the sysId
	 */
	public String getSysId() {
		return sysId;
	}

	/**
	 * 系统ID
	 * @author: huangcheng.dong
	 * @param sysId the sysId to set
	 */
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

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
	 * 类别
	 * 
	 * @author: xiaoqin.huang
	 * @return the plcType
	 */
	public String getPicType() {
		return picType;
	}

	/**
	 * 类别
	 * 
	 * @author: xiaoqin.huang
	 * @param plcType
	 *            the plcType to set
	 */
	public void setPicType(String picType) {
		this.picType = picType;
	}


	/**
	 * @author: xiaoqin.huang
	 * @return the activityId
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * @author: xiaoqin.huang
	 * @param activityId the activityId to set
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
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
	 * 链接
	 * @author: xiaoqin.huang
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 链接
	 * @author: xiaoqin.huang
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	
}
