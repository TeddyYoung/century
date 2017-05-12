package com.century.modules.util;
/**
 * 业务常量类
 * @author yang
 *
 */
public class BussConst {
	/*
	 * ****************通联业务常量start*********************/
	/************ 会员类型 *************/
	/**
	 * 会员类型-企业
	 */
	public static final String MEMBER_TYPE_ENTERPRISE = "2"; 
	/**
	 * 会员类型-个人
	 */
	public static final String MEMBER_TYPE_PERSONAL = "3"; 
	
	/*********** 访问终端类型 **********/
	/**
	 * 访问终端类型-手机
	 */
	public static final String ACCESS_SOURCE_PHONE = "1"; 
	/**
	 * 访问终端类型-PC
	 */
	public static final String ACCESS_SOURCE_PC = "2"; 
	/*********** 证件类型 **********/
	/**
	 * 证件类型-身份证
	 */
	public static final String IDENTITYTYPE_IDCARD = "1"; 
	/*********** 银行卡类型 **********/
	/**
	 * 银行卡类型-储蓄卡
	 */
	public static final String CARD_TYPE_DEPOSIT = "1"; 
	/**
	 * 银行卡类型-信用卡
	 */
	public static final String CARD_TYPE_CREDIT = "2"; 
	/*********** 支付方式 **********/
	/**
	 * 支付方式-快捷支付
	 */
	public static final String PAY_METHOD_QUICKPAY = "1"; 
	/**
	 * 支付方式-网关支付
	 */
	public static final String PAY_METHOD_GATEWAY = "2"; 
	/**
	 * 支付方式-账户余额
	 */
	public static final String PAY_METHOD_ACCOUNT = "3"; 
	/**
	 * 支付方式-账户余额+快捷支付
	 */
	public static final String PAY_METHOD_ACCOUNT_QUICKPAY = "4"; 
	/**
	 * 支付方式-账户余额+网关支付
	 */
	public static final String PAY_METHOD_ACCOUNT_GATEWAY = "5"; 
	/*********** 网关支付方式 **********/
	/**
	 *  网关支付方式- 网关支付方式
	 */
	public static final String GATEWAY_PAYTYPE_DEBIT = "1"; 
	/**
	 *  网关支付方式- 企业网银
	 */
	public static final String GATEWAY_PAYTYPE_ENTERPRISE = "4"; 
	/**
	 *  网关支付方式- 信用卡
	 */
	public static final String GATEWAY_PAYTYPE_CREDIT = "11"; 
	/**
	 *  网关支付方式- 认证支付
	 */
	public static final String GATEWAY_PAYTYPE_AUTHENTICATION = "28"; 
	
	/*********** 银行卡绑定状态 **********/
	/**
	 * 银行卡绑定状态-校验通过
	 */
	public static final String CARD_BIND_STATE_ORIGINAL = "0";
	/**
	 * 银行卡绑定状态-已绑定
	 */
	public static final String CARD_BIND_STATE_BIND = "1"; 
	/**
	 * 银行卡绑定状态-已解除
	 */
	public static final String CARD_BIND_STATE_UNBIND = "2"; 
	/*********** 验证码类型 **********/
	/**
	 * 绑定手机号
	 */
	public static final String VERIFICATION_CODE_TYPE_BIND = "9"; 
	
	/*
	 * ****************通联业务常量end*********************/
	/*
	 * ****************业务常量start*********************/
	/** 支付信息情况 0（初始化），1（成功），2（失败） */
	/**
	 *  支付状态-初始化(未支付)
	 */
	public static final String PAY_STATUS_INIT = "1";
	/**
	 *  支付状态-支付完成
	 */
	public static final String PAY_STATUS_DONE = "2";
	/**
	 * 支付状态-失败（交易关闭）
	 */
	public static final String PAY_STATUS_FAILURE = "3";
	/**
	 * 交易成功
	 */
	public static final String PAY_STATUS_SUCCESS = "4";
	/**
	 * 支付状态-支付中
	 */
	public static final String PAY_STATUS_PAYING = "99";
	/**  **************交易类型 **************/
	/**
	 * 交易类型-- 手续费
	 */
	public static final String TRADE_TYPE_CHARGE = "0";
	/**
	 * 交易类型-- 充值
	 */
	public static final String TRADE_TYPE_DEPOSIT = "1";
	/**
	 * 交易类型--提现
	 */ 
	public static final String TRADE_TYPE_WITHDRAW = "2";
	/**
	 * 交易类型--商家消费
	 */ 
	public static final String TRADE_TYPE_CONSUME = "3";
	/**
	 * 交易类型--转账
	 */ 
	public static final String TRADE_TYPE_TRANSFER = "4";
	/**  **************账户变动类型 **************/
	/**
	 * 账户变动类型-收入
	 */
	public static final String ACCOUNT_MOVEMENTS_TYPE_INCOME = "0";
	/**
	 * 账户变动类型-支出
	 */
	public static final String ACCOUNT_MOVEMENTS_TYPE_EXPENSE = "1";
	/**  **************支付操作方式 **************/
	/**
	 * 支付操作方式-后台
	 */
	public static final String PAY_OPERATE_METHOD_BACK = "1";
	/**
	 * 支付操作方式-前台
	 */
	public static final String PAY_OPERATE_METHOD_FRONT = "2";
	/**  **************性别 **************/
	public static final String GENDER_MALE = "M";
	
	public static final String GENDER_FEMALE = "F";
	/*
	* ****************业务常量end*********************/
	/*
	 * ****************通联错误码start*********************/
	/**
	 * 用户已经实名认证
	 */
	public static final String TL_RSP_REAL_NAME = "30007";
	/*
	 * ****************通联错误码end*********************/
	
}
