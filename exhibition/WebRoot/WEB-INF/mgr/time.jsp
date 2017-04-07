<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style>
<!--
.nav_time{
	width:260px;
	height:45px;
	text-align:center;
	line-height:45px;
}
-->
</style>

<div class="navbar-header pull-right nav_time">
	当前系统时间：<span id="txt"></span>
</div>
<script type="text/javascript">
	function startTime() {
		var today = new Date();
		var y = today.getFullYear();
		var M = today.getMonth()+1;
		var d = today.getDate();
		var h = today.getHours();
		var m = today.getMinutes();
		var s = today.getSeconds();
		// add a zero in front of numbers<10
		m = checkTime(m);
		M = checkTime(M);
		s = checkTime(s);
		document.getElementById('txt').innerHTML =y+"-"+M+"-"+d+" "+ h + ":" + m + ":" + s;
		t = setTimeout('startTime()', 500);
	}

	function checkTime(i) {
		if (i < 10) {
			i = "0" + i;
		};
		return i;
	}
	startTime();
</script>
