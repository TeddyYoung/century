package com.century.modules.entity.account;

import java.math.BigDecimal;
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
@Table(name = "t_sjt_account_detail")
@SelectBeforeUpdate
@DynamicInsert(true)
// 动态插入
@DynamicUpdate(true)
// 动态更新
public class CenturyAccountDetail extends StringEntity {

	private static final long serialVersionUID = 1828510281057612954L;
	
	private String userId;//用户Id

	private BigDecimal avaliableAmt;//余额
	
	private BigDecimal freezeAmt;//冻结

	private String accountingType;//账户变动类型（交易方向）	
	
	private String accountId;//
	/**
	 * 交易编号
	 */
	private String txNo;//
	/**
	 * 交易类型
	 */
	private String tradeType;//
	/**
	 * 交易金额
	 */
	private BigDecimal txAmt;//	
	/**
	 * 交易日期
	 */
	private Date txDate;//

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

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getTxNo() {
		return txNo;
	}

	public void setTxNo(String txNo) {
		this.txNo = txNo;
	}


	public BigDecimal getTxAmt() {
		return txAmt;
	}

	public void setTxAmt(BigDecimal txAmt) {
		this.txAmt = txAmt;
	}

	public Date getTxDate() {
		return txDate;
	}

	public void setTxDate(Date txDate) {
		this.txDate = txDate;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getAccountingType() {
		return accountingType;
	}

	public void setAccountingType(String accountingType) {
		this.accountingType = accountingType;
	}
	
	
}
