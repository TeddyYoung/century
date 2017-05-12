package com.century.modules.service.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.century.modules.databean.account.PayBackDataBean;
import com.century.modules.entity.account.CenturyPaymentInfo;
import com.century.modules.util.BizException;
import com.century.modules.util.BussConst;
import com.century.modules.vo.TradeVO;

@Service
public class CallBackService {
	
	private static Logger log = LoggerFactory.getLogger(CallBackService.class);
	
	@Autowired
	private CenturytPaymentInfoService centurytPaymentInfoService;
	
	@Autowired
	private CenturyAccountService centuryAccountService;
	
	@Transactional
	public void pay(PayBackDataBean payBackDataBean)throws BizException{

		String orderNo = payBackDataBean.getOrderNo();
		CenturyPaymentInfo paymentInfo = centurytPaymentInfoService.getByCloudOrderNo(orderNo);
		if(paymentInfo == null){
			log.error("通知服务：订单号为"+orderNo+"不存在");
			throw new BizException("订单不存在");
		}
		if(BussConst.PAY_STATUS_PAYING.equals(paymentInfo.getStatus())){//
			
			paymentInfo.setStatus(BussConst.PAY_STATUS_SUCCESS);
			centurytPaymentInfoService.update(paymentInfo);
			
			TradeVO tradeVO = new TradeVO();
			tradeVO.setAmount(paymentInfo.getTotalAmount());
			tradeVO.setBizOrderNo(paymentInfo.getBizOrderNo());
			tradeVO.setCloudOrderNo(paymentInfo.getCloudOrderNo());
			tradeVO.setCloudTradeNo(orderNo);
			tradeVO.setExtendInfo(paymentInfo.getExtendInfo());
			tradeVO.setFee(paymentInfo.getFee());
			tradeVO.setGoodsDesc(paymentInfo.getGoodsDesc());
			tradeVO.setGoodsName(paymentInfo.getGoodsName());
			tradeVO.setOrderTime(paymentInfo.getOrderTime());
			tradeVO.setRecieverId(paymentInfo.getRecieverId());
			tradeVO.setSummary(paymentInfo.getSummary());
			tradeVO.setTradeType(paymentInfo.getTradeType());
			tradeVO.setUserId(paymentInfo.getUserId());
			tradeVO.setRecieverId(paymentInfo.getRecieverId());
			centuryAccountService.transferAccount(tradeVO);
		}else{
			log.error("通知服务：订单号为"+orderNo+"不是支付状态！");
			return;
		}
	}
	
}
