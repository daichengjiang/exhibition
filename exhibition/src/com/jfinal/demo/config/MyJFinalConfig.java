package com.jfinal.demo.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.demo.controller.FrontController;
import com.jfinal.demo.controller.MgrController;
import com.jfinal.demo.handler.ResourceHandler;
import com.jfinal.demo.model._MappingKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;

public class MyJFinalConfig extends JFinalConfig{
	
	/**
	 * 此方法用于：配置JFinal常量、默认视图类型<br />
	 * JFinal支持三种常用视图：JSP、FreeMarker、Velocity
	 */
	public void configConstant(Constants me){
		PropKit.use("jdbc.properties");
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
	}
	
	/**
	 * 此方法用于：配置JFinal访问路由
	 */
	public void configRoute(Routes me){
		//前端路由
		//me.add(new FrontRoutes());
		
		//项目根访问路径
		me.add("/", FrontController.class);
		
		//第三个参数为视图存放路径
		me.add("/mgr", MgrController.class,"WEB-INF/mgr/");
	}
	
	/**
	 * 此方法用于：配置JFinal的Plugin（插件）
	 */
	public void configPlugin(Plugins me){
		//配置c3p0插件
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
		
		//配置缓存插件
		me.add(new EhCachePlugin());
	}
	
	/**
	 * 此方法用于：配置JFinal的全局拦截器，将拦截所有action请求<br />
	 * 可在Controller中使用@Clear清除<br />
	 * Interceptor配置粒度分为：Global、Class、Method三个层次
	 */
	public void configInterceptor(Interceptors me){
		//me.add(new MgrLoginInterceptor());
	}
	
	/**
	 * 此方法用于：配置JFinal的Handler（处理器）
	 */
	public void configHandler(Handlers me){
		me.add(new ResourceHandler(".html"));
	}
	
	public static C3p0Plugin createC3p0Plugin(){
		return new C3p0Plugin(
				PropKit.get("jdbcUrl"),
				PropKit.get("user"),
				PropKit.get("password")
		);
	}
	
}
