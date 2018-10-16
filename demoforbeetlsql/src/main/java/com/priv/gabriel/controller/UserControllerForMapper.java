package com.priv.gabriel.controller;

import com.priv.gabriel.entity.User;
import com.priv.gabriel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @Author: Gabriel
 * @Date: 2018-10-14
 * @Description:
 */
@RestController
@RequestMapping("/mapper/users")
public class UserControllerForMapper {

	@Autowired
	private UserRepository repository;

	@RequestMapping(value = {"","/"},method = RequestMethod.POST)
	public void addUser(User user){
		repository.insert(user);
	}

	@RequestMapping(value = {"","/"},method = RequestMethod.DELETE)
	public String deleteUserById(User user){
		if(repository.deleteById(user) >0 ){
			return "删除成功";
		}else{
			return "删除失败";
		}
	}

	@RequestMapping(value = {"","/"},method = RequestMethod.PUT)
	public String updateUserById(User user){
		//repository.updateById(user)
		if(repository.deleteById(user) > 0){
			return "修改成功";
		}else{
			return "修改失败";
		}
	}

	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public User selectUserById(@PathVariable("id")int id){
		//repository.unique(id);
		return repository.single(id);
	}

	@RequestMapping(value = {"","/"},method = RequestMethod.GET)
	public List<User> getUsers(){
		//repository.all(1,2);
		//repository.allCount();
		return repository.all();
	}

	@RequestMapping(value="/test",method = RequestMethod.GET)
	public List<User> getUsersByTest(){
		return repository.selectByTest();
	}
}