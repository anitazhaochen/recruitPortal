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
				当前位置:<a href="admin/OperatorServlet">用户管理</a>
			</td>
			<td width="50%" align="right" valign="middle">
				<a href="admin/OperatorInitAdd">新增</a>
			</td>
		</tr>
	</table>
	<hr>
	<form action="admin/OperatorServlet" method="post">
		<p>
			用户名:<input type="text" name="name" value="${name}"/> <input type="submit" value="查询"/>
		</p>
	</form>
	
	<table width="100%" class="dataTable">
		<thead>
			<tr class="title">
				<td width="15%">姓名</td>
				<td width="15%">帐号</td>
				<td width="15%">用户类型</td>
				<td width="15%">性别</td>
				<td width="15%">邮箱</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="item">
				<tr>
					<td>${item.name}</td>
					<td>${item.account}</td>
					<td>${item.typeName}</td>
					<td>${item.gender}</td>
					<td>${item.email}</td>
					<td>
						<a href="admin/OperatorDetail?operatorID=${item.operatorID}">详细</a> | 
						<a href="admin/OperatorInitEdit?operatorID=${item.operatorID}">编辑</a> | 
						<a href="admin/OperatorDelete?operatorID=${item.operatorID}">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${not empty paginationHtml}">
		<div class="page">${paginationHtml}</div>
	</c:if>
</body>
</html>