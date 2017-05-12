/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: ShopDataBean.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月23日
 */
package com.century.modules.databean.merchant;

import java.io.Serializable;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年9月23日
 */
public class ShopDataBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5475903932252127073L;
	
	private String merchantId;
	
	private String name;//商户名称
	
	private String image;//头像
	
	private String memo;
	
	private String city;
	
	private String logo;


	/**
	 * 
	 * @author: huiyang.yu
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}


	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
}
