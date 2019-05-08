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
<title>后台管理</title>
<style type="text/css">
body{
	margin: 0;
}

/*下面的 css 用于框架的布局*/
.content{
	position: absolute;
	top: 87px;
	left: 0;
	bottom: 0;
	right:0;
}
.left{
	position: absolute;
	left: 5px;
	width: 190px;
	bottom: 5px;
	top:5px;
	overflow-x:hidden;
	overflow-y:auto;
	border: 1px solid #e5e5e5;
}
.right{
	position: absolute;
	left: 202px;
	top: 5px;
	right: 5px;
	bottom: 5px;
	border: 1px solid #e5e5e5;
}

/*设置Iframe的样式*/
.innerFrame {
	border: 0;
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>

	<!-- ${loginOperator.name} 这样的代码叫做EL表达式 -->
	<div>
	<table width="100%" bgcolor="#383838">
		<tr>
			<td width="80%">
			
			<%--<img alt="" src="image/ll.png">--%>
			</td>
			<td width="20%" valign="middle">
				<div style="height: 32px;line-height: 32px; min-width: 50px;float: left;">
					<font color="#ffffff">欢迎 ${loginOperator.name}&nbsp;</font>
				</div>
				<a href="admin/AdminLogout"><img alt="" src="image/exit.png"></a>
				
			</td>
		</tr>
	</table>
	<div class="content">
		<div class="left">
			
			<!-- 系统管理员 -->
			<c:if test="${loginOperator.type==1}">
				<p>&nbsp;&nbsp;<a href="admin/OperatorServlet" target="innerFrame">用户管理</a></p>
				<p>&nbsp;&nbsp;<a href="admin/OperatorTypeServlet" target="innerFrame">用户类型管理</a></p>
				<p>&nbsp;&nbsp;<a href="admin/NewsTypeServlet" target="innerFrame">品牌类型管理</a></p>
				<p>&nbsp;&nbsp;<a href="admin/PermissionServlet" target="innerFrame">权限分配</a></p>
			</c:if>
			<!-- 管理员 -->
			<c:if test="${loginOperator.type==2}">
				<p>&nbsp;&nbsp;<a href="admin/NewsServlet" target="innerFrame">品牌信息管理</a></p>
			</c:if>
		
		</div>
		<div class="right">
			<iframe src="admin/welcome.jsp" class="innerFrame" name="innerFrame" frameborder="0" scrolling="auto" style="margin: 0px auto; height: 100%; -ms-overflow-x: hidden;"></iframe>
		</div>
	</div>
	
	</div>
	
	
</body>
</html>