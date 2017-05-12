/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyPushService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	10 Jun,2015
 */
package com.century.modules.service.social;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.ITemplate;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.payload.APNPayload.DictionaryAlertMsg;
import com.gexin.rp.sdk.exceptions.PushAppException;
import com.gexin.rp.sdk.exceptions.PushSingleException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotyPopLoadTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 10 Jun,2015
 */
@Service
public class CenturyPushService {

	private static String appid = "EMRA7ENubK6pFkOGoCKTU2";
	private static String appKey = "AZl7GskRfw6VqV297cc8v6";
	private static String masterSecret = "1x1hNva8Ui6wO3fyY9pyn1";
//	private static String appid = "OTKbmQbKiY9lg2GksRa1y1";
//	private static String appKey = "mIiF3SE4hE7AyVqwnEqEN3";
//	private static String masterSecret = "xSIoWkmMK88F5oESTuPXr6";
	private static String host = "http://sdk.open.api.igexin.com/apiex.htm";
	private static int expireTime = 172800000;//默认48小时
	private static IGtPush push;//推送器
	
	public static IGtPush getPusher() throws IOException {
	    if (push == null) {                         //Single Checked
	        synchronized (IGtPush.class) {
	            if (push == null) {                 //Double Checked
	            	push = new IGtPush(host, appKey, masterSecret);
	            	try {
	            		push.connect();
					} catch (Exception e) {
						push = new IGtPush(host, appKey, masterSecret);
						push.connect();
					}
	            }
	        }
	    }
	    return push ;
	}
	
	/**
	 * 推送透传消息给全部人
	 * @author: weihuang.peng
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public IPushResult pushTransmission(String content) throws Exception {
		TransmissionTemplate template = getTransmissionTemplate(content);
		return sendMessage(template);
	}

	/**
	 * 推送透传消息给单用户
	 * @author: weihuang.peng
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public IPushResult pushTransmission(String content, String client) throws Exception {
		TransmissionTemplate template = getTransmissionTemplate(content);
		return sendMessage(template, client);
	}
	
	/**
	 * 推送透传消息给列表
	 * @author: weihuang.peng
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public IPushResult pushTransmission(String content, List<String> cilentList) throws Exception {
		TransmissionTemplate template = getTransmissionTemplate(content);
		return sendMessage(template, cilentList);
	}
	
	/**
	 * 推送弹窗模板给全部用户
	 * @author: weihuang.peng
	 * @param template
	 * @return
	 * @throws IOException 
	 * @throws PushAppException 
	 */
	public IPushResult pushNotyPopLoadTemplate(NotyPopLoadTemplate template) throws PushAppException, IOException {
		template = getNotyPopLoadTemplate(template);
		return sendMessage(template);
	}
	
	/**
	 * 推送弹窗模板给个人
	 * @author: weihuang.peng
	 * @param template
	 * @param clientId
	 * @return
	 * @throws IOException 
	 * @throws PushSingleException 
	 */
	public IPushResult pushNotyPopLoadTemplate(NotyPopLoadTemplate template, String clientId) throws PushSingleException, IOException {
		template = getNotyPopLoadTemplate(template);
		return sendMessage(template, clientId);
	}
	
	/**
	 * 推送弹窗模板给群组
	 * @author: weihuang.peng
	 * @param template
	 * @param aliasList
	 * @return
	 * @throws IOException 
	 */
	public IPushResult pushNotyPopLoadTemplate(NotyPopLoadTemplate template, List<String> aliasList) throws IOException {
		template = getNotyPopLoadTemplate(template);
		return sendMessage(template, aliasList);
	}

	/**
	 * 获取弹窗模板
	 * @author: weihuang.peng
	 * @param template
	 * @return
	 */
	public NotyPopLoadTemplate getNotyPopLoadTemplate(NotyPopLoadTemplate template) {
	    // 设置APPID与APPKEY
	    template.setAppId(appid);
	    template.setAppkey(appKey);
	    return template;
	}
	
	/**
	 * 获取透传模板
	 * @author: weihuang.peng
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public TransmissionTemplate getTransmissionTemplate(String content) throws Exception {
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appid);//设定接收的应用
		template.setAppkey(appKey);//用于鉴定身份是否合法
		template.setTransmissionType(2);//收到消息是否立即启动应用，1为立即启动，2则广播等待客户端自启动
		template.setTransmissionContent(content);//透传内容，不支持转义字符
		
		APNPayload payload = new APNPayload();
	    payload.setBadge(1);
	    payload.setContentAvailable(1);
	    payload.setSound("tip.wav");
	    payload.setCategory("ACTIONABLE");
	    payload.setAlertMsg(getDictionaryAlertMsg("你有一条新消息..."));
	    template.setAPNInfo(payload);
		
		//template.setPushInfo("", 1, "通知栏内容", "tip.wav", content, "", "", "");
		return template;
	}
	
	/**
	 * @author: weihuang.peng
	 * @param content 
	 * @return
	 */
	DictionaryAlertMsg getDictionaryAlertMsg(String content) {
		APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
	    alertMsg.setBody(content);
	    alertMsg.setTitle(content);
	    return alertMsg;
	}
	
	/**
	 * 推送消息
	 * @author: weihuang.peng
	 * @param template
	 * @return
	 * @throws IOException 
	 * @throws PushAppException 
	 */
	public IPushResult sendMessage(ITemplate template) throws PushAppException, IOException {
		AppMessage message = new AppMessage();
		message.setData(template);
		message.setOffline(true);//设置消息离线
		message.setOfflineExpireTime(expireTime);//离线有效时间，单位为毫秒，可选

		List<String> appIdList = new ArrayList<String>();
		appIdList.add(appid);
		message.setAppIdList(appIdList);
		
		message.setPushNetWorkType(0);
		IPushResult ret = getPusher().pushMessageToApp(message);
		return ret;
	}
	
	/**
	 * 推送单个消息
	 * @author: weihuang.peng
	 * @param template
	 * @param alias
	 * @return
	 * @throws IOException 
	 * @throws PushSingleException 
	 */
	public IPushResult sendMessage(ITemplate template, String clientId) throws PushSingleException, IOException {
		SingleMessage message = new SingleMessage();
		message.setData(template);
		message.setOffline(true);//设置消息离线
		message.setOfflineExpireTime(expireTime);//离线有效时间，单位为毫秒，可选

		Target target = new Target();
		target.setAppId(appid);
		target.setClientId(clientId);
		
		message.setPushNetWorkType(0);
		IPushResult ret = getPusher().pushMessageToSingle(message, target);
		return ret;
	}

	/**
	 * 推送群组消息
	 * @author: weihuang.peng
	 * @param template
	 * @param aliasList
	 * @return
	 * @throws IOException 
	 */
	public IPushResult sendMessage(ITemplate template, List<String> clientIds) throws IOException {
		ListMessage message = new ListMessage();
		message.setData(template);
		message.setOffline(true);//设置消息离线
		message.setOfflineExpireTime(expireTime);//离线有效时间，单位为毫秒，可选
		
		List<Target> targets = new ArrayList<Target>();
		for (String clinetId : clientIds) {
			Target target = new Target();
			target.setAppId(appid);
			target.setClientId(clinetId);
		}
		
		message.setPushNetWorkType(0);
		String taskId = getPusher().getContentId(message);
		IPushResult ret = getPusher().pushMessageToList(taskId, targets);
		return ret;
	}
	

	@Test
	public void test() throws Exception {
		pushTransmission("bbb","71083d83da1b0793131334026e29c7fd");
	}
}