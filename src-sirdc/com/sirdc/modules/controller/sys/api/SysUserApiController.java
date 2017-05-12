/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysDeptApiController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年2月1日
 */
package com.sirdc.modules.controller.sys.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import jodd.mail.Email;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.entity.account.CenturyCard;
import com.century.modules.filter.account.CenturyCardFilter;
import com.century.modules.service.account.CenturyCardService;
import com.century.modules.service.sms.SysValidService;
import com.century.modules.util.BizException;
import com.century.modules.util.BussConst;
import com.century.modules.util.Constants;
import com.century.modules.util.cache.SmsMessageManager;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.databean.sys.SysUserDataBean;
import com.sirdc.modules.entity.sys.SysLogin;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.security.UsernamePasswordToken;
import com.sirdc.modules.service.sys.SysLoginService;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.util.PasswordHelper;
import com.sirdc.modules.utils.StringUtils;
import com.sirdc.modules.utils.UUIDGenerator;
import com.sirdc.modules.utils.ValidUtils;
import com.sirdc.modules.utils.encrypt.Base64Utils;

/**
 * 用户api
 * @author: huangcheng.dong
 * @version Revision: 0.0.1
 * @Date: 2015年2月1日
 */
@Controller
@RequestMapping("/api/sys/user")
public class SysUserApiController extends JsonBaseController {
	
	private static Logger log = LoggerFactory.getLogger(SysUserApiController.class);

	@Autowired
	private SysUserService userService;
	@Autowired
	private SysLoginService loginService;
	@Autowired
	private PasswordHelper passwordHelper;
	
	@Autowired
	private SysValidService validService;
	
	@Autowired
	private CenturyCardService centuryCardService;
	
	@Override
	protected String getView(String view) {
		return "/modules/api/sys/user/" + view;
	}
	
	/**************************************项目****************************************/
	
	@ResponseBody
	@RequestMapping(value = "/checkMobile/{mobile}")
	public Message checkMobile(@PathVariable String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return coverMessage("500", "参数有误");
		}
		
