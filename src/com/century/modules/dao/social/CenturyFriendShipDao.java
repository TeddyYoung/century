package com.century.modules.dao.social;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.century.modules.entity.social.CenturyFriendShip;
import com.century.modules.filter.social.CenturyFriendShipFilter;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.utils.StringUtils;

/**
 * 好友关系表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
@Repository
public class CenturyFriendShipDao extends StringDao<CenturyFriendShip> {
	public List<CenturyFriendShip> query(final CenturyFriendShipFilter filter) {
		GenericQuery query = createQuery("obj");
		if (StringUtils.isNotBlank(filter.getUserId())) {
			query.append(" and obj.userId =:userId");
			query.setParam("userId", filter.getUserId());
			// 用户ID
		}
		if (StringUtils.isNotBlank(filter.getFriendId())) {
			query.append(" and obj.friendId =:friendId");
			query.setParam("friendId", filter.getFriendId());
			// 好友ID
		}
		if (StringUtils.isNotBlank(filter.getState())) {
			query.append(" and obj.state =:state");
			query.setParam("state", filter.getState());
			// 状态
		}
		return query.listResult(filter);
	}
	
	/**
	 * 通过 userId =userId 和 friendId =friendId 查询唯一对象
	 * @author: huiyang.yu
	 * @param userId
	 * @param friendid
	 * @return
	 */
	public CenturyFriendShip getFriend(String userId ,String friendId) {
		GenericQuery query = createQuery("obj");
		query.append(" and obj.userId =:userId ").setParam("userId", userId);
		query.append(" and obj.friendId =:friendId ").setParam("friendId", friendId);
		return query.singleResult();
	}
}
