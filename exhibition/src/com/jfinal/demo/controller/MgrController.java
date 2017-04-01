package com.jfinal.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.common.Constant;
import com.jfinal.core.Controller;
import com.jfinal.demo.model.User;
import com.jfinal.demo.util.CommonUtil;

public class MgrController extends Controller {
	
	/**
	 * ��ת����¼ҳ��
	 */
	public void login(){
		render("login.jsp");
	}
	
	/**
	 * ��ת�������̨��ҳ
	 */
	public void index(){
		render("index.jsp");
	}
	
	/**
	 * ��̨�û���¼
	 */
	public void logon(){
		//������¼���map
		Map<String, Object> map = new HashMap<String, Object>();
		//�ж���֤���Ƿ���ȷ
		if (validateCaptcha("captcha")) {
			//��ȡ��¼�û���������
			User user = getModel(User.class);
			//��¼��ѯsql
			String sql = "select * from sys_user where username = '" + user.getUsername() + "' and password = '" + user.getPassword() + "'";
			//ִ�в�ѯ����
			User u = User.dao.findFirst(sql);
			if (u == null) {
				map.put(Constant.RESULT_CODE, -101);
				map.put(Constant.RESULT_MESSAGE, "�û������������");
			}else {
				getSession().setAttribute(Constant.CURRENT_USER, u);
				map.put(Constant.RESULT_CODE, 100);
				map.put(Constant.RESULT_MESSAGE, "��¼�ɹ�,��ӭ�����̨����ϵͳ...");
				//��ȡ��¼ip
				u.setLastIp(CommonUtil.getIpAddr(getRequest()));
				
			}
		}else{
			map.put(Constant.RESULT_CODE, -100);
			map.put(Constant.RESULT_MESSAGE, "��֤�벻��ȷ");
		}
		renderJson(map);
	}
	
	/**
	 * �˳���̨����ϵͳ
	 */
	public void logout(){
		//��ȡsession�е������û���Ϣ
		User user = (User)getSession().getAttribute(Constant.CURRENT_USER);
		//���user��Ϊ�գ������user
		if (user != null) {
			//���session�е������û���Ϣ
			getSession().removeAttribute(Constant.CURRENT_USER);
		}
		redirect("/mgr/login.html");
	}
	
	
	/**
	 * ����ͼ����֤��
	 */
	public void captcha(){
		renderCaptcha();
	}
	
	
}
