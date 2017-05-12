/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyAccountService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月21日
 */
package com.century.modules.service.account;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.century.modules.dao.account.CenturyCardDao;
import com.century.modules.databean.account.CardBinDataBean;
import com.century.modules.databean.account.CardDataBean;
import com.century.modules.databean.account.CardRespDataBean;
import com.century.modules.entity.account.CenturyCard;
import com.century.modules.filter.account.CenturyCardFilter;
import com.century.modules.service.TlTransaction.TlTransactionService;
import com.century.modules.util.BizException;
import com.century.modules.util.BussConst;
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
public class CenturyCardService extends StringPKService<CenturyCard> {
	
	@Autowired
	private CenturyCardDao dao;
	
	@Autowired
	private SysTableService sysTableService;
	
	@Autowired
	private TlTransactionService tlTransactionService;
	
	@Override
	protected BaseDao<CenturyCard, String> getDao() {
		return dao;
	}
	
	@Override
	protected void beforeSave(CenturyCard entity) {
		entity.setSysId(sysTableService.updateMaxSysId("t_sjt_card", ""));
		super.beforeSave(entity);
	}
	
	public List<CenturyCard> query(CenturyCardFilter filter) {
		return dao.query(filter);
	}
	
	@Transactional
	public void applyBindBankCard(CardDataBean dataBean)throws BizException{
		String blankInfo = "不能为空";
		if(StringUtils.isBlank(dataBean.getBankCardNo())){
			throw new BizException("银行卡号"+blankInfo);
		}
		if(StringUtils.isBlank(dataBean.getUserId())){
			throw new BizException("UserId"+blankInfo);
		}
		if(StringUtils.isBlank(dataBean.getPhone())){
			throw new BizException("手机号"+blankInfo);
		}
		if(StringUtils.isBlank(dataBean.getUserName())){
			throw new BizException("姓名"+blankInfo);
		}
		if(StringUtils.isBlank(dataBean.getIdCard())){
			throw new BizException("身份证号"+blankInfo);
		}
		String bankCardNo = dataBean.getBankCardNo();
		CenturyCard card = dao.getByBankCardNo(bankCardNo);
		if(card != null && BussConst.CARD_BIND_STATE_BIND.equals(card)){
			throw new BizException("该卡号已绑定，不能重复绑定");
		}
		
		CardBinDataBean cardBin = tlTransactionService.getBankCardBin(bankCardNo);
		if(cardBin == null){
			throw new BizException("查无银行卡信息");
		}
		if(StringUtils.isBlank(cardBin.getCardType())){
			throw new BizException("卡号格式有误");
		}
		if(!BussConst.CARD_TYPE_DEPOSIT.equals(cardBin.getCardType())){
			throw new BizException("目前只支持绑定储蓄卡");
		}
		
		dataBean.setBankCode(cardBin.getBankCode());
		dataBean.setBankName(cardBin.getBankName());
		dataBean.setCardType(cardBin.getCardType());
		CardRespDataBean cardResp = tlTransactionService.applyBindBankCard(dataBean);
		
		if(card == null){
			card = new CenturyCard();
			card.setBankCardNo(bankCardNo);
			card.setBankCode(cardBin.getBankCode());
			card.setBankName(cardBin.getBankName());
			card.setBindState(BussConst.CARD_BIND_STATE_ORIGINAL);
			card.setCardType(cardBin.getCardType());
			card.setPhone(dataBean.getPhone());
			card.setUserId(dataBean.getUserId());
			card.setTranceNum(cardResp.getTranceNum());
			card.setTransDate(cardResp.getTransDate());
			save(card);
		}else{
			card.setPhone(dataBean.getPhone());
			card.setBindState(BussConst.CARD_BIND_STATE_ORIGINAL);
			card.setTranceNum(cardResp.getTranceNum());
			card.setTransDate(cardResp.getTransDate());
			update(card);
		}
	}
	@Transactional
	public void bindBankCard(CardDataBean dataBean)throws BizException{
		String blankInfo = "不能为空";
		if(StringUtils.isBlank(dataBean.getBankCardNo())){
			throw new BizException("银行卡号"+blankInfo);
		}
		if(StringUtils.isBlank(dataBean.getUserId())){
			throw new BizException("UserId"+blankInfo);
		}
		if(StringUtils.isBlank(dataBean.getPhone())){
			throw new BizException("手机号"+blankInfo);
		}
		if(StringUtils.isBlank(dataBean.getUserName())){
			throw new BizException("姓名"+blankInfo);
		}
		if(StringUtils.isBlank(dataBean.getIdCard())){
			throw new BizException("身份证号"+blankInfo);
		}
		String bankCardNo = dataBean.getBankCardNo();
		CenturyCard card = dao.getByBankCardNo(bankCardNo);
		if(card == null){
			throw new BizException("卡号与上一次填写不一致，请重新验证");
		}
		if(!dataBean.getPhone().equals(card.getPhone())){
			throw new BizException("手机号码验证不通过，请重新验证");
		}
		if(BussConst.CARD_BIND_STATE_BIND.equals(card)){
			throw new BizException("该卡号已绑定，不能重复绑定");
		}
		CardRespDataBean cardResp = new CardRespDataBean();
		cardResp.setTranceNum(card.getTranceNum());
		cardResp.setTransDate(card.getTransDate());
		
		card.setBindState(BussConst.CARD_BIND_STATE_BIND);
		card.setBindTime(new Date());
		
		tlTransactionService.bindBankCard(dataBean,cardResp);
		
	}
	
	@Transactional
	public void unbindBankCard(CardDataBean dataBean)throws BizException{
		if(StringUtils.isBlank(dataBean.getBankCardNo())){
			throw new BizException("银行卡号不能为空");
		}
		if(StringUtils.isBlank(dataBean.getUserId())){
			throw new BizException("UserId不能为空");
		}
		String bankCardNo = dataBean.getBankCardNo();
		CenturyCard card = dao.getByBankCardNo(bankCardNo);
		if(card == null || !dataBean.getUserId().equals(card.getUserId())){
			throw new BizException("获取不到该银行卡信息");
		}
		if(!BussConst.CARD_BIND_STATE_BIND.equals(card.getBindState())){
			throw new BizException("该卡已删除");
		}
		
		card.setBindState(BussConst.CARD_BIND_STATE_UNBIND);
		card.setUnbindTime(new Date());
		update(card);
		tlTransactionService.unbindBankCard(dataBean.getUserId(), bankCardNo);
	}
	
	public CenturyCard getByBankCardNo(String bankCardNo) {
		return dao.getByBankCardNo(bankCardNo);
	}
}
