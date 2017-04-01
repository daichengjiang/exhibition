package com.jfinal.demo.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * 登录：表单域属性校验
 * @author daicj
 *
 */
public class LoginValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		validateRequiredString("username", "message", "用户名不能为空");
		validateRequiredString("password", "message", "密码不能为空");
		validateRequiredString("captcha", "message", "验证码不能为空");
		
		
	}

	@Override
	protected void handleError(Controller c) {
		c.render("login.jsp");
	}

}
