package com.jfinal.demo.controller;

import com.jfinal.core.Controller;

/**
 * ǰ̨���Ʋ�Controller
 * @author admin
 *
 */
public class FrontController extends Controller {
	
	/**
	 * ��ת��ǰ̨��ҳ
	 */
	public void index(){
		render("/index.html");
	}
}
