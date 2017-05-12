package com.century.modules.entity.account;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.sirdc.modules.core.entity.StringEntity;
@Entity
@Table(name = "t_sjt_trade")
@SelectBeforeUpdate
@DynamicInsert(true)
@DynamicUpdate(true)
public class CenturyTrade extends StringEntity{
	
	private static final long serialVersionUID = -4971157184721850030L;

	private String userId;
	
	private String recieverId;
	/**
	 * 订单号
	 */
	private String bizOrderNo;
	
	private String cloudOrderNo;
	
	private String cloudTradeNo;
	/**
	 * 金额
	 */
	private BigDecimal amount;
	
	/**
	 * 手续费
	 */
	private BigDecimal fee;
	/**
	 * 摘要
	 */
	private String summary;
	/**
	 * 扩展信息
	 */
	private String extendInfo;
	
	private String goodsName;
	
	private String goodsDesc;
	
	private Date orderTime;
	
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(String extendInfo) {
		this.extendInfo = extendInfo;
	}
	public String getRecieverId() {
		return recieverId;
	}
	public void setRecieverId(String recieverId) {
		this.recieverId = recieverId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public String getBizOrderNo() {
		return bizOrderNo;
	}
	public void setBizOrderNo(String bizOrderNo) {
		this.bizOrderNo = bizOrderNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCloudOrderNo() {
		return cloudOrderNo;
	}
	public void setCloudOrderNo(String cloudOrderNo) {
		this.cloudOrderNo = cloudOrderNo;
	}
	public String getCloudTradeNo() {
		return cloudTradeNo;
	}
	public void setCloudTradeNo(String cloudTradeNo) {
		this.cloudTradeNo = cloudTradeNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	
	
}
