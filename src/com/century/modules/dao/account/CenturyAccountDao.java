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

import com.century.modules.entity.account.CenturyAccount;
import com.century.modules.filter.account.CenturyAccountFilter;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年9月21日
 */
@Repository
public class CenturyAccountDao extends StringDao<CenturyAccount> {
	
	public List<CenturyAccount> query(CenturyAccountFilter filter){
		GenericQuery query = createQuery("obj");
		
		return query.listResult(filter);
	}

	/**
	 * 通过用户ID获取唯一对象
	 * @author: huiyang.yu
	 * @param userId
	 * @return
	 */
	public CenturyAccount getByUserId(String userId) {
		GenericQuery query = createQuery("obj");
		query.append("and obj.userId=:userId").setParam("userId", userId);
		return query.singleResult();
	}
}
