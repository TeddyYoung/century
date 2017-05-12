/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyAccountDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月21日
 */
package com.century.modules.dao.account;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.century.modules.entity.account.CenturyPaymentInfo;
import com.century.modules.filter.account.CenturyPaymentInfoFilter;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.utils.StringUtils;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年9月21日
 */
@Repository
public class CenturytPaymentInfoDao extends StringDao<CenturyPaymentInfo> {
	
	public List<CenturyPaymentInfo> query(CenturyPaymentInfoFilter filter){
		GenericQuery query = createQuery("obj");
		if (StringUtils.isNotBlank(filter.getStatus())) {
			query.append(" and obj.status = :status ").setParam("status",filter.getStatus());
		}
		if (StringUtils.isNotBlank(filter.getUserId())) {
			query.append(" and obj.userId = :userId ").setParam("userId",filter.getUserId());
		}
		return query.listResult(filter);
	}

	/**
	 * 
	 * @param cloudOrderNo
	 * @return
	 */
	public CenturyPaymentInfo getByCloudOrderNo(String cloudOrderNo) {
		GenericQuery query = createQuery("obj");
		query.append("and obj.cloudOrderNo=:cloudOrderNo").setParam("cloudOrderNo", cloudOrderNo);
		return query.singleResult();
	}
	
	public CenturyPaymentInfo getByOrderNo(String orderNo) {
		GenericQuery query = createQuery("obj");
		query.append("and obj.bizOrderNo=:bizOrderNo").setParam("bizOrderNo", orderNo);
		return query.singleResult();
	}
}
