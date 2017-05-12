/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: NewscastFilter.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月6日
 */
package com.century.modules.filter.newscast;

import com.sirdc.modules.core.filter.BaseFilter;

/**
 * 新闻广播表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
public class CenturyNewscastFilter extends BaseFilter {

	private String title;// 标题
	private String content;// 内容
	private String newsType;// 类型
	private String url;// 链接
	private String image;// 图像

	/**
	 * 标题
	 * 
	 * @author: xiaoqin.huang
	 * @return the title
	 */
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

}
