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
@Table(name = "t_sjt_payment_info")
@SelectBeforeUpdate
@DynamicInsert(true)
@DynamicUpdate(true)
public class CenturyPaymentInfo extends StringEntity{
	
	private static final long serialVersionUID = -4971157184721850030L;

	private String userId;
	
	private String recieverId;
	/**
	 * 订单号
	 */
	private String bizOrderNo;
	
	private String cloudOrderNo;
	/**
	 * 金额
	 */
	private BigDecimal totalAmount;
	/**
	 * 余额支付金额
	 */
	private BigDecimal accountAmount;
	/**
	 * 银行卡支付金额
	 */
	private BigDecimal cardAmount;
	/**
	 * 手续费
	 */
	private BigDecimal fee;
	/**
	 * 支付方式
	 */
	private String payMethod;
	/**
	 * 摘要
	 */
	private String summary;
	/**
	 * 扩展信息
	 */
	private String extendInfo;
	/**
	 * 银行卡号
	 */
	private String bankCardNo;
	/**
	 * 前台通知地址
	 */
	private String frontUrl;
	/**
	 * 后台通知地址
	 */
	private String backUrl;

	private String showUrl;
	/**
	 * 银行代码
	 */
	private String bankCode;
	/**
	 * 网关支付类型
	 */
	private String payType;
	
	private String goodsName;
	
	private String goodsDesc;
	
	private String status;
	
	private Date orderTime;
	
	private String tradeType;
	
	private String accountType;
	
	private String cloudTradeNo;
	
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
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
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getFrontUrl() {
		return frontUrl;
	}
	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getAccountAmount() {
		return accountAmount;
	}
	public void setAccountAmount(BigDecimal accountAmount) {
		this.accountAmount = accountAmount;
	}
	public BigDecimal getCardAmount() {
		return cardAmount;
	}
	public void setCardAmount(BigDecimal cardAmount) {
		this.cardAmount = cardAmount;
	}
	public String getRecieverId() {
		return recieverId;
	}
	public void setRecieverId(String recieverId) {
		this.recieverId = recieverId;
	}
	public String getShowUrl() {
		return showUrl;
	}
	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getCloudTradeNo() {
		return cloudTradeNo;
	}
	public void setCloudTradeNo(String cloudTradeNo) {
		this.cloudTradeNo = cloudTradeNo;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	
}
