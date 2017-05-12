package com.century.modules.filter.social;

import com.sirdc.modules.core.filter.BaseFilter;

/**
 * 好友关系表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
public class CenturyFriendShipFilter extends BaseFilter {

	private String userId;// 用户ID
	private String friendId;// 好友ID
	private String state;// 状态

	/**
	 * 用户ID
	 * 
	 * @author: xiaoqin.huang
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 用户ID
	 * 
	 * @author: xiaoqin.huang
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 好友ID
	 * 
	 * @author: xiaoqin.huang
	 * @return the friendId
	 */
	public String getFriendId() {
		return friendId;
	}

	/**
	 * 好友ID
	 * 
	 * @author: xiaoqin.huang
	 * @param friendId
	 *            the friendId to set
	 */
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	/**
	 * 状态
	 * 
	 * @author: xiaoqin.huang
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * 状态
	 * 
	 * @author: xiaoqin.huang
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

}
