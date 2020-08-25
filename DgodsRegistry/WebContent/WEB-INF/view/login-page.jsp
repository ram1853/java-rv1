<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix= "c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
	.error{color:red}
	.logoutmsg{color:blue}
</style>
</head>
<body>
		<h2>Login with Username and Password</h2>
		
		<form:form action="${pageContext.request.contextPath}/processLogin" method="POST">
			
			Username: <input type="text" name="username"/>
			<br><br>
			
			Password: <input type="password" name="password"/>
			<br><br>
			
			<input type="submit" name="Login"/>
			<br><br>
			
			<c:if test="${param.error == 'true'}">
			
				<i class="error">Invalid credentials</i>
			
			</c:if>
			
		</form:form>
</body>
</html>