<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.recruitPortal.dto.*" %>
<%@ page import="com.cqut.recruitPortal.entity.*" %>
<%@ page import="com.cqut.recruitPortal.servlet.SysUtil" %>
<!-- 这里引入jstl的核心标签库()：他的作用是方便页面的处理后台传递给jsp页面的数据处理，但是我们开始不用jstl,用纯的java代码。在后台管理系统里面全是jstl编写的。
在这里我将编写两个版本的，第一个是jstl的林一个java的
 -->
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
<link href="css/page.css" rel="stylesheet"/>
<link href="css/font.css" rel="stylesheet"/>
	<link href="bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet"/>
	<link href="bootstrap-3.3.7/css/bootstrap-theme.css" rel="stylesheet"/>
	<style>
		body {background-image:url('image/backgroud.jpg');}
	</style>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>


<div class="row">
	<%
		if(request.getAttribute("list")!=null){
			List<Map<String,Object>> list = (List<Map<String,Object>>)request.getAttribute("list");
			for(Map<String,Object> news:list){
	%>
	<div class="col-xs-6 col-md-3">
		<a href="Index?actionType=detail&newsID=<%=news.get("newsID")%>" class="thumbnail">
			<img src="<%=news.get("img")%>" alt="<%=news.get("title")%>" style="height: 180px; width: 100%; display: block;" >
			<%=news.get("title")%>
		</a>
	</div>
	<%
			}
		}
	%>
</div>

</body>
</html>