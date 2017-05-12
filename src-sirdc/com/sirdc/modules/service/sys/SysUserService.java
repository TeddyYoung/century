/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysUserService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月20日
 */
package com.sirdc.modules.service.sys;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.century.modules.entity.account.CenturyAccount;
import com.century.modules.service.TlTransaction.TlTransactionService;
import com.century.modules.service.account.CenturyAccountService;
import com.century.modules.util.BizException;
import com.century.modules.util.BussConst;
import com.century.modules.util.NumberUtils;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.core.exceptions.ServiceException;
import com.sirdc.modules.core.exceptions.UserExistException;
import com.sirdc.modules.dao.sys.SysUserDao;
import com.sirdc.modules.databean.sys.SysUserDataBean;
import com.sirdc.modules.databean.sys.UserAndLoginUserDatabean;
import com.sirdc.modules.databean.sys.UserDataBean;
import com.sirdc.modules.entity.sys.SysLogin;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.entity.sys.SysUserRelate;
import com.sirdc.modules.filter.sys.SysUserFilter;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;
import com.sirdc.modules.sys.util.SysGlobals;
import com.sirdc.modules.sys.util.UserUtils;
import com.sirdc.modules.utils.StringUtils;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年1月20日
 */
@Service
public class SysUserService extends StringPKService<SysUser> {
	
	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysLoginService sysLoginService;
	
	@Autowired
	private SysTableService tableService;
	
	@Autowired
	private TlTransactionService tlTransactionService;
	
	@Autowired
	private SysUserRelateService sysUserRelateService;
	
	@Autowired
	private CenturyAccountService centuryAccountService;
	
	@Override
	protected BaseDao<SysUser, String> getDao() {
		return sysUserDao;
	}

	public List<SysUser> query(SysUserFilter filter) {
		filter.setSuperAdmin(UserUtils.isSuperAdmin());
		return sysUserDao.query(filter);
	}
	
//	@Override
//	protected void beforeSave(SysUser entity) {
//		entity.setSysId(tableService.updateMaxSysId("t_sys_user", null));
//	}
	protected String getSysId() {
		return tableService.updateMaxSysId("t_sys_user", null);
	}
	public String sendVerificationCode(String phone)throws BizException {
		if(StringUtils.isBlank(phone)){
			throw new BizException("手机号不能为空");
		}
		SysLogin login = sysLoginService.getLoginByMobile(phone);
		if (login != null) {
			throw new BizException("用户已存在");
		}
		String bizUserId = tableService.updateMaxSysId("t_sys_user", null);
		//云创建用户
		String cloudUserId = tlTransactionService.createMember(bizUserId, BussConst.ACCESS_SOURCE_PHONE);
		SysUserRelate sysUserRelate = new SysUserRelate();
		sysUserRelate.setCloudUserId(cloudUserId);
		sysUserRelate.setUserId(bizUserId);
		sysUserRelateService.save(sysUserRelate);
		tlTransactionService.sendVerificationCode(bizUserId, phone);
		return bizUserId;
	}
	
	/**
	 * 更新用户详情
	 * @author: weihuang.peng
	 * @param databean
	 */
	public void updateDetail(SysUserDataBean databean) {
		
		SysUser user = loadById(databean.getSysId());
		user.setImage(databean.getImage());
		user.setNickName(databean.getNickName());
		user.setGender(databean.getGender());
		user.setNativeAddr(databean.getNativeAddr());
		user.setBirthday(databean.getBirthday());
		
//		user.setName(databean.getName());
//		user.setIdCard(databean.getIdCard());
		user.setMarital(databean.getMarital());
		user.setCity(databean.getCity());
		user.setCultural(databean.getCultural());
		user.setHaveChild(databean.getHaveChild());
		
		update(user);
		
		databean.setImage(user.getImage());
		databean.setNickName(user.getNickName());
		databean.setGender(user.getGender());
		databean.setNativeAddr(user.getNativeAddr());
		databean.setBirthday(user.getBirthday());
		
		databean.setName(user.getName());
		databean.setIdCard(user.getIdCard());
		databean.setMarital(user.getMarital());
		databean.setCity(user.getCity());
		databean.setCultural(user.getCultural());
		databean.setHaveChild(user.getHaveChild());
		
	}
	/**
	 * 
	 * @param databean
	 * @param isAuth
	 */
	public void setRealName(SysUserDataBean dataBean,boolean isAuth)throws BizException {
		if (StringUtils.isBlank(dataBean.getName()) || StringUtils.isBlank(dataBean.getIdCard())) {
			throw new BizException("姓名和身份证号不能为空");
		}
		SysUser user = loadById(dataBean.getSysId());
		if (StringUtils.isNotEmpty(user.getName()) 
				&& StringUtils.isNotEmpty(user.getIdCard())){
			throw new BizException("重复实名认证");
		}
		SysUser sysUser = sysUserDao.getByIdCard(dataBean.getIdCard());
		if(sysUser != null){
			throw new BizException("该身份证号已认证");
		}
		user.setName(dataBean.getName());
		user.setIdCard(dataBean.getIdCard());
		
		update(user);
		
		if(!tlTransactionService.setRealName(dataBean, isAuth)){//是否认证通过
			throw new BizException("实名认证不通过");
		}
		
	}
	
