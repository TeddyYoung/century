/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLoginService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月22日
 */
package com.sirdc.modules.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.century.modules.util.BizException;
import com.gexin.rp.sdk.base.uitls.MD5Util;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.core.exceptions.UserExistException;
import com.sirdc.modules.dao.sys.SysLoginDao;
import com.sirdc.modules.databean.sys.SysUserDataBean;
import com.sirdc.modules.entity.sys.SysLogin;
import com.sirdc.modules.filter.sys.SysUserFilter;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.filter.SysLoginFilter;
import com.sirdc.modules.sys.util.SysGlobals.AccountStates;
import com.sirdc.modules.util.PasswordHelper;
import com.sirdc.modules.utils.StringUtils;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年1月22日
 */
@Service
public class SysLoginService extends StringPKService<SysLogin> {
	
	@Autowired
	private SysLoginDao sysLoginDao;
	
	@Autowired
    private PasswordHelper passwordHelper;
	
	@Override
	protected BaseDao<SysLogin, String> getDao() {
		return sysLoginDao;
	}

	public List<SysLogin> query(SysLoginFilter filter) {
		return sysLoginDao.query(filter);
	}
	
	@Override
	protected void beforeSave(SysLogin user) {
		user.setState(AccountStates.NOMAL);//初始化帐号状态
		passwordHelper.encryptPassword(user);//密码加密
		
		//判断用户是否已经存在
		if(isExistForMobile(user.getMobile())) {
			throw new UserExistException();
		}
		
		//判断用户是否已经存在
//		if(isExistForEmail(user.getEmail())) {
//			throw new UserExistException();
//		}
	}
	
	/**
	 * 根据用户的口令信息获取用户信息
	 * @author: weihuang.peng
	 * @param token
	 * @return
	 */
	public SysLogin getUserByLoginName(String token) {
		return sysLoginDao.getUserByLoginName(token);
	}
	
	/**
	 * 根据手机号码获取sysLogin
	 * @author: weihuang.peng
	 * @return
	 */
	public SysLogin getLoginByMobile(String mobile) {
		return sysLoginDao.getLoginByMobile(mobile);
	}
	
	/**
	 * 根据邮箱获取sysLogin
	 * @author: weihuang.peng
	 * @return
	 */
	public SysLogin getLoginByEmail(String email) {
		return sysLoginDao.getLoginByEmail(email);
	}
	
	/**
	 * 根据电话号码判断用户是否存在
	 * @author: weihuang.peng
	 * @param mobile
	 * @return
	 */
	public boolean isExistForMobile(String mobile) {
		SysLogin sysLogin = sysLoginDao.getLoginByMobile(mobile);
		if(sysLogin == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * 根据邮箱判断用户是否存在
	 * @author: weihuang.peng
	 * @param mail
	 * @return
	 */
	public boolean isExistForEmail(String mail) {
		SysLogin sysLogin = sysLoginDao.getLoginByEmail(mail);
		if(sysLogin == null) {
			return false;
		}
		return true;
	}

	/**
	 * 更新其他信息
	 * @author: weihuang.peng
	 * @param datebean
	 */
	public void updateLogin(SysUserDataBean databean) {
		//保存登陆信息
		SysLogin login = getById(databean.getSysId());
		login.setSysId(databean.getSysId());
		login.setDeptId(databean.getDeptId());
		login.setRoleId(databean.getRoleId());
		
		if (!login.getPassword().equals(databean.getPassword())) {
			login.setPassword(databean.getPassword());
			passwordHelper.encryptPassword(login);
		}
		update(login);
	}
	
	/**
	 * 返回加密后的新密码
	 * @author: weihuang.peng
	 * @param password
	 * @param salt
	 * @return
	 */
	public String getEncryPass(String password, String salt) {
		return passwordHelper.encryptPassResult(password, salt);
	}

	/**
	 * 更新密码
	 * @author: weihuang.peng
	 * @param databean
	 * @return 
	 */
	public boolean updatePassword(SysUserDataBean databean) {
		SysLogin login = getById(databean.getSysId());
		if (StringUtils.isNotBlank(login.getPassword())) {
			String oldPassword = passwordHelper.encryptPassResult(databean.getOldPassword(), login.getSaltKey());
			if (!login.getPassword().equals(oldPassword)) {
				return false;
			}
		}
		login.setPassword(databean.getPassword());
		passwordHelper.encryptPassword(login);
		login.setMailValidCode("");
		login.setMailValidTime("");
		sysLoginDao.update(login);
		
		return true;
	}
	/**
	 * 设置支付密码
	 * @param databean
	 */
	@Transactional
	public void setCashPwd(SysUserDataBean databean) {
		SysLogin login = getById(databean.getSysId());
		login.setCashPassword(PasswordHelper.getMD5Str(databean.getPassword()));
		sysLoginDao.update(login);
	}
	@Transactional
	public void changeCashPwd(SysUserDataBean databean)throws BizException {
		SysLogin login = getById(databean.getSysId());
		if(!PasswordHelper.getMD5Str(databean.getOldPassword()).equals(login.getCashPassword())){
			throw new BizException("旧密码不正确");
		}
		login.setCashPassword(PasswordHelper.getMD5Str(databean.getPassword()));
		sysLoginDao.update(login);
	}
	/**
	 * 重置登录密码
	 */
	@Transactional
	public boolean resetPassword(SysUserDataBean databean)throws  BizException {
		SysLogin login = getLoginByMobile(databean.getMobile());
		if (login == null) {
			throw new BizException("帐号不存在");
		}
		
		login.setPassword(databean.getPassword());
		passwordHelper.encryptPassword(login);
		sysLoginDao.update(login);
		
		return true;
	}

	/**
	 * 根据部门和角色获取用户ID列表
	 * @author: weihuang.peng
	 * @param filter
	 */
	public List<String> queryLoginByRoleDept(SysUserFilter filter) {
		return sysLoginDao.queryLoginByRoleDept(filter);
	}

	/**
	 * 判断用户是否用户部门和角色
	 * @author: weihuang.peng
	 * @param userId
	 * @param roleId
	 * @param deptId
	 * @return
	 */
	public boolean isExistUserPermission(String userId, String roleId, String deptId) {
		return sysLoginDao.isExistUserPermission(userId, roleId, deptId);
	}
	
	
	public SysLogin getSjctSysLogin(String mobile, String password){
		String encodePass = MD5Util.getMD5Format(password);
		return sysLoginDao.getSjctSysLogin(mobile,encodePass);
	}
}
