/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLogFilter.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年5月10日
 */
package com.sirdc.modules.filter.cms;

import com.sirdc.modules.core.filter.BaseFilter;


/**
 * 
 * @author: huangcheng.dong
 * @version Revision: 0.0.1
 * @Date: 2015年6月3日
 */
public class ArticleFilter extends BaseFilter {

	private String title;      //标题
	
	private String category;   //分类
	
	private String content;	   //内容
	
	/**
	 * 标题
	 * @author: huangcheng.dong
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 标题
	 * @author: huangcheng.dong
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 分类
	 * @author: huangcheng.dong
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * 分类
	 * @author: huangcheng.dong
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * 内容
	 * @author: huangcheng.dong
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 内容
	 * @author: huangcheng.dong
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
}