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
<style type="text/css">
.formTable td{
	height: 30px;
	vertical-align: middle;
}

</style>
</head>
<body>
	<table width="100%">
		<tr>
			<td width="50%" align="left" valign="middle">
				当前位置:<a href="admin/OperatorServlet">用户管理</a> > <a href="admin/OperatorDetail?operatorID=${operator['operatorID']}">用户详细信息</a>
			</td>
			<td width="50%" align="right" valign="middle">
				
			</td>
		</tr>
	</table>
	<hr/>
	
	
	<table width="100%" class="formTable">
		<tbody>
			<tr>
				
				<td width="30%" align="right">姓名:</td>
				<td>
					&nbsp;<c:out value="${operator['name']}"></c:out>
				</td>
			</tr>
			
			<tr>
				<td width="30%" align="right">性别:</td>
				<td>
					&nbsp;<c:out value="${operator['gender']}"></c:out>
				</td>
			</tr>
			
			<tr>
				<td width="30%" align="right">帐号:</td>
				<td>
					&nbsp;<c:out value="${operator['account']}"></c:out>
				</td>
			</tr>
			
			<tr>
				<td width="30%" align="right">邮箱:</td>
				<td>
					&nbsp;<c:out value="${operator['email']}"></c:out>
				</td>
			</tr>
			
			<tr>
				<td width="30%" align="right">用户类型:</td>
				<td>
					&nbsp;<c:out value="${operator['typeName']}"></c:out>						
				</td>
			</tr>
			
			<tr>
				<td width="30%" align="right">头像:</td>
				<td>
					<img alt="" src="LoadImage?fileName=${operator['photo']}&type=head" style="width:100px;"/>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>