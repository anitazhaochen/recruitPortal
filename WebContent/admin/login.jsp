<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理网站</title>
<link href="css/page.css" rel="stylesheet"/>
<link href="css/common.css" rel="stylesheet"/>
	<link href="bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet"/>
	<link href="bootstrap-3.3.7/css/bootstrap-theme.css" rel="stylesheet"/>
	<%--<style>--%>
		<%--body {background-image:url('<%=basePath%>/image/backgroud.jpg');}--%>
	<%--</style>--%>
<style type="text/css">

/*为了降低初学的难度，我尽量的不用 css，javascript 。但是 我也会引入一些(毕竟做web,写网站离不开这些东西)，在引入的地点我都有详细的说明*/
.loginform{
	/*这是css。这段代码的作用是让 class属性为 loginform 的 元素水平垂直居中。很经典的代码 */	
	position: absolute;
	width:300px;
	height: 400px;
	top:50%;
	left:50%;
	margin-top: -125px;
	margin-left: -200px;
	/*设置边框*/
	/*border: 1px solid #8a8a8a;*/
}

/*给页面设置背景颜色*/
body{
	background-color: #e5e5e5;
}

</style>
</head>
<body>


	<div class="loginform">

		<form action="admin/AdminLogin" method="post">
            <p>
				<h1>管理员登录</h1>
			</p>
            </br>
			</br>

		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1">登录账号：</span>
			<input type="text" class="form-control" placeholder="账号" aria-describedby="basic-addon1" name="account" value="${account}" id="accountField">
		</div>

		<div class="input-group">
			<span class="input-group-addon" id="basic-addon2">登录密码：</span>
			<input type="password" class="form-control" aria-describedby="basic-addon2" name="password" id="passwordField">
		</div>
			<br>
			<button type="submit" class="btn btn-primary btn-lg btn-block">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
	</form>
	</div>

</body>
</html>