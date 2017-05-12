/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLogDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年2月9日
 */
package com.century.modules.dao.account;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.century.modules.entity.account.CenturyCard;
import com.century.modules.filter.account.CenturyCardFilter;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.utils.StringUtils;

/**
 * 
 */
@Repository
public class CenturyCardDao extends StringDao<CenturyCard> {

	public List<CenturyCard> query(CenturyCardFilter filter){
		GenericQuery query = createQuery("obj");
		if (StringUtils.isNotBlank(filter.getUserId())) {
			query.append(" and obj.userId =:userId");
			query.setParam("userId", filter.getUserId());
		}
		if (StringUtils.isNotBlank(filter.getBindState())) {
			query.append(" and obj.bindState =:bindState");
			query.setParam("bindState", filter.getBindState());
		}
		return query.listResult(filter);
	}
	
	public CenturyCard getByBankCardNo(String bankCardNo) {
		GenericQuery query = createQuery("obj");
		query.append("and obj.bankCardNo=:bankCardNo").setParam("bankCardNo", bankCardNo);
		return query.singleResult();
	}

}