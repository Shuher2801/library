<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/css.css"/>">
<meta http-equiv="Content-Type"
	content="text/html; charset=charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
	

 <security:authorize access="isAuthenticated()">
 
 <security:authorize access="hasRole('ROLE_USER')">
<form action="userMenu">
					<button type="submit" value="User">User</button>
				</form>
 <c:redirect url="userMenu"/>
 </security:authorize>
<security:authorize access="hasRole('ROLE_ADMIN')"> 
<form action="adminMenu">
					<button type="submit" value="Admin">Admin</button>
				</form>
  <c:redirect url="adminMenu"/>
 </security:authorize>
 
</security:authorize>








</body>
</html>