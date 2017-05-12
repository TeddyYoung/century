package com.century.modules.util;
/**
 * 公共及技术常量类
 * @author yang
 *
 */
public class Constants {
	/*
	 * **************** 公共常量start *********************/
	/************ 处理结果标识 *************/
	/**
	 * 响应状态-成功
	 */
	public static final String RESPONSE_STATUS_OK = "OK"; 
	/**
	 * 响应状态-失败
	 */
	public static final String RESPONSE_STATUS_ERROR = "error";
	/************ 是否标识 *************/
	/**
	 * 是否标识 -是
	 */
	public static final String YES_FLAG = "1"; 
	/**
	 * 是否标识 -否
	 */
	public static final String NO_FLAG = "0"; 
	/************ 有效标识 *************/
	/**
	 * 有效标识
	 */
	public static final String VALID = "1"; 
	/**
	 * 无效标识
	 */
	public static final String INVALID = "0"; 
	/************ 技术常量 *************/
	/**
	 * sysId属性-订单号
	 */
	public static final String SYS_TABLE_NAME_ORDERNO = "order_no"; 
	
	/**
	 * 短信验证码的有效期
	 */
	public static final int AUTH_CODE_MESSAGE_TIME = 2 * 60 * 1000;
	
	/*
	 * **************** 公共常量end *********************/
}
