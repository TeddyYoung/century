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
import com.sirdc.modules.entity.sys.SysUserRelate;
import com.sirdc.modules.filter.sys.SysUserRelateFilter;

/**
 * 
 */
@Repository
public class SysUserRelateDao extends StringDao<SysUserRelate> {

	public List<SysUserRelate> query(final SysUserRelateFilter filter) {
		GenericQuery query = createQuery("obj");

		return query.listResult(filter);
	}

}
