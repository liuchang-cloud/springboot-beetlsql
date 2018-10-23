package com.priv.gabriel.service.impl;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NettyHttpClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.priv.gabriel.JpushConfig;
import com.priv.gabriel.service.JpushService;
import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created with Intellij IDEA.
 *
 * @Author: Gabriel
 * @Date: 2018-10-23
 * @Description:
 */
@Service
public class JpushServiceImpl implements JpushService {

	private Logger LOG = LoggerFactory.getLogger(JpushServiceImpl.class);

	@Autowired
	JpushConfig jpushConfig;

	@Override
	public PushResult sendCustomPush(String title, String content, Map<String, String> extrasMap, String... alias) {
		System.out.println("======================================");
		System.out.println(jpushConfig.getAppkey());
		ClientConfig clientConfig = ClientConfig.getInstance();
		clientConfig.setTimeToLive(Long.valueOf(jpushConfig.getLiveTime()));
		JPushClient jPushClient = new JPushClient(jpushConfig.getMasterSecret(),jpushConfig.getAppkey(),null,clientConfig);
		PushPayload payload = buildCustomPushPayload(title,content,extrasMap,alias);
		PushResult result = null;
		try{
			result = jPushClient.sendPush(payload);
			LOG.info("极光推送结果 - " + result+",接收推送的别名列表:" + String.join(",", alias));
		} catch (APIConnectionException e) {
			LOG.error("极光推送连接错误，请稍后重试 ", e);
			LOG.error("Sendno: " + payload.getSendno());
		} catch (APIRequestException e) {
			LOG.error("极光服务器响应出错，请修复！ ", e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			LOG.info("Msg ID: " + e.getMsgId());
			LOG.info("以下存在不能识别的别名: " + String.join(",", alias));
			LOG.error("Sendno: " + payload.getSendno());
		}
		return result;
	}

	@Override
	public PushResult sendPush(String title, String content, Map<String, String> extrasMap, String... alias) {
		ClientConfig clientConfig = ClientConfig.getInstance();
		clientConfig.setTimeToLive(Long.valueOf(jpushConfig.getLiveTime()));
		// 使用NativeHttpClient网络客户端，连接网络的方式，不提供回调函数
		JPushClient jpushClient = new JPushClient(jpushConfig.getMasterSecret(), jpushConfig.getAppkey(), null,
				clientConfig);
		// 设置推送方式
		PushPayload payload = buildPushPayload(title, content, extrasMap, alias);
		PushResult result = null;
		try {
			result = jpushClient.sendPush(payload);
			LOG.info("极光推送结果 - " + result);
		} catch (APIConnectionException e) {
			LOG.error("极光推送连接错误，请稍后重试 ", e);
			LOG.error("Sendno: " + payload.getSendno());
		} catch (APIRequestException e) {
			LOG.error("极光服务器响应出错，请修复！ ", e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			LOG.info("Msg ID: " + e.getMsgId());
			LOG.info("以下存在不能识别别名: " + alias);
			LOG.error("Sendno: " + payload.getSendno());
		}
		return result;
	}

	@Override
	public void sendPushWithCallBack(String title, String content, Map<String, String> extrasMap, String... alias) {
		ClientConfig clientConfig = ClientConfig.getInstance();
		clientConfig.setTimeToLive(Long.valueOf(jpushConfig.getLiveTime()));
		String host = (String) clientConfig.get(ClientConfig.PUSH_HOST_NAME);
		NettyHttpClient client = new NettyHttpClient(
				ServiceHelper.getBasicAuthorization(jpushConfig.getAppkey(), jpushConfig.getMasterSecret()), null,
				clientConfig);
		try {
			URI uri = new URI(host + clientConfig.get(ClientConfig.PUSH_PATH));
			PushPayload payload = buildPushPayload(title, content, extrasMap, alias);
			client.sendRequest(HttpMethod.POST, payload.toString(), uri, new NettyHttpClient.BaseCallback() {
				@Override
				public void onSucceed(ResponseWrapper responseWrapper) {
					if (200 == responseWrapper.responseCode) {
						LOG.info("极光推送成功");
					} else {
						LOG.info("极光推送失败，返回结果: " + responseWrapper.responseContent);
					}
				}
			});
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			// 需要手动关闭Netty请求进程,否则会一直保留
			client.close();
		}
	}

	public PushPayload buildCustomPushPayload(String title, String content, Map<String, String> extrasMap, String... alias) {
		String[] newAlias = removeArrayEmptyElement(alias);
		return PushPayload.newBuilder().setPlatform(Platform.android_ios())
				.setAudience((null == newAlias || newAlias.length == 0) ? Audience.all() : Audience.alias(alias))
				.setMessage(Message.newBuilder().setTitle(title).setMsgContent(content).addExtras(extrasMap).build())
				.build();
	}

	private PushPayload buildPushPayload(String title, String content, Map<String, String> extrasMap, String... alias) {
		if (extrasMap == null || extrasMap.isEmpty()) {
			extrasMap = new HashMap<String, String>();
		}
		// 批量删除数组中空元素
		String[] newAlias = removeArrayEmptyElement(alias);
		return PushPayload.newBuilder().setPlatform(Platform.android_ios())
				// 别名为空，全员推送；别名不为空，按别名推送
				.setAudience((null == newAlias || newAlias.length == 0) ? Audience.all() : Audience.alias(alias))
				.setNotification(Notification.newBuilder().setAlert(content)
						.addPlatformNotification(
								AndroidNotification.newBuilder().setTitle(title).addExtras(extrasMap).build())
						.addPlatformNotification(IosNotification.newBuilder().incrBadge(1).addExtras(extrasMap).build())
						.build())
				.build();
	}

	private String[] removeArrayEmptyElement(String... strArray) {
		if (null == strArray || strArray.length == 0) {
			return null;
		}
		List<String> tempList = Arrays.asList(strArray);
		List<String> strList = new ArrayList<String>();
		Iterator<String> iterator = tempList.iterator();
		while (iterator.hasNext()) {
			String str = iterator.next();
			// 消除空格后再做比较
			if (null != str && !"".equals(str.trim())) {
				strList.add(str);
			}
		}
		// 若仅输入"",则会将数组长度置为0
		String[] newStrArray = strList.toArray(new String[strList.size()]);
		return newStrArray;
	}
}