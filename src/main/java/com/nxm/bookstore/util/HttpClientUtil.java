package com.nxm.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientUtil {
	private static Logger logger = Logger.getLogger(HttpClientUtil.class);

	public static String sendGet(String url, int connectTimeout, int readTimeout) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String content = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(connectTimeout)
					.setConnectTimeout(readTimeout).build();
			httpGet.setConfig(requestConfig);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					content = EntityUtils.toString(entity);
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			logger.error("Get url faild, url: " + url, e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	public static String sendPost(String url, Map<String, String> parameterMap, int connectTimeout, int readTimeout, String encoding) {
		String content = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(connectTimeout)
				.setConnectTimeout(readTimeout).build();
		httppost.setConfig(requestConfig);
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		Set<Entry<String, String>> set = parameterMap.entrySet();
		for (Entry<String, String> entry : set) {
			formparams.add(new BasicNameValuePair(entry.getKey(), entry
					.getValue()));
		}
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, encoding);
			httppost.setEntity(uefEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					content = EntityUtils.toString(entity, encoding);
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			logger.error("Get url faild, url: " + url, e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	public static String sendPostFile(String url, Map<String, Object> parameterMap, int connectTimeout, int readTimeout, String encoding) {
		String content = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(connectTimeout)
					.setConnectTimeout(readTimeout).build();
			httppost.setConfig(requestConfig);

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();

			Iterator<String> iterator = parameterMap.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				Object value = parameterMap.get(key);
				if (value instanceof File) {
					builder.addBinaryBody(key, (File) value);
				} else if (value instanceof InputStream) {
					// builder.addBinaryBody(key, (InputStream)value);
					builder.addBinaryBody(key, (InputStream) value,
							ContentType.DEFAULT_BINARY,
							System.currentTimeMillis() + ".jpg");
				} else if (value instanceof byte[]) {
					builder.addBinaryBody(key, (byte[]) value);
				} else {
					if (value != null && !"".equals(value)) {
						builder.addTextBody(key, (String) value);
					} else {
						builder.addTextBody(key, "");
					}
				}

			}
			HttpEntity reqEntity = builder.build();
			httppost.setEntity(reqEntity);

			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					content = EntityUtils.toString(entity, encoding);
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

}
