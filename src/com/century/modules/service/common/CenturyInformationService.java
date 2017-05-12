/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyInformationService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月7日
 */
package com.century.modules.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.century.modules.dao.common.CenturyInformationDao;
import com.century.modules.entity.common.CenturyInformation;
import com.century.modules.filter.common.CenturyInformationFilter;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;
import com.sirdc.modules.utils.StringUtils;

/**
 * 文章信息表
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月7日
 */
@Service
public class CenturyInformationService extends StringPKService<CenturyInformation> {

	@Autowired
	private SysTableService sysTableService;

	@Autowired 
	private CenturyInformationDao informationDao;
	
	@Override
	protected BaseDao<CenturyInformation, String> getDao() {
		return informationDao;
	}
	@Override
	protected void beforeSave(CenturyInformation entity) {
		if(StringUtils.isBlank(entity.getSysId())){
			entity.setSysId(sysTableService.updateMaxSysId("t_sjt_information", ""));
		}
		
	}

	public List<CenturyInformation> query(CenturyInformationFilter filter) {
		return informationDao.query(filter);
	}
}
