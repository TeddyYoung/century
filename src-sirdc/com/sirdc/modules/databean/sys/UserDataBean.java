/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: friendDatabean.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月10日
 */
package com.sirdc.modules.databean.sys;

import java.io.Serializable;

/**
 * 2.1.	好友查找
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年6月10日
 */
public class UserDataBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2754996784318206633L;
	
	private String sysId;//用户系统Id
	
	private String nickName;//用户昵称
	
	private String image;//头像
	
	/**
	 * 用户系统Id
	 * @author: huiyang.yu
	 * @return the sysId
	 */
	public String getSysId() {
		return sysId;
	}

	/**
	 * 用户系统Id
	 * @author: huiyang.yu
	 * @param sysId the sysId to set
	 */
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	/**
	 * 用户昵称
	 * @author: huiyang.yu
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * 用户昵称
	 * @author: huiyang.yu
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * 头像
	 * @author: huiyang.yu
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * 头像
	 * @author: huiyang.yu
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	

}
