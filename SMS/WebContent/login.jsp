<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
${message}<br>
	<form action="${pageContext.request.contextPath}/loginServlet" method="post">
		学号：<input type="text" name="num"/><br>
		密码：<input type="password" name="password"/><br>
		<input type="submit" value="登录"/>
	</form>
	<a href="${pageContext.request.contextPath}/toRegisterServlet">注册</a>
</body>
</html>