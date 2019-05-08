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
<style type="text/css">

</style>
</head>
<body>

	<h1 style="margin:0;padding:0;">品牌系统门户网站</h1>
	<!-- 把表单的请求交给后台的一个url-pattern 为 IndexQuerySerlvet 的 Servlet去处理，这个Servlet的详细配置在web.xml
	里面
	  <servlet>
	    <description></description>
	    <display-name>IndexQuerySerlvet</display-name> 
	    <servlet-name>IndexQuerySerlvet</servlet-name> //这是servlet 的名字
	    <servlet-class>com.cqut.recruitPortal.servlet.IndexQuerySerlvet</servlet-class> //这个Servlet对应的java代码
	  </servlet>
	  
	  servlet 和 servlet-mapping是相对应的，一个Servlet可以有0-n个Servlet-mapping
	  
	  <servlet-mapping>
	    <servlet-name>IndexQuerySerlvet</servlet-name>
	    <url-pattern>/IndexQuerySerlvet</url-pattern> //前台可以访问的路径
	  </servlet-mapping>
	 -->
	 
	<div class="pageContent">
		<p>
			<a href="Index">首页</a>
		</p>
		<form action="Index" method="post">
			<p>
				标题:<input type="text" name="title" value="<%=request.getAttribute("title")%>"/><input type="hidden" name="actionType" value="indexQuery"/><input type="submit" value="查询">
			</p>
		</form>
		<hr>
		<!-- 写这个是一种煎熬 -->
		<table width="100%">
			<thead>
				<tr>
					<td width="70%">标题</td>
					<td width="30%">发布时间</td>
				</tr>
			</thead>
			<tbody>
			
			<%
			
			if(request.getAttribute("list")!=null){
				List<Map<String,Object>> list = (List<Map<String,Object>>)request.getAttribute("list");
				for(Map<String,Object> m:list){
			%>
				<tr>
					<td><a href="Index?actionType=detail&newsID=<%=m.get("newsID")%>"><%=m.get("title")%></a></td>
					<td>
					<%
					if(m.get("publishTime")!=null)
					{
					%>
						<%=SysUtil.formatDate((Date)m.get("publishTime")) %>
					<%
					} 
					%>
					</td>
				</tr>
				
			<%
				}
			}
			%>
			</tbody>
		</table>
		<div class="page"><%=request.getAttribute("paginationHtml")%></div>
	</div>
	
</body>
</html>