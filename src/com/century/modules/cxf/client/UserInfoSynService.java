package com.century.modules.cxf.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jodd.http.HttpBrowser;
import jodd.http.HttpRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.century.modules.util.HttpUtil;
import com.sirdc.modules.utils.StringUtils;
import com.sirdc.modules.utils.encrypt.Base64Utils;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class UserInfoSynService {

	private static Logger log = LoggerFactory
			.getLogger(UserInfoSynService.class);

	private static UserInfoSynService userInfoSynService = new UserInfoSynService();

	private String uri = "http://senewing.oicp.net:8080/p2p/android/userInfoSyn";

	public static UserInfoSynService getUserInfoSynService() {
		return userInfoSynService;
	}

	private UserInfoSynService() {
		super();
	}

	/**
	 * 注册
	 * 
	 * @author: huiyang.yu
	 * @param userInfo
	 */
	public void synRegister(UserInfo userInfo) {
		userInfo.setOperate("1");
		userInfo.setPassword(Base64Utils.encode(userInfo.getPassword()
				.getBytes()));
		if (StringUtils.isNotBlank(userInfo.getSex())) {
			if (userInfo.getSex().equals("F")) {
				userInfo.setSex("0");
			} else if (userInfo.getSex().equals("M")) {
				userInfo.setSex("1");
			}
		}
		// String[] result = getSynService().userInfoSyn(userInfo);
		// System.out.println(result[0]);
		// System.out.println(result[1]);

		HttpBrowser browser = new HttpBrowser();
		HttpRequest request = HttpRequest.post(uri);
		request = setquery(userInfo, request);
		browser.sendRequest(request);
		String page = browser.getPage();
		System.out.println(page);
	};

	/**
	 * 修改用户信息
	 * 
	 * @author: huiyang.yu
	 * @param userInfo
	 */
	public void synUpdateDetail(UserInfo userInfo) {
		userInfo.setOperate("2");
		if (StringUtils.isNotBlank(userInfo.getSex())) {
			if (userInfo.getSex().equals("F")) {
				userInfo.setSex("0");
			} else if (userInfo.getSex().equals("M")) {
				userInfo.setSex("1");
			}
		}
		// String[] result = getSynService().userInfoSyn(userInfo);
		// System.out.println(result[0]);
		// System.out.println(result[1]);

		HttpBrowser browser = new HttpBrowser();
		HttpRequest request = HttpRequest.post(uri);
		request = setquery(userInfo, request);
		browser.sendRequest(request);
		String page = browser.getPage();
		System.out.println(page);
	};

	/**
	 * 认证
	 * 
	 * @author: huiyang.yu
	 * @param userInfo
	 */
	public void synAttestation(UserInfo userInfo) {
		userInfo.setOperate("3");
		if (StringUtils.isNotBlank(userInfo.getSex())) {
			if (userInfo.getSex().equals("F")) {
				userInfo.setSex("0");
			} else if (userInfo.getSex().equals("M")) {
				userInfo.setSex("1");
			}
		}
		// String[] result = getSynService().userInfoSyn(userInfo);
		// System.out.println(result[0]);
		// System.out.println(result[1]);

		HttpBrowser browser = new HttpBrowser();
		HttpRequest request = HttpRequest.post(uri);
		request = setquery(userInfo, request);
		browser.sendRequest(request);
		String page = browser.getPage();
		System.out.println(page);
	};

	/**
	 * 修改密码
	 * 
	 * @author: huiyang.yu
	 * @param userInfo
	 */
	public void synChangePwd(UserInfo userInfo) {
		userInfo.setOperate("4");
		userInfo.setPassword(Base64Utils.encode(userInfo.getPassword()
				.getBytes()));
		if (StringUtils.isNotBlank(userInfo.getSex())) {
			if (userInfo.getSex().equals("F")) {
				userInfo.setSex("0");
			} else if (userInfo.getSex().equals("M")) {
				userInfo.setSex("1");
			}
		}
		// String[] result = getSynService().userInfoSyn(userInfo);
		// System.out.println(result[0]);
		// System.out.println(result[1]);

		HttpBrowser browser = new HttpBrowser();
		HttpRequest request = HttpRequest.post(uri);
		request = setquery(userInfo, request);
		browser.sendRequest(request);
		String page = browser.getPage();
		System.out.println(page);
	};

	private HttpRequest setquery(UserInfo userInfo, HttpRequest request) {
		request.header("Accept-Charset", "utf-8");
		request.header("Content-Type", "text/html;charset=UTF-8");
		request.query("age", userInfo.getAge());
		request.query("birthday", userInfo.getBirthday());
		request.query("city", userInfo.getCity());
		request.query("cultural", userInfo.getCultural());
		request.query("custName", userInfo.getCustName());
		request.query("haveChild", userInfo.getHaveChild());
		request.query("idCard", userInfo.getIdCard());
		request.query("loginName", userInfo.getLoginName());
		request.query("mail", userInfo.getMail());
		request.query("marital", userInfo.getMarital());
		request.query("mobile", userInfo.getMobile());
		request.query("nativeAddr", userInfo.getNativeAddr());
		request.query("operate", userInfo.getOperate());
		request.query("password", userInfo.getPassword());
		request.query("sex", userInfo.getSex());
		return request;
	}

	public void userInfoSync(UserInfo userInfo) {
		if (userInfo == null) {
			return;
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("operate", userInfo.getOperate());
		params.put("loginName", userInfo.getLoginName());
		params.put("mobile", userInfo.getMobile());
		params.put("mail", userInfo.getMail());
		// params.put("refereeName", userInfo.getRefereeName());
		params.put("idCard", userInfo.getIdCard());
		params.put("age", userInfo.getAge());
		params.put("birthday", userInfo.getBirthday());
		params.put("custName", userInfo.getCustName());
		params.put("cultural", userInfo.getCultural());
		// params.put("telephone", userInfo.getTelephone());
		if (StringUtils.isNotBlank(userInfo.getSex())) {
			if (userInfo.getSex().equals("F")) {
				params.put("sex", "0");
			} else if (userInfo.getSex().equals("M")) {
				params.put("sex", "1");
			}
		}

		params.put("marital", userInfo.getMarital());
		params.put("haveChild", userInfo.getHaveChild());
		params.put("nativeAddr", userInfo.getNativeAddr());
		params.put("city", userInfo.getCity());
		if (StringUtils.isNotEmpty(userInfo.getPassword())) {
			params.put("password",
					Base64.encode(userInfo.getPassword().getBytes()));
		}
		// TODO 用户数据同步
		/*try {
			HttpUtil.UserInfoSync(uri, params);
		} catch (IOException e) {
			log.error("用户数据同步失败：mobile" + userInfo.getMobile());
		}*/
	}
}
