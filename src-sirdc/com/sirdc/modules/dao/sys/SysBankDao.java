/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysLogDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年2月9日
 */
package com.sirdc.modules.dao.sys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.entity.sys.SysBank;
import com.sirdc.modules.filter.sys.SysBankFilter;

/**
 * 
 */
@Repository
public class SysBankDao extends StringDao<SysBank> {

	public List<SysBank> query(SysBankFilter filter){
		GenericQuery query = createQuery("obj");
		
		return query.listResult(filter);
	}

	/**
	 * 通过用户ID获取唯一对象
	 * @author: huiyang.yu
	 * @param userId
	 * @return
	 */
	public SysBank getByBankCode(String bankCode) {
		GenericQuery query = createQuery("obj");
		query.append("and obj.bankCode=:bankCode").setParam("bankCode", bankCode);
		return query.singleResult();
	}
}