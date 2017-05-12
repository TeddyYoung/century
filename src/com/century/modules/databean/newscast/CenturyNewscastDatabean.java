/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyNewscastDatabean.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月9日
 */
package com.century.modules.databean.newscast;

import com.sirdc.modules.utils.StringUtils;



/**
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月9日
 */
public class CenturyNewscastDatabean {

	private String sysId;//主键ID
	private String title;// 标题
	private String content;// 内容
	private String newsType;// 类型
	private String url;// 链接
	private String image;// 图像
	private Integer orderBy;//排序
	private String createDate;//创建日期
	private String createTime;//创建时间
	private String category;//栏目
	
	/**
	 * @author: xiaoqin.huang
	 * @return the sysId
	 */
	public String getSysId() {
		return sysId;
	}
	
	/**
	 * @author: xiaoqin.huang
	 * @param sysId the sysId to set
	 */
	public void setSysId(String sysId) {
		this.sysId = StringUtils.defaultString(sysId);
	}
	
	/**
	 * @author: xiaoqin.huang
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @author: xiaoqin.huang
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = StringUtils.defaultString(title);
	}
	
	/**
	 * @author: xiaoqin.huang
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * @author: xiaoqin.huang
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = StringUtils.defaultString(content);
	}
	
	/**
	 * @author: xiaoqin.huang
	 * @return the newsType
	 */
	public String getNewsType() {
		return newsType;
	}
	
	/**
	 * @author: xiaoqin.huang
	 * @param newsType the newsType to set
	 */
	public void setNewsType(String newsType) {
		this.newsType = StringUtils.defaultString(newsType);
	}
	
	/**
	 * @author: xiaoqin.huang
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * @author: xiaoqin.huang
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = StringUtils.defaultString(url);
	}
	
	/**
	 * @author: xiaoqin.huang
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * @author: xiaoqin.huang
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = StringUtils.defaultString(image);
	}
	
	/**
	 * @author: xiaoqin.huang
	 * @return the order
	 */
	public Integer getOrderBy() {
		return orderBy;
	}
	
	/**
	 * @author: xiaoqin.huang
	 * @param order the order to set
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
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @author: xiaoqin.huang
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = StringUtils.defaultString(createTime);
	}

	/**
	 * 栏目
	 * @author: xiaoqin.huang
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 栏目
	 * @author: xiaoqin.huang
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
	
	
}
