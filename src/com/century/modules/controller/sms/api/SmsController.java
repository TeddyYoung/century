/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SmsController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年5月28日
 */
package com.century.modules.controller.sms.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.service.sms.SmsService;
import com.century.modules.service.sms.SysValidService;
import com.century.modules.util.BizException;
import com.century.modules.util.MessageHelper;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.service.sys.SysLoginService;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.utils.StringUtils;
import com.sirdc.modules.utils.ValidUtils;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年5月28日
 */
@Controller
@RequestMapping("/api/modules/verifyCode")
public class SmsController extends JsonBaseController {
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private SysLoginService sysLoginService;
	
	@Autowired
	private SysValidService validService;
	
	@Autowired
	private SysUserService sysUserService;
	
//	private char[] ch= "0123456789".toCharArray();
//	@ResponseBody  
//	@RequestMapping("/sendSMS")
//	public Message sendSMS(@ModelAttribute SmsModel smsModel, HttpServletRequest request){
//		//电话号码真实性判断
//		if (StringUtils.isBlank(smsModel.getPhone()) || smsModel.getPhone().length() != 11) {
//			return coverMessage("500", "手机号错误");
//		}
//		//生成6位数验证码
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < 6; i++) {
//			Random random = new Random();
//			int index  = random.nextInt(ch.length);
//			sb.append(ch[index]);
//		}
//		smsModel.setVerifyCode(sb.toString());;
//		
//		//发送短信
//		smsService.sendTemplateSMS(smsModel);
//		request.getSession().setAttribute("smsModel", smsModel);//存入Session
//		return coverMessage("200");
//	}
	
	/**
	 * 1.14 获取验证码
	 * @author: weihuang.peng
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/captcha/{mobile}") 
	public Message captcha(@PathVariable String mobile) {
		if (StringUtils.isBlank(mobile) || !ValidUtils.valid(mobile, ValidUtils.PHONE)) {
			return MessageHelper.coverMessage("500", "参数有误，请重新输入");
		}
		/*char[] codeSeq = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		Random random = new Random();
		StringBuilder code = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			String randomCode = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);// random.nextInt(10));
			code.append(randomCode);
		}
		
		SysValid validObj = new SysValid();
		validObj.setCode(code.toString());
		validObj.setCurrentTimeMillis(System.currentTimeMillis());
		validObj.setEnable(true);
		validObj.setMobile(mobile);*/
//		validService.save(validObj); 
		Map<String, String> map = new HashMap<String, String>();
		try {
			String userId = sysUserService.sendVerificationCode(mobile);
			map.put("sysId", userId);
		} catch (BizException e) {
			return coverMessage("500", "发送失败");
		}
		return coverMessage("200", "验证码发送成功",map);
	}
	
	/**
	 * 验证验证码是否通过
	 * @author: weihuang.peng
	 * @param mobile
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/validCode/{mobile}/{code}")
	public Message validCode(@PathVariable String mobile, @PathVariable String code) {
		if (StringUtils.isBlank(mobile)
				|| StringUtils.isBlank(code)
				|| !ValidUtils.valid(mobile, ValidUtils.PHONE)) {
			return coverMessage("500", "参数有误，请重新输入");
		}
		
		if (validService.isValid(mobile, code)) {
			return coverMessage("200", "验证通过");
		}
		
		return coverMessage("500", "验证码不正确");
	}
	
	@Override
	protected String getView(String view) {
		return null;
	}
}
