<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=charset=UTF-8">
<title>Admin menu</title>
</head>
<body>

	<form name="Add genre" method="POST" action="genre/genreList">
	 <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}">
			<input type="submit" value="Genres" />
	</form>
<br>
	<form name="Add books" method="POST" action="adminPages/adminPage/1">
	 <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}">
			<input type="submit" value="Books" />
	</form>
<br>
	<form name="Journal" method="POST" action="journal">
	 <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}">
			<input type="submit" value="Journal" />
	</form>	
<br>
<hr>

	<form action="logout" method="post">
	 <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}">
			<input type="submit" value="Logout">
	</form>


</body>
</html>