package com.sirdc.modules.entity.sys;

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
@Table(name = "t_sys_bank")
@SelectBeforeUpdate
@DynamicInsert(true)
// 动态插入
@DynamicUpdate(true)
// 动态更新
public class SysBank extends StringEntity {

	private static final long serialVersionUID = 1828510281057612954L;
	
	/**
	 * 银行代码
	 */
	private String bankCode;
	/**
	 * 银行名称
	 */
	private String bankName;
	
	private String code_value;
	
	private int enable;

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

	public String getCode_value() {
		return code_value;
	}

	public void setCode_value(String code_value) {
		this.code_value = code_value;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}
	
	
}