	public void saveUserLogin(SysUserDataBean databean)throws BizException {
		if(StringUtils.isBlank(databean.getValidCode())){
			throw new BizException("验证码不能为空");
		}
		if(StringUtils.isBlank(databean.getInviteCode())){
			throw new BizException("邀请码不能为空");
		}
		//检测用户是否已经存在
		if (sysLoginService.isExistForMobile(databean.getMobile())) {
			throw new UserExistException(databean.getMobile());
		}
		
		SysUser invieteUser = getByInviteCode(databean.getInviteCode());
		if(invieteUser == null){
			throw new BizException("邀请码不存在");
		}
		
		//保存用户信息
		SysUser user = new SysUser();
		user.setNickName(databean.getNickName());
		user.setBirthday(databean.getBirthday());
		user.setName(databean.getName());
		if (StringUtils.isBlank(databean.getGender())) {
			databean.setGender(BussConst.GENDER_MALE);//默认为男
		}
		user.setGender(databean.getGender());
		user.setNativeAddr(databean.getNativeAddr());
		user.setState(SysGlobals.AccountStates.NOMAL);//状态为正常
		
		user.setName(databean.getName());
		user.setIdCard(databean.getIdCard());
		user.setMarital(databean.getMarital());
		user.setCity(databean.getCity());
		user.setCultural(databean.getCultural());
		user.setHaveChild(databean.getHaveChild());
		user.setImage(databean.getImage());
		user.setDemo(databean.getDemo());
		user.setIsMerchant(databean.getIsMerchant());
		user.setInviteCode(databean.getInviteCode());
		if(StringUtils.isBlank(databean.getSysId())){
			user.setSysId(getSysId());
		}else{
			user.setSysId(databean.getSysId());
		}
		//生成邀请码
		user.setInviteCode(NumberUtils.getRefereeCode());
		user.setInviteId(invieteUser.getSysId());
		user.setMobile(databean.getMobile());
		save(user);
		databean.setSysId(user.getSysId());
		
		//保存登陆信息
		SysLogin login = new SysLogin();
		login.setDeptId(databean.getDeptId());
		login.setEmail(databean.getEmail());
		login.setMobile(databean.getMobile());
		login.setPassword(databean.getPassword());
		login.setRoleId(databean.getRoleId());
		login.setSysId(user.getSysId());
		login.setUsername(databean.getUsername());
		if (StringUtils.isBlank(login.getRoleId())) {
			login.setRoleId("02");//普通会员
			login.setDeptId("20150130100000000040");//世纪通部门
		}
		sysLoginService.save(login);
		
		//初始化账户信息
		CenturyAccount centuryAccount = new CenturyAccount();
		centuryAccount.setAvaliableAmt(BigDecimal.ZERO);
		centuryAccount.setFreezeAmt(BigDecimal.ZERO);
		centuryAccount.setType("0");
		centuryAccount.setUserId(user.getSysId());
		centuryAccountService.save(centuryAccount);
		
		tlTransactionService.bindPhone(user.getSysId(), databean.getMobile(), databean.getValidCode());
	}
	
