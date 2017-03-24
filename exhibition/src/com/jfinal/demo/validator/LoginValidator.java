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
		validateRequiredString(0, "user.username", "�û�������Ϊ��");
		validateRequiredString(1, "user.password", "���벻��Ϊ��");
		
		
	}

	@Override
	protected void handleError(Controller c) {
		c.keepPara("user.username");
		c.render("/login.jsp");
	}

}
