package com.priv.gabriel.controller;

import com.priv.gabriel.service.JpushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with Intellij IDEA.
 *
 * @Author: Gabriel
 * @Date: 2018-10-23
 * @Description:
 */
@RestController
@RequestMapping("/push")
public class PushController {
	@Autowired
	JpushService jpushService;

	@RequestMapping("/start")
	public String start(){
		String title = "标题";
		String content = "内容";
		Map<String, String> extrasMap = new HashMap<String, String>();
		extrasMap.put("额外附加传递内容","App可以解析到，没有值仅实例化即可");
		jpushService.sendPush(title,content,extrasMap,"123");
		return "推送成功";
	}
}