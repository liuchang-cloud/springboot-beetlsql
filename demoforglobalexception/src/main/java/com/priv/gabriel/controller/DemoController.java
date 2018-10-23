package com.priv.gabriel.controller;

import com.priv.gabriel.exception.TempException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with Intellij IDEA.
 *
 * @Author: Gabriel
 * @Date: 2018-10-23
 * @Description:
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("/index")
	public String index(){
		throw new TempException("111","没找到对象");
	}

}