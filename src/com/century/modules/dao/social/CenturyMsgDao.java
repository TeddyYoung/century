package com.century.modules.dao.social;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.century.modules.entity.social.CenturyMsg;
import com.century.modules.filter.social.CenturyMsgFilter;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.core.filter.Paging;
import com.sirdc.modules.utils.CollectionsUtils;
import com.sirdc.modules.utils.StringUtils;

/**
 * 消息记录表
 * 
 * @author: xiaoqin.huang
 * @version Revision: 0.0.1
 * @Date: 2015年6月6日
 */
@Repository
public class CenturyMsgDao extends StringDao<CenturyMsg> {
	public List<CenturyMsg> query(final CenturyMsgFilter filter) {
		GenericQuery query = createQuery("obj");
		if (StringUtils.isNotBlank(filter.getSendUserId())) {
			query.append(" and obj.sendUserId =:sendUserId");
			query.setParam("sendUserId", filter.getSendUserId());
			// 发送者ID
		}
		if (StringUtils.isNotBlank(filter.getRecvUserId())) {
			query.append(" and obj.recvUserId =:recvUserId");
			query.setParam("recvUserId", filter.getRecvUserId());
			// 接收者ID

		}
		if (StringUtils.isNotBlank(filter.getReadState())) {
			query.append(" and obj.readState =:readState");
			query.setParam("readState", filter.getReadState());
			// 阅读状态

		}
		if (StringUtils.isNotBlank(filter.getMsgType())) {
			query.append(" and obj.msgType =:msgType");
			query.setParam("msgType", filter.getMsgType());
			// 消息类别

		}
		if (filter.getUnreadState4friend() != null && filter.getUnreadState4friend().size() != 0) {
			query.append(" and obj.msgType in :unreadState4friend");
			query.setParamList("unreadState4friend", filter.getUnreadState4friend());
			// 文本消息类别
		}
		return query.listResult(filter);

	}

	/**
	 * 根据用户查询每个好友最后一条未读消息
	 * @author: weihuang.peng
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CenturyMsg> queryMsg(final CenturyMsgFilter filter) {
		final List<String> msgType = new ArrayList<>();
		msgType.add(CenturyMsg.MSG_TYPE_CHAT_FILE);
		msgType.add(CenturyMsg.MSG_TYPE_CHAT_PICTURE);
		msgType.add(CenturyMsg.MSG_TYPE_CHAT_SPEECH);
		msgType.add(CenturyMsg.MSG_TYPE_CHAT_TEXT);
		
		BigInteger records = hibernateTemplate.execute(new HibernateCallback<BigInteger>() {
			@Override
			public BigInteger doInHibernate(Session session) throws HibernateException {
				String sql = "select count(1) from (select * from (select * from t_sjt_msg t where t.recv_user_id=:recv_user_id and t.msg_type in :msg_type"
						+ " and t.read_state=1 ORDER BY t.sys_id desc) s GROUP BY s.send_user_id) tb";
				Query query = session.createSQLQuery(sql);
				query.setParameter("recv_user_id", filter.getRecvUserId());
				query.setParameterList("msg_type", msgType);
				return (BigInteger) query.uniqueResult();
			}
		});
		
		final Paging paging = filter.getPaging();
		if (paging != null) {
			paging.setRecords(records.intValue());
		}
		
		return hibernateTemplate.execute(new HibernateCallback<List<CenturyMsg>>() {
			@Override
			public List<CenturyMsg> doInHibernate(Session session) throws HibernateException {
				String sql = "select {s.*} from (select * from t_sjt_msg t where t.recv_user_id=:recv_user_id and t.msg_type in :msg_type"
						+ " and t.read_state=1 ORDER BY t.sys_id desc) s GROUP BY s.send_user_id";
				Query query = session.createSQLQuery(sql).addEntity("s", CenturyMsg.class);
				query.setParameter("recv_user_id", filter.getRecvUserId());
				query.setParameterList("msg_type", msgType);
				query.setFirstResult(paging.getStartRow());
				query.setMaxResults(paging.getPageSize());
				return query.list();
			}
		});
	}

	/**
	 * 更新好友的消息为已读
	 * @author: weihuang.peng
	 * @param sendUserId
	 * @param recvUserId
	 */
	public void updateFriendMsgRead(String sendUserId, String recvUserId) {
		GenericQuery query = create("update CenturyMsg msg set msg.readState=:readState where msg.recvUserId=:recvUserId and msg.sendUserId=:sendUserId");
		query.setParam("readState", CenturyMsg.READ_STATE_READ);
		query.setParam("recvUserId", recvUserId);
		query.setParam("sendUserId", sendUserId);
		query.executeUpdate();
	}
	
	/**
	 * 获取我与好友之间的对话
	 * @author: weihuang.peng
	 * @param filter
	 * @return
	 */
	public List<CenturyMsg> queryMsgList(final CenturyMsgFilter filter) {
		GenericQuery query = createQuery("obj");
		query.append(" and (obj.sendUserId =:sendUserId1 and obj.recvUserId =:recvUserId1) or (obj.sendUserId =:sendUserId2 and obj.recvUserId =:recvUserId2)");
		query.setParam("sendUserId1", filter.getSendUserId());
		query.setParam("recvUserId1", filter.getRecvUserId());
		query.setParam("sendUserId2", filter.getRecvUserId());
		query.setParam("recvUserId2", filter.getSendUserId());

		/*List<String> msgType = new ArrayList<>();
		msgType.add(CenturyMsg.MSG_TYPE_CHAT_FILE);
		msgType.add(CenturyMsg.MSG_TYPE_CHAT_PICTURE);
		msgType.add(CenturyMsg.MSG_TYPE_CHAT_SPEECH);
		msgType.add(CenturyMsg.MSG_TYPE_CHAT_TEXT);
		query.append(" and obj.msgType =:msgType");
		query.setParamList("msgType", msgType);*/
		
		
		query.setOrder("createDate", GenericQuery.DESC);
		query.setOrder("createTime", GenericQuery.DESC);
		return query.listResult(filter);
	}

	/**
	 * 获取所有的消息
	 * @author: weihuang.peng
	 * @param filter
	 * @return
	 */
	public List<CenturyMsg> queryAllMsgList(CenturyMsgFilter filter) {
		GenericQuery query = createQuery("obj");
		if (StringUtils.isNotBlank(filter.getSendUserId())) {
			query.append(" and obj.sendUserId =:sendUserId");
			query.setParam("sendUserId", filter.getSendUserId());
			// 发送者ID
		}
		if (StringUtils.isNotBlank(filter.getRecvUserId())) {
			query.append(" and obj.recvUserId =:recvUserId");
			query.setParam("recvUserId", filter.getRecvUserId());
			// 接收者ID

		}
		if (StringUtils.isNotBlank(filter.getReadState())) {
			query.append(" and obj.readState =:readState");
			query.setParam("readState", filter.getReadState());
			// 阅读状态

		}
		if (StringUtils.isNotBlank(filter.getMsgType())) {
			query.append(" and obj.msgType =:msgType");
			query.setParam("msgType", filter.getMsgType());
			// 消息类别

		}
		
		if (CollectionsUtils.isNotEmpty(filter.getMsgTypes())) {
			query.append(" and obj.msgType in :msgTypes");
			query.setParamList("msgTypes", filter.getMsgTypes());
		}
		return query.listResult();
	}
}
