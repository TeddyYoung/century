/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysUser.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月19日
 */
package com.sirdc.modules.entity.sys;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 */
@Entity
@Table(name = "t_sys_user_relate")
@DynamicUpdate//动态更新
@DynamicInsert//动态插入
public class SysUserRelate extends StringEntity {

	private static final long serialVersionUID = 2011718216921828248L;

    private String cloudUserId;
    
    private String userId;

	public String getCloudUserId() {
		return cloudUserId;
	}

	public void setCloudUserId(String cloudUserId) {
		this.cloudUserId = cloudUserId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
