package com.jfinal.demo.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.demo.controller.IndexController;
import com.jfinal.demo.controller.UserController;
import com.jfinal.demo.handler.ResourceHandler;
import com.jfinal.demo.interceptor.AuthInterceptor;
import com.jfinal.demo.model._MappingKit;
import com.jfinal.demo.routes.FrontRoutes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

public class MyJFinalConfig extends JFinalConfig{
	
	/**
	 * �˷������ڣ�����JFinal������Ĭ����ͼ����<br />
	 * JFinal֧�����ֳ�����ͼ��JSP��FreeMarker��Velocity
	 */
	public void configConstant(Constants me){
		PropKit.use("jdbc.properties");
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
	}
	
	/**
	 * �˷������ڣ�����JFinal����·��
	 */
	public void configRoute(Routes me){
		//ǰ��·��
		//me.add(new FrontRoutes());
		
		//��Ŀ������·��
		me.add("/", IndexController.class);
		
		//����������Ϊ��ͼ���·��
		me.add("/user", UserController.class,"/WEB-INF/mgr");
	}
	
	/**
	 * �˷������ڣ�����JFinal��Plugin�������
	 */
	public void configPlugin(Plugins me){
		loadPropertyFile("jdbc.properties");
		C3p0Plugin c3p0Plugin = new C3p0Plugin(
				getProperty("jdbcUrl"),
				getProperty("user"),
				getProperty("password")
		);
		me.add(c3p0Plugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		_MappingKit.mapping(arp);
	}
	
	/**
	 * �˷������ڣ�����JFinal��ȫ��������������������action����<br />
	 * ����Controller��ʹ��@Clear���<br />
	 * Interceptor�������ȷ�Ϊ��Global��Class��Method�������
	 */
	public void configInterceptor(Interceptors me){
		me.add(new AuthInterceptor());
	}
	
	/**
	 * �˷������ڣ�����JFinal��Handler����������
	 */
	public void configHandler(Handlers me){
		me.add(new ResourceHandler(".htm"));
	}
	
	public static C3p0Plugin createC3p0Plugin(){
		return new C3p0Plugin(
				PropKit.get("jdbcUrl"),
				PropKit.get("user"),
				PropKit.get("password")
		);
	}
	
}
