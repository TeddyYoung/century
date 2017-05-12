/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: Article.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月3日
 */
package com.sirdc.modules.entity.cms;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.sirdc.modules.core.entity.StringEntity;


/**
 * 文章表
 * @author: huangcheng.dong
 * @version Revision: 0.0.1
 * @Date: 2015年6月3日
 */
@Entity
@Table(name = "t_cms_article")
@DynamicUpdate//动态更新
@DynamicInsert//动态插入
public class Article extends StringEntity{

	private static final long serialVersionUID = 1209005788225082272L;
	
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
