/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: Banner.java
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
 * 滚屏图片表
 * @author: huangcheng.dong
 * @version Revision: 0.0.1
 * @Date: 2015年6月11日
 */
@Entity
@Table(name = "t_sjt_banner")
@SelectBeforeUpdate
@DynamicInsert(true)// 动态插入
@DynamicUpdate(true)// 动态更新
public class CenturyBanner extends StringEntity {

	private static final long serialVersionUID = 6743299747547688022L;
	private String pictureId;// 图片ID
	private String state;// 状态
	private String picType;// 类别
	private String activityId;// 活动ID
	private String url;//链接
	private Integer orderBy;//排序
	
	private String title;
	/**
	 * 备注
	 */
	private String memo;
	
	private String image;


	/**
	 * 图片ID
	 * 
	 * @author: xiaoqin.huang
	 * @return the pictureId
	 */
	@Column(length = 20)
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
	@Column(length = 20)
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	
}
