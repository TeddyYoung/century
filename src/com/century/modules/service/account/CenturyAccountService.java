/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyAccountService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月21日
 */
package com.century.modules.service.account;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.century.modules.dao.account.CenturyAccountDao;
import com.century.modules.entity.account.CenturyAccount;
import com.century.modules.entity.account.CenturyAccountDetail;
import com.century.modules.entity.account.CenturyTrade;
import com.century.modules.util.BizException;
import com.century.modules.util.BussConst;
import com.century.modules.vo.TradeVO;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;

/**
 * 
 * @author: yang
 * @version 
 * @Date: 
 */
@Service
public class CenturyAccountService extends StringPKService<CenturyAccount> {
	
	@Autowired
	private CenturyAccountDao dao;
	
	@Autowired
	private CenturyAccountDetailService centuryAccountDetailService;
	
	@Autowired
	private SysTableService sysTableService;
	
	@Autowired
	private CenturyTradeService centuryTradeService;
	
	@Override
	protected BaseDao<CenturyAccount, String> getDao() {
		return dao;
	}
	
	@Override
	protected void beforeSave(CenturyAccount entity) {
		entity.setSysId(sysTableService.updateMaxSysId("t_sjt_account", ""));
		super.beforeSave(entity);
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param userId
	 * @return
	 */
	public CenturyAccount getByUserId(String userId) {
		return dao.getByUserId(userId);
	}
	
	
	@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
	public synchronized void transferAccount(TradeVO tradeVO) throws BizException {
		if(StringUtils.isBlank(tradeVO.getUserId())) 
			throw new BizException("参数[userId]不能为空");
		if(StringUtils.isBlank(tradeVO.getBizOrderNo())) 
			throw new BizException("参数[bizOrderNo]不能为空");
		if(StringUtils.isBlank(tradeVO.getCloudOrderNo())) 
			throw new BizException("参数[cloudOrderNo]不能为空");
		if(tradeVO.getAmount() == null || tradeVO.getAmount().compareTo(BigDecimal.ZERO)<0)
			throw new BizException("转账金额[transferAmt]不能为小于零");
		if(StringUtils.isBlank(tradeVO.getTradeType())) 
			throw new BizException("参数[tradeType]不能为空");
		if(tradeVO.getAmount().compareTo(BigDecimal.ZERO)==0)
			return;
		String recieverId = tradeVO.getRecieverId();
		if(BussConst.TRADE_TYPE_DEPOSIT.equals(tradeVO.getTradeType())
				|| BussConst.TRADE_TYPE_WITHDRAW.equals(tradeVO.getTradeType())) {
			recieverId = tradeVO.getUserId();
		} 
		if(StringUtils.isBlank(recieverId)){
			throw new BizException("参数[recieverId]不能为空");
		}
		BigDecimal fee = tradeVO.getFee();
		if(fee == null){
			fee = BigDecimal.ZERO;
		}
		//开始处理转出金额
		if(!BussConst.TRADE_TYPE_DEPOSIT.equals(tradeVO.getTradeType())){//充值不转出
			CenturyAccount account = getByUserId(tradeVO.getUserId());
			
//			if(account.getAvaliableAmt().compareTo(tradeVO.getAmount().add(fee)) < 0){
//				throw new BizException("账户余额不足");
//			}
			BigDecimal avaliableAmt = account.getAvaliableAmt().subtract(tradeVO.getAmount());
			BigDecimal avaliableAmt1 = account.getAvaliableAmt().subtract(fee);
			
			account.setAvaliableAmt(avaliableAmt1);
			update(account);
			//记录账户明细
			CenturyAccountDetail accountDetail = new CenturyAccountDetail();
			accountDetail.setUserId(tradeVO.getUserId());
			accountDetail.setAvaliableAmt(avaliableAmt);
			accountDetail.setAccountId(account.getSysId());
			accountDetail.setTradeType(tradeVO.getTradeType());
			accountDetail.setFreezeAmt(account.getFreezeAmt());
			accountDetail.setTxAmt(tradeVO.getAmount().negate());
			accountDetail.setTxDate(new Date());
			accountDetail.setTxNo(tradeVO.getBizOrderNo());
			accountDetail.setAccountingType(BussConst.ACCOUNT_MOVEMENTS_TYPE_EXPENSE);
			centuryAccountDetailService.save(accountDetail);
			if(fee.compareTo(BigDecimal.ZERO) > 0){
				CenturyAccountDetail feeDetail = new CenturyAccountDetail();
				feeDetail.setUserId(tradeVO.getUserId());
				feeDetail.setAvaliableAmt(avaliableAmt1);
				feeDetail.setAccountId(account.getSysId());
				feeDetail.setTradeType(BussConst.TRADE_TYPE_CHARGE);
				feeDetail.setFreezeAmt(account.getFreezeAmt());
				feeDetail.setTxAmt(fee.negate());
				feeDetail.setTxDate(new Date());
				feeDetail.setTxNo(tradeVO.getBizOrderNo());
				feeDetail.setAccountingType(BussConst.ACCOUNT_MOVEMENTS_TYPE_EXPENSE);
				centuryAccountDetailService.save(feeDetail);
			}
			
		}
		//开始处理转入金额
		if(!BussConst.TRADE_TYPE_WITHDRAW.equals(tradeVO.getTradeType())){//提现不转入
			CenturyAccount recieverAccount = getByUserId(recieverId);	
			recieverAccount.setAvaliableAmt(recieverAccount.getAvaliableAmt().add(tradeVO.getAmount()));
			
			update(recieverAccount);
			//记录账户明细
			CenturyAccountDetail accountDetail = new CenturyAccountDetail();
			accountDetail.setUserId(recieverId);
			accountDetail.setAvaliableAmt(recieverAccount.getAvaliableAmt());
			accountDetail.setAccountId(recieverAccount.getSysId());
			accountDetail.setTradeType(tradeVO.getTradeType());
			accountDetail.setFreezeAmt(recieverAccount.getFreezeAmt());
			accountDetail.setTxAmt(tradeVO.getAmount());
			accountDetail.setTxDate(new Date());
			accountDetail.setTxNo(tradeVO.getBizOrderNo());
			accountDetail.setAccountingType(BussConst.ACCOUNT_MOVEMENTS_TYPE_INCOME);
			centuryAccountDetailService.save(accountDetail);
		}
		//记录交易流水
		CenturyTrade trade = new CenturyTrade();
		trade.setAmount(tradeVO.getAmount());
		trade.setBizOrderNo(tradeVO.getBizOrderNo());
		trade.setCloudOrderNo(tradeVO.getCloudOrderNo());
		trade.setCloudTradeNo(tradeVO.getCloudTradeNo());
		trade.setExtendInfo(tradeVO.getExtendInfo());
		trade.setFee(fee);
		trade.setGoodsDesc(tradeVO.getGoodsDesc());
		trade.setGoodsName(tradeVO.getGoodsName());
		trade.setOrderTime(tradeVO.getOrderTime());
		trade.setRecieverId(recieverId);
//		trade.setStatus(status);
		trade.setSummary(tradeVO.getSummary());
		trade.setUserId(tradeVO.getUserId());
		
		centuryTradeService.save(trade);
		
	}
	

}
