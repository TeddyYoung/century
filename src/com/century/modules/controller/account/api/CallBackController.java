package com.century.modules.controller.account.api;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.century.modules.databean.account.PayBackDataBean;
import com.century.modules.gateway.tl.TlAPI;
import com.century.modules.service.account.CallBackService;
import com.century.modules.service.account.CenturyCardService;
import com.century.modules.service.account.CenturytPaymentInfoService;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.service.sys.SysLoginService;
import com.sirdc.modules.service.sys.SysUserService;
import com.sirdc.modules.util.JsonMapper;

/**
 * 
 */
@Controller
@RequestMapping("/api/account/callBack")
public class CallBackController extends JsonBaseController {

	private static Logger log = LoggerFactory
			.getLogger(CallBackController.class);

	@Autowired
	private SysUserService userService;
	@Autowired
	private SysLoginService loginService;
	@Autowired
	private CenturyCardService centuryCardService;
	@Autowired
	private CenturytPaymentInfoService centurytPaymentInfoService;
	@Autowired
	private CallBackService callBackService;

	@Override
	protected String getView(String view) {
		return "/api/account/callBack" + view;
	}

	@ResponseBody
	@RequestMapping(value = "/pay")
	public String pay(HttpServletRequest request) {
		String sysid = request.getParameter("sysid");
		String sign = request.getParameter("sign");
		String timestamp = request.getParameter("timestamp");
//		String v = request.getParameter("v");
		String rps = request.getParameter("rps");
		if(log.isDebugEnabled()){
			log.debug("通联通知开始================="
						+"sign:"+sign+"sysid:"+sysid+"timestamp:"+timestamp+"rps:"+rps);
		}
		try {
			JSONObject result = TlAPI.verifyCallBack(rps, sign,sysid, timestamp);
			JSONObject returnValue = result.getJSONObject("returnValue");
			JsonMapper jsonMapper = JsonMapper.getInstance();
			PayBackDataBean payback = jsonMapper.fromJson(JSONObject.valueToString(returnValue),
					PayBackDataBean.class);
			callBackService.pay(payback);
		} catch (Exception e) {
			log.error("回调异常=======" + e.getMessage());
			log.error("rps========"+rps);
			return null;
		}

		return "OK";
	}
}
