package com.priv.gabriel.controller;

import com.priv.gabriel.entity.User;
import io.swagger.annotations.ApiImplicitParam;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with Intellij IDEA.
 *
 * @Description:
 */
@RestController
@RequestMapping("/users")
public class UserController {

	List<User> users = new ArrayList();

	@RequestMapping(value = "",method = RequestMethod.GET)
	public List getUsers() throws Exception {

		User user1 = new User();
		user1.setId(1);
		user1.setUsername("asd");
		user1.setNickname("王小花");
		user1.setAge(18);
		users.add(user1);
		//导入到excel
		//1.构造数据
		List<User> list = new ArrayList<User>();
		list.add(user1);
		//2.创建工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		//3.构造sheet
		String[] titles = {"id", "username", "nickname","age"};
		Sheet sheet = workbook.createSheet();
		Row row = sheet.createRow(0);
		AtomicInteger headersAi = new AtomicInteger();
		for (String title : titles) {
			Cell cell = row.createCell(headersAi.getAndIncrement());
			cell.setCellValue(title);
		}
		AtomicInteger datasAi = new AtomicInteger(1);
		Cell cell = null;
		for (User user : list) {
			Row dataRow = sheet.createRow(datasAi.getAndIncrement());
			//编号
			cell = dataRow.createCell(0);
			cell.setCellValue(user.getId());
			//姓名
			cell = dataRow.createCell(1);
			cell.setCellValue(user.getUsername());
			cell = dataRow.createCell(2);
			cell.setCellValue(user.getNickname());
			cell = dataRow.createCell(3);
			cell.setCellValue(user.getAge());
		}
		//通过输出流将workbook对象下载到磁盘
		FileOutputStream out = new FileOutputStream("D:\\hehe.xlsx");
		workbook.write(out);
		out.flush();
		out.close();
		workbook.close();
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