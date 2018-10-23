package com.priv.gabriel.service;

import cn.jpush.api.push.PushResult;

import java.util.Map;

/**
 * Created with Intellij IDEA.
 *
 * @Author: Gabriel
 * @Date: 2018-10-23
 * @Description:
 */
public interface JpushService {

	PushResult sendCustomPush(String title,String content,Map<String,String> extrasMap,String...alias);

	PushResult sendPush(String title,String content,Map<String,String> extrasMap,String...alias);

	void sendPushWithCallBack(String title, String content, Map<String, String> extrasMap, String... alias);

}