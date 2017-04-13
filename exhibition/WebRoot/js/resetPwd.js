jQuery(function($) {

	// 绑定保存按钮点击事件
	$("#save").on("click",function(){
		if(validate_form()){
			$.post(basePath + "mgr/resetPwd.html", {
				password : $("#form-field-pass1").val()
			}, function(data) {
				// 清空表单
				$("#reset").click();
				mylayer.alert(data.retMsg, {
					icon : data.retCode,
					skin : 'layer-ext-moon'
				});
			});
		}
	});	

	// 清空表单
	$("#reset").on("click", function() {
		$("#restpwd_form :input").not(
			":button, :submit, :reset, :hidden").val("")
			.removeAttr("checked").remove("selected");
	});
	
	/**
	 * 表单元素回车事件
	 */
	$("#restpwd_form").keyup(function(event){
	  if(event.keyCode ==13){
	    $("#save").trigger("click");
	  }
	});
});

//表单校验
function validate_form(){
	var flag = false;
	//input元素不能为空校验
	$("#restPwd_form input").each(function(i){
		if($(this).val().length == 0){
			mylayer.tips('此项不能为空', '#' + $(this).attr("id"), {tipsMore: true, tips: [2, '#d15b47']});
			flag = false;
		}else{
			flag = true;
		}
	});
	//两次密码是否相同校验
	if(flag){
		if($("#form-field-pass1").val() == $("#form-field-pass2").val()){
			flag = true;
		}else{
			$("#form-field-pass2").val('').focus();
			mylayer.tips('两次输入密码不一致', '#form-field-pass2', {tipsMore: true, tips: [2, '#d15b47']});
			flag = false;
		}
	}
	return flag;
}
