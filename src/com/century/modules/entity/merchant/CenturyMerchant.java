/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysUser.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月19日
 */
package com.century.modules.entity.merchant;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 */
@Entity
@Table(name = "t_sjt_merchant")
@DynamicUpdate//动态更新
@DynamicInsert//动态插入
public class CenturyMerchant extends StringEntity {

	private static final long serialVersionUID = 2011718216921828248L;

	private String merchantName;//中文名称
	
	private String shortName;
	
	private String telephone;
	
	private String telephoneOne;
	
	private String image;
	
	private String address;
	
	private String provinceId;
	
	private String cityId;
	
	private String streetAddress;
	
	private String nationalityId;
	
	private String logo;
	
    private String discount; 
    
    private String mobile; 
    
    /**
     * 居住城市
     */
    private String city;

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelephoneOne() {
		return telephoneOne;
	}

	public void setTelephoneOne(String telephoneOne) {
		this.telephoneOne = telephoneOne;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(String nationalityId) {
		this.nationalityId = nationalityId;
	}


	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}  
	

	
	
}
