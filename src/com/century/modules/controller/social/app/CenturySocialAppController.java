/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturySocialAppController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月10日
 */
package com.century.modules.controller.social.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.databean.social.MsgDataBean;
import com.century.modules.entity.social.CenturyFriendShip;
import com.century.modules.entity.social.CenturyMsg;
import com.century.modules.filter.social.CenturyFriendShipFilter;
import com.century.modules.filter.social.CenturyMsgFilter;
import com.century.modules.service.social.CenturyFriendShipService;
import com.century.modules.service.social.CenturyMsgService;
import com.century.modules.util.MapDistance;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.databean.sys.UserAndLoginUserDatabean;
import com.sirdc.modules.databean.sys.UserDataBean;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.filter.sys.SysUserFilter;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.sys.util.UserUtils;
import com.sirdc.modules.util.SysUserUtils;
import com.sirdc.modules.utils.StringUtils;

/**
 * 社交相关需要登录
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年6月10日
 */
@Controller
@RequestMapping(value = "/app/social/")
public class CenturySocialAppController extends JsonBaseController {
	
	@Autowired
	private CenturyFriendShipService friendShipService;
	
	@Autowired
	private CenturyMsgService msgService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Override
	protected String getView(String view) {
		return "/app/social/" + view;
	}
	
	/**
	 * 2.1.	好友查找
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findFriendList", method = RequestMethod.POST)
	public Message findFriendList(@ModelAttribute SysUserFilter filter) {
//		if (StringUtils.isBlank(filter.getMobile())) {
//			return coverMessage("500", "请求参数错误");
//		}
		if (StringUtils.isNotBlank(filter.getLatStr()) && StringUtils.isNotBlank(filter.getLngStr())) {
			if (StringUtils.isBlank(filter.getRaidus())) {
				filter.setRaidus("2000");//默认两公里范围内
			}
			SysUser user = sysUserService.getById(UserUtils.getUserId());
			user.setLatStr(filter.getLatStr());
			user.setLngStr(filter.getLngStr());
			sysUserService.merge(user);
			Map<String, String>  map = MapDistance.getAround(filter.getLatStr(), filter.getLngStr(), filter.getRaidus());
			
			filter.setMinLat(map.get("minLat"));
			filter.setMaxLat(map.get("maxLat"));
			filter.setMinLng(map.get("minLng"));
			filter.setMaxLng(map.get("maxLng"));
			
		}
		
		List<UserAndLoginUserDatabean> userAndLoginUserDatabeans = sysUserService.queryJoinUserLogin(filter);
		if (userAndLoginUserDatabeans == null || userAndLoginUserDatabeans.size() == 0) {
			return coverMessage("500", "查无用户");
		}
		
		List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
		for (UserAndLoginUserDatabean userAndLoginUserDatabean : userAndLoginUserDatabeans) {
			
			Map<String, Object> userMap = new HashMap<String, Object>();  
			SysUser user = userAndLoginUserDatabean.getSysUser();
			UserDataBean userDatabean = sysUserService.getUserDataBean(user.getSysId());
			userMap.put("user", userDatabean);
			
			if (StringUtils.isNotBlank(filter.getLatStr()) && StringUtils.isNotBlank(filter.getLngStr())) {
				String distance = MapDistance.getAboutDistance(user.getLatStr(), user.getLngStr(), filter.getLatStr(), filter.getLngStr(), "50");
				userMap.put("distance", distance);
			}else {
				userMap.put("distance", "");
			}
			userList.add(userMap);
		}
		return coverMessage("200", " ",userList);
	}
	
	/**
	 * 2.2.	好友添加请求
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addFriend", method = RequestMethod.POST)
	public Message addFriend(@ModelAttribute CenturyMsgFilter filter){
		if (StringUtils.isBlank(filter.getRecvUserId())) {
			return coverMessage("500", "请求参数错误");
		}
		SysUser recvUser = sysUserService.getById(filter.getRecvUserId());
		if (recvUser == null) {
			return coverMessage("500", "请求加为好友的用户不存在");
		}
		CenturyMsg msg = new CenturyMsg();
		msg.setSendUserId(SysUserUtils.getUserId());
		msg.setRecvUserId(recvUser.getSysId());
		
		if (msg.getSendUserId().equals(msg.getRecvUserId())) {
			return coverMessage("500", "您不能添加自己为好友");
		}
		
		CenturyFriendShip friendShip= friendShipService.getFriend(SysUserUtils.getUserId(), recvUser.getSysId());
		if (friendShip != null) {
			return coverMessage("500", "对方已经是您的好友");
		}
		
		if (StringUtils.isNotBlank(filter.getContent())) {
			msg.setContent(filter.getContent());
		}else {
			msg.setContent("");
		}
		msg.setReadState(CenturyMsg.READ_STATE_UNREAD);
		msg.setMsgType(CenturyMsg.MSG_TYPE_ADD_FRIEND);
		try {
			msgService.saveMsgAndNotice(msg);
		} catch (Exception e) {
			e.printStackTrace();
			return coverMessage("500", "请求加好友失败");
		}
		return coverMessage("200", "已经向用户发送添加好友请求");
	}
	
	/**
	 * 2.3.	同意好友请求
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/passFriend", method = RequestMethod.POST)
	public Message passFriend(@ModelAttribute CenturyMsgFilter filter){
		if ( StringUtils.isBlank(filter.getMsgSysId())) {
			return coverMessage("500", "请求参数错误");
		}
		CenturyMsg passMsg = msgService.getByLockId(filter.getMsgSysId());
		
		if (passMsg == null || passMsg.getReadState().equals(CenturyMsg.READ_STATE_READ) || passMsg.getSendUserId().equals("10086")) {
			return coverMessage("500", "该请求已过时");
		}
		
		CenturyFriendShip friendShip= friendShipService.getFriend(SysUserUtils.getUserId(), passMsg.getSendUserId());
		if (SysUserUtils.getUserId().equals(passMsg.getSendUserId())) {
			return coverMessage("500", "不能加自己为好友");
		}
		if (friendShip != null) {
			return coverMessage("500", "对方已经是您的好友");
		}
		try {
			msgService.saveFriendAndSendMsg(passMsg,SysUserUtils.getUserId(), passMsg.getSendUserId());
		} catch (Exception e) {
			e.printStackTrace();
			return coverMessage("500", "添加好友失败");
		}
		
		return coverMessage("200", "添加好友成功");
	}
	
	/**
	 * 2.4.	拒绝好友请求
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/rejectFriend", method = RequestMethod.POST)
	public Message rejectFriend(@ModelAttribute CenturyMsgFilter filter){
		if (StringUtils.isBlank(filter.getMsgSysId())) {
			return coverMessage("500", "请求参数错误");
		}
		
		CenturyMsg passMsg = msgService.getByLockId(filter.getMsgSysId());
		if (passMsg == null || passMsg.getReadState().equals(CenturyMsg.READ_STATE_READ)) {
			return coverMessage("500", "该请求已过时");
		}
		
		try {
			msgService.updateMsgAndSendRejectMsg(passMsg, SysUserUtils.getUserId(), passMsg.getSendUserId());
		} catch (Exception e) {
			e.printStackTrace();
			return coverMessage("500", "拒绝失败");
		}
		
		return coverMessage("200", "拒绝成功");
	}
	
	/**
	 * 2.5.	删除好友
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delFriend", method = RequestMethod.POST)
	public Message delFriend(@ModelAttribute CenturyFriendShipFilter filter){
		if (StringUtils.isBlank(filter.getFriendId())) {
			return coverMessage("500", "请求参数错误");
		}
		
		try {
			friendShipService.deleteFriendShip(SysUserUtils.getUserId(), filter.getFriendId());
			//通知更新通讯录
			CenturyMsg userInstructionMsg = new CenturyMsg();
			userInstructionMsg.setSendUserId(UserUtils.getUserId());
			userInstructionMsg.setRecvUserId(filter.getFriendId());
			userInstructionMsg.setContent(" ");
			userInstructionMsg.setReadState(CenturyMsg.READ_STATE_UNREAD);
			userInstructionMsg.setMsgType(CenturyMsg.INSTRUCTION_PHONE_LIST);
			msgService.saveMsgAndNotice(userInstructionMsg);
			
			CenturyMsg fUserInstructionMsg = new CenturyMsg();
			fUserInstructionMsg.setSendUserId(filter.getFriendId());
			fUserInstructionMsg.setRecvUserId(UserUtils.getUserId());
			fUserInstructionMsg.setContent(" ");
			fUserInstructionMsg.setReadState(CenturyMsg.READ_STATE_UNREAD);
			fUserInstructionMsg.setMsgType(CenturyMsg.INSTRUCTION_PHONE_LIST);
			msgService.saveMsgAndNotice(fUserInstructionMsg);
		} catch (Exception e) {
			e.printStackTrace();
			return coverMessage("500", "删除失败");
		}
		
		return coverMessage("200", "删除成功");
	}
	
	/**
	 * 2.6.	我的好友列表
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/friendList", method = RequestMethod.POST)
	public Message friendList(@ModelAttribute CenturyFriendShipFilter filter){
		filter.setUserId(SysUserUtils.getUserId());
		List<CenturyFriendShip>  friendShips = friendShipService.query(filter);
		if (friendShips == null || friendShips.size() == 0) {
			return coverMessage("500", "无更多数据");
		}
		List<UserDataBean> userList = new ArrayList<UserDataBean>();
		for (CenturyFriendShip centuryFriendShip : friendShips) {
			UserDataBean userDatabean = sysUserService.getUserDataBean(centuryFriendShip.getFriendId());
			userList.add(userDatabean);
		}
		
		return coverMessage("200", " ", userList );
	}
	
	/**
	 * 2.7.	消息发送接口
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
	public Message sendMsg(@ModelAttribute CenturyMsgFilter filter){
		if (StringUtils.isBlank(filter.getRecvUserId()) || StringUtils.isEmpty(filter.getContent()) || StringUtils.isBlank(filter.getMsgType())) {
			return coverMessage("500", "请求参数错误");
		}
		CenturyMsg msg  = new CenturyMsg();
		msg.setSendUserId(SysUserUtils.getUserId());
		msg.setRecvUserId(filter.getRecvUserId());
		msg.setContent(filter.getContent());
		msg.setReadState(CenturyMsg.READ_STATE_UNREAD);
		msg.setMsgType(filter.getMsgType());
		
		try {
			msgService.saveMsgAndNotice(msg);
		} catch (Exception e) {
			e.printStackTrace();
			return coverMessage("500", "发送失败");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sysId", msg.getSysId());
		return coverMessage("200", "发送成功", map);
	}
	
	/**
	 * 2.8.	更新消息为已读
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateMsgState", method = RequestMethod.POST)
	public Message updateMsgState(@ModelAttribute CenturyMsgFilter filter){
		if (StringUtils.isBlank(filter.getMsgSysId())) {
			return coverMessage("500", "请求参数错误");	
		}
		
		CenturyMsg msg = msgService.getById(filter.getMsgSysId());
		if (msg == null) {
			return coverMessage("500", "该消息不存在");
		}
		msg.setReadState(CenturyMsg.READ_STATE_READ);
		try {
			msgService.update(msg);
		} catch (Exception e) {
			e.printStackTrace();
			return coverMessage("500", "更新失败");
		}
		
		return coverMessage("200", "更新成功");
	}
	
	/**
	 * 2.9.	获取未读消息列表
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/msgList", method = RequestMethod.POST)
	public Message msgList(@ModelAttribute CenturyMsgFilter filter){
		
		List<MsgDataBean> msgList =  new ArrayList<MsgDataBean>();
		filter.setRecvUserId(SysUserUtils.getUserId());
		filter.setReadState(CenturyMsg.READ_STATE_UNREAD);
		List<CenturyMsg> centuryMsgs = msgService.query(filter);
		if (centuryMsgs == null || centuryMsgs.size() == 0) {
			return coverMessage("500", "无更多数据");
		}
		for (CenturyMsg centuryMsg : centuryMsgs) {
			MsgDataBean msgDataBean = new MsgDataBean(centuryMsg); 
			msgList.add(msgDataBean);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msgList", msgList);
		return coverMessage("200", " ", map);
	}
	
	/**
	 * 2.10.读取好友未读消息
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/msgListByFriend", method = RequestMethod.POST)
	public Message msgListByFriend(@ModelAttribute CenturyMsgFilter filter){
		if (StringUtils.isBlank(filter.getSendUserId())) {
			return coverMessage("500", "请求参数错误");	
		}
		List<MsgDataBean> msgList =  new ArrayList<MsgDataBean>();
		filter.setSendUserId(filter.getSendUserId());
		filter.setRecvUserId(SysUserUtils.getUserId());
		filter.setReadState(CenturyMsg.READ_STATE_UNREAD);
		
		List<String> msgTypeChatList = new ArrayList<String>();
		msgTypeChatList.add(CenturyMsg.MSG_TYPE_CHAT_TEXT);
		msgTypeChatList.add(CenturyMsg.MSG_TYPE_CHAT_SPEECH);
		msgTypeChatList.add(CenturyMsg.MSG_TYPE_CHAT_PICTURE);
		msgTypeChatList.add(CenturyMsg.MSG_TYPE_CHAT_FILE);
		filter.setUnreadState4friend(msgTypeChatList);
		
		List<CenturyMsg> centuryMsgs = msgService.query(filter);
		if (centuryMsgs == null || centuryMsgs.size() == 0) {
			return coverMessage("500", "无更多数据");
		}
		for (CenturyMsg centuryMsg : centuryMsgs) {
			MsgDataBean msgDataBean = new MsgDataBean(centuryMsg); 
			msgList.add(msgDataBean);
		}
		
		return coverMessage("200", " ", msgList);
	}
	
	/**
	 * 2.11.读取系统通知
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sysMsgList", method = RequestMethod.POST)
	public Message sysMsgList(@ModelAttribute CenturyMsgFilter filter){
		
		List<MsgDataBean> msgList =  new ArrayList<MsgDataBean>();
		filter.setRecvUserId(SysUserUtils.getUserId());
		filter.setReadState(CenturyMsg.READ_STATE_UNREAD);
		filter.setMsgType(CenturyMsg.MSG_TYPE_SYSMSG);
		List<CenturyMsg> centuryMsgs = msgService.query(filter);
		if (centuryMsgs == null || centuryMsgs.size() == 0) {
			return coverMessage("500", "无更多数据");
		}
		for (CenturyMsg centuryMsg : centuryMsgs) {
			MsgDataBean msgDataBean = new MsgDataBean(centuryMsg); 
			msgList.add(msgDataBean);
		}
		return coverMessage("200", " ", msgList);
	}
	
	/**
	 * 2.12.读取好友添加请求
	 * @author: huiyang.yu
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addMsgList", method = RequestMethod.POST)
	public Message addMsgList(@ModelAttribute CenturyMsgFilter filter){
		
		List<MsgDataBean> msgList =  new ArrayList<MsgDataBean>();
	
		filter.setRecvUserId(SysUserUtils.getUserId());
		filter.setReadState(CenturyMsg.READ_STATE_UNREAD);
		filter.setMsgType(CenturyMsg.MSG_TYPE_ADD_FRIEND);
		List<CenturyMsg> centuryMsgs = msgService.query(filter);
		if (centuryMsgs == null || centuryMsgs.size() == 0) {
			return coverMessage("500", "无更多数据");
		}
		for (CenturyMsg centuryMsg : centuryMsgs) {
			MsgDataBean msgDataBean = new MsgDataBean(centuryMsg); 
			msgList.add(msgDataBean);
		}
		return coverMessage("200", " ", msgList);
	}
}
