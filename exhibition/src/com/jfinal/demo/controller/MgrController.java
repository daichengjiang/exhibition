package com.jfinal.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.common.Constant;
import com.jfinal.core.Controller;
import com.jfinal.demo.interceptor.MgrLoginInterceptor;
import com.jfinal.demo.model.Operatelog;
import com.jfinal.demo.model.User;
import com.jfinal.demo.util.CommonUtil;
import com.jfinal.plugin.activerecord.Db;

/**
 * 后台控制层Controller
 * @author admin
 *
 */
@Before(MgrLoginInterceptor.class)
public class MgrController extends Controller {

	/**
	 * 跳转到登录页面
	 */
	public void login() {
		render("login.jsp");
	}

	/**
	 * 跳转到管理后台主页
	 */
	public void index() {
		render("index.jsp");
	}

	/**
	 * 后台用户登录
	 */
	public void logon() {
		// 声明登录结果map
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断验证码是否正确
		if (validateCaptcha("captcha")) {
			// 获取登录用户名和密码
			User user = getModel(User.class);
			// 登录查询sql
			String sql = "select * from sys_user where username = '" + user.getUsername() + "' and password = '"
					+ CommonUtil.encodePassword(user.getPassword(), "md5") + "'";
			// 执行查询操作
			User u = User.dao.findFirst(sql);
			if (u == null) {
				map.put(Constant.RESULT_CODE, 5);
				map.put(Constant.RESULT_MESSAGE, "用户名或密码错误");
			} else {
				getSession().setAttribute(Constant.CURRENT_USER, u);
				map.put(Constant.RESULT_CODE, 6);
				map.put(Constant.RESULT_MESSAGE, "登录成功,欢迎进入后台管理系统...");
				// 更新上次登录IP
				Db.update("update sys_user set lastip = ? where id = ?", CommonUtil.getIpAddr(getRequest()), u.getId());
			}
		} else {
			map.put(Constant.RESULT_CODE, 5);
			map.put(Constant.RESULT_MESSAGE, "验证码不正确");
		}
		renderJson(map);
	}

	/**
	 * 退出后台管理系统
	 */
	public void logout() {
		// 获取session中的在线用户信息
		User user = (User) getSession().getAttribute(Constant.CURRENT_USER);
		// 如果user不为空，则清除user
		if (user != null) {
			// 清除session中的在线用户信息
			getSession().removeAttribute(Constant.CURRENT_USER);
		}
		redirect("/mgr/login.html");
	}

	/**
	 * 重置密码
	 */
	public void resetPwd() {
		// 获取用户重置密码
		String password = getPara("password");
		System.out.println("加密前:" + password);
		// md5加密密码
		password = CommonUtil.encodePassword(password, "md5");
		System.out.println("加密后:" + password);
		//获取当前登录用户
		User currentUser = CommonUtil.getCurrentUser(getSession());
		//返回信息map
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 更新数据
		int result = Db.update("update sys_user set password = ? where id = ?", password,currentUser.getId());
		if (result > 0) {
			resultMap.put(Constant.RESULT_CODE, 6);
			resultMap.put(Constant.RESULT_MESSAGE, "重置密码成功");
			// 写操作日志
			Operatelog operateLog = new Operatelog();
			operateLog.setOperateType("重置密码");
			operateLog.setOperateContent("重置管理后台登录密码为【加密前："+getPara("password")+"】-【加密后："+password+"】");
			operateLog.setOperateIP(CommonUtil.getIpAddr(getRequest()));
			operateLog.setOperator(CommonUtil.getCurrentUser(getSession()).getUsername());
			operateLog.setOperateTime(new Date());
			operateLog.save();
		} else {
			resultMap.put(Constant.RESULT_CODE, 5);
			resultMap.put(Constant.RESULT_MESSAGE, "重置密码失败");
		}
		renderJson(resultMap);
	}

	/**
	 * 生成图形验证码
	 */
	public void captcha() {
		renderCaptcha();
	}

	/**
	 * 视图跳转
	 */
	public void view() {
		// 获取视图名称
		String view = getPara("view");
		// 跳转到重置密码页面
		if ("restpwd".equals(view)) {
			render("restpwd.jsp");
		}
		// 跳转到个人资料页面
		if ("profile".equals(view)) {
			render("profile.jsp");
		}
	}
}
