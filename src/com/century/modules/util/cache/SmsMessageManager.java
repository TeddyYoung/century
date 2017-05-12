package com.century.modules.util.cache;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.century.modules.entity.sms.SmsModel;
import com.century.modules.util.Constants;

/**
 * 短信验证码缓存管理器
 * 
 * @author Administrator
 * 
 */
public class SmsMessageManager {

	/**
	 * 保存手机号码和验证密码
	 */
	private static Map<String, SmsModel> smsMessageMap = new ConcurrentHashMap<String, SmsModel>();

	private static SmsMessageManager instance = new SmsMessageManager();

	private SmsMessageManager() {
		new ClearUnUseCodeThread().start();
	}

	public static SmsMessageManager getInstance() {
		return instance;
	}

	public void addSmsMessage(SmsModel message) {
		if (null == message)
			return;

		String tel = message.getPhone();
		smsMessageMap.put(tel, message);
	}
	public void remove(String mobile) {
		if (StringUtils.isBlank(mobile))
			return;
		
		smsMessageMap.remove(mobile);
	}
	

	public String getAuthCode(String tel) {
		if (StringUtils.isEmpty(tel))
			return null;
		SmsModel message = smsMessageMap.get(tel);
		if (null != message) {
			long createTime = message.getCreateTime();
			long now = System.currentTimeMillis();
			if ((now - createTime) <= Constants.AUTH_CODE_MESSAGE_TIME) {
				return message.getVerifyCode();
			}
		}
		return null;
	}

	class ClearUnUseCodeThread extends Thread {

		public ClearUnUseCodeThread() {
			super();
			this.setDaemon(true);
			this.setName("ClearUnUseCodeThread");
		}

		@Override
		public void run() {
			for (String tel : smsMessageMap.keySet()) {
				SmsModel message = smsMessageMap.get(tel);
				if (null != message) {
					long createTime = message.getCreateTime();
					long now = System.currentTimeMillis();
					if ((now - createTime) > Constants.AUTH_CODE_MESSAGE_TIME) {
						smsMessageMap.remove(tel);
					}
				}
			}
		}
	}
	
	public static String getAuthMessage() {
		String[] digits = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
		Random rnum = new Random(new Date().getTime());

		for (int i = 0; i < digits.length; i++) {
			int index = Math.abs(rnum.nextInt()) % 10;
			String tmpDigit = digits[index];
			digits[index] = digits[i];
			digits[i] = tmpDigit;
		}

		String returnStr = digits[0];
		for (int i = 1; i < 6; i++) {
			returnStr = digits[i] + returnStr;
		}
		return returnStr;
	}
}
