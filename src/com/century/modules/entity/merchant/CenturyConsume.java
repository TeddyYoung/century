/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyConsume.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年7月8日
 */
package com.century.modules.entity.merchant;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 * 消费记录表
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年7月8日
 */
@Entity
@Table(name = "t_sjt_consume")
@SelectBeforeUpdate
@DynamicInsert(true)
// 动态插入
@DynamicUpdate(true)
// 动态更新
public class CenturyConsume extends StringEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1916731504772657093L;
	
	/**
	 * 1:在线消费
	 */
	public static final String CONSUME_TYPE_ONLINE = "1";
	
	
	/**
	 * 未付款
	 */
	public static final String STATE_PLACE_ORDER  = "1";
	
	/**
	 * 已付款
	 */
	public static final String STATE_PLACE_SECCESS  = "2";
	
	
	private String userId;//用户ID
	
	private String shopId;//商家ID
	
	private BigDecimal balance;//消费金额
	
	private String consumeType;//消费方式
	
	private String state;//状态
	
	private String demo;

	/**
	 * 用户ID
	 * @author: huiyang.yu
	 * @return the userId
	 */
	@Column(length = 50)
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
	 * 商家ID
	 * @author: huiyang.yu
	 * @return the shopId
	 */
	@Column(length = 50)
	public String getShopId() {
		return shopId;
	}

	/**
	 * 商家ID
	 * @author: huiyang.yu
	 * @param shopId the shopId to set
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	/**
	 * 消费金额
	 * @author: huiyang.yu
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * 消费金额
	 * @author: huiyang.yu
	 * @param balance the balance to set
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * 消费方式
	 * @author: huiyang.yu
	 * @return the consumeType
	 */
	@Column(length = 50)
	public String getConsumeType() {
		return consumeType;
	}

	/**
	 * 消费方式
	 * @author: huiyang.yu
	 * @param consumeType the consumeType to set
	 */
	public void setConsumeType(String consumeType) {
		this.consumeType = consumeType;
	}

	/**
	 * 状态
	 * @author: huiyang.yu
	 * @return the state
	 */
	@Column(length = 50)
	public String getState() {
		return state;
	}

	/**
	 * 状态
	 * @author: huiyang.yu
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 备注
	 * @author: huiyang.yu
	 * @return the demo
	 */
	@Column(columnDefinition="TEXT")
	public String getDemo() {
		return demo;
	}

	/**
	 * 备注
	 * @author: huiyang.yu
	 * @param demo the demo to set
	 */
	public void setDemo(String demo) {
		this.demo = demo;
	}
	
	
}
