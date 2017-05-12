/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SmsService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年5月28日
 */
package com.century.modules.service.sms;

import java.util.HashMap;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.century.modules.entity.sms.SmsModel;
import com.century.modules.gateway.sms.SMSConfig;
import com.cloopen.rest.sdk.CCPRestSmsSDK;


/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年5月28日
 */
@Service
@SuppressWarnings("unchecked")
public class SmsService {
	
	
	/**
	 * 发送短信验证码
	 * @author: huiyang.yu
	 * @param smsModel
	 * @return
	 */
	public HashMap<String, Object> sendTemplateSMS(SmsModel smsModel){
		HashMap<String, Object> result = null;
		
		//******************************注释****************************************************************
		//*调用发送模板短信的接口发送短信                                                                  *
		//*参数顺序说明：                                                                                  *
		//*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号                          *
		//*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。    *
		//*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
		//*第三个参数是要替换的内容数组。																														       *
		//**************************************************************************************************
		
		//**************************************举例说明***********************************************************************
		//*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为           *
		//*result = restAPI.sendTemplateSMS("13800000000","1" ,new String[]{"6532","5"});																		  *
		//*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入     *
		//*********************************************************************************************************************
		CCPRestSmsSDK restAPI = SMSConfig.getCCPRestSmsSDK();
		result = restAPI.sendTemplateSMS(smsModel.getPhone(),SMSConfig.SMS_NO ,new String[]{smsModel.getVerifyCode(),"2"});

		System.out.println("SDKTestGetSubAccounts result=" + result);
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
		return result;
	}
	
	
}
