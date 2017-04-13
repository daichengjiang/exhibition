package com.jfinal.demo.routes;

import com.jfinal.config.Routes;
import com.jfinal.demo.controller.FrontController;
import com.jfinal.demo.controller.MgrController;

public class FrontRoutes extends Routes {

	@Override
	public void config() {
		//��Ŀ������·��
		add("/", FrontController.class);
		
		add("/user", MgrController.class);
	}

}
