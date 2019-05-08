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
				当前位置:<a href="admin/OperatorServlet">用户管理</a> > <a href="admin/OperatorInitAdd">新增用户</a>
			</td>
			<td width="50%" align="right" valign="middle">
				
			</td>
		</tr>
	</table>
	<hr/>
	
	<form action="admin/OperatorAdd" method="post">
		<table width="100%" class="formTable">
			<tbody>
				<tr>
					<td width="30%" align="right"><font color="red">*</font>姓名</td>
					<td>
						<input type="text" name="name" value="${name}"/>
						<c:if test="${not empty nameMessage}">
							<font color="red">${nameMessage}</font>
						</c:if>
					</td>
				</tr>
				
				<tr>
					<td width="30%" align="right">性别</td>
					<td>
						<input type="radio" name="gender" value="男" checked="checked" id="male"/><label for="male">男</label> 
						<input type="radio" name="gender" value="女" id="female"/><label for="female">女</label>
					</td>
				</tr>
				
				<tr>
					<td width="30%" align="right"><font color="red">*</font>帐号</td>
					<td>
						<input type="text" name="account" value="${account}"/>
						<c:if test="${not empty accountMessage}">
							<font color="red">${accountMessage}</font>
						</c:if>
					</td>
				</tr>
				
				<tr>
					<td width="30%" align="right">邮箱</td>
					<td>
						<input type="email" name="email" value="${email}"/>
						<c:if test="${not empty errorMessage}">
							<font color="red">${errorMessage}</font>
						</c:if>
					</td>
				</tr>
				
				<tr>
					<td width="30%" align="right">用户类型</td>
					<td>
						
						<c:forEach items="${list}" var="item" varStatus="status">
							<c:if test="${status.count==1}">
								<input type="radio" name="type" value="${item['operatorTypeID']}" id="type${status.count}" checked="checked"/><label for="type${status.count}">${item['name']}</label>
							</c:if>
							<c:if test="${status.count!=1}">
								<input type="radio" name="type" value="${item['operatorTypeID']}" id="type${status.count}"/><label for="type${status.count}">${item['name']}</label>
							</c:if>
						</c:forEach>
					</td>
				</tr>
				
				<tr>
					<td width="30%" align="right"><font color="red">*</font>密码</td>
					<td>
						<input type="password" name="password"/>
						<c:if test="${not empty passwordMessage}">
							<font color="red">${passwordMessage}</font>
						</c:if>
					</td>
				</tr>
				
				<tr>
					<td width="30%" align="right"><font color="red">*</font>确认密码</td>
					<td>
						<input type="password" name="repassword"/>
						<c:if test="${not empty repasswordMessage}">
							<font color="red">${repasswordMessage}</font>
						</c:if>
					</td>
				</tr>
				
				<tr>
					<td width="30%"></td>
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