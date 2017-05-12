/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyConsumeDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年7月8日
 */
package com.century.modules.dao.merchant;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.century.modules.databean.merchant.UserAndMerchantDatabean;
import com.century.modules.entity.merchant.CenturyMerchant;
import com.century.modules.filter.merchant.CenturyMerchantFilter;
import com.century.modules.util.Constants;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.utils.StringUtils;

/**
 * 
 */
@Repository
public class CenturyMerchantDao extends StringDao<CenturyMerchant> {
	
	public List<CenturyMerchant> query(CenturyMerchantFilter filter){
		GenericQuery query = createQuery("obj");
		
		return query.listResult(filter);
	}
	
	public List<CenturyMerchant> queryJoinUser(CenturyMerchantFilter filter){
		GenericQuery query = create("select m from SysUser user , CenturyMerchant m where 1=1");
		query.append(" and user.sysId = m.sysId ");
		query.append(" and user.isMerchant = :isMerchant ").setParam("isMerchant", Constants.YES_FLAG);
		if (StringUtils.isNotBlank(filter.getMobile())) {
			query.append(" and user.mobile = :mobile ").setParam("mobile", filter.getMobile());
		}
		return query.listResult(filter);
	} 
	
	public List<UserAndMerchantDatabean> queryUserAndMerchant(CenturyMerchantFilter filter){
		GenericQuery query = create("select new com.century.modules.databean.merchant.UserAndMerchantDatabean(u, m) from SysUser u , CenturyMerchant m where 1=1");
		query.append(" and u.sysId = m.sysId ");
		
		if (StringUtils.isNotBlank(filter.getMobile())) {
			query.append(" and u.mobile = :mobile ").setParam("mobile", filter.getMobile());
		}
		
		if (StringUtils.isNotBlank(filter.getMerchantName())) {
			query.append(" and m.merchantName like :merchantName ").setParam("merchantName", StringUtils.getLikewords(filter.getMerchantName()));
		}
		
		return query.listResult(filter);
	} 
	
}
