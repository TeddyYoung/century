/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: Newscast.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月6日
 */
package com.century.modules.entity.newscast;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 * 新闻广播表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
@Entity
@Table(name = "t_sjt_newscast")
@SelectBeforeUpdate
@DynamicInsert(true)// 动态插入
@DynamicUpdate(true)// 动态更新
public class CenturyNewscast extends StringEntity {

	private static final long serialVersionUID = 2556371519701982295L;

	private String title;// 标题
	private String content;// 内容
	private String newsType;// 类型
	private String category;//栏目
	private String url;// 链接
	private String image;// 图像
	private Integer orderBy;//排序

	/**
	 * 标题
	 * 
	 * @author: xiaoqin.huang
	 * @return the title
	 */
	@Column(length = 200)
	public String getTitle() {
		return title;
	}

	/**
	 * 标题
	 * 
	 * @author: xiaoqin.huang
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 内容
	 * 
	 * @author: xiaoqin.huang
	 * @return the content
	 */
	@Column(columnDefinition="TEXT")
	public String getContent() {
		return content;
	}

	/**
	 * 内容
	 * 
	 * @author: xiaoqin.huang
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 类型
	 * 
	 * @author: xiaoqin.huang
	 * @return the newsType
	 */
	@Column(length = 20)
	public String getNewsType() {
		return newsType;
	}

	/**
	 * 类型
	 * 
	 * @author: xiaoqin.huang
	 * @param newsType
	 *            the newsType to set
	 */
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	/**
	 * 链接
	 * 
	 * @author: xiaoqin.huang
	 * @return the url
	 */
	@Column(length = 200)
	public String getUrl() {
		return url;
	}

	/**
	 * 链接
	 * 
	 * @author: xiaoqin.huang
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 图像
	 * 
	 * @author: xiaoqin.huang
	 * @return the image
	 */
	@Column(length = 200)
	public String getImage() {
		return image;
	}

	/**
	 * 图像
	 * 
	 * @author: xiaoqin.huang
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * 排序
	 * @author: weihuang.peng
	 * @return the orderBy
	 */
	public Integer getOrderBy() {
		return orderBy;
	}

	
	/**
	 * 排序
	 * @author: weihuang.peng
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
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
