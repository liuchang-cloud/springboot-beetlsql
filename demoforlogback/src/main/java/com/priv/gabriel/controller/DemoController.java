package com.priv.gabriel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created with Intellij IDEA.
 *
 * @Author: Gabriel
 * @Date: 2018-10-14
 * @Description:
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

	@RequestMapping("/test")
	public void test(){
		logger.info("进入test方法");
		logger.debug("进入test方法");
		logger.warn("进入test方法");
		logger.error("进入test方法");
	}


}