/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: CenturyActivityController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年6月9日
 */
package com.century.modules.controller.activity.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.entity.activity.CenturyEnroll;
import com.century.modules.service.activity.CenturyActivityService;
import com.century.modules.service.activity.CenturyBannerService;
import com.century.modules.service.activity.CenturyEnrollService;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.util.SysUserUtils;
import com.sirdc.modules.utils.ObjectUtils;

/**
 * 
 * @author: huangcheng.dong
 * @version Revision: 0.0.1
 * @Date: 2015年6月10日
 */
@Controller
@RequestMapping("/app/activity")
public class CenturyActivityAppController extends JsonBaseController {

	@Autowired
	private CenturyActivityService centuryActivityService;

	@Autowired
	private CenturyBannerService centuryBannerService;

	@Autowired
	private CenturyEnrollService centuryEnrollService;

	@Override
	protected String getView(String view) {
		return "/app/activity" + view;
	}

	/**
	 * 活动报名表
	 * @author: huangcheng.dong
	 * @param activityId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/activityRegister/{activityId}", method = RequestMethod.POST)
	public Message activityRegister(@PathVariable String activityId) {
		String userId = SysUserUtils.getUserId();
		String state = CenturyEnroll.ENROLL_STATE_REGISTER;//报名
		CenturyEnroll enroll = centuryEnrollService.queryUserId(userId,activityId, state);
		if (ObjectUtils.isNotBlank(enroll)) {
			return coverMessage("500", "用户已经报名");
		}
		CenturyEnroll centuryEnroll = new CenturyEnroll();
		centuryEnroll.setActivityId(activityId);
		centuryEnroll.setUserId(userId);
		centuryEnroll.setState(state);
		centuryEnrollService.save(centuryEnroll);
		centuryEnrollService.updateDeductionCurrency(centuryEnroll);
		return coverMessage("200", "报名成功");
	}

	/**
	 * 活动签到
	 * @author: huangcheng.dong
	 * @param activityId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/activitySign/{activityId}", method = RequestMethod.POST)
	public Message activitySign(@PathVariable String activityId) {
		String userId = SysUserUtils.getUserId();
		String state = CenturyEnroll.ENROLL_STATE_SIGN; //签到
		CenturyEnroll enroll = centuryEnrollService.queryUserId(userId,activityId, state);
		if (ObjectUtils.isNotBlank(enroll)) {
			return coverMessage("500", "用户已签到");
		}
		state=CenturyEnroll.ENROLL_STATE_REGISTER;//报名
		CenturyEnroll activitySignEnroll = centuryEnrollService.queryUserId(userId, activityId, state);
		if (ObjectUtils.isNotBlank(activitySignEnroll)) {
			activitySignEnroll.setState(CenturyEnroll.ENROLL_STATE_SIGN);//将状态改变程签到
			centuryEnrollService.update(activitySignEnroll);			
			return coverMessage("200", "签到成功");
		}
		//新增一条签到记录
		CenturyEnroll centuryEnroll = new CenturyEnroll();
		centuryEnroll.setActivityId(activityId);
		centuryEnroll.setUserId(userId);
		centuryEnroll.setState(CenturyEnroll.ENROLL_STATE_SIGN); //签到
		centuryEnrollService.save(centuryEnroll);
		return coverMessage("200", "签到成功");
	}

}
