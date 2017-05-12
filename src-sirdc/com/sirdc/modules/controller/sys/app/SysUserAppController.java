/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysUserAppController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	10 Jun,2015
 */
package com.sirdc.modules.controller.sys.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.databean.account.AccountDataBean;
import com.century.modules.entity.account.CenturyCard;
import com.century.modules.filter.account.CenturyCardFilter;
import com.century.modules.service.TlTransaction.TlTransactionService;
import com.century.modules.service.account.CenturyCardService;
import com.century.modules.service.sms.SysValidService;
import com.century.modules.util.BizException;
import com.century.modules.util.BussConst;
import com.century.modules.util.Constants;
import com.century.modules.util.NumberUtils;
import com.century.modules.util.cache.SmsMessageManager;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.databean.sys.SysUserDataBean;
import com.sirdc.modules.databean.sys.UserAndLoginUserDatabean;
import com.sirdc.modules.entity.sys.SysLogin;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.filter.sys.SysUserFilter;
import com.sirdc.modules.service.sys.SysLoginService;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.sys.util.UserUtils;
import com.sirdc.modules.util.SysUserUtils;
import com.sirdc.modules.utils.StringUtils;
import com.sirdc.modules.utils.encrypt.Base64Utils;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 10 Jun,2015
 */
@Controller
@RequestMapping("/app/sys/user")
public class SysUserAppController extends JsonBaseController {

	@Autowired
	private SysUserService userService;
	@Autowired
	private SysLoginService loginService;
	@Autowired
	private SysValidService validService;
	@Autowired
	private CenturyCardService centuryCardService;
	@Autowired
	private TlTransactionService tlTransactionService;
	
	@Override
	protected String getView(String view) {
		return "/app/sys/user" + view;
	}

	@ResponseBody
	@RequestMapping(value = "/updateDetail")
	public Message updateDetail(@ModelAttribute SysUserDataBean dataBean) {
		String userId = SysUserUtils.getUserId();
		dataBean.setSysId(userId);
		userService.updateDetail(dataBean);
		
		/*//同步数据到客户端服务器
		SysLogin login= loginService.getById(userId);
		dataBean.setMobile(login.getMobile());
		UserInfo userInfo  = new UserInfo(dataBean);
		UserInfoSynService.getUserInfoSynService().userInfoSync(userInfo);*/
		
		return coverMessage("200", "更新成功");
	}
	
	@ResponseBody
	@RequestMapping(value = "/bindClientId/{clientId}", method = RequestMethod.POST)
	public Message bindClientId(@PathVariable String clientId) {
		String userId = SysUserUtils.getUserId();
		SysUser user = userService.getById(userId);
		user.setClientId(clientId);
		userService.update(user);
		return coverMessage("200", "绑定成功");
	}
	
