/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyFriendShipService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月6日
 */
package com.century.modules.service.social;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.century.modules.dao.social.CenturyFriendShipDao;
import com.century.modules.entity.social.CenturyFriendShip;
import com.century.modules.filter.social.CenturyFriendShipFilter;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;

/**
 * 好友关系表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
@Service
public class CenturyFriendShipService extends StringPKService<CenturyFriendShip> {

	@Autowired
	private SysTableService sysTableService;

	@Autowired
	private CenturyFriendShipDao friendShipDao;

	@Override
	protected BaseDao<CenturyFriendShip, String> getDao() {

		return friendShipDao;
	}

	@Override
	protected void beforeSave(CenturyFriendShip entity) {
		entity.setSysId(sysTableService.updateMaxSysId("t_sjt_friend", ""));
		super.beforeSave(entity);
	}

	public List<CenturyFriendShip> query(CenturyFriendShipFilter filter) {
		return friendShipDao.query(filter);
	}
	
	
	/**
	 * 查询是否是好友关系
	 * 如果不是好友返回 null
	 * @author: huiyang.yu
	 * @return
	 */
	public CenturyFriendShip getFriend(String userId ,String friendId){
		return friendShipDao.getFriend(userId, friendId);
	}
	
	/**
	 * 删除朋友关系
	 * @author: huiyang.yu
	 * @param userId
	 * @param friendId
	 */
	public void deleteFriendShip(String userId, String friendId){
		CenturyFriendShip  userShip = friendShipDao.getFriend(userId, friendId);
		CenturyFriendShip  fUsertShip = friendShipDao.getFriend(friendId, userId);
		
		if (userShip != null) {
			delete(userShip);
		}
		if (fUsertShip != null) {
			delete(fUsertShip);
		}
		
	}
}
