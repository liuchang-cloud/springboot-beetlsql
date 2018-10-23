package com.priv.gabriel.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * Created with Intellij IDEA.
 *
 * @Author: Gabriel
 * @Date: 2018-10-23
 * @Description:
 */
@Data
public class User {

	@NotBlank(message = "name不允许为空")
	@Length(min = 2,max = 10,message = "你的长度不对劲呀")
	private String name;
	@NotNull(message = "进入未成年人入内！")
	@Min(18)
	private int age;
	@NotBlank(message = "拒绝黑户")
	private String address;

}