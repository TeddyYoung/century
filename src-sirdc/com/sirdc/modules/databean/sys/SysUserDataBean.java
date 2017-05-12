/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysUserDataBean.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年4月12日
 */
package com.sirdc.modules.databean.sys;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.century.modules.cxf.client.UserInfo;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年4月12日
 */
public class SysUserDataBean {

	private String sysId;//登陆表ID和用户表ID相同
	
	private String name;//中文名称

	private String nickName;//用户昵称
	
	private String birthday;//出生日期

	private String gender;//性别
	
	private String nativeAddr;//籍贯
	
	private String image;//头像	

	@NotEmpty
	private String password;//密码

	private String email;//电子邮箱
	
	@NotEmpty
	@Length(min=11, max=11)
	private String mobile;//手机

	private String deptId;//部门编号
	
	private String roleId;//角色编号 ->小类id
	
	private String username;//用户名
	
	//更新密码的时候需要的数据
	private String oldPassword;//旧密码
	
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
    /**
     * 验证码
     */
    private String validCode;
    /**
     * 邀请码
     */
    private String inviteCode;
    /**
     * 是否店主
     */
    private String isMerchant;
    
    
	/**
	 * 
	 * @author: huiyang.yu
	 * @return the demo
	 */
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

	/**
	 * 默认构造器
	 */
	public SysUserDataBean() {
		super();
	}
	
	/**
	 * 数据同步实体转换
	 * @author: huiyang.yu
	 * @param userInfo
	 */
	public SysUserDataBean(UserInfo userInfo) {
		super();
		this.name = userInfo.getCustName();
		this.nickName = userInfo.getLoginName();
		this.birthday = userInfo.getBirthday();
		this.gender = userInfo.getSex();
		this.nativeAddr = userInfo.getNativeAddr();
		this.password = userInfo.getPassword();
		this.email = userInfo.getMail();
		this.mobile = userInfo.getMobile();
		this.idCard = userInfo.getIdCard();
		this.city = userInfo.getCity();
		this.cultural = userInfo.getCultural();
		this.marital = userInfo.getMarital();
		this.haveChild = userInfo.getHaveChild();
		
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
	 * @return the sysId
	 */
	public String getSysId() {
		return sysId;
	}

	/**
	 * @author: weihuang.peng
	 * @param sysId the sysId to set
	 */
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	/**
	 * @author: weihuang.peng
	 * @return the nickName
	 */
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @author: weihuang.peng
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @author: weihuang.peng
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @author: weihuang.peng
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * @author: weihuang.peng
	 * @return the name
	 */
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
	 * @return the birthday
	 */
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @author: weihuang.peng
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @author: weihuang.peng
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @author: weihuang.peng
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * @author: weihuang.peng
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * @author: weihuang.peng
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @author: weihuang.peng
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getIsMerchant() {
		return isMerchant;
	}

	public void setIsMerchant(String isMerchant) {
		this.isMerchant = isMerchant;
	}
}
