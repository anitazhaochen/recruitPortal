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
<title>这里是标题</title>
<link href="css/page.css" rel="stylesheet"/>
<link href="css/common.css" rel="stylesheet"/>
</head>
<body>
	<table width="100%">
		<tr>
			<td width="50%" align="left" valign="middle">
				当前位置:<a href="admin/PermissionServlet">权限分配</a> > <a href="admin/PermissionInitEdit?operatorID=${operator.operatorID}">编辑${operator.name}的权限</a>
			</td>
		</tr>
	</table>
	<hr>
	
	<form action="admin/PermissionEdit" method="post">
	
		<input type="hidden" name="operatorID" value="${operator.operatorID}">
		<table width="100%">
			<tbody>
				<tr>
					<td width="30%"></td>
					<td>
						${allPermission}
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="submit" value="提交">
					</td>
				</tr>
			</tbody>
		</table>
	
	</form>
	
</body>
</html>