<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String operatorID = request.getParameter("operatorID");
String photo = request.getParameter("photo");
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
	<form action="admin/PhotoUpload" method="POST" enctype="multipart/form-data">
		<div style="width:100px;min-height:100px;">
			<img alt="" src="LoadImage?fileName=${param.photo}&type=head" style="width:100px;"/>
		</div>
		<input type="hidden" name="operatorID" value="${param.operatorID}"/>
		<input type="file" name="photo"/>
		<input type="submit" value="上传"/>
	</form>
</body>
</html>