/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SMSConfig.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年5月28日
 */
package com.century.modules.gateway.sms;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

/**
 * 云通讯短信配置
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年5月28日
 */
public class SMSConfig {
	
	
	/**
	 * 构造方法私有化禁止new()
	 */
	private SMSConfig() {
		super();
	}
	/**
	 * 短信模板号
	 */
	public  static String SMS_NO = "60310";//短信模板号

	//******************************注释*********************************************
	//*初始化服务器地址和端口                                                       *
	//*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
	//*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
	//*******************************************************************************
//	public static String INIT_URL = "sandboxapp.cloopen.com";//开发
	public static String INIT_URL = "app.cloopen.com";//生产
	
	public static String INIT_PORT = "8883";
	
	//******************************注释*********************************************
	//*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
	//*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
	//*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
	//*******************************************************************************
	public static String ACCOUNT_SID = "aaf98f894e7826aa014e8a5e23470eba";
	
	public static String AUTH_TOKEN = "38c06773ae64452da0ffaa800691fc30";
	
	//******************************注释*********************************************
	//*初始化应用ID                                                                 *
	//*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
	//*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
	//*******************************************************************************
//	public static String APP_ID = "aaf98f894d7439d8014d97ef57e619a5";//测试
	public static String APP_ID = "8a48b5514fba2f87014fbfee4d960ed3";//正式
	
	/**
	 * 
	 */
	private static CCPRestSmsSDK restAPI;
	
	/**
	 * 初始化SDK
	 * @author: huiyang.yu
	 * @return
	 */
	public static CCPRestSmsSDK getCCPRestSmsSDK(){
		//初始化SDK
		if (restAPI == null) {
			restAPI = new CCPRestSmsSDK();
			restAPI.init(SMSConfig.INIT_URL, SMSConfig.INIT_PORT);
			restAPI.setAccount(SMSConfig.ACCOUNT_SID,SMSConfig.AUTH_TOKEN);
			restAPI.setAppId(SMSConfig.APP_ID);
		}
		return restAPI;
	}
	
}
