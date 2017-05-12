/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyHistory.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月21日
 */
package com.century.modules.entity.merchant;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 * 帐户流水表
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年9月21日
 */
@Entity
@Table(name = "t_sjt_history")
@SelectBeforeUpdate
@DynamicInsert(true)
// 动态插入
@DynamicUpdate(true)
// 动态更新
public class CenturyHistory extends StringEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2810660873228658116L;
	
	/**
	 * 1.充值
	 */
	public static final String OPERATION_TYPE_RECHARGE  = "充值";
	
	/**
	 * 2.转账
	 */
	public static final String OPERATION_TYPE_TRANSFER   = "转账";
	
	/**
	 * 3.消费
	 */
	public static final String OPERATION_TYPE_CONSUME= "消费";
	
	/**
	 * 3.用户购买
	 */
	public static final String OPERATION_TYPE_USER_BUY= "用户购买";
	
	
	private String userId;//用户ID

	private BigDecimal amount;//金额

	private String orderId;//交易编号

	private String operationType;//操作类型

	/**
	 * 用户ID
	 * @author: huiyang.yu
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 用户ID
	 * @author: huiyang.yu
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 金额
	 * @author: huiyang.yu
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 金额
	 * @author: huiyang.yu
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 交易编号
	 * @author: huiyang.yu
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 交易编号
	 * @author: huiyang.yu
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * 操作类型
	 * @author: huiyang.yu
	 * @return the operationType
	 */
	public String getOperationType() {
		return operationType;
	}

	/**
	 * 操作类型
	 * @author: huiyang.yu
	 * @param operationType the operationType to set
	 */
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	
	
}