	/**
	 * 判断用户是否已经存在
	 * @author: weihuang.peng
	 * @return
	 */
	public boolean isExist(String sysId) {
		SysUser user = getById(sysId);
		if (user == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public void batchDelete(List<String> ids) {
		sysUserDao.batchDeleteUser(ids);
	}
	
	/**
	 * 获取用户对象
	 * @author: weihuang.peng
	 * @param id
	 * @return
	 */
	public SysUserDataBean getUserDataBeanById(String id) {
		SysUser sysUser = getById(id);
		if (sysUser == null) {
			throw new ServiceException("object is empty");
		}
		SysLogin sysLogin = sysLoginService.getById(id);
		
		SysUserDataBean databean = new SysUserDataBean();
		databean.setBirthday(sysUser.getBirthday());
		databean.setGender(sysUser.getGender());
		databean.setImage(sysUser.getImage());
		databean.setName(sysUser.getName());
		databean.setNativeAddr(sysUser.getNativeAddr());
		databean.setRoleId(sysLogin.getRoleId());
		databean.setDeptId(sysLogin.getDeptId());
		databean.setPassword(sysLogin.getPassword());
		databean.setEmail(sysLogin.getEmail());
		databean.setMobile(sysLogin.getMobile());
		databean.setNickName(sysUser.getNickName());
		databean.setUsername(sysLogin.getUsername());
		return databean;
	}

	/**
	 * @author: weihuang.peng
	 * @param databean
	 */
	public void updateUserLogin(SysUserDataBean databean) {
		//保存用户信息
		SysUser user = new SysUser();
		user.setSysId(databean.getSysId());
		user.setBirthday(databean.getBirthday());
		user.setName(databean.getName());
		user.setGender(databean.getGender());
		user.setNativeAddr(databean.getNativeAddr());
		sysUserDao.update(user);
		
		//更新登陆信息
		sysLoginService.updateLogin(databean);
	}
	
	/**
	 * 更新密码
	 * @author: weihuang.peng
	 * @param databean
	 */
	public void updatePassword(SysUserDataBean databean) {
		sysLoginService.updatePassword(databean);
	}
	
	/**
	 * 通过部门和角色查询用户登陆id
	 * @author: weihuang.peng
	 * @param filter
	 * @return
	 */
	public List<String> queryLoginByRoleDept(SysUserFilter filter) {
		return sysLoginService.queryLoginByRoleDept(filter);
	}
	
	/**
	 * 获取用户登陆信息
	 * @author: weihuang.peng
	 * @param sysId
	 * @return
	 */
	public SysLogin getSysLoginById(String sysId) {
		return sysLoginService.getById(sysId);
	}
	/**
	 * 
	 * @param inviteCode
	 * @return
	 */
	public SysUser getByInviteCode(String inviteCode) {
		return sysUserDao.getByInviteCode(inviteCode);
	}
	
	/**
	 * 获取加密后的密码
	 * @author: weihuang.peng
	 * @param password
	 * @param salt
	 * @return
	 */
	public String getEncryPass(String password, String salt) {
		return sysLoginService.getEncryPass(password, salt);
	}
	
	/**
	 * 获取系统消息用户Id
	 * @author: huiyang.yu
	 * @param user 当前登录用户
	 * @return
	 */
	public String getSysMsgUser(String userId){
		return "10086";
	}
	
	/**
	 * 用户表关联登录表查询数据
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	public List<UserAndLoginUserDatabean> queryJoinUserLogin(SysUserFilter filter){
		return sysUserDao.queryJoinUserLogin(filter);
	}
	
	/**
	 * 返回用户的基本DataBean
	 * @author: huiyang.yu
	 * @param userId 用户的sysId
	 * @return
	 */
	public UserDataBean getUserDataBean(String userId){
		SysUser user = getById(userId);
		
		UserDataBean userDataBean =  new UserDataBean();
		userDataBean.setSysId(user.getSysId());
		userDataBean.setNickName(StringUtils.defaultString(user.getNickName()));
		userDataBean.setImage(StringUtils.defaultString(user.getImage()));
		
		return userDataBean;
	}
	/**
	 * 判断是否实名认证
	 * @param userId
	 * @return
	 */
	public boolean isRealName(String userId){
		SysUser user = getById(userId);
		if(StringUtils.isNotEmpty(user.getName()) && StringUtils.isNotEmpty(user.getIdCard())){
			return true;
		}
		return false;
	}
	
	public SysUser getByMobile(String mobile) {
		return sysUserDao.getByMobile(mobile);
	}
}
