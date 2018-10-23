package com.priv.gabriel.controller;

import com.priv.gabriel.entity.User;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @Author: Gabriel
 * @Date: 2018-10-23
 * @Description:
 */
@RestController
@RequestMapping("/users")
public class UserController {

	List<User> users = new ArrayList();

	@RequestMapping(value = "",method = RequestMethod.GET)
	public List getUsers(){

		User user1 = new User();
		user1.setId(1);
		user1.setUsername("asd");
		user1.setNickname("王小花");
		user1.setAge(18);
		User user2 = new User();
		user2.setId(2);
		user2.setUsername("zxc");
		user2.setNickname("李铁蛋");
		user2.setAge(17);
		users.add(user1);
		users.add(user2);
		return users;
	}

	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ApiImplicitParam(name = "id" ,value = "用户id" ,paramType = "path",dataType = "Integer")
	public User getUserById(@PathVariable("id") int id){
		User user = users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
		return user;
	}

	@RequestMapping(value ="",method = RequestMethod.POST)
	public String addUser(User user){
		users.add(user);
		return "新增成功";
	}

	@RequestMapping(value = "",method = RequestMethod.PUT)
	public String updateUser(){
		return "修改成功";
	}

	@RequestMapping(value = "",method = RequestMethod.DELETE)
	public String delUser(){
		return "删除成功";
	}
}