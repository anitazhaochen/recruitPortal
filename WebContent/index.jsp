<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.recruitPortal.dto.*" %>
<%@ page import="com.cqut.recruitPortal.entity.*" %>
<%@ page import="com.cqut.recruitPortal.servlet.SysUtil" %>
<!-- 这里引入jstl的核心标签库()：他的作用是方便页面的处理后台传递给jsp页面的数据处理，但是我们开始不用jstl,用纯的java代码。在后台管理系统里面全是jstl编写的。
在这里我将编写两个版本的，第一个是jstl的另一个java的
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
<title>品牌查询平台， 欢饮你</title>
<%--<link href="css/front.css" rel="stylesheet"/>--%>
	<link href="bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet"/>
	<link href="bootstrap-3.3.7/css/bootstrap-theme.css" rel="stylesheet"/>
    <style>
		body {background-image:url('image/backgroud.jpg');}
	</style>

</head>
<body>

	<jsp:include   page="header.jsp" flush="true"/>
	<div class="row">
	<%
		List<NewsTypeNews> list = (List<NewsTypeNews>)request.getAttribute("list");
		int size = list.size();
		for(int i=0;i<size;i++){
			NewsType newsType = list.get(i).getNewsType();
	%>

	<%
		List<Map<String,Object>> listMap = list.get(i).getNews();
		for(Map<String,Object> news:listMap){
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

	<script src="https://cdn.bootcdn.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	<script src="bootstrap-3.3.7/js/bootstrap.js"></script>
</body>
</html>