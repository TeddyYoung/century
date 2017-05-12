package com.century.modules.gateway.tl;

import ime.service.client.SOAClient;
import ime.service.util.RSAUtil;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.century.modules.util.BizException;

public class TlAPI {

	private static Logger log = LoggerFactory.getLogger(TlAPI.class);

	/**
	 * 行业代码
	 */
	public static final String INDUSTRY_CODE = "1914";
	/**
	 * 行业名称
	 */
	public static final String INDUSTRY_NAME = "交易市场";

	private static final String serverAddress = "http://122.227.225.142:23661/service/soa";

	private static final String sysid = "100000000004";

	private static final String pwd = "403941";

	private static final String alias = "100000000004";

	private static final String path = "C:\\conf\\100000000004.pfx";

	private static final String signMethod = "SHA1WithRSA";

	private static final String memberService = "MemberService";

	private static final String orderService = "OrderService";

	private static final SOAClient client = new SOAClient();

	private static PublicKey publicKey;

	private static PrivateKey privateKey;

	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	public static String ACCOUNT_NO = "200003";
	//生产
//	public static String ACCOUNT_NO = "2000040";

	static {
		try {
			privateKey = RSAUtil.loadPrivateKey(alias, path, pwd);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		try {
			publicKey = RSAUtil.loadPublicKey(alias, path, pwd);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		client.setServerAddress(serverAddress);
		client.setSignKey(privateKey);
		client.setPublicKey(publicKey);
		client.setSysId(sysid);
		client.setSignMethod(signMethod);
	};

	/**
	 * 通联请求服务
	 * 
	 * @param service
	 * @param method
	 * @param param
	 * @return
	 */
	public static JSONObject request(String service, String method,
			JSONObject param) {
		JSONObject response = new JSONObject();
		try {
			response = client.request(service, method, param);
		} catch (Exception e) {
			log.error("通联请求服务异常========" + e.getMessage());
			response.put("status", "error");
		}
		return response;
	}

	/**
	 * 会员类接口
	 * 
	 * @param method
	 * @param param
	 * @return
	 */
	public static JSONObject memberService(String method, JSONObject param) {
		JSONObject response = request(memberService, method, param);
		return response;
	}

	/**
	 * 订单类接口
	 * 
	 * @param method
	 * @param param
	 * @return
	 */
	public static JSONObject orderService(String method, JSONObject param) {
		JSONObject response = request(orderService, method, param);
		return response;
	}

	// RSA加密
	public static String rsaEncrypt(String str) throws Exception {
		try {
			if (publicKey == null) {
				try {
					publicKey = RSAUtil.loadPublicKey(alias, path, pwd);
				} catch (Exception e) {
				}
			}
			if (privateKey == null) {
				try {
					privateKey = RSAUtil.loadPrivateKey(alias, path, pwd);
				} catch (Exception e) {
				}
			}
			RSAUtil rsaUtil = new RSAUtil((RSAPublicKey) publicKey,
					(RSAPrivateKey) privateKey);
			String encryptStr = rsaUtil.encrypt(str);
			return encryptStr;
		} catch (Exception e) {
			throw e;
		}
	}

	// RSA解密
	public static String rsaDecrypt(String signStr) throws Exception {
		try {
			if (publicKey == null) {
				try {
					publicKey = RSAUtil.loadPublicKey(alias, path, pwd);
				} catch (Exception e) {
				}
			}
			if (privateKey == null) {
				try {
					privateKey = RSAUtil.loadPrivateKey(alias, path, pwd);
				} catch (Exception e) {
				}
			}
			RSAUtil rsaUtil = new RSAUtil((RSAPublicKey) publicKey,
					(RSAPrivateKey) privateKey);
			String dencryptStr = rsaUtil.dencrypt(signStr);
			return dencryptStr;
		} catch (Exception e) {
			throw e;
		}
	}

	public static JSONObject verifyCallBack(String rsp,String sign,
			String sysId,String timestamp) throws Exception {
		JSONObject result = new JSONObject(rsp);
		if (result.has("returnValue")) {
			String value = result.getString("returnValue");
//			String sign = result.getString("sign");
			if (!(RSAUtil.verify(publicKey, sysId+rsp+timestamp, sign)))
				throw new Exception("签名验证错误");
			if (("OK".equals(result.getString("status")))
					&& (!("null".equals(value)))) {
				JSONObject ret = new JSONObject(value);
				if (ret.has("$PrimitiveReturn$"))
					result.put("returnValue", ret.get("$PrimitiveReturn$"));
				else {
					result.put("returnValue", ret);
				}
			}
			return result;
		}
		return result;
	}

	/**
	 * 金额格式转换
	 * 
	 * @param amount
	 * @return
	 * @throws BizException
	 */
	public static Long amountConvert(BigDecimal amount) throws BizException {
		try {
			Long longAmout = amount.multiply(new BigDecimal(100)).longValue();
			return longAmout;
		} catch (Exception e) {
			throw new BizException("金额不正确");
		}
	}

	/**
	 * 获取订单过期时间
	 * 
	 * @return
	 */
	public static String getOrdErexpireDatetime() {
		// 订单过期时间
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, 10);
		Date date = calendar.getTime();
		return sdf.format(date);
	}
}
