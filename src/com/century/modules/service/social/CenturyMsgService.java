package com.century.modules.service.social;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.century.modules.dao.social.CenturyMsgDao;
import com.century.modules.entity.social.CenturyFriendShip;
import com.century.modules.entity.social.CenturyMsg;
import com.century.modules.filter.social.CenturyMsgFilter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.core.filter.Paging;
import com.sirdc.modules.core.mapper.JsonMapper;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.entity.sys.SysLogin;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.service.sys.SysLoginService;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;
import com.sirdc.modules.utils.StringUtils;

/**
 * 消息记录表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
@Service
public class CenturyMsgService extends StringPKService<CenturyMsg> {

	private static Logger log = LoggerFactory.getLogger(CenturyMsg.class);
	
	@Autowired
	private SysTableService sysTableService;
	
	@Autowired
	private CenturyMsgDao msgDao;
	
	@Autowired
	private CenturyFriendShipService friendShipService;
	
	@Autowired
	private CenturyPushService pushService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysLoginService sysLoginService;
	
	@Override
	protected BaseDao<CenturyMsg, String> getDao() {
		return msgDao;
	}

	@Override
	protected void beforeSave(CenturyMsg entity) {
		entity.setSysId(sysTableService.updateMaxSysId("t_sjt_msg", ""));
		super.beforeSave(entity);
	}

	public List<CenturyMsg> query(CenturyMsgFilter filter) {
		return msgDao.query(filter);
	}
	
	/**
	 * 保存并推送消息
	 * @author: huiyang.yu
	 * @param msg
	 */
	public void saveMsgAndNotice(CenturyMsg msg){
		save(msg);
		
		SysUser reveivedUser = sysUserService.getById(msg.getRecvUserId());
		Message message = new Message();
		message.setCode("101");
		message.setData(msg);
		JsonMapper mapper = new JsonMapper(Include.ALWAYS);
		String json = mapper.toJson(message); 
		
		try {
			pushService.pushTransmission(json, reveivedUser.getClientId());
		} catch (Exception e) {
			try {
				pushService.pushTransmission(json, reveivedUser.getClientId());
			} catch (Exception e2) {
				log.error("消息发送失败：" + msg);
			}
		}
	}
	
	/**
	 * 更新消息为已读，并且加好友发消息
	 * @author: huiyang.yu
	 * @param msg 消息对象
	 * @param userId 当前登录用户
	 * @param friendId 
	 */
	public void saveFriendAndSendMsg(CenturyMsg msg,String userId, String friendId){
		msg.setReadState(CenturyMsg.READ_STATE_READ);
		update(msg);
		
		//为t_sjt_friend 新增两条记录
		CenturyFriendShip userShip = new CenturyFriendShip();
		userShip.setUserId(userId);
		userShip.setFriendId(friendId);
		userShip.setState(CenturyFriendShip.STATE_NORMAL);
		friendShipService.save(userShip);
		
		CenturyFriendShip friendShip = new CenturyFriendShip();
		friendShip.setUserId(friendId);
		friendShip.setFriendId(userId);
		friendShip.setState(CenturyFriendShip.STATE_NORMAL);
		friendShipService.save(friendShip);
		
	    //为 t_sjt_msg 新增两条记录
		CenturyMsg userMsg = new CenturyMsg();
		userMsg.setSendUserId(userId);
		userMsg.setRecvUserId(friendId);
		userMsg.setContent("我们已经是好友了，现在可以对话了");
		userMsg.setReadState(CenturyMsg.READ_STATE_UNREAD);
		userMsg.setMsgType(CenturyMsg.MSG_TYPE_CHAT_TEXT);
		saveMsgAndNotice(userMsg);
		
		CenturyMsg fUserMsg = new CenturyMsg();
		fUserMsg.setSendUserId(friendId);
		fUserMsg.setRecvUserId(userId);
		fUserMsg.setContent("我们已经是好友了，现在可以对话了");
		fUserMsg.setReadState(CenturyMsg.READ_STATE_UNREAD);
		fUserMsg.setMsgType(CenturyMsg.MSG_TYPE_CHAT_TEXT);
		saveMsgAndNotice(fUserMsg);
		
		//通知更新通讯录
		CenturyMsg userInstructionMsg = new CenturyMsg();
		userInstructionMsg.setSendUserId(userId);
		userInstructionMsg.setRecvUserId(friendId);
		userInstructionMsg.setContent(" ");
		userInstructionMsg.setReadState(CenturyMsg.READ_STATE_UNREAD);
		userInstructionMsg.setMsgType(CenturyMsg.INSTRUCTION_PHONE_LIST);
		saveMsgAndNotice(userInstructionMsg);
		
		CenturyMsg fUserInstructionMsg = new CenturyMsg();
		fUserInstructionMsg.setSendUserId(friendId);
		fUserInstructionMsg.setRecvUserId(userId);
		fUserInstructionMsg.setContent(" ");
		fUserInstructionMsg.setReadState(CenturyMsg.READ_STATE_UNREAD);
		fUserInstructionMsg.setMsgType(CenturyMsg.INSTRUCTION_PHONE_LIST);
		saveMsgAndNotice(fUserInstructionMsg);
	}
	
	/**
	 * 更新消息为已读并发送拒绝消息
	 * @author: huiyang.yu
	 * @param msg 消息对象
	 * @param userId 当前登录用户
	 * @param friendId 
	 */
	public void updateMsgAndSendRejectMsg(CenturyMsg msg, String userId, String friendId){
		msg.setReadState(CenturyMsg.READ_STATE_READ);
		update(msg);
		
		SysUser user = sysUserService.getById(userId);
		SysLogin login = sysLoginService.getById(userId);

		CenturyMsg userMsg = new CenturyMsg();
		userMsg.setSendUserId(sysUserService.getSysMsgUser(user.getSysId()));
		userMsg.setRecvUserId(friendId);
		userMsg.setContent(StringUtils.defaultString(user.getNickName()) +"("+login.getMobile() +")"+ "拒绝了您的好友请求");
		userMsg.setReadState(CenturyMsg.READ_STATE_UNREAD);
		userMsg.setMsgType(CenturyMsg.MSG_TYPE_SYSMSG);
		saveMsgAndNotice(userMsg);
	}
	
	/**
	 * 根据用户查询每个好友最后一条未读消息
	 * @author: weihuang.peng
	 * @param filter
	 * @return
	 */
	public List<CenturyMsg> queryMsg(CenturyMsgFilter filter) {
		if (filter.getPaging() == null) {
			filter.setPaging(new Paging());
		}
		return msgDao.queryMsg(filter);
	}
	
	/**
	 * 设置好友的消息状态为已读
	 * @author: weihuang.peng
	 * @param sendUserId
	 * @param recvUserId
	 */
	public void updateFriendMsgRead(String sendUserId, String recvUserId) {
		msgDao.updateFriendMsgRead(sendUserId, recvUserId);
	}
	
	/**
	 * 获取我与好友之间的对话(历史)
	 * @author: weihuang.peng
	 * @param filter
	 * @return
	 */
	public List<CenturyMsg> queryMsgList(final CenturyMsgFilter filter) {
		if (filter.getPaging() == null) {
			filter.setPaging(new Paging());
		}
		return msgDao.queryMsgList(filter);
	}
	
	/**
	 * 获取所有的消息
	 * @author: weihuang.peng
	 * @param filter
	 * @return
	 */
	public List<CenturyMsg> queryAllMsgList(CenturyMsgFilter filter) {
		return msgDao.queryAllMsgList(filter);
	} 
}
