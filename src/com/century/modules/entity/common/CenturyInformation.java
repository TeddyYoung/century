/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: Information.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月7日
 */
package com.century.modules.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 *文章信息表 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月7日
 */
@Entity
@Table(name = "t_sjt_information")
@SelectBeforeUpdate
@DynamicInsert(true)// 动态插入
@DynamicUpdate(true)// 动态更新
public class CenturyInformation extends StringEntity {

	private static final long serialVersionUID = -6709395192267583318L;

	private String content;// 活动内容

	/**
	 * 活动内容
	 * @author: xiaoqin.huang
	 * @return the content
	 */
	@Column(columnDefinition ="TEXT")
	public String getContent() {
		return content;
	}

	/**
	 * 活动内容
	 * 
	 * @author: xiaoqin.huang
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}