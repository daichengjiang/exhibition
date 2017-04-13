package com.jfinal.demo.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.common.Constant;
import com.jfinal.demo.controller.MgrController;
import com.jfinal.demo.model.User;

/**
 * ��̨��¼������
 * @author admin
 *
 */
public class MgrLoginInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		//��ȡcontroller
		MgrController controller = (MgrController) inv.getController();
		//��ȡsession�еĵ�¼�û�
		User user = controller.getSessionAttr(Constant.CURRENT_USER);
		// �жϵ�¼�����Ƿ����(���˵�¼���ܲ�����֮�⣬����������)
		if (user == null && !inv.getMethod().getName().equals("login") && !inv.getMethod().getName().equals("captcha")
				&& !inv.getMethod().getName().equals("logon")) {
			controller.redirect("/mgr/login.html");
		} else {
			inv.invoke();
		}
	}

}
