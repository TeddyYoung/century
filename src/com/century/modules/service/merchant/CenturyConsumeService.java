/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyConsumeService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年7月8日
 */
package com.century.modules.service.merchant;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.century.modules.dao.merchant.CenturyConsumeDao;
import com.century.modules.entity.account.CenturyAccount;
import com.century.modules.entity.merchant.CenturyConsume;
import com.century.modules.entity.merchant.CenturyHistory;
import com.century.modules.filter.merchant.CenturyConsumeFilter;
import com.century.modules.service.account.CenturyAccountService;
import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.core.exceptions.ServiceException;
import com.sirdc.modules.entity.sys.SysUser;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;
import com.sirdc.modules.sys.util.UserUtils;
import com.sirdc.modules.util.SysUserUtils;

/**
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年7月8日
 */
@Service
public class CenturyConsumeService extends StringPKService<CenturyConsume> {
	
	@Autowired
	private CenturyConsumeDao centuryConsumeDao;
	
	@Autowired
	private SysTableService sysTableService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private CenturyAccountService accountService;
	
	@Autowired
	private CenturyHistoryService historyService;
	
	@Override
	protected BaseDao<CenturyConsume, String> getDao() {
		return centuryConsumeDao;
	}
	
	@Override
	protected void beforeSave(CenturyConsume entity) {
		entity.setSysId(sysTableService.updateMaxSysId("t_sjt_consume", ""));
		super.beforeSave(entity);
	}
	
	public List<CenturyConsume> query(CenturyConsumeFilter filter){
		return centuryConsumeDao.query(filter);
	}
	
	/**
	 * 支付并扣钱
	 * @author: huiyang.yu
	 * @param entity
	 * @param user
	 */
	public void updateAndDeductMoney(CenturyConsume centuryConsume, SysUser user){
		
		if (user.getAvaliableMoney().compareTo(centuryConsume.getBalance()) < 0 ) {
			throw new ServiceException("余额不足");
		}
		
		user.setAvaliableMoney(user.getAvaliableMoney().subtract(centuryConsume.getBalance()));
		sysUserService.update(user);
		
		centuryConsume.setState(CenturyConsume.STATE_PLACE_SECCESS);
		centuryConsume.setUserId(SysUserUtils.getUserId());
		update(centuryConsume);
		
	}

	/**
	 * 通过传入的商家ID，生成订单并记录流水
	 * @author: huiyang.yu
	 * @param id
	 */
	public String savePayment(CenturyConsumeFilter filter) {
		//扣钱 
		CenturyAccount userAcconut = accountService.getByUserId(UserUtils.getUserId());
		if (userAcconut == null) {
			return "1";//余额不足
		}
		if (userAcconut.getAvaliableAmt().compareTo(filter.getBalance()) < 0) {
			return "1";//余额不足
		}
		userAcconut.setAvaliableAmt(userAcconut.getAvaliableAmt().subtract(filter.getBalance()));
		accountService.update(userAcconut);
		//加钱
		CenturyAccount shopAcconut = accountService.getByUserId(filter.getShopId());
		if (shopAcconut == null) {
			shopAcconut = new CenturyAccount();
			shopAcconut.setAvaliableAmt(filter.getBalance());
			shopAcconut.setUserId(filter.getShopId());
			accountService.save(shopAcconut);
		}else {
			shopAcconut.setAvaliableAmt(shopAcconut.getAvaliableAmt().add(filter.getBalance()));
			accountService.update(shopAcconut);
		}
		//生成订单
		CenturyConsume centuryConsume = new CenturyConsume();
		centuryConsume.setShopId(filter.getShopId());
		centuryConsume.setUserId(UserUtils.getUserId());
		centuryConsume.setState(CenturyConsume.STATE_PLACE_SECCESS);
		centuryConsume.setConsumeType(CenturyConsume.CONSUME_TYPE_ONLINE);
		centuryConsume.setDemo(filter.getDemo());
		save(centuryConsume);
		
		//记录流水
		CenturyHistory userhistory = new CenturyHistory(); 
		userhistory.setOrderId(centuryConsume.getSysId());
		userhistory.setAmount(new BigDecimal(0).subtract(filter.getBalance()));
		userhistory.setOperationType(CenturyHistory.OPERATION_TYPE_CONSUME);
		userhistory.setUserId(UserUtils.getUserId());
		historyService.save(userhistory);
		
		CenturyHistory shophistory = new CenturyHistory(); 
		shophistory.setOrderId(centuryConsume.getSysId());
		shophistory.setAmount(filter.getBalance());
		shophistory.setOperationType(CenturyHistory.OPERATION_TYPE_USER_BUY);
		shophistory.setUserId(filter.getShopId());
		historyService.save(shophistory);
		
		return "0";
	}
	
	

}
