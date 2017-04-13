package com.jfinal.demo.controller;

import com.jfinal.core.Controller;

/**
 * 前台控制层Controller
 * @author admin
 *
 */
public class FrontController extends Controller {
	
	/**
	 * 跳转到前台首页
	 */
	public void index(){
		render("/index.html");
	}
}