		SysLogin login = loginService.getLoginByMobile(mobile);
		if (login == null) {
			return coverMessage("500", "用户不存在");
		}
		return coverMessage("200", "用户已存在");
	}
	@ResponseBody
	@RequestMapping(value = "/checkRealName/{userId}")
	public Message checkRealName(@PathVariable String userId) {
		if (StringUtils.isBlank(userId)) {
			return coverMessage("500", "用户id不能为空");
		}
		
		boolean isRealName = userService.isRealName(userId);
		if (isRealName) {
			return coverMessage("200", "已实名认证");
		}
		return coverMessage("500", "未实名认证");
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkInviteCode/{inviteCode}")
	public Message checkInviteCode(@PathVariable String inviteCode) {
		if (StringUtils.isBlank(inviteCode)) {
			return coverMessage("500", "邀请码不能为空");
		}
		
		SysUser sysUser = userService.getByInviteCode(inviteCode);
		if (sysUser == null) {
			return coverMessage("500", "邀请码不存在");
		}
		return coverMessage("200", "");
	}
	
	@ResponseBody
	@RequestMapping(value = "/register")
	public Message register(@ModelAttribute SysUserDataBean dataBean) {
		if (StringUtils.isBlank(dataBean.getMobile())) {
			return coverMessage("500", "手机号不能为空");
		}
		if (StringUtils.isBlank(dataBean.getPassword())) {
			return coverMessage("500", "密码不能为空");
		}
		if (StringUtils.isBlank(dataBean.getValidCode())) {
			return coverMessage("500", "验证码不能为空");
		}
		if (StringUtils.isBlank(dataBean.getSysId())) {
			return coverMessage("500", "请重新获取验证码");
		}
		if (StringUtils.isBlank(dataBean.getValidCode())) {
			return coverMessage("500", "邀请码不能为空");
		}
		
		String regex = "^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";  
        Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);  
        Matcher m = p.matcher(dataBean.getMobile()); 
        if (!m.matches()) {
        	return coverMessage("500", "手机号码格式有误");
		}
        String password = "";
		try {
			password = new String(Base64Utils.decode(dataBean.getPassword()));
			dataBean.setPassword(password);
		} catch (Exception e) {
			return coverMessage("500", "密码格式有误");
		}
//		if (validService.isValid(dataBean.getMobile(), dataBean.getValidCode())) {
//			return coverMessage("500", "验证码不正确");
//		}
		
//		SysLogin login = loginService.getLoginByMobile(dataBean.getMobile());
//		if (login != null) {
//			return coverMessage("500", "用户已存在");
//		}
		try {
			userService.saveUserLogin(dataBean);
		} catch (BizException e) {
			return coverMessage("500", e.getMessage());
		}
		
		//同步数据到客户端服务器
		/*UserInfo userInfo  = new UserInfo(dataBean);
		UserInfoSynService.getUserInfoSynService().userInfoSync(userInfo);*/
		
		UsernamePasswordToken token = new UsernamePasswordToken(dataBean.getMobile(), password, true, true);
		try {
			SecurityUtils.getSubject().login(token);
		} catch (UnknownAccountException uae) { 
			return coverMessage("500", "用户不存在");
		} catch (IncorrectCredentialsException ice) { 
			return coverMessage("500", "密码不正确");
		} catch (LockedAccountException lae) { 
			return coverMessage("500", "帐号被锁定");
		} catch (ExcessiveAttemptsException eae) {
			return coverMessage("500", "密码输入错误超过五次, 系统自动开启了保护模式, 请十分钟后再试");
		} catch (AuthenticationException ae) { 
			return coverMessage("500", "请联系管理员");
		}
		SysUser user = userService.getById(dataBean.getSysId());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sysId", StringUtils.defaultString(dataBean.getSysId()));
		map.put("nickName", StringUtils.defaultString(user.getNickName()));
		map.put("imageId", StringUtils.defaultString(user.getImage()));
		return coverMessage("200", "注册成功", map);
	}
	
	@ResponseBody
	@RequestMapping(value = "/login")
	public Message login(@ModelAttribute SysUserDataBean dataBean) {
		if (StringUtils.isBlank(dataBean.getMobile()) || StringUtils.isBlank(dataBean.getPassword())) {
			return coverMessage("500", "参数有误");
		}
		
		SysLogin login = loginService.getLoginByMobile(dataBean.getMobile());
		if (login == null) {
			return coverMessage("500", "用户不存在");
		}
		
		String password = "";
		try {
			password = new String(Base64Utils.decode(dataBean.getPassword()));
		} catch (Exception e) {
			return coverMessage("500", "参数有误");
		};
		
		UsernamePasswordToken token = new UsernamePasswordToken(dataBean.getMobile(), password, true, true);
		try {
			SecurityUtils.getSubject().login(token);
		} catch (UnknownAccountException uae) { 
			return coverMessage("500", "用户不存在");
		} catch (IncorrectCredentialsException ice) { 
			return coverMessage("500", "密码不正确");
		} catch (LockedAccountException lae) { 
			return coverMessage("500", "帐号被锁定");
		} catch (ExcessiveAttemptsException eae) {
			return coverMessage("500", "有人尝试登录您的帐号超过五次, 系统自动开启了保护模式, 请十分钟后再试");
		} catch (AuthenticationException ae) { 
			return coverMessage("500", "用户名或密码错误");
		}
		SysUser user = userService.getById(login.getSysId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", StringUtils.defaultString(login.getSysId()));
		map.put("nickName", StringUtils.defaultString(user.getNickName()));
		map.put("image", StringUtils.defaultString(user.getImage()));
		
		map.put("gender", StringUtils.defaultString(user.getGender()));
		map.put("nativeAddr", StringUtils.defaultString(user.getNativeAddr()));
		map.put("birthday", StringUtils.defaultString(user.getBirthday()));
		map.put("mobile", StringUtils.defaultString(login.getMobile()));
		map.put("inviteCode", StringUtils.defaultString(user.getInviteCode()));
		if(StringUtils.isNotEmpty(user.getName()) && StringUtils.isNotEmpty(user.getIdCard())){
			map.put("isRealName", Constants.YES_FLAG);//是否实名认证
		}else{
			map.put("isRealName", Constants.NO_FLAG);//是否实名认证
		}
		if(StringUtils.isEmpty(login.getCashPassword())){
			map.put("isCashPwdSet", Constants.NO_FLAG);//
		}else{
			map.put("isCashPwdSet", Constants.YES_FLAG);//
		}
		map.put("name", StringUtils.defaultString(user.getName()));//
		map.put("idCard", StringUtils.defaultString(user.getIdCard()));
		CenturyCardFilter filter = new CenturyCardFilter();
		filter.setBindState(BussConst.CARD_BIND_STATE_BIND);
		filter.setUserId(login.getSysId());
		List<CenturyCard> list =centuryCardService.query(filter);
		if(list != null && list.size() > 0){
			map.put("isBlind", Constants.YES_FLAG);//
		}else{
			map.put("isBlind", Constants.NO_FLAG);//
		}
		return coverMessage("200", "登陆成功", map);
	}
	
	/**
	 * 
	 * @param dataBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/forgetPwd/sendAuthCode/{mobile}")
	public Message sendAuthCode(@PathVariable String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return coverMessage("500", "手机号不能为空");
		}
		SysLogin login = loginService.getLoginByMobile(mobile);
		if (login == null) {
			return coverMessage("500", "帐号不存在");
		}
		
		boolean success = validService.sendAuthCode(mobile);
		if(success){
			return coverMessage("200", "发送成功");
		}else{
			return coverMessage("500", "验证码发送失败");
		}
		
	}
	@ResponseBody
	@RequestMapping(value = "/forgetPwd")
	public Message forgetPwd(@ModelAttribute SysUserDataBean dataBean) {
		if (StringUtils.isBlank(dataBean.getMobile())) {
			return coverMessage("500", "手机号不能为空");
		}
		if (StringUtils.isBlank(dataBean.getPassword())) {
			return coverMessage("500", "密码不能为空");
		}
		if (StringUtils.isBlank(dataBean.getValidCode())) {
			return coverMessage("500", "验证码不能为空");
		}
		
		String password = "";
		try {
			password = new String(Base64Utils.decode(dataBean.getPassword()));
			dataBean.setPassword(password);
		} catch (Exception e) {
			return coverMessage("500", "密码格式有误");
		};
		String authCode = SmsMessageManager.getInstance().getAuthCode(dataBean.getMobile());
		if(StringUtils.isEmpty(authCode)){
			return coverMessage("500", "验证码失效");
		}else{
			if(dataBean.getValidCode().equals(authCode)){
				try {
					loginService.resetPassword(dataBean);
					SmsMessageManager.getInstance().remove(dataBean.getMobile());
				} catch (BizException e) {
					return coverMessage("500", e.getMessage());
				}
			}else{
				return coverMessage("500", "验证码不正确");
			}
		}
		
		return coverMessage("200", "更新成功");
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
	public Message changePwd(@ModelAttribute SysUserDataBean dataBean) {
		if (StringUtils.isBlank(dataBean.getPassword()) || StringUtils.isBlank(dataBean.getMobile())) {
			return coverMessage("500", "参数异常");
		}
		
		String regex = "^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";  
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);  
        Matcher m = p.matcher(dataBean.getMobile()); 
        if (!m.matches()) {
        	return coverMessage("500", "电话号码格式有误");
		}
		
		String password = "";
		try {
			password = new String(Base64Utils.decode(dataBean.getPassword()));
		} catch (Exception e) {
			return coverMessage("500", "参数异常");
		};

		SysLogin login = loginService.getLoginByMobile(dataBean.getMobile());
		if (login == null) {
			return coverMessage("500", "帐号不存在");
		}
		
		login.setPassword(password);
		passwordHelper.encryptPassword(login);
		loginService.update(login);
		
//		//同步数据到客户端服务器
//		UserInfo userInfo  = new UserInfo(dataBean);
//		UserInfoSynService.getUserInfoSynService().synChangePwd(userInfo);
		
		return coverMessage("200", "更新成功");
	}
	
	
	/**************************************项目****************************************/
	
	/**************************************平台****************************************/
	
	/**
	 * 校验用户编号是否存在
	 * @author: weihuang.peng
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/isExistForUserId")
	public boolean isExistForUserId(String userId) {
		return userService.isExist(userId);
	}
	
	/**
	 * 校验邮箱是否存在
	 * @author: weihuang.peng
	 * @param email
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/isExistForEmail")
	public boolean isExistForEmail(String email) {
		return loginService.isExistForEmail(email);
	}
	
	/**
	 * 校验电话号码是否存在
	 * @author: weihuang.peng
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/isExistForMobile")
	public boolean isExistForMobile(String mobile) {
		return loginService.isExistForMobile(mobile);
	}
	
	/**
	 * 找回密码
	 * @author: weihuang.peng
	 * @param datebean
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/searchPassword")
	public Message searchPassword(String email, HttpServletRequest request) {
		if(!ValidUtils.valid(email, ValidUtils.EMAIL)) {
			return coverMessage("500", "邮箱格式有误，请重新输入");
		}
		
		if(!loginService.isExistForEmail(email)) {
			return coverMessage("500", "此邮箱尚未注册,<a href='javascript:location.reload();'>现在注册?</a>");
		}
		
		String uuid = UUIDGenerator.uuid2();
		StringBuffer changePath = new StringBuffer(String.format("%s://%s:%s", request.getScheme(), request.getServerName(), request.getServerPort())).append(request.getContextPath()).append("/api/modules/sys/user/changePasswordWithSearch");
		changePath.append("?email=").append(email);
		changePath.append("&key=").append(uuid);
		Email mail = Email.create();
		mail.addHtml("<a href='" + changePath + "'>点击此处进行更换密码</a>");
        mail.addText("邮件内容");//文本消息
        mail.from("xmutsoft@163.com").to(email);
        mail.subject("来自创新研发中心的密码重置邮件");//标题
        SmtpServer<?> smtpServer = SmtpServer.create("smtp.163.com").authenticateWith("xmutsoft@163.com", "soft333999");
        SendMailSession mailSession = smtpServer.createSession();
        mailSession.open();
        mailSession.sendMail(mail);
        mailSession.close();
        
        SysLogin login = loginService.getUserByLoginName(email);
        login.setMailValidTime(String.format("%d", System.currentTimeMillis()));
        login.setMailValidCode(uuid);
        return coverMessage("200");
	}
	
	/**
	 * 找回密码的更新操作
	 * @author: weihuang.peng
	 * @param datebean
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/changePasswordWithSearch")
	public Message changePasswordWithSearch(String email, String key) {
		if(!ValidUtils.valid(email, ValidUtils.EMAIL)) {
			return coverMessage("500", "邮箱格式有误，请重新输入");
		}
		if(!loginService.isExistForEmail(email)) {
			return coverMessage("500", "此邮箱尚未注册,<a href='javascript:location.reload();'>现在注册?</a>");
		}
		
		SysLogin login = loginService.getUserByLoginName(email);
		long result = (System.currentTimeMillis() - StringUtils.toLong(login.getMailValidTime())) / (1000L * 60 * 30);
		if(result > 1) {
			if(!loginService.isExistForEmail(email)) {
				return coverMessage("500", "您未发起找回密码");
			}
		} else {
			if (login.getMailValidCode().equals(key)) {
				SysUserDataBean databean = new SysUserDataBean();
				databean.setOldPassword(login.getPassword());
				databean.setPassword("123456");
				loginService.updatePassword(databean);
				return coverMessage("200", "密码已经初始化为123456，请登陆系统更换密码");
			}
		}
		
		return coverMessage("200");
	}
	
	/**************************************平台***************************************-*/
}
