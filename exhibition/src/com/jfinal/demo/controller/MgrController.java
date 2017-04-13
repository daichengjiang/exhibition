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
 * ��̨���Ʋ�Controller
 * @author admin
 *
 */
@Before(MgrLoginInterceptor.class)
public class MgrController extends Controller {

	/**
	 * ��ת����¼ҳ��
	 */
	public void login() {
		render("login.jsp");
	}

	/**
	 * ��ת�������̨��ҳ
	 */
	public void index() {
		render("index.jsp");
	}

	/**
	 * ��̨�û���¼
	 */
	public void logon() {
		// ������¼���map
		Map<String, Object> map = new HashMap<String, Object>();
		// �ж���֤���Ƿ���ȷ
		if (validateCaptcha("captcha")) {
			// ��ȡ��¼�û���������
			User user = getModel(User.class);
			// ��¼��ѯsql
			String sql = "select * from sys_user where username = '" + user.getUsername() + "' and password = '"
					+ CommonUtil.encodePassword(user.getPassword(), "md5") + "'";
			// ִ�в�ѯ����
			User u = User.dao.findFirst(sql);
			if (u == null) {
				map.put(Constant.RESULT_CODE, 5);
				map.put(Constant.RESULT_MESSAGE, "�û������������");
			} else {
				getSession().setAttribute(Constant.CURRENT_USER, u);
				map.put(Constant.RESULT_CODE, 6);
				map.put(Constant.RESULT_MESSAGE, "��¼�ɹ�,��ӭ�����̨����ϵͳ...");
				// �����ϴε�¼IP
				Db.update("update sys_user set lastip = ? where id = ?", CommonUtil.getIpAddr(getRequest()), u.getId());
			}
		} else {
			map.put(Constant.RESULT_CODE, 5);
			map.put(Constant.RESULT_MESSAGE, "��֤�벻��ȷ");
		}
		renderJson(map);
	}

	/**
	 * �˳���̨����ϵͳ
	 */
	public void logout() {
		// ��ȡsession�е������û���Ϣ
		User user = (User) getSession().getAttribute(Constant.CURRENT_USER);
		// ���user��Ϊ�գ������user
		if (user != null) {
			// ���session�е������û���Ϣ
			getSession().removeAttribute(Constant.CURRENT_USER);
		}
		redirect("/mgr/login.html");
	}

	/**
	 * ��������
	 */
	public void resetPwd() {
		// ��ȡ�û���������
		String password = getPara("password");
		System.out.println("����ǰ:" + password);
		// md5��������
		password = CommonUtil.encodePassword(password, "md5");
		System.out.println("���ܺ�:" + password);
		//��ȡ��ǰ��¼�û�
		User currentUser = CommonUtil.getCurrentUser(getSession());
		//������Ϣmap
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// ��������
		int result = Db.update("update sys_user set password = ? where id = ?", password,currentUser.getId());
		if (result > 0) {
			resultMap.put(Constant.RESULT_CODE, 6);
			resultMap.put(Constant.RESULT_MESSAGE, "��������ɹ�");
			// д������־
			Operatelog operateLog = new Operatelog();
			operateLog.setOperateType("��������");
			operateLog.setOperateContent("���ù����̨��¼����Ϊ������ǰ��"+getPara("password")+"��-�����ܺ�"+password+"��");
			operateLog.setOperateIP(CommonUtil.getIpAddr(getRequest()));
			operateLog.setOperator(CommonUtil.getCurrentUser(getSession()).getUsername());
			operateLog.setOperateTime(new Date());
			operateLog.save();
		} else {
			resultMap.put(Constant.RESULT_CODE, 5);
			resultMap.put(Constant.RESULT_MESSAGE, "��������ʧ��");
		}
		renderJson(resultMap);
	}

	/**
	 * ����ͼ����֤��
	 */
	public void captcha() {
		renderCaptcha();
	}

	/**
	 * ��ͼ��ת
	 */
	public void view() {
		// ��ȡ��ͼ����
		String view = getPara("view");
		// ��ת����������ҳ��
		if ("restpwd".equals(view)) {
			render("restpwd.jsp");
		}
		// ��ת����������ҳ��
		if ("profile".equals(view)) {
			render("profile.jsp");
		}
	}
}
