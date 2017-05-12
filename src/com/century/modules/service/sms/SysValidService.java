/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysValidService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	29 Jul,2015
 */
package com.century.modules.service.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.century.modules.dao.sms.SysValidDao;
import com.century.modules.entity.sms.SmsModel;
import com.century.modules.entity.sms.SysValid;
import com.century.modules.filter.sms.SysValidFilter;
import com.century.modules.util.cache.SmsMessageManager;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.sys.base.LongPKService;
import com.sirdc.modules.sys.service.SysTableService;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 29 Jul,2015
 */
@Service
public class SysValidService extends LongPKService<SysValid> {

	Logger logger = LoggerFactory.getLogger(SysValidService.class); 
	
	@Autowired
	private SysValidDao dao;
	@Autowired
	private SysTableService tableService;
	@Autowired
	private SmsService smsService;
	
	@Override
	protected BaseDao<SysValid, Long> getDao() {
		return dao;
	}
	
	@Override
	protected void afterSave(SysValid obj) {
		SmsModel smsModel = new SmsModel();
		smsModel.setPhone(obj.getMobile());
		smsModel.setVerifyCode(obj.getCode());
		//发送短信
		smsService.sendTemplateSMS(smsModel);
	}
	
	public boolean sendAuthCode(String mobile){
		System.currentTimeMillis();
		SmsModel smsModel = new SmsModel();
		smsModel.setPhone(mobile);
		smsModel.setVerifyCode(SmsMessageManager.getAuthMessage());
		smsModel.setCreateTime(System.currentTimeMillis());
		SmsMessageManager.getInstance().addSmsMessage(smsModel);
		//发送短信
		smsService.sendTemplateSMS(smsModel);
		return true;
	}
	
	/**
	 * 获取手机验证码发送的时间间隔
	 * @author: weihuang.peng
	 * @param mobile
	 * @return
	 */
	public long getValidTimeSpace(SysValid sysValid) {
		if (sysValid == null) {
			return Long.MAX_VALUE;
		}
		return getTimeSpace(sysValid.getCurrentTimeMillis());
	}
	
	/**
	 * 判断验证码是否已经超过5分钟
	 * 验证码验证成功一次就不会有第二次了
	 * @author: weihuang.peng
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean isPast5MinOrErr(String mobile, String code) {
		SysValidFilter filter = new SysValidFilter();
		filter.setMobile(mobile);
		SysValid sysValid = dao.getLastSysValid(filter);
		
		//用户攻击
		if (sysValid == null) {
			return true;
		}
		
		//用户攻击
		if (!sysValid.isEnable()) {
			return true;
		}
		
		//判断验证码是否正确
		if (!sysValid.getCode().equals(code)) {
			return true;
		}
		
		//判断验证码是否超时
		long timeSpace = getValidTimeSpace(sysValid);
		if (timeSpace > 5) {
			return true;
		}

		//状态更新为已读，下次不走验证，需要重新发验证码
		sysValid.setEnable(false);
		update(sysValid);
		return false;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	public boolean isValid(String mobile, String code) {
		SysValidFilter filter = new SysValidFilter();
		filter.setMobile(mobile);
		SysValid sysValid = dao.getLastSysValid(filter);
		
		//用户攻击
		if (sysValid == null) {
			return false;
		}
		
		//用户攻击
		if (!sysValid.isEnable()) {
			return false;
		}
		
		//判断验证码是否正确
		if (!sysValid.getCode().equals(code)) {
			return false;
		}
		
		//判断验证码是否超时
		long timeSpace = getValidTimeSpace(sysValid);
		if (timeSpace > 5) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 获取时间戳间隔
	 * @author: weihuang.peng
	 * @param oldTimeMillis
	 * @return
	 */
	public long getTimeSpace(Long oldTimeMillis) {
		return (System.currentTimeMillis() - oldTimeMillis) / (1000 * 60);
	}
}
