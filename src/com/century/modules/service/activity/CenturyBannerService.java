/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyBannerService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月6日
 */
package com.century.modules.service.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.century.modules.dao.activity.CenturyBannerDao;
import com.century.modules.entity.activity.CenturyBanner;
import com.century.modules.filter.activity.CenturyBannerFilter;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;

/**
 * 滚屏图片表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
@Service
public class CenturyBannerService extends StringPKService<CenturyBanner> {

	@Autowired
	private SysTableService sysTableService;

	@Autowired
	private CenturyBannerDao bannerDao;

	@Override
	protected BaseDao<CenturyBanner, String> getDao() {
		return bannerDao;
	}

	@Override
	protected void beforeSave(CenturyBanner entity) {
		entity.setSysId(sysTableService.updateMaxSysId("t_sjt_banner", ""));
	}

	public List<CenturyBanner> query(CenturyBannerFilter filter) {
		return bannerDao.query(filter);
	}

}
