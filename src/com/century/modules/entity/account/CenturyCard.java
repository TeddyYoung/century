package com.century.modules.entity.account;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 * 
 */
@Entity
@Table(name = "t_sjt_card")
@SelectBeforeUpdate
@DynamicInsert(true)
// 动态插入
@DynamicUpdate(true)
// 动态更新
public class CenturyCard extends StringEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1828510281057612954L;
	
	private String userId;//用户Id
	/**
	 * 银行代码
	 */
	private String bankCode;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 绑定时间
	 */
	private Date bindTime;
	/**
	 * 解绑时间
	 */
	private Date unbindTime;
	/**
	 * 银行卡类型
	 */
	private String cardType;
	/**
	 * 绑定状态
	 */
	private String bindState;
	/**
	 * 有效期
	 */
	private String validate;
	private String cvv2;
	private String phone;
	
	private String bankCardNo;
	
	private String tranceNum;
	
	private String transDate;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Date getBindTime() {
		return bindTime;
	}
	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}
	public Date getUnbindTime() {
		return unbindTime;
	}
	public void setUnbindTime(Date unbindTime) {
		this.unbindTime = unbindTime;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getBindState() {
		return bindState;
	}
	public void setBindState(String bindState) {
		this.bindState = bindState;
	}
	public String getValidate() {
		return validate;
	}
	public void setValidate(String validate) {
		this.validate = validate;
	}
	public String getCvv2() {
		return cvv2;
	}
	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getTranceNum() {
		return tranceNum;
	}
	public void setTranceNum(String tranceNum) {
		this.tranceNum = tranceNum;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
}
