package com.priv.gabriel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
public class MailController {

	@Autowired
	JavaMailSender jms;

	@RequestMapping("/send")
	public String send(){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("814180366@qq.com");
		message.setTo("youarephoenix@163.com");
		message.setSubject("我来自SpringBoot");
		message.setText("这是一封测试邮件，感谢您的查看");
		jms.send(message);
		return "发送成功!";
	}
}