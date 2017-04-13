package com.jfinal.demo.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.common.Constant;
import com.jfinal.demo.controller.MgrController;
import com.jfinal.demo.model.User;

/**
 * 后台登录拦截器
 * @author admin
 *
 */
public class MgrLoginInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		//获取controller
		MgrController controller = (MgrController) inv.getController();
		//获取session中的登录用户
		User user = controller.getSessionAttr(Constant.CURRENT_USER);
		// 判断登录条件是否成立(除了登录功能不拦截之外，其他都拦截)
		if (user == null && !inv.getMethod().getName().equals("login") && !inv.getMethod().getName().equals("captcha")
				&& !inv.getMethod().getName().equals("logon")) {
			controller.redirect("/mgr/login.html");
		} else {
			inv.invoke();
		}
	}

}
