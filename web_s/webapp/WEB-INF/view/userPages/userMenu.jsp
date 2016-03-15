<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=charset=UTF-8">
<title>User menu</title>
</head>
<body>

	<form name="Add genre" method="POST" action="userPages/userPage/1">
	 <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}">
			<input type="hidden" name="command" value="UserStartPage" />
			
			<input type="submit" value="Select books" />
	</form>
<br>
	<form name="Journal" method="POST" action="userPages/userBooks">
	 <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}">
			<input type="hidden" name="command" value="userBooks" />
			<input type="submit" value="My books" />
	</form>	
<br>
<hr>
	<form action="logout" method="post">
	 <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}">
			<input type="submit" value="Logout">
	</form>
</body>
</html>