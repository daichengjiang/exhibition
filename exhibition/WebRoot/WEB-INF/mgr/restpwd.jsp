<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style>
<!--
.tab-content>.tab-pane, .pill-content>.pill-pane {
    display: block;
}
.list-unstyled, .list-inline {
    margin-left: 260px;
}
-->
</style>
<div class="breadcrumbs" id="breadcrumbs">
	<script type="text/javascript">
		try {
			ace.settings.check('breadcrumbs', 'fixed')
		} catch (e) {
		}
	</script>

	<ul class="breadcrumb">
		<li><i class="icon-home home-icon"></i> <a href="${basePath }mgr/index.html">首页</a></li>
		<li class="active">重置密码</li>
	</ul>
	<!-- .breadcrumb -->

	<div class="nav-search" id="nav-search">
		<form class="form-search">
			<span class="input-icon"> <input type="text"
				placeholder="Search ..." class="nav-search-input"
				id="nav-search-input" autocomplete="off" /> <i
				class="icon-search nav-search-icon"></i>
			</span>
		</form>
	</div>
	<!-- #nav-search -->
</div>

<div class="page-content">
	<div id="user-profile-3" class="user-profile row">
		<div class="col-sm-offset-1 col-sm-10">
			<div class="space"></div>

			<form id="restpwd_form" class="form-horizontal">
				<div class="tabbable">
					<ul class="nav nav-tabs padding-16">

						<li class="active"><a data-toggle="tab" href="#edit-password"> <i
								class="blue icon-key bigger-125"></i> 重置密码
						</a></li>
					</ul>

					<div class="tab-content profile-edit-tab-content">
						<ul class="list-unstyled spaced">
							<li>
								<i class="icon-bell bigger-110 purple"></i>
								建议密码以字母、数字、符号组合
							</li>
						</ul>
						
						<div class="space-8"></div>

						<div id="edit-password" class="tab-pane">
							<div class="space-10"></div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-pass1">新密码</label>

								<div class="col-sm-9">
									<input type="password" id="form-field-pass1" />
								</div>
							</div>

							<div class="space-4"></div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-pass2">确认密码</label>

								<div class="col-sm-9">
									<input type="password" id="form-field-pass2" />
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
						<button id="save" class="btn btn-info" type="button">
							<i class="icon-ok bigger-110"></i> 保存
						</button>

						&nbsp; &nbsp;
						<button id="reset" class="btn" type="reset">
							<i class="icon-undo bigger-110"></i> 清空
						</button>
					</div>
				</div>
			</form>
		</div>
		<!-- /span -->
	</div>
	<!-- /user-profile -->
</div>
<!-- PAGE CONTENT ENDS -->

<!-- inline scripts related to this page -->

<script src="js/resetPwd.js" ></script>
