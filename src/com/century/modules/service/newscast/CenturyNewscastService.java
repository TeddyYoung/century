/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyNewscastService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月6日
 */
package com.century.modules.service.newscast;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.century.modules.dao.newscast.CenturyNewscastDao;
import com.century.modules.entity.newscast.CenturyNewscast;
import com.century.modules.filter.newscast.CenturyNewscastFilter;
import com.century.modules.service.social.CenturyPushService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.core.mapper.JsonMapper;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;
import com.sirdc.modules.utils.StringUtils;

/**
 * 新闻广播表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
@Service
public class CenturyNewscastService extends StringPKService<CenturyNewscast> {
	
	private static Logger log = LoggerFactory.getLogger(CenturyNewscastService.class);
	
	@Autowired
	private SysTableService sysTableService;

	@Autowired
	private CenturyNewscastDao newscastDao;
	
	@Autowired
	private CenturyPushService centuryPushService;

	@Override
	protected BaseDao<CenturyNewscast, String> getDao() {
		return newscastDao;
	}

	@Override
	protected void beforeSave(CenturyNewscast entity) {
		entity.setSysId(sysTableService.updateMaxSysId("t_sjt_newscast", ""));
		super.beforeSave(entity);
	}

	public List<CenturyNewscast> query(CenturyNewscastFilter filter) {
		return newscastDao.query(filter);
	}
	
	/**
	 * 保存并推送
	 * @author: huiyang.yu
	 * @param entity
	 */
	public void saveNewscastAndNotice(CenturyNewscast entity){
		save(entity);
		
		//推送
		if (StringUtils.isNotEmpty(entity.getContent()) && entity.getContent().length() > 17) {
			entity.setContent(entity.getContent().substring(0, 17) + "...");
		}
		Message message = new Message();
		message.setCode("102");
		message.setData(entity);
		JsonMapper mapper = new JsonMapper(Include.ALWAYS);
		String json = mapper.toJson(message);
		try {
			centuryPushService.pushTransmission(json);
		} catch (Exception e) {
			try {
				centuryPushService.pushTransmission(json);
			} catch (Exception e2) {
				log.error("消息发送失败：" + entity);
			}
		}
	}
}
