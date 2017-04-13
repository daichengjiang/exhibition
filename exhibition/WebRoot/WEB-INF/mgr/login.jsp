<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Shufty Login</title>

<!-- basic styles -->

<link href="ace/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="ace/assets/css/font-awesome.min.css" />

<!--[if IE 7]>
		  <link rel="stylesheet" href="ace/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

<!-- page specific plugin styles -->

<!-- fonts -->

<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

<!-- ace styles -->

<link rel="stylesheet" href="ace/assets/css/ace.min.css" />
<link rel="stylesheet" href="ace/assets/css/ace-rtl.min.css" />

<!--[if lte IE 8]>
		  <link rel="stylesheet" href="ace/assets/css/ace-ie.min.css" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
		<script src="ace/assets/js/html5shiv.js"></script>
		<script src="ace/assets/js/respond.min.js"></script>
		<![endif]-->

<style type="text/css">
.position-relative{
	padding-top:200px;
}

.ca_img{
	width:105px;
	height:34px;
	cursor: pointer;
}

</style>
		
</head>

<body class="login-layout">
	<div class="main-container" style="text-align:center;clear:both;">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="space-6"></div>

						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="icon-coffee green"></i> 欢迎进入管理后台
										</h4>

										<div class="space-6"></div>

										<form id="login_frm">
											<fieldset>
												<label id="u_label" class="block clearfix"> 
													<span class="block input-icon input-icon-right"> 
														<input id="username" name="user.username" type="text" class="form-control" placeholder="用户名" value="${user.username }" />
														<i class="icon-user"></i>
													</span>
												</label> 
												
												<label id="p_label" class="block clearfix"> 
													<span class="block input-icon input-icon-right"> 
														<input id="password" name="user.password" type="password" class="form-control" placeholder="密码" /> 
														<i class="icon-lock"></i>
													</span>
												</label>
												
												<label id="c_label" class="block clearfix"> 
													<span class="block input-icon input-icon-right" style="width:170px;float:left;margin-right:15px;"> 
														<input id="captcha" name="captcha" type="text" class="form-control" placeholder="验证码" /> 
														<i class="icon-certificate"></i>
													</span>
													<span style="float:left;">
														<img class="ca_img" title="看不清，点击刷新" alt="验证码" src="${basePath}mgr/captcha.html?time=<%=new Date().getTime() %>">
													</span>
												</label>

												<div class="space"></div>

												<div class="clearfix">
													<button id="login_btn" type="button" class="width-35 pull-right btn btn-sm btn-primary">
														<i class="icon-key"></i> 登录
													</button>
												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>
									</div>
									<!-- /widget-main -->

								</div>
								<!-- /widget-body -->
							</div>
							<!-- /login-box -->
						</div>
						<!-- /position-relative -->
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<script type="text/javascript">
		var basePath = '${basePath}';
	</script>
	
	<!--[if !IE]> -->

	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>

	<!-- <![endif]-->

	<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

	<!--[if !IE]> -->

	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='ace/assets/js/jquery-2.0.3.min.js'>"
								+ "<"+"/script>");
	</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='ace/assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

	
	<script type="text/javascript" src="js/layer-v3.0.3/layer.js" ></script>

	<script type="text/javascript" src="js/login.js" ></script>

	<script type="text/javascript">
		if ("ontouchend" in document)
			document
					.write("<script src='ace/assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>

	<!-- inline scripts related to this page -->

	<script type="text/javascript">
		function show_box(id) {
			jQuery('.widget-box.visible').removeClass('visible');
			jQuery('#' + id).addClass('visible');
		}
	</script>
</body>
</html>
