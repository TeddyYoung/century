/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysUserUtil.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年4月30日
 */
package com.sirdc.modules.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.sirdc.modules.security.SysPrincipal;
import com.sirdc.modules.sys.util.UserUtils;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年4月30日
 */
public class SysUserUtils extends UserUtils {

	/**
	 * 获取用户的全部信息
	 * @author: weihuang.peng
	 * @return
	 */
	public static SysPrincipal getSysPrincipal() {
		Subject	subject = SecurityUtils.getSubject();
		if(subject == null) {
			return null;
		}
		return (SysPrincipal) subject.getPrincipal();
	}
	
	/**
	 * 获取部门ID
	 * @author: weihuang.peng
	 * @return
	 */
	public static String getDeptId() {
		SysPrincipal principal = getSysPrincipal();
		if (principal == null) {
			return "";
		}
		return principal.getDeptId();
	}
	
	/**
	 * 获取部门ID
	 * @author: weihuang.peng
	 * @return
	 */
	public static String getRoleId() {
		SysPrincipal principal = getSysPrincipal();
		if (principal == null) {
			return "";
		}
		return principal.getRoleId();
	}
	
	/**
	 * 获取默认角色ID
	 * @author: weihuang.peng
	 * @return
	 */
	public static String getDefaultRoleId() {
		SysPrincipal principal = getSysPrincipal();
		if (principal == null) {
			return "";
		}
		return principal.getDefaultRoleId();
	}
	
	/**
	 * 获取默认部门ID
	 * @author: weihuang.peng
	 * @return
	 */
	public static String getDefaultDeptId() {
		SysPrincipal principal = getSysPrincipal();
		if (principal == null) {
			return "";
		}
		return principal.getDefaultDeptId();
	}
}
