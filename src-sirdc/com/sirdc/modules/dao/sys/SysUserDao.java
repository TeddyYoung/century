/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysUserDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月20日
 */
package com.sirdc.modules.dao.sys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.databean.sys.UserAndLoginUserDatabean;
import com.sirdc.modules.entity.sys.SysBank;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.filter.sys.SysUserFilter;
import com.sirdc.modules.utils.CollectionsUtils;
import com.sirdc.modules.utils.StringUtils;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年1月20日
 */
@Repository
public class SysUserDao extends StringDao<SysUser> {

	public List<SysUser> query(final SysUserFilter filter) {
		GenericQuery query = createQuery("obj");
		if (StringUtils.isNotBlank(filter.getName())) {
			query.append(" and obj.name like :name");
			query.setParam("name", StringUtils.getLikewords(filter.getName()));
		}
		
		if (CollectionsUtils.isNotEmpty(filter.getInIds())) {
			query.append(" and obj.sysId in (:ins)");
			query.setParamList("ins", filter.getInIds());
		}
		
		if (CollectionsUtils.isNotEmpty(filter.getNotInIds())) {
			query.append(" and obj.sysId not in (:notins)");
			query.setParamList("notins", filter.getNotInIds());
		}
		
		//如果不是超级管理员，则不显示超级管理员用户
		if(!filter.isSuperAdmin()) {
			query.append(" and obj.superAdmin = :superAdmin");
			query.setParam("superAdmin", false);
		}
		return query.listResult(filter);
	}

	/**
	 * 批量删除用户(帐号停用)
	 * @author: weihuang.peng
	 * @param ids
	 */
	public void batchDeleteUser(List<String> ids) {
		if (CollectionsUtils.isNotEmpty(ids)) {
			GenericQuery query = create("update SysUser user set user.state=:state").setParam("state", "03");
			query.append(" where user.id in (:ids)").setParamList("ids", ids);
			query.append(" and user.superAdmin != :superAdmin").setParam("superAdmin", true);
			query.executeUpdate();
		}
	}
	
	/**
	 * 关联登录表查数据
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	public List<UserAndLoginUserDatabean> queryJoinUserLogin(SysUserFilter filter){
		GenericQuery query = create("select new com.sirdc.modules.databean.sys.UserAndLoginUserDatabean(user, login) from SysUser user , SysLogin login where 1=1");
		query.append(" and user.sysId = login.sysId ");
		//电话号码查找
		if (StringUtils.isNotBlank(filter.getMobile())) {
			query.append(" and login.mobile = :mobile ").setParam("mobile", filter.getMobile());
		}
		
		/**经纬度范围查找 开始**/
		if (StringUtils.isNotBlank(filter.getMinLat())) {
			query.append(" and user.latStr >= :minLat ").setParam("minLat", filter.getMinLat());
		}
		
		if (StringUtils.isNotBlank(filter.getMaxLat())) {
			query.append(" and user.latStr <= :maxLat ").setParam("maxLat", filter.getMaxLat());
		}
		
		if (StringUtils.isNotBlank(filter.getMinLng())) {
			query.append(" and user.lngStr >= :minLng ").setParam("minLng", filter.getMinLng());
		}
		
		if (StringUtils.isNotBlank(filter.getMaxLng())) {
			query.append(" and user.lngStr <= :maxLng ").setParam("maxLng", filter.getMaxLng());
		}
		/**经纬度范围查找 结束**/
		if (StringUtils.isNotBlank(filter.getDeptId())) {
			query.append(" and login.deptId = :deptId ").setParam("deptId", filter.getDeptId());
		}
		if (StringUtils.isNotBlank(filter.getRoleId())) {
			query.append(" and login.roleId = :roleId ").setParam("roleId", filter.getRoleId());
		}
		if (StringUtils.isNotBlank(filter.getName())) {
			query.append(" and user.name like :name ").setParam("name", StringUtils.getLikewords(filter.getName()));
		}
		
//		query.append(" and login.state ").setParam("state", "1");
		
		return query.listResult(filter);
	} 
	
	public SysUser getByIdCard(String idCard) {
		GenericQuery query = createQuery("obj");
		query.append("and obj.idCard=:idCard").setParam("idCard", idCard);
		return query.singleResult();
	}
	
	public SysUser getByInviteCode(String inviteCode) {
		GenericQuery query = createQuery("obj");
		query.append("and obj.inviteCode=:inviteCode").setParam("inviteCode", inviteCode);
		return query.singleResult();
	}
	
	public SysUser getByMobile(String mobile) {
		GenericQuery query = createQuery("obj");
		query.append("and obj.mobile=:mobile").setParam("mobile", mobile);
		return query.singleResult();
	}
}