	@ResponseBody
	@RequestMapping(value = "/changePwd", method = RequestMethod.POST)
	public Message changePwd(@ModelAttribute SysUserDataBean dataBean) {
		if (StringUtils.isBlank(dataBean.getPassword())) {
			return coverMessage("500", "新密码不能为空");
		}
		if (StringUtils.isBlank(dataBean.getOldPassword())) {
			return coverMessage("500", "旧密码不能为空");
		}
		
		String password = "";
		String oldPassword = "";
		try {
			oldPassword = new String(Base64Utils.decode(dataBean.getOldPassword()));
			password = new String(Base64Utils.decode(dataBean.getPassword()));
			dataBean.setPassword(password);
			dataBean.setOldPassword(oldPassword);
		} catch (Exception e) {
			return coverMessage("500", "密码格式不正确");
		};
		
		dataBean.setSysId(SysUserUtils.getUserId());
		if (!loginService.updatePassword(dataBean)) {
			return coverMessage("500", "旧密码不正确");
		}
//		//同步数据到客户端服务器
//		UserInfo userInfo  = new UserInfo(dataBean);
//		UserInfoSynService.getUserInfoSynService().synChangePwd(userInfo);
		
		return coverMessage("200", "更新成功");
	}
	@ResponseBody
	@RequestMapping(value = "/changeCashPwd", method = RequestMethod.POST)
	public Message changeCashPwd(@ModelAttribute SysUserDataBean dataBean) {
		if (StringUtils.isBlank(dataBean.getPassword())) {
			return coverMessage("500", "新密码不能为空");
		}
		if (StringUtils.isBlank(dataBean.getOldPassword())) {
			return coverMessage("500", "旧密码不能为空");
		}
		
		String password = "";
		String oldPassword = "";
		try {
			oldPassword = new String(Base64Utils.decode(dataBean.getOldPassword()));
			password = new String(Base64Utils.decode(dataBean.getPassword()));
			dataBean.setPassword(password);
			dataBean.setOldPassword(oldPassword);
		} catch (Exception e) {
			return coverMessage("500", "密码格式不正确");
		};
		String userId = SysUserUtils.getUserId();
		dataBean.setSysId(userId);
		try {
			loginService.changeCashPwd(dataBean);
		} catch (BizException e) {
			return coverMessage("500", e.getMessage());
		}
		
//		//同步数据到客户端服务器
//		UserInfo userInfo  = new UserInfo(dataBean);
//		UserInfoSynService.getUserInfoSynService().synChangePwd(userInfo);
		
		return coverMessage("200", "更新成功");
	}
	/**
	 * 
	 * @param dataBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/forgetCashPwd/sendAuthCode/{mobile}")
	public Message sendAuthCode(@PathVariable String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return coverMessage("500", "手机号不能为空");
		}
		SysLogin login = loginService.getLoginByMobile(mobile);
		if (login == null) {
			return coverMessage("500", "帐号不存在");
		}
		String userId = SysUserUtils.getUserId();
		if(!userId.equals(login.getSysId())){
			return coverMessage("500", "非法操作");
		}
		boolean success = validService.sendAuthCode(mobile);
		if(success){
			return coverMessage("200", "发送成功");
		}else{
			return coverMessage("500", "验证码发送失败");
		}
		
	}
	@ResponseBody
	@RequestMapping(value = "/setCashPwd")
	public Message setCashPwd(@ModelAttribute SysUserDataBean dataBean) {
		if (StringUtils.isBlank(dataBean.getPassword())) {
			return coverMessage("500", "密码不能为空");
		}
		String password = "";
		try {
			password = new String(Base64Utils.decode(dataBean.getPassword()));
			dataBean.setPassword(password);
		} catch (Exception e) {
			return coverMessage("500", "密码格式有误");
		};
		String userId = SysUserUtils.getUserId();
		dataBean.setSysId(userId);
		loginService.setCashPwd(dataBean);
		return coverMessage("200", "更新成功");
	}
	@ResponseBody
	@RequestMapping(value = "/forgetCashPwd")
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
				dataBean.setSysId(SysUserUtils.getUserId());
				loginService.setCashPwd(dataBean);
			}else{
				return coverMessage("500", "验证码不正确");
			}
		}
		
		return coverMessage("200", "更新成功");
	}
	
	/**
	 * 2.9.	更新用户经纬度信息
	 * @author: huiyang.yu
	 * @param latStr 纬度
	 * @param lngStr 经度
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateLatAndLng", method = RequestMethod.POST)
	public Message updateLatAndLng(@RequestParam String latStr, @RequestParam String lngStr) {
		SysUser user = userService.getById(UserUtils.getUserId());
		user.setLatStr(latStr);
		user.setLngStr(lngStr);
		try {
			userService.merge(user);
		} catch (Exception e) {
			e.printStackTrace();
			return coverMessage("500", "更新失败");
		}
		return coverMessage("200", "更新成功");
	}
	
	/**
	 * 实名认证
	 * @param dataBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/setRealName")
	public Message setRealName(@ModelAttribute SysUserDataBean dataBean) {
		if (StringUtils.isBlank(dataBean.getName()) || StringUtils.isBlank(dataBean.getIdCard())) {
			return coverMessage("500", "姓名和身份证号不能为空");
		}
		if(dataBean.getName().length() > 10){
			return coverMessage("500", "姓名输入有误");
		}
		String userId = SysUserUtils.getUserId();
		dataBean.setSysId(userId);
		try {
			this.userService.setRealName(dataBean, true);
		} catch (BizException e) {
			return coverMessage("500", e.getMessage());
		}
		//同步数据到客户端服务器
//		UserInfo userInfo  = new UserInfo(dataBean);
//		UserInfoSynService.getUserInfoSynService().userInfoSync(userInfo);
		
		return coverMessage("200", "认证成功");
	}
	/**
	 * 查询账户详情
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userDetail/{userId}")
	public Message userDetail(@PathVariable String userId) {
		SysUser user = userService.getById(userId);
		if (user == null) {
			return coverMessage("500", "用户不存在");
		}
		
		SysLogin login = userService.getSysLoginById(userId);
		boolean isRealName = userService.isRealName(userId);
		Map<String, Object> data = new HashMap<>();
		data.put("userId", StringUtils.defaultString(user.getSysId()));
		data.put("nickName", StringUtils.defaultString(user.getNickName()));
		data.put("image", StringUtils.defaultString(user.getImage()));
		data.put("gender", StringUtils.defaultString(user.getGender()));
		data.put("nativeAddr", StringUtils.defaultString(user.getNativeAddr()));
		data.put("birthday", StringUtils.defaultString(user.getBirthday()));
		data.put("mobile", StringUtils.defaultString(login.getMobile()));
		data.put("inviteCode", StringUtils.defaultString(user.getInviteCode()));
		if(isRealName){
			data.put("isRealName", Constants.YES_FLAG);//是否实名认证
		}else{
			data.put("isRealName", Constants.NO_FLAG);//是否实名认证
		}
		if(StringUtils.isEmpty(login.getCashPassword())){
			data.put("isCashPwdSet", Constants.NO_FLAG);//
		}else{
			data.put("isCashPwdSet", Constants.YES_FLAG);//
		}
		
		data.put("name", StringUtils.defaultString(user.getName()));//
		data.put("idCard", StringUtils.defaultString(user.getIdCard()));
		CenturyCardFilter filter = new CenturyCardFilter();
		filter.setBindState(BussConst.CARD_BIND_STATE_BIND);
		filter.setUserId(user.getSysId());
		List<CenturyCard> list =centuryCardService.query(filter);
		if(list != null && list.size() > 0){
			data.put("isBlind", Constants.YES_FLAG);//
		}else{
			data.put("isBlind", Constants.NO_FLAG);//
		}
		return coverMessage("200", "", data);
	}
	@ResponseBody
	@RequestMapping(value = "/mobileUserDetail/{mobile}")
	public Message mobileUserDetail(@PathVariable String mobile) {
		if(StringUtils.isBlank(mobile)){
			return coverMessage("500", "手机号不能为空");
		}
		SysUserFilter filter = new SysUserFilter();
		filter.setMobile(mobile);
		List<UserAndLoginUserDatabean> userList = userService.queryJoinUserLogin(filter);
		if (userList == null || userList.size() != 1) {
			return coverMessage("500", "用户信息有误");
		}
		UserAndLoginUserDatabean userInfo = userList.get(0);
		Map<String, Object> data = new HashMap<>();
		data.put("userId", StringUtils.defaultString(userInfo.getSysUser().getSysId()));
		data.put("nickName", StringUtils.defaultString(userInfo.getSysUser().getNickName()));
		data.put("image", StringUtils.defaultString(userInfo.getSysUser().getImage()));
		data.put("mobile", StringUtils.defaultString(userInfo.getSysLogin().getMobile()));
		data.put("name", StringUtils.defaultString(userInfo.getSysUser().getName()));//
		data.put("idCard", StringUtils.defaultString(userInfo.getSysUser().getIdCard()));
		
		return coverMessage("200", "", data);
	}
	@ResponseBody
	@RequestMapping(value = "/transferInfo/{mobile}")
	public Message transferInfo(@PathVariable String mobile) {
		if(StringUtils.isBlank(mobile)){
			return coverMessage("500", "手机号不能为空");
		}
		SysUserFilter filter = new SysUserFilter();
		filter.setMobile(mobile);
		List<UserAndLoginUserDatabean> userList = userService.queryJoinUserLogin(filter);
		if (userList == null || userList.size() != 1) {
			return coverMessage("500", "用户信息有误");
		}
		
		UserAndLoginUserDatabean userInfo = userList.get(0);
		Map<String, Object> data = new HashMap<>();
		data.put("userId", StringUtils.defaultString(userInfo.getSysUser().getSysId()));
		data.put("nickName", StringUtils.defaultString(userInfo.getSysUser().getNickName()));
		data.put("image", StringUtils.defaultString(userInfo.getSysUser().getImage()));
		data.put("mobile", StringUtils.defaultString(userInfo.getSysLogin().getMobile()));
		data.put("name", StringUtils.defaultString(userInfo.getSysUser().getName()));//
		data.put("idCard", StringUtils.defaultString(userInfo.getSysUser().getIdCard()));
		
		String userId = SysUserUtils.getUserId();
		AccountDataBean account = tlTransactionService.queryBalance(userId);
		if(account != null){
			data.put("balance", NumberUtils.format2(account.getAllAmount()));
		}else{
			data.put("balance", "0.00");
		}
		
		return coverMessage("200", "", data);
	}
}
