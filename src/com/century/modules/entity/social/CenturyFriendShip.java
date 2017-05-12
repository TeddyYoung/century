package com.century.modules.entity.social;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.sirdc.modules.core.entity.StringEntity;

/**
 * 好友关系表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
@Entity
@Table(name = "t_sjt_friend")
@SelectBeforeUpdate
@DynamicInsert(true)
// 动态插入
@DynamicUpdate(true)
// 动态更新
public class CenturyFriendShip extends StringEntity {

	private static final long serialVersionUID = 3531824368747680510L;
	
	/**
	 * 1正常
	 */
	public static final String STATE_NORMAL = "1";
	
	/**
	 * 2拉黑
	 */
	public static final String STATE_PULL_BLACK = "2";

	private String userId;// 用户ID
	private String friendId;// 好友ID
	private String state;// 状态

	/**
	 * 用户ID
	 * 
	 * @author: xiaoqin.huang
	 * @return the userId
	 */
	@Column(length = 50)
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
	@Column(length = 50)
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
	@Column(length = 50)
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
