package com.priv.gabriel;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with Intellij IDEA.
 *
 * @Author: Gabriel
 * @Date: 2018-10-23
 * @Description:
 */
@Component("jpushConfig")
@Data
public class JpushConfig {

	@Value("${jpush.appKey}")
	private String appkey;
	@Value("${jpush.masterSecret}")
	private String masterSecret;
	@Value("${jpush.liveTime}")
	private String liveTime;
}