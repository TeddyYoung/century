/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturySocialAController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	07 Jul,2015
 */
package com.century.modules.controller.social;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.databean.social.MsgDataBean;
import com.century.modules.entity.social.CenturyMsg;
import com.century.modules.filter.social.CenturyMsgFilter;
import com.century.modules.service.social.CenturyFriendShipService;
import com.century.modules.service.social.CenturyMsgService;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.JqGrid;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.sys.util.UserUtils;
import com.sirdc.modules.util.SysUserUtils;
import com.sirdc.modules.utils.StringUtils;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 07 Jul,2015
 */
@Controller
@RequestMapping(value = "/modules/century/social/")
public class CenturySocialController extends JsonBaseController {

	@Autowired
	private CenturyFriendShipService friendShipService;
	
	@Autowired
	private CenturyMsgService msgService;
	
	@Autowired
	private SysUserService userService;
	
	@Override
	protected String getView(String view) {
		return "/modules/century/social/" + view;
	}

	/**
	 * 初始化界面
	 * @return url
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initPage(Model model) {
		return getView("query");
	}
	
	/**
	 * @param filter
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public JqGrid query(@ModelAttribute CenturyMsgFilter filter) {
		
		filter.setRecvUserId(UserUtils.getUserId());
		filter.setReadState(CenturyMsg.READ_STATE_UNREAD);
		List<CenturyMsg> msgList = msgService.queryMsg(filter);
		List<Map<String, Object>> result = new ArrayList<>();
		
		//转换数据实体
		if (!msgList.isEmpty()) {
			for (CenturyMsg centuryMsg : msgList) {
				Map<String, Object> map = MsgDataBean.toMap(centuryMsg);
				
				SysUser sendUser = userService.getById(centuryMsg.getSendUserId());
				map.put("sendUserNickName", sendUser.getNickName());
				map.put("sendUserImage", sendUser.getImage());
				map.put("sendUserGender", sendUser.getGender());
				map.put("sendUserNativeAddr", sendUser.getNativeAddr());
				map.put("sendUserBirthday", sendUser.getBirthday());
				
				result.add(map);
			}
		}
		return coverJqGrid(filter, result);
	}
	
	@RequestMapping("/chat/{sendUserId}")
	public String toChat(Model model, @PathVariable String sendUserId) {
		if (StringUtils.isBlank(sendUserId)) {
			return initPage(model);
		}
		
		SysUser sendUser = userService.getById(sendUserId);
		SysUser recvUser = userService.getById(UserUtils.getUserId());
		
		model.addAttribute("recvUser", recvUser);
		model.addAttribute("sendUser", sendUser);
		return getView("form");
	}
	
	/**
	 * 读取消息历史
	 * @author: weihuang.peng
	 * @param filter
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/readMsgList")
	public Object readMsgList(@ModelAttribute CenturyMsgFilter filter) {
		msgService.updateFriendMsgRead(filter.getSendUserId(), SysUserUtils.getUserId());
		
		List<MsgDataBean> msgList =  new ArrayList<MsgDataBean>();
		filter.setSendUserId(filter.getSendUserId());
		filter.setRecvUserId(SysUserUtils.getUserId());
		
		List<String> msgTypeChatList = new ArrayList<String>();
		msgTypeChatList.add(CenturyMsg.MSG_TYPE_CHAT_TEXT);
		msgTypeChatList.add(CenturyMsg.MSG_TYPE_CHAT_SPEECH);
		msgTypeChatList.add(CenturyMsg.MSG_TYPE_CHAT_PICTURE);
		msgTypeChatList.add(CenturyMsg.MSG_TYPE_CHAT_FILE);
		
		List<CenturyMsg> centuryMsgs = msgService.queryMsgList(filter);
		if (centuryMsgs == null || centuryMsgs.size() == 0) {
			return coverMessage("500", "无更多数据");
		}
		for (CenturyMsg centuryMsg : centuryMsgs) {
			MsgDataBean msgDataBean = new MsgDataBean(centuryMsg); 
			msgList.add(msgDataBean);
		}
		return coverJqGrid(filter, msgList);
	}
	
	/**
	 * 读取所有未读的消息
	 * @author: weihuang.peng
	 * @param sendUserId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/readMsgList/{sendUserId}")
	public Object readUnReadMsgList(@PathVariable String sendUserId) {
		List<String> msgTypes = new ArrayList<>();
		msgTypes.add(CenturyMsg.MSG_TYPE_CHAT_FILE);
		msgTypes.add(CenturyMsg.MSG_TYPE_CHAT_PICTURE);
		msgTypes.add(CenturyMsg.MSG_TYPE_CHAT_SPEECH);
		msgTypes.add(CenturyMsg.MSG_TYPE_CHAT_TEXT);
		
		CenturyMsgFilter filter = new CenturyMsgFilter();
		filter.setSendUserId(sendUserId);
		filter.setRecvUserId(SysUserUtils.getUserId());
		filter.setReadState(CenturyMsg.READ_STATE_UNREAD);
		filter.setMsgTypes(msgTypes);
		
		List<CenturyMsg> centuryMsgs = msgService.queryAllMsgList(filter);
		
		msgService.updateFriendMsgRead(sendUserId, SysUserUtils.getUserId());
		return coverMessage("200", "", centuryMsgs);
	}
	
	@ResponseBody
	@RequestMapping("/sendMessage")
	public Object sendMessage(@ModelAttribute CenturyMsg msg) {
		msg.setSendUserId(UserUtils.getUserId());
		msg.setReadState(CenturyMsg.READ_STATE_UNREAD);
		msgService.saveMsgAndNotice(msg);
		return coverMessage("200", "" , msg);
	}
}