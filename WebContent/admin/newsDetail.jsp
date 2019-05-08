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
				当前位置:<a href="admin/NewsServlet">新闻管理</a> > <a href="admin/NewsDetail?newsID=${news['newsID']}">详细信息</a>
			</td>
			<td width="50%" align="right" valign="middle">
				
			</td>
		</tr>
	</table>
	<hr/>
	
	<h3 style="text-align: center;">${news['title']}</h3>
	<p style="text-align: right;">浏览次数:${news['count']}&nbsp;发布人:${news['operatorName']};&nbsp;发布时间:${news['publishTime']};&nbsp;截至时间:${news['deadLine']}</p>
	<p>${news['content']}</p>
	
</body>
</html>