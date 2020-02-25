package com.priv.gabriel.entity;

/**
 * Created with Intellij IDEA.
 *


 * @Description:
 */
public class User {

	private long id;

	private String username;

	private String nickname;

	private int age;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", nickname='" + nickname + '\'' +
				", age=" + age +
				'}';
	}

	public User(long id, String username, String nickname, int age) {
		this.id = id;
		this.username = username;
		this.nickname = nickname;
		this.age = age;
	}
}
