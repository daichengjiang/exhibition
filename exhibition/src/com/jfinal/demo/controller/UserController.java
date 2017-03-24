package com.jfinal.demo.controller;

import java.util.List;

import org.omg.CORBA.Request;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.demo.model.User;
import com.jfinal.demo.validator.LoginValidator;

public class UserController extends Controller {
	
	/**
	 * ��ת����¼ҳ��
	 */
	public void login(){
		render("login.jsp");
	}
	
	/**
	 * ��̨�û���¼
	 */
	@Before(LoginValidator.class)
	public void logon(){
		//��ȡ��¼�û���������
		User user = getModel(User.class);
		//��¼��ѯsql
		String sql = "select * from sys_user where username = '" + user.getUsername() + "' and password = '" + user.getPassword() + "'";
		//ִ�в�ѯ����
		List<User> users = User.dao.find(sql);
		if (users.size() <= 0) {
			getRequest().setAttribute("message", "�û������������");
			forwardAction("/login");
		}else {
			getRequest().setAttribute("user", users.get(0).getUsername());
			redirect("/success.jsp");
		}
	}
	
}
