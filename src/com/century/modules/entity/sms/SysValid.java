/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysValid.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	29 Jul,2015
 */
package com.century.modules.entity.sms;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.sirdc.modules.core.entity.LongEntity;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 29 Jul,2015
 */
@Entity
@Table(name="t_sms_valid")
public class SysValid extends LongEntity {

	private static final long serialVersionUID = -6424950156695489035L;

	private String mobile;
	
	private String code;
	
	private Long currentTimeMillis;
	
	private boolean enable = true;//是否已经读过了
	
	/**
	 * @author: weihuang.peng
	 * @return the enable
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * @author: weihuang.peng
	 * @param enable the enable to set
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 * @author: weihuang.peng
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @author: weihuang.peng
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @author: weihuang.peng
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @author: weihuang.peng
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @author: weihuang.peng
	 * @return the currentTimeMillis
	 */
	public Long getCurrentTimeMillis() {
		return currentTimeMillis;
	}

	/**
	 * @author: weihuang.peng
	 * @param currentTimeMillis the currentTimeMillis to set
	 */
	public void setCurrentTimeMillis(Long currentTimeMillis) {
		this.currentTimeMillis = currentTimeMillis;
	}
}