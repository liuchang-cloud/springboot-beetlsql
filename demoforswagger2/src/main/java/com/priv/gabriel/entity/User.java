package com.priv.gabriel.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @Author: Gabriel
 * @Date: 2018-10-23
 * @Description:
 */
@Data
public class User implements Serializable{

	private static final long serialVersionUID = 6071491163583185734L;

	private int id;

	private String username;

	private String nickname;

	private int age;

}