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

	<link href="bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet"/>
	<link href="bootstrap-3.3.7/css/bootstrap-theme.css" rel="stylesheet"/>
	<%--<style>--%>
		<%--body {background-image:url('image/backgroud.jpg');}--%>
	<%--</style>--%>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

	<div class="pageContent">
	<%
		if(request.getAttribute("news")!=null){
			Map<String,Object> news = (Map<String,Object>)request.getAttribute("news");
	%>
		<div class="col-xs-6 col-md-3">
			<a href="#" class="thumbnail">
				<img src="<%=news.get("img")%>" alt="<%=news.get("title")%>" style="height: 180px; width: 100%; display: block;">
			</a>
		</div>
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

		<h3 style="text-align: center;"><%=news.get("title")%></h3>
        </br>
		</br>
		</br>
		<p style="text-align: right;">
			<%--发布人:<%=news.get("operatorName")%> | --%>
			发布时间:<%=news.get("publishTime") %> |
		            <%--截至时间:<%=news.get("deadLine") %>| --%>
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