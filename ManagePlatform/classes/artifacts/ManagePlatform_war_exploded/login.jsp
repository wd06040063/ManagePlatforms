<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.WangD.model.Admin" %>
<%@ page import="com.WangD.model.DormManager" %>
<%@ page import="com.WangD.model.Student" %>
<%@ page import="com.WangD.servlet.ImageServlet" %>
<%
	if(request.getAttribute("user")==null){
		String userName=null;
		String password=null;
		String userType=null;
		String remember=null;
		Cookie[] cookies=request.getCookies();
		for(int i=0;cookies!=null && i<cookies.length;i++){
			if(cookies[i].getName().equals("dormuser")){
				userName=cookies[i].getValue().split("-")[0];
				password=cookies[i].getValue().split("-")[1];
				userType=cookies[i].getValue().split("-")[2];
				remember=cookies[i].getValue().split("-")[3];
			}
		}
		
		if(userName==null){
			userName="";
		}
		
		if(password==null){
			password="";
		}

		if(userType==null){
			userType="";
		} else if("admin".equals(userType)){
			pageContext.setAttribute("user", new Admin(userName,password));
			pageContext.setAttribute("userType", 1);
		} else if("dormManager".equals(userType)) {
			pageContext.setAttribute("user", new DormManager(userName,password));
			pageContext.setAttribute("userType", 2);
		} else if("student".equals(userType)) {
			pageContext.setAttribute("user", new Student(userName,password));
			pageContext.setAttribute("userType", 3);
		}
		
		if("yes".equals(remember)) {
			pageContext.setAttribute("remember", 1);
		}
		
	}
%>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>校园生活管家云管理平台</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/icon.png">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery1.9.1.js"></script>
<script type="text/javascript">
	function checkForm() {
		var userName = document.getElementById("userName").value;
		var password = document.getElementById("password").value;
		var checkcode = document.getElementsByName("checkcode");
		var userTypes = document.getElementsByName("userType");
		var userType = null;
		for(var i=0;i<userTypes.length;i++) {
			if(userTypes[i].checked) {
				userType=userTypes[i].value;
				break;
			}
		}
		if (userName == null || userName == "") {
			document.getElementById("error").innerHTML = "用户名不能为空";
			return false;
		}
		if (password == null || password == "") {
			document.getElementById("error").innerHTML = "密码不能为空";
			return false;
		}
		if (checkcode == null || checkcode == "") {
			document.getElementById("error").innerHTML = "验证码不能为空";
			return false;
		}
		if (userType == null || userType == "") {
			document.getElementById("error").innerHTML = "请选择用户类型";
			return false;
		}
		
		return true;
	}

	 function reload(){
			document.getElementById("imag").src="<%=request.getContextPath() %>/imageServlet?date="+new Date().getTime();
			$("#checkcode").val("");   // 将验证码清空
		} 
		 
</script>

<style type="text/css">
	 
      .radio {
      	padding-top: 10px;
       	padding-bottom:10px;
      }
      
      .form-signin-heading{
      	text-align: center;
      }

      .form-signin {
        max-width: 260px;
        padding: 19px 29px 0px;
        margin: 0 auto 20px;
        <!--background-color: #fff;-->
        border: 0px solid #e5e5e5;
        
      }
       .main{
        padding-top: 160px;
        padding-bottom: 40px;
        <!--background-image: url('images/bg3.jpg');-->
        background-position: center;
		background-repeat: no-repeat;
		background-attachment: fixed;
		background-size:100%;
      }
      
      .abc {
		z-index: -999;
        position: fixed;
        right: 0;
        min-width: 100%;
        min-height: 100%;
        width: auto;
        height: auto;
        
    }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }
      .footer{
      	height:5%;
      	width:5%
      	padding-top:5%;
      }
     

</style>

</head>
<body>

<video class="abc"autoplay="autoplay" loop="loop"><source src="images/VespaBokeh.mp4" type="video/mp4"></video>
<div class ="main">
<div class="container" style="">
		
		 <strong><h1 class="form-signin-heading""><font color="white">校园生活管家云管理平台</font></h1></strong>
      <form name="myForm" class="form-signin" action="login" method="post" onsubmit="return checkForm()">
<h2 class="form-signin-heading"style="padding-top:6px;"><font color="gray"></font></h2>
        <input id="userName" name="userName" value="${user.userName }" type="text" class="input-block-level" placeholder="请输入您的用户名">
        <input id="password" name="password" value="${user.password }" type="password" class="input-block-level" placeholder="请输入您的密码" >
      <input type="text" name="checkcode" style="width:150px;font-size:13px;" id="checkcode"  placeholder="请输入右侧验证码"/>
  	<a href="javascript:reload();"><img src="<%=request.getContextPath() %>/imageServlet" style="margin-top:-20px;" alt="验证码" id="imag" /></a>
	<span id="span"></span>
        <label class="radio inline"style="color:white;">
      	  	<input id="admin" type="radio" name="userType" value="admin"  checked/> 系统管理
		</label>
		<label class="radio inline"style="color:white;">
			<input id="dormManager" type="radio" name="userType" value="dormManager" ${userType==2?'checked':''} /> 宿舍管理
		</label>
		 <label class="radio inline"style="color:white;">
			<input id="student" type="radio" name="userType" value="student"  ${userType==3?'checked':''}/>维修管理
		</label> 
        <label class="checkbox"style="color:white;">
          <input id="remember" name="remember" type="checkbox" value="remember-me" ${remember==1?'checked':''}>记住登录信息 &nbsp;&nbsp;&nbsp;&nbsp; <font id="error" color="red">${error }</font>  
        </label>
        <button class="btn btn-large  btn-primary"style="margin-left:10px;width:90px;" type="submit">登录</button>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="<%=request.getContextPath() %>/login.jsp"><button class="btn btn-large btn-primary"style="margin-left:20px;width:90px" type="button" >重置</button></a>
        <br>
		
      </form>
</div>
<!--  <p align="center" style="padding-top: 12px;color:white;font-size:25;font-family:隶书">努力学习，勤奋工作，让青春更加光彩。</p>
<p align="center" style="padding-top: 12px;color:white;font-size:20px;margin-left:450px;"> —— 王光美</p>-->

</div>
</body>
</html>