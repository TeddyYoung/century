/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.sirdc.modules.security;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirdc.modules.core.servlet.ValidateCodeServlet;
import com.sirdc.modules.entity.sys.SysLogin;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.service.sys.SysLoginLogService;
import com.sirdc.modules.service.sys.SysLoginService;
import com.sirdc.modules.service.sys.SysRolePermissionService;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.shiro.exception.CaptchaException;
import com.sirdc.modules.shiro.matcher.RetryLimitHashedCredentialsMatcher;
import com.sirdc.modules.sys.security.Principal;
import com.sirdc.modules.sys.util.SysGlobals;
import com.sirdc.modules.util.SysUserUtils;
import com.sirdc.modules.utils.CollectionsUtils;
import com.sirdc.modules.utils.StringUtils;

/**
 * 系统安全认证实现类
 * 
 * @author ThinkGem
 * @version 2013-5-29
 */
@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {

	@Autowired
	private SysLoginService sysLoginService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private RetryLimitHashedCredentialsMatcher retryMather;
	@Autowired
	private SysRolePermissionService sysRolePermissionService;
	@Autowired
	private SysLoginLogService sysLoginLogService;
		
	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		
		validCode(token);//校验验证码是否正确
		SysLogin loginUser = sysLoginService.getUserByLoginName(token.getUsername());
		
		if (loginUser == null) {
			throw new UnknownAccountException();//没找到帐号
		}
		
		if (loginUser.getState().equals(SysGlobals.AccountStates.DISABLE)) {
			throw new LockedAccountException();//帐号被锁定
		}
		
		SysUser userInfo = sysUserService.getById(loginUser.getSysId());

		//验证用户信息
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				new SysPrincipal(loginUser, userInfo, token.isApi()),//用户信息，需要多少信息就为对象赋多少属性
				loginUser.getPassword(), //用户被加密过的密码
				ByteSource.Util.bytes(loginUser.getSaltKey()), //解密的钥匙(与加密钥匙一样)
				getName());
		return authenticationInfo;
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取用户信息
		SysPrincipal principal = SysUserUtils.getSysPrincipal();
		String roleId = principal.getRoleId();
		String deptId = principal.getDeptId();
		sysLoginLogService.updateLoginLog(principal);//更新登陆时间
		
		//赋予权限信息
		List<String> permissions = sysRolePermissionService.queryPermissionByRoleIdDeptId(roleId, deptId);
		SysLogin user = sysLoginService.getUserByLoginName(principal.getUserId());
		if (user != null && CollectionsUtils.isNotEmpty(permissions)) {
			SimpleAuthorizationInfo info = constructInfo(permissions);//添加权限字符信息
			info.addRole(roleId.toString());//添加角色
			return info;
		}
		return null;
	}
	
	/**
	 * 判断校验码是否正确或者不使用
	 * @author: weihuang.peng
	 * @param authcToken
	 */
	public void validCode(UsernamePasswordToken token) {
		// 判断验证码
		Session session = SecurityUtils.getSubject().getSession();
		String code = (String)session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
		if (token.isApi()) {
			return;
		}
		if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(code)){
			throw new CaptchaException("验证码错误");
		}
	}
	
	/**
	 * 构造用户的权限字符
	 * @author: weihuang.peng
	 * @param permissions
	 * @return
	 */
	public SimpleAuthorizationInfo constructInfo(List<String> permissions) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		for (String permission : permissions) {
			if (StringUtils.isNotBlank(permission)) {
				info.addStringPermission(permission);//遍历添加用户的权限信息进入对象中
			}
		}
		//并更新用户的登陆时间，登陆日期等等参数
		return info;
	}
	
	/**
	 * session关闭时候的操作
	 * @author: weihuang.peng
	 */
	public void invalidate() {
		SysPrincipal principal = SysUserUtils.getSysPrincipal();
		sysLoginLogService.updateLogoutLog(principal);
	}
	
	/**
	 * 设定密码校验的Hash算法与迭代次数
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		setCredentialsMatcher(retryMather);
	}

	/**
	 * 清空用户关联权限认证，待下次使用时重新加载
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清空用户关联权限认证，待下次使用时重新加载
	 */
	public void clearCachedAuthorizationInfo(Principal principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清空所有关联认证
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}
}