<%@ page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Your books </h3>
	<table border="1px">
		<tr>
			<th>id</th>
			<th>User</th>
			<th>Book</th>
			<th>Author</th>
			<th>Publisher</th>
			<th>Date publication</th>
			<th>date time take</th>

		</tr>
		<c:forEach var="b" items="${journal}">
			<tr>
				 <td><c:out value="${b.getId()}" />
				<td><c:out value="${b.getUsers().getName()}" />
				<td><c:out value="${b.getBooks().getName_book()}" />
				<td><c:out value="${b.getBooks().getAuthor()}" />
				<td><c:out value="${b.getBooks().getPublisher()}" />
				<td><c:out value="${b.getBooks().getPublication_date()}" />
				<td><c:out value="${b.getDate_time_take()}" />
			<tr />
		</c:forEach>
	
	</table>

<hr>
<p>
<br>
	<form action="/web_s/userMenu" method = "GET">
		 <input	type="submit" value="Menu">
	</form>
<br>
 

	<form action="/web_s/logout" method="post">
	 		<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}">
			<input type="submit" value="Logout">
	</form>



</body>
</html>