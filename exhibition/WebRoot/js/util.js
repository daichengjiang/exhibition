$(document).ready(function(){
	
	
	/** 
	 * login method 
	 * 
	 */
	$("#login_btn").on("click",function(){
		var username = $("#username").val();
		var password = $("#password").val();
		
		if((username == "" && username.length == 0) || (password == "" &&　password.length == 0)){
			return;
		}else{
			$("#login_frm").submit();
		}
		
	})
	
	
	
	
})

