/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysUser.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月19日
 */
package com.sirdc.modules.entity.sys;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 * 用户表
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年1月19日
 */
@Entity
@Table(name = "t_sys_user")
@DynamicUpdate//动态更新
@DynamicInsert//动态插入
public class SysUser extends StringEntity {

	private static final long serialVersionUID = 2011718216921828248L;

	private String name;//中文名称
	
	private String nickName;//用户昵称
	
	private String birthday;//出生日期
	
	private String gender;//性别
	
	private String nativeAddr;//籍贯
	
	private String image;//头像
	
	private String state;//帐号状态
	
	private String clientId;
	
	private boolean superAdmin;
	
	private String lngStr;//经度字符串
	
	private String latStr;//纬度度字符串
	
	private BigDecimal avaliableMoney;//可用余额
	
    private String idCard;  //身份证号码
    
    /**
     * 居住城市
     */
    private String city;  
    /**
     * 文化程度（学历）
     */
    private String cultural;  
    /**
     * 婚姻状况
     */
    private String marital;  
    /**
     * 有无子女
     */
    private String haveChild; 
    
    private String demo;
    
    private String isMerchant;
    
    private String cloudUserId;
    
    private String inviteCode;
    
    private String mobile;
    
    private String inviteId;
	
	/**
	 * 
	 * @author: huiyang.yu
	 * @return the demo
	 */
    @Column(length = 1000)
	public String getDemo() {
		return demo;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param demo the demo to set
	 */
	public void setDemo(String demo) {
		this.demo = demo;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCultural() {
		return cultural;
	}

	public void setCultural(String cultural) {
		this.cultural = cultural;
	}

	public String getMarital() {
		return marital;
	}

	public void setMarital(String marital) {
		this.marital = marital;
	}

	public String getHaveChild() {
		return haveChild;
	}

	public void setHaveChild(String haveChild) {
		this.haveChild = haveChild;
	}

	/**
	 * @author: weihuang.peng
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @author: weihuang.peng
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @author: weihuang.peng
	 * @return the name
	 */
	@Column(length=50)
	public String getName() {
		return name;
	}

	/**
	 * @author: weihuang.peng
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @author: weihuang.peng
	 * @return the nickName
	 */
	@Column(length=50)
	public String getNickName() {
		return nickName;
	}

	/**
	 * @author: weihuang.peng
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @author: weihuang.peng
	 * @return the birthday
	 */
	@Column(length=10)
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @author: weihuang.peng
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @author: weihuang.peng
	 * @return the gender
	 */
	@Column(length=50)
	public String getGender() {
		return gender;
	}

	/**
	 * @author: weihuang.peng
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @author: weihuang.peng
	 * @return the nativeAddr
	 */
	@Column(length=50)
	public String getNativeAddr() {
		return nativeAddr;
	}

	/**
	 * @author: weihuang.peng
	 * @param nativeAddr the nativeAddr to set
	 */
	public void setNativeAddr(String nativeAddr) {
		this.nativeAddr = nativeAddr;
	}

	/**
	 * @author: weihuang.peng
	 * @return the image
	 */
	@Column(length=50)
	public String getImage() {
		return image;
	}

	/**
	 * @author: weihuang.peng
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @author: weihuang.peng
	 * @return the state
	 */
	@Column(length=50)
	public String getState() {
		return state;
	}

	/**
	 * @author: weihuang.peng
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @author: weihuang.peng
	 * @return the superAdmin
	 */
	public boolean isSuperAdmin() {
		return superAdmin;
	}

	/**
	 * @author: weihuang.peng
	 * @param superAdmin the superAdmin to set
	 */
	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}

	/**
	 * 经度字符串
	 * @author: huiyang.yu
	 * @return the lngStr
	 */
	public String getLngStr() {
		return lngStr;
	}

	/**
	 * 经度字符串
	 * @author: huiyang.yu
	 * @param lngStr the lngStr to set
	 */
	public void setLngStr(String lngStr) {
		this.lngStr = lngStr;
	}

	/**
	 * 纬度度字符串
	 * @author: huiyang.yu
	 * @return the latStr
	 */
	public String getLatStr() {
		return latStr;
	}

	/**
	 * 纬度度字符串
	 * @author: huiyang.yu
	 * @param latStr the latStr to set
	 */
	public void setLatStr(String latStr) {
		this.latStr = latStr;
	}

	/**
	 * 可用余额
	 * @author: huiyang.yu
	 * @return the avaliableMoney
	 */
	public BigDecimal getAvaliableMoney() {
		return avaliableMoney;
	}

	/**
	 * 可用余额
	 * @author: huiyang.yu
	 * @param avaliableMoney the avaliableMoney to set
	 */
	public void setAvaliableMoney(BigDecimal avaliableMoney) {
		this.avaliableMoney = avaliableMoney;
	}

	public String getIsMerchant() {
		return isMerchant;
	}

	public void setIsMerchant(String isMerchant) {
		this.isMerchant = isMerchant;
	}

	public String getCloudUserId() {
		return cloudUserId;
	}

	public void setCloudUserId(String cloudUserId) {
		this.cloudUserId = cloudUserId;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getInviteId() {
		return inviteId;
	}

	public void setInviteId(String inviteId) {
		this.inviteId = inviteId;
	}
	
	
}
