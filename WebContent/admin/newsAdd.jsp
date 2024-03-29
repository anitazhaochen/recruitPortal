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
	<table width="1240px">
		<tr>
			<td width="50%" align="left" valign="middle">
				当前位置:<a href="admin/NewsServlet">品牌管理</a> > <a href="admin/NewsInitAdd">新增</a>
			</td>
			<td width="50%" align="right" valign="middle">
				
			</td>
		</tr>
	</table>
	<hr/>
	


	<form action="admin/NewsAdd" method="post"  enctype="multipart/form-data">>
		<table width="100%" class="formTable">
			<tbody>
				<tr>
					<td width="100px" align="right">
						<font color="red">*</font>品牌标题
					</td>
					<td>
						<input type="text" name="timestamp" hidden="true" value="${timestamp}"/>
						<input type="text" name="title" value="${title}" style="width:100%;"/>
						<c:if test="${not empty titleMessage}">
							<font color="red">${titleMessage}</font>
						</c:if>
					</td>
				</tr>


				<tr>
					<td width="100px" align="right" valign="top">
						<font color="red">*</font>品牌LOGO
					</td>
					<td>
						<input type="file" name="fileName"/>
						<font color='red'>${contentMessage}</font>
					</td>
				</tr>

				<tr>
					<td width="100px" align="right">
						<font color="red">*</font>品牌类型
					</td>
					<td>
						${phtml}<font color='red'>${typeMessage}</font>
					</td>
				</tr>
				<tr>
					<td width="100px" align="right" valign="top">
						<font color="red">*</font>品牌介绍
					</td>
					<td>
						<textarea style="width:100%;height:300px;resize:none;overflow: auto;" name="content">${content}</textarea>
						<font color='red'>${contentMessage}</font>
					</td>
				</tr>
				
				<tr>
					<td width="100px"></td>
					<td>
						<input type="submit" value="提交">
						<c:if test="${not empty addMessage}">
							<font color="red">${addMessage}</font>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>