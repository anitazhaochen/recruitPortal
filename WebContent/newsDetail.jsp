<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.recruitPortal.dto.*" %>
<%@ page import="com.cqut.recruitPortal.entity.*" %>
<%@ page import="com.cqut.recruitPortal.servlet.SysUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>品牌系统门户网站</title>
<style type="text/css">

.newsContent{
	line-height:24px;
}
</style>
</head>
<body>
	<h1 style="margin:0;padding:0;">品牌系统门户网站</h1>
	<hr/>
	
	<div class="pageContent">
	<%
		if(request.getAttribute("news")!=null){
			Map<String,Object> news = (Map<String,Object>)request.getAttribute("news");
	%>
		<p>
			<a href="Index">首页</a> 
			<%
			if(request.getAttribute("newsTypeLink")!=null){
			%>
			> <%=request.getAttribute("newsTypeLink")%>	
			<%
			}
			%>
			<%
			if(request.getAttribute("titleLink")!=null){
			%>
			> <%=request.getAttribute("titleLink")%>	
			<%
			}
			%>
			
		</p>
		<h3 style="text-align: center;"><%=news.get("title")%></h3>
		<p style="text-align: right;">
			发布人:<%=news.get("operatorName")%> | 
			发布时间:<%=news.get("publishTime") %> | 
		            截至时间:<%=news.get("deadLine") %>| 
		            浏览次数：<%=news.get("count")%>
		</p>
		
		<p class="newsContent">
			<%=news.get("content")%>
		</p>
		
	<%
		}
		
	%>
		
	</div>
	
	
</body>
</html>