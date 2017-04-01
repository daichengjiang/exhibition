package com.jfinal.demo.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * ��¼����������У��
 * @author daicj
 *
 */
public class LoginValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		validateRequiredString("username", "message", "�û�������Ϊ��");
		validateRequiredString("password", "message", "���벻��Ϊ��");
		validateRequiredString("captcha", "message", "��֤�벻��Ϊ��");
		
		
	}

	@Override
	protected void handleError(Controller c) {
		c.render("login.jsp");
	}

}
