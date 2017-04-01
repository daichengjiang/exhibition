$(document).ready(function(){
	
	$("#username").focus();
	
	/** 
	 * 后台登录
	 * 
	 */
	$("#login_btn").on("click",function(){
		
		//表单元素非空校验
		if ($("#username").val() == "" || $("#username").val().length == 0) {
			$("#u_label").addClass("has-error");
			$("#username").focus();
			return;
		}else{
			$("#u_label").removeClass("has-error");
		}
		if ($("#password").val() == "" || $("#password").val().length == 0) {
			$("#p_label").addClass("has-error");
			$("#password").focus();
			return;
		}else{
			$("#p_label").removeClass("has-error");
		}
		if ($("#captcha").val() == "" || $("#captcha").val().length == 0) {
			$("#c_label").addClass("has-error");
			$("#captcha").focus();
			return;
		}else{
			$("#c_label").removeClass("has-error");
		}
		
		//获取表单元素值
		var data = $("#login_frm").serialize();
		//登录ajax方法
		$.ajax({
			type:"post",
			url:basePath + "mgr/logon.html",
			data:data,
			success:function(ret){
				console.log(ret);
				if(ret.retCode == 100){
					window.location.href = basePath + "mgr/index.html";
				}else{
					$(".ca_img").trigger("click");
					
					$("#password").val("").focus();
					$("#captcha").val("");
					
				}
				layer.msg(ret.retMsg);
			}
		});
	});
	
	/**
	 * 表单元素回车事件
	 */
	$("#login_frm").keyup(function(event){
	  if(event.keyCode ==13){
	    $("#login_btn").trigger("click");
	  }
	});
	
	
	/**
	 * 点击刷新图形验证码
	 */
	$(".ca_img").on("click",function(){
		$(this).attr("src",basePath + "mgr/captcha.html?time=" + new Date().getTime());
	})
	
})

