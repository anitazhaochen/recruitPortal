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
<link href="css/front.css" rel="stylesheet"/>

</head>
<body>

	<h1 style="margin:0;padding:0;">品牌查询平台 欢迎你</h1>
	<!-- 把表单的请求交给后台的一个url-pattern 为 IndexQuerySerlvet 的 Servlet去处理，这个Servlet的详细配置在web.xml
	里面
	  <servlet>
	    <description></description>
	    <display-name>IndexQuerySerlvet</display-name> 
	    <servlet-name>IndexQuerySerlvet</servlet-name> //这是servlet 的名字
	    <servlet-class>com.cqut.recruitPortal.servlet.IndexQuerySerlvet</servlet-class> //这个Servlet对应的java代码
	  </servlet>
	  
	  servlet 和 servlet-mapping是想对应的，一个Servlet可以有0-n个Servlet-mapping
	  
	  <servlet-mapping>
	    <servlet-name>IndexQuerySerlvet</servlet-name>
	    <url-pattern>/IndexQuerySerlvet</url-pattern> //前台可以访问的路径
	  </servlet-mapping>
	 -->
	 
	<form action="Index" method="post">
		<p>
			品牌关键字:<input type="text" name="title"/><input type="hidden" name="actionType" value="indexQuery"/><input type="submit" value="查询">
		</p>
	</form>

	<hr>
	<!--在页面上分类展示页面系统所有类型的新闻  -->
	<%
	List<NewsTypeNews> list = (List<NewsTypeNews>)request.getAttribute("list");
	int size = list.size();
	for(int i=0;i<size;i++){
		NewsType newsType = list.get(i).getNewsType();
	%>
		<p class="newsType"><%=newsType.getName()%> <a href="Index?actionType=newsTypeQuery&newsType=<%=newsType.getNewsTypeID()%>">更多</a></p>
		<table width="100%">
			<thead>
				<tr>
					<td width="30%">标题</td>
					<td width="40%">品牌LOGO</td>
					<td width="30%">发布时间</td>
				</tr>
			</thead>
	<%
		List<Map<String,Object>> listMap = list.get(i).getNews();
		for(Map<String,Object> news:listMap){
	%>
		<tr>
		<td><a href="Index?actionType=detail&newsID=<%=news.get("newsID")%>"><%=news.get("title")%></a></td>
		<td>
			<%
				if(news.get("img")!=null)
				{
			%>
			<img src="<%=news.get("img")%>" style="width:120px; height: 80px" >
			<%
				}
			%>
		</td>
		<td>


			<%
			if(news.get("publishTime")!=null)
			{
			%>
				<%=SysUtil.formatDate((Date)news.get("publishTime")) %>
			<%
			} 
			%>
		</td>
		</tr>
	<%
		}
	%>
		</table>
			
	<%
	}
	%>

	<%--<p class="newsType">最近浏览 <a href="Index?actionType=newsTypeQuery&newsType=">更多</a></p>--%>
	<p class="newsType">最近浏览</p>
	<table width="100%">
		<thead>
		<tr>
			<td width="30%">标题 </td>
			<td width="40%">品牌LOGO </td>
			<td width="30%">发布时间</td>
		</tr>
		</thead>
		<%
            Set<Long>  set = new HashSet<>();
			ArrayList<Map<String, Object>> listMap1 = (ArrayList<Map<String, Object>>) session.getAttribute("news_list");
			if (listMap1 != null) {
			for(Map<String,Object> news :listMap1){
				System.out.println(news.get("newsID"));
			    if (set.contains(news.get("newsID"))){
			    	continue;
				}
		%>
		<tr>
			<td><a href="Index?actionType=detail&newsID=<%=news.get("newsID")%>"><%=news.get("title")%></a></td>
			<td>
				<img src="http://pic32.nipic.com/20130823/13339320_183302468194_2.jpg" style="width:50px; height: 50px" >
			</td>
		</tr>
        <%
					set.add((Long) news.get("newsID"));
			}	}%>
	</table>

</body>
</html>