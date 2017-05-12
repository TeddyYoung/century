package com.century.modules.cxf.client;


import java.io.Serializable;

import com.sirdc.modules.databean.sys.SysUserDataBean;
	  
@SuppressWarnings("serial")  
public class UserInfo implements Serializable {  
  
	/**
	 * 登陆名
	 */
    private String loginName;  
    /**
     * 真实名称
     */
    private String custName;  
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 性别
     */
    private String sex;  
    /**
     * 年龄
     */
    private String age;  
    /**
     * 身份证号码
     */
    private String idCard;  
    /**
     * 户籍地
     */
    private String nativeAddr;  
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
    /**
     * 手机
     */
    private String mobile;  
    /**
     * 邮箱
     */
    private String mail;  
    /**
     * 密码（加密）
     */
    private String password;  
    /**
     * 操作方式  注册：1 修改用户信息:2 实名认证:3 修改密码：4
     */
    private String operate;
      
    public UserInfo() {  
        super();  
    }
    
    
	/**
	 *  数据同步实体转换
	 * @author: huiyang.yu
	 * @param datebean
	 */
	public UserInfo(SysUserDataBean databean) {
		super();
		this.loginName = databean.getMobile();
		this.custName = databean.getName();
		this.birthday = databean.getBirthday();
		this.sex = databean.getGender();
		this.idCard = databean.getIdCard();
		this.nativeAddr = databean.getNativeAddr();
		this.city = databean.getCity();
		this.cultural = databean.getCultural();
		this.marital = databean.getMarital();
		this.haveChild = databean.getHaveChild();
		this.mobile = databean.getMobile();
		this.mail = databean.getEmail();
		this.password = databean.getPassword();
	}


	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getNativeAddr() {
		return nativeAddr;
	}

	public void setNativeAddr(String nativeAddr) {
		this.nativeAddr = nativeAddr;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}  
  
      
}
