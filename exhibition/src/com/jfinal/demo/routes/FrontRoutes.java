package com.jfinal.demo.routes;

import com.jfinal.config.Routes;
import com.jfinal.demo.controller.IndexController;
import com.jfinal.demo.controller.UserController;

public class FrontRoutes extends Routes {

	@Override
	public void config() {
		//��Ŀ������·��
		add("/", IndexController.class);
		
		add("/user", UserController.class);
	}

}
