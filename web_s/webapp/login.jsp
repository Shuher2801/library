<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login page</title>
		<link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
		<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
		<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
	</head>
</head>
<body>

<div class="container" style="width: 300px;">
    		<c:url value="/j_spring_security_check" var="loginUrl" />
   				 <form action="${loginUrl}" method="post">
       				 <h2 class="form-signin-heading">Please sign in</h2>
       					 <input type="text" maxlength="50" class="form-control" name="j_username" placeholder="Enter Username" required="required">
       					 <input type="password" maxlength="50" class="form-control" name="j_password" placeholder="Enter Password" required="required">
       					 <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}"><jsp:text/>
       				 <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
   				 </form>
		</div>
<p style="color: red;">
<c:if test="${param.error=='invalidLoginPassword'}">
invalid login or password. Please check and try again
</c:if>

</p>


</body>
</html>