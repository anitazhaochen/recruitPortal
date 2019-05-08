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
<title>新闻列表</title>
<link href="css/page.css" rel="stylesheet"/>
<link href="css/common.css" rel="stylesheet"/>
</head>
<body>
	<table width="100%">
		<tr>
			<td width="50%" align="left" valign="middle">
				当前位置:<a href="admin/NewsServlet">新闻管理</a>
			</td>
			<td align="right">
				<a href="admin/NewsInitAdd">新增</a>
			</td>
		</tr>
	</table>
	<hr>
	<form action="admin/NewsServlet" method="post">
		<p>
			新闻标题:<input type="text" name="querytitle" value="${querytitle}"/> <input type="submit" value="查询"/>
		</p>
	</form>
	
	<table width="100%" class="dataTable">
		<thead>
			<tr class="title">
				<td>新闻标题</td>
				<td width="100">类型</td>
				<td width="100" align="center">发布时间</td>
				<td width="100" align="center">截至时间</td>
				<td width="90" align="center">发布人</td>
				<td width="90" align="right">浏览次数</td>
				<td width="90" align="center">状态</td>
				<td width="270">操作</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="item">
				<tr>
					<td>${item.title}</td>
					<td>${item.typeName}</td>
					<td align="center">${item.publishTime}</td>
					<td align="center">${item.deadLine }</td>
					<td align="center">${item.operatorName}</td>
					<td align="right">${item.count}</td>
					<td align="center">${item.status}</td>
					<td>${item.operate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${not empty paginationHtml}">
		<div class="page">${paginationHtml}</div>
	</c:if>
</body>
</html>