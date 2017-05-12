/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SmsModel.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年5月28日
 */
package com.century.modules.entity.sms;

import java.io.Serializable;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年5月28日
 */
public class SmsModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6812636846670778158L;
	
	private String phone;//手机号
	
	private String verifyCode;//验证码
	
	private String timestamp;//时间戳
	
	private Long createTime;

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the verifyCode
	 */
	public String getVerifyCode() {
		return verifyCode;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param verifyCode the verifyCode to set
	 */
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	
	
	
}
