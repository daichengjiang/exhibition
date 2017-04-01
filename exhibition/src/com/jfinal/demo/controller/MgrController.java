package com.jfinal.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.common.Constant;
import com.jfinal.core.Controller;
import com.jfinal.demo.model.User;
import com.jfinal.demo.util.CommonUtil;

public class MgrController extends Controller {
	
	/**
	 * 跳转到登录页面
	 */
	public void login(){
		render("login.jsp");
	}
	
	/**
	 * 跳转到管理后台主页
	 */
	public void index(){
		render("index.jsp");
	}
	
	/**
	 * 后台用户登录
	 */
	public void logon(){
		//声明登录结果map
		Map<String, Object> map = new HashMap<String, Object>();
		//判断验证码是否正确
		if (validateCaptcha("captcha")) {
			//获取登录用户名和密码
			User user = getModel(User.class);
			//登录查询sql
			String sql = "select * from sys_user where username = '" + user.getUsername() + "' and password = '" + user.getPassword() + "'";
			//执行查询操作
			User u = User.dao.findFirst(sql);
			if (u == null) {
				map.put(Constant.RESULT_CODE, -101);
				map.put(Constant.RESULT_MESSAGE, "用户名或密码错误");
			}else {
				getSession().setAttribute(Constant.CURRENT_USER, u);
				map.put(Constant.RESULT_CODE, 100);
				map.put(Constant.RESULT_MESSAGE, "登录成功,欢迎进入后台管理系统...");
				//获取登录ip
				u.setLastIp(CommonUtil.getIpAddr(getRequest()));
				
			}
		}else{
			map.put(Constant.RESULT_CODE, -100);
			map.put(Constant.RESULT_MESSAGE, "验证码不正确");
		}
		renderJson(map);
	}
	
	/**
	 * 退出后台管理系统
	 */
	public void logout(){
		//获取session中的在线用户信息
		User user = (User)getSession().getAttribute(Constant.CURRENT_USER);
		//如果user不为空，则清除user
		if (user != null) {
			//清除session中的在线用户信息
			getSession().removeAttribute(Constant.CURRENT_USER);
		}
		redirect("/mgr/login.html");
	}
	
	
	/**
	 * 生成图形验证码
	 */
	public void captcha(){
		renderCaptcha();
	}
	
	
}
