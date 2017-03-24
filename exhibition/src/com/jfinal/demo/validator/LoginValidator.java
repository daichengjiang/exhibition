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
		validateRequiredString(0, "user.username", "用户名不能为空");
		validateRequiredString(1, "user.password", "密码不能为空");
		
		
	}

	@Override
	protected void handleError(Controller c) {
		c.keepPara("user.username");
		c.render("/login.jsp");
	}

}
