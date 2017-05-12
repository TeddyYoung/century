package com.century.modules.entity.account;

import java.math.BigDecimal;

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
@Table(name = "t_sjt_account")
@SelectBeforeUpdate
@DynamicInsert(true)
// 动态插入
@DynamicUpdate(true)
// 动态更新
public class CenturyAccount extends StringEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1828510281057612954L;
	
	private String userId;//用户Id

	private BigDecimal avaliableAmt;//余额
	
	private BigDecimal freezeAmt;//冻结

	private String type;//账户类型	

	/**
	 * 用户Id
	 * @author: huiyang.yu
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 用户Id
	 * @author: huiyang.yu
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


	/**
	 * 账户类型
	 * @author: huiyang.yu
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 账户类型
	 * @author: huiyang.yu
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAvaliableAmt() {
		return avaliableAmt;
	}

	public void setAvaliableAmt(BigDecimal avaliableAmt) {
		this.avaliableAmt = avaliableAmt;
	}

	public BigDecimal getFreezeAmt() {
		return freezeAmt;
	}

	public void setFreezeAmt(BigDecimal freezeAmt) {
		this.freezeAmt = freezeAmt;
	}
	
	
}
