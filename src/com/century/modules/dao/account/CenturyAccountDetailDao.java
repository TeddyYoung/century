/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyAccountDao.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月21日
 */
package com.century.modules.dao.account;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.century.modules.databean.account.AccountDetailAndTradeDataBean;
import com.century.modules.databean.account.AccountSumDataBean;
import com.century.modules.databean.account.TradeRecordQueryDataBean;
import com.century.modules.entity.account.CenturyAccountDetail;
import com.century.modules.entity.social.CenturyMsg;
import com.century.modules.filter.account.CenturyAccountDetailFilter;
import com.century.modules.util.BussConst;
import com.sirdc.modules.core.dao.GenericQuery;
import com.sirdc.modules.core.dao.StringDao;
import com.sirdc.modules.core.filter.Paging;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年9月21日
 */
@Repository
public class CenturyAccountDetailDao extends StringDao<CenturyAccountDetail> {
	
	public List<CenturyAccountDetail> query(CenturyAccountDetailFilter filter){
		GenericQuery query = createQuery("obj");
		query.append(" and obj.userId = :userId").setParam("userId", filter.getUserId());
		query.setOrder("txDate", GenericQuery.DESC);
		return query.listResult(filter);
	}
	
	public List<AccountDetailAndTradeDataBean> queryJoinTrade(CenturyAccountDetailFilter filter){
		GenericQuery query = create("select new com.century.modules.databean.account.AccountDetailAndTradeDataBean(d, t) from CenturyAccountDetail d , CenturyTrade t where 1=1");
		query.append(" and d.txNo = t.bizOrderNo ");
		query.append(" and d.userId = :userId").setParam("userId", filter.getUserId());
		query.setOrder("txDate", GenericQuery.DESC);
		return query.listResult(filter);
	} 
	
	public List<TradeRecordQueryDataBean> queryTradeRecord(final CenturyAccountDetailFilter filter) {
		final List<String> payStatus = new ArrayList<>();
		payStatus.add(BussConst.PAY_STATUS_PAYING);
		payStatus.add(BussConst.PAY_STATUS_FAILURE);
		BigInteger records = hibernateTemplate.execute(new HibernateCallback<BigInteger>() {
			@Override
			public BigInteger doInHibernate(Session session) throws HibernateException {
				String sql = "select count(1) from ("
						+ " select ad.tx_no as order_no,ad.tx_amt,tx_date,ad.accounting_type,trade_type,'' as status"
						+ " from t_sjt_account_detail ad where ad.user_id=:userId"
						+ " union select pi.biz_order_no as order_no, total_amount as tx_amt,order_time as tx_date, accounting_type,trade_type,status "
						+ " from t_sjt_payment_info pi where pi.user_id=:userId and pi.status in (:status)" 
						+ " ) trade";
				Query query = session.createSQLQuery(sql);
				query.setParameter("userId", filter.getUserId());
				query.setParameterList("status", payStatus);
				return (BigInteger) query.uniqueResult();
			}
		});
		
		final Paging paging = filter.getPaging();
		if (paging != null) {
			paging.setRecords(records.intValue());
		}
		
		return hibernateTemplate.execute(new HibernateCallback<List<TradeRecordQueryDataBean>>() {
			@Override
			public List<TradeRecordQueryDataBean> doInHibernate(Session session) throws HibernateException {
				String sql = "select trade.* from ("
						+ " select ad.tx_no as orderNo,ad.tx_amt as txAmt,tx_date as txDate,ad.accounting_type as accountingType,trade_type as tradeType,'' as status"
						+ " from t_sjt_account_detail ad where ad.user_id=:userId"
						+ " union select pi.biz_order_no as orderNo, total_amount as txAmt,order_time as txDate,accounting_type as accountingType,trade_type as tradeType,status "
						+ " from t_sjt_payment_info pi where pi.user_id=:userId and pi.status in (:status)" 
						+ " ) trade order by trade.txDate desc";
				Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(TradeRecordQueryDataBean.class));
				query.setParameter("userId", filter.getUserId());
				query.setParameterList("status", payStatus);
				query.setFirstResult(paging.getStartRow());
				query.setMaxResults(paging.getPageSize());
				return query.list();
			}
		});
	}
	
	public List<AccountSumDataBean> sumByTxDate(CenturyAccountDetailFilter filter){
		GenericQuery query = create("select new com.century.modules.databean.account.AccountSumDataBean(DATE_FORMAT(d.txDate,'%Y-%m'),sum(txAmt)) from CenturyAccountDetail d where 1=1");
		query.append(" and d.userId = :userId").setParam("userId", filter.getUserId());
		if(StringUtils.isNotBlank(filter.getType())){
			query.append(" and d.type = :type").setParam("type", filter.getType());
		}
		if (filter.getTxDate() != null && filter.getTxDate().size() != 0) {
			query.append(" and DATE_FORMAT(d.txDate,'%Y-%m') in (:txDate)");
			query.setParamList("txDate", filter.getTxDate());
		}
		query.append(" group by DATE_FORMAT(d.txDate,'%Y-%m')");
		query.append(" order by DATE_FORMAT(d.txDate,'%Y-%m') desc");
		List<AccountSumDataBean> list = query.listResult();
		return list;
	} 

}
