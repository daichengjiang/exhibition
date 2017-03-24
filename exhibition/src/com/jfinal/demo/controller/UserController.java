package com.jfinal.demo.controller;

import java.util.List;

import org.omg.CORBA.Request;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.demo.model.User;
import com.jfinal.demo.validator.LoginValidator;

public class UserController extends Controller {
	
	/**
	 * 跳转到登录页面
	 */
	public void login(){
		render("login.jsp");
	}
	
	/**
	 * 后台用户登录
	 */
	@Before(LoginValidator.class)
	public void logon(){
		//获取登录用户名和密码
		User user = getModel(User.class);
		//登录查询sql
		String sql = "select * from sys_user where username = '" + user.getUsername() + "' and password = '" + user.getPassword() + "'";
		//执行查询操作
		List<User> users = User.dao.find(sql);
		if (users.size() <= 0) {
			getRequest().setAttribute("message", "用户名或密码错误");
			forwardAction("/login");
		}else {
			getRequest().setAttribute("user", users.get(0).getUsername());
			redirect("/success.jsp");
		}
	}
	
}
