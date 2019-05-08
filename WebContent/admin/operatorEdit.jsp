<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String type = request.getAttribute("type").toString();
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
				当前位置:<a href="admin/OperatorServlet">用户管理</a> > <a href="admin/OperatorInitEdit?operatorID=${operator['operatorID']}">编辑用户信息</a>
			</td>
			<td width="50%" align="right" valign="middle">
				
			</td>
		</tr>
	</table>
	<hr/>
	
	<form action="admin/OperatorEdit" method="post">
		<input type="hidden" value="${operator['operatorID']}" name="operatorID">
		<table width="100%" class="formTable" border="0">
			<tbody>
				<tr>
					<td width="150" align="right"><font color="red">*</font>姓名</td>
					<td width="350">
						<input type="text" name="name" value="${operator['name']}"/>
						<c:if test="${not empty nameMessage}">
							<font color="red">${nameMessage}</font>
						</c:if>
					</td>
					<td rowspan="6" valign="top" style="margin: 0;padding:0;">
						<div style="width:100%;height:220px; position: relative;overflow: atuo;">
							<iframe src="admin/photoUpload.jsp?operatorID=${operator['operatorID']}&photo=${operator['photo']}" border="0" frameborder="0" style="height: 200px;"></iframe>
						</div>
					</td>
				</tr>
				
				<tr>
					<td width="150" align="right">性别</td>
					<td width="350">
						<c:if test="${operator['gender']==\"男\"}">
							<input type="radio" name="gender" value="男" checked="checked" id="male"/><label for="male">男</label>
							<input type="radio" name="gender" value="女" id="female"/><label for="female">女</label>
						</c:if>
						<c:if test="${operator['gender']==\"女\"}">
							<input type="radio" name="gender" value="男" id="male"/><label for="male">男</label>
							<input type="radio" name="gender" value="女" id="female" checked="checked"/><label for="female">女</label>
						</c:if>
						 
						
					</td>
				</tr>
				
				<tr>
					<td width="150" align="right"><font color="red">*</font>帐号</td>
					<td width="350">
						<input type="text" name="account" value="${operator['account'] }"/>
						<input type="hidden" name="oldaccount" value="${operator['account'] }"/>
						<c:if test="${not empty accountMessage}">
							<font color="red">${accountMessage}</font>
						</c:if>
					</td>
				</tr>
				
				<tr>
					<td width="150" align="right">邮箱</td>
					<td width="350">
						<input type="email" name="email" value="${operator['email']}"/>
						<c:if test="${not empty errorMessage}">
							<font color="red">${errorMessage}</font>
						</c:if>
					</td>
				</tr>
				
				<tr>
					<td width="150" align="right">用户类型</td>
					<td width="350">
						<c:set value="${operator['type']}" var="type"></c:set>
						<c:forEach items="${operatorTypeList}" var="item" varStatus="status">
							<c:if test="${item['operatorTypeID'] == type}">
								<input type="radio" name="type" value="${item['operatorTypeID']}" id="type${status.count}" checked="checked"/><label for="type${status.count}">${item['name']}</label>
							</c:if>
							<c:if test="${item['operatorTypeID'] != type}">
								<input type="radio" name="type" value="${item['operatorTypeID']}" id="type${status.count}"/><label for="type${status.count}">${item['name']}</label>
							</c:if>
							
						</c:forEach>
					</td>
				</tr>
				
<!-- 				<tr> -->
<!-- 					<td width="150" align="right"><font color="red">*</font>密码</td> -->
<!-- 					<td> -->
<!-- 						<input type="password" name="password"/> -->
<%-- 						<c:if test="${not empty passwordMessage}"> --%>
<%-- 							<font color="red">${passwordMessage}</font> --%>
<%-- 						</c:if> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
				
<!-- 				<tr> -->
<!-- 					<td width="150" align="right"><font color="red">*</font>确认密码</td> -->
<!-- 					<td> -->
<!-- 						<input type="password" name="repassword"/> -->
<%-- 						<c:if test="${not empty repasswordMessage}"> --%>
<%-- 							<font color="red">${repasswordMessage}</font> --%>
<%-- 						</c:if> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
				
				<tr>
					<td width="150"></td>
					<td width="350">
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