<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	$(document).ready(function(){
		$("ul li:eq(0)").addClass("active");
	});
</script>
<style>
.main{
	height:90%;
	width:auto;
	padding:20px;
	background-color:#191b30;
}
.zm{
	height:40px;
	width:auto;
}
.but{
	height:90%;
	width:65%;
	background-color:#191b30;
	float:left;
}
.jj{
	height:90%;
	width:32%;
	background-color:#2f3753;
	float:left;
	
}
.jj-gg{
	height:6%;
	width:auto;
	
}
.jj-gg{
	margin:10px;
	
}
.font_1
{
	color:white;
	padding:5px;
	margin-left:5px;
	float:left;
}
.font_2{
	float:right;
	size:4;
	color:#e5e6e9;
	margin-top:5px;
}
.ggl{
	height:90%;
	width:100%;
	background-color:#8089a7;
}
.ggl_gg
{
	width:80%;
	height:90%;
	margin:0 30px;
}
.jiemian{
	width:90%;
	height:80%;
	padding:30px;
	margin-left:20px;
	
}
.jiemian_1
{
	width:90%;
	height:100%;
}
.jmui{
		height:100px;
		width:280px;
		posion:relative;
}
.small{
	width:25px;
	height:25px;
	float:left;
}
.font_3
{
	float:left;
	color:#2f3753;
}
.tongzhi{
	margin-right:5px;
	float:right;
	font-size:14;
	color:white;
}
</style>
<div>
	<div class="main">
		<div class="zm">
		<font color="white" size="4" >桌面</font>
		</div>
		<div class="but">
			<div class="jiemian">
				<a href="dormBuild?action=list"title="楼层管理"><img src="images/lcgl.png" class="jmui"></a>
				<a href="dormManager?action=list"title="宿管管理"><img src="images/sggl.png" class="jmui"style="margin-left:50px;"></a>
				<a href="student?action=list"title="学生管理"><img src="images/xsgl.png" class="jmui"style="margin-top:10px;"></a>
				<a href="record?action=list"title="缺勤记录"><img src="images/qqjl.png" class="jmui"style="margin-left:50px;margin-top:10px"></a>
				<a href="rechargeRecord?action=list"title="充值缴费"><img src="images/czjf.png" class="jmui"style="margin-top:10px;"></a>
				<a href="repairManager?action=list"title="报修管理"><img src="images/bxgl.png" class="jmui"style="margin-left:50px;margin-top:10px"></a>
				<a href="password?action=preChange"title="修改密码"><img src="images/xgmm.png" class="jmui"style="margin-top:10px;"></a>
				<a href="#"onclick="logout()"title="退出系统"><img src="images/tcxt1.png" class="jmui"style="margin-left:50px;margin-top:10px"></a>
		</div>
	</div>
		<div class="jj">
			<div class="jj-gg">
				<div class="jj-gg-1">
				<img src="images/laba.png"class="small" ><p class="font_1">系统公告</p>
			 	<a href="#" class="font_2">更多</a>
				</div>

			</div>
			<div class="ggl">
				<div class="ggl_gg">
					<div style="height:30px;"></div>
					<p class="font_3">03-22</p>
					<a href="notice.html"><p class="tongzhi">3月28号关于第二生活区停水停电通知</p></a>
				</div>
			</div>
		</div>
	
</div>
</div>