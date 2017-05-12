package com.century.modules.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
	private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

	/**
	 * 获取请求ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static void UserInfoSync(String reqURL, Map<String, String> params)
			throws IOException {
		// CloseableHttpAsyncClient httpclient =
		// HttpAsyncClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(3000).setConnectTimeout(10000).build();
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
				.setDefaultRequestConfig(requestConfig).build();
		try {
			httpclient.start();
			HttpPost httpPost = new HttpPost(reqURL);
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			Set<Entry<String, String>> set = params.entrySet();
			Iterator<Entry<String, String>> it = set.iterator();
			while (it.hasNext()) {
				Entry<String, String> entry = it.next();
				formparams.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(
					formparams, "UTF-8");
			httpPost.setEntity(uefEntity);
			final CountDownLatch latch = new CountDownLatch(1);
			httpclient.execute(httpPost, new FutureCallback<HttpResponse>() {
				@Override
				public void completed(final HttpResponse response) {
					latch.countDown();
					HttpEntity entity = response.getEntity();
					String responseContent = "";
					if (entity != null) {
						try {
							responseContent = EntityUtils.toString(entity,
									"UTF-8");
						} catch (ParseException e) {
						} catch (IOException e) {
						}
					}
					latch.countDown();
					if (log.isDebugEnabled()) {
						log.debug(response.getStatusLine().toString());
						log.debug(responseContent);
					}
				}

				@Override
				public void failed(final Exception ex) {
					latch.countDown();
					log.error(ex.getMessage());
				}

				@Override
				public void cancelled() {
					if (log.isDebugEnabled()) {
						log.debug("cancelled");
					}
				}
			});
			try {
				latch.await();
			} catch (InterruptedException e) {
			}
		} finally {
			httpclient.close();
		}
		if (log.isDebugEnabled()) {
			log.debug("Done");
		}
	}
}
