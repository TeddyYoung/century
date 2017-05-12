/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLoginDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月22日
 */
package com.sirdc.modules.dao.sys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.entity.sys.SysLogin;
import com.sirdc.modules.filter.sys.SysUserFilter;
import com.sirdc.modules.sys.filter.SysLoginFilter;
import com.sirdc.modules.utils.ValidUtils;


/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年1月22日
 */
@Repository
public class SysLoginDao extends StringDao<SysLogin> {

	
	/**
	 * 查看全部的用户登陆信息
	 * @author: weihuang.peng
	 * @param filter
	 * @return
	 */
	public List<SysLogin> query(SysLoginFilter filter) {
		GenericQuery query = createQuery("obj");
		return query.listResult(filter);
	}

	/**
	 * @param token
	 * @return
	 */
	public SysLogin getUserByLoginName(String token) {
		SysLogin user = null;
		
		//判断是否为电话号码
		if (ValidUtils.valid(token, ValidUtils.PHONE)) {
			String hql = "from SysLogin login where login.mobile=:mobile";
			GenericQuery query = create(hql);
			query.setParam("mobile", token);
			user = query.singleResult();
		} else {
			String hql = "from SysLogin login where login.sysId=:userId";
			GenericQuery query = create(hql);
			query.setParam("userId", token);
			user = query.singleResult();
		}

		return user;
	}

	/**
	 * @author: weihuang.peng
	 * @param mail
	 * @return
	 */
	public SysLogin getLoginByEmail(String mail) {
		String hql = "from SysLogin login where login.email=:email";
		GenericQuery query = create(hql);
		query.setParam("email", mail);
		return query.singleResult();
	}

	/**
	 * @author: weihuang.peng
	 * @param mobile
	 * @return
	 */
	public SysLogin getLoginByMobile(String mobile) {
		String hql = "from SysLogin login where login.mobile=:mobile";
		GenericQuery query = create(hql);
		query.setParam("mobile", mobile);
		return query.singleResult();
	}
	
	/**
	 * @author: weihuang.peng
	 * @param userId
	 * @return
	 */
	public SysLogin getLoginByUserId(String userId) {
		String hql = "from SysLogin login where login.sysId=:userId";
		GenericQuery query = create(hql);
		query.setParam("userId", userId);
		return query.singleResult();
	}

	/**
	 * @author: weihuang.peng
	 * @param filter
	 * @return
	 */
	public List<String> queryLoginByRoleDept(SysUserFilter filter) {
		GenericQuery query = create("select sysId from SysLogin where roleId=:roleId and deptId=:deptId");
		query.setParam("roleId", filter.getRoleId());
		query.setParam("deptId", filter.getDeptId());
		return query.listResult();
	}

	/**
	 * @author: weihuang.peng
	 * @param userId
	 * @param roleId
	 * @param deptId
	 * @return
	 */
	public boolean isExistUserPermission(String userId, String roleId, String deptId) {
		GenericQuery query = create("from SysLogin where roleId=:roleId and deptId=:deptId and sysId=:userId");
		query.setParam("roleId", roleId);
		query.setParam("deptId", deptId);
		query.setParam("userId", userId);
		return (query.count() > 0);
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param userName
	 * @param password
	 * @return
	 */
	public SysLogin getSjctSysLogin(String mobile, String password) {
		GenericQuery query = create("from SysLogin obj where obj.mobile=:mobile and obj.sjctPassword=:sjctPassword");
		query.setParam("mobile", mobile);
		query.setParam("sjctPassword", password);
		return query.singleResult();
	}
}
