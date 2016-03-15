<%@ page language="java" contentType="text/html;  charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.List"%>
<%@ page import="it.by.library.entity.Books"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset= UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>Id</th>
			<th>Genre</th>
		</tr>
		<c:forEach var="b" items="${genres}">
			<tr>
				<td><c:out value="${b.getId()}" />
				<td><c:out value="${b.getGenre()}" />
				<td><a href="deleteGenre?id=${b.getId()}">delete</a>
				
			<tr />
		</c:forEach>
	</table>
<br>
<p>


<sf:url var="saveUrl" value="addGenre" />
<!--  
<form:form modelAttribute="genre" method="POST" action="${saveUrl}" >
    <table>
        <tr>
            <td><form:label path="genre">Genre:</form:label></td>
            <td><form:input path="genre" name="genre" value="" /></td>
        </tr>
    </table>
    <font color="red" face="Arial">${errorDataMessage}</font><br />
		  <font color="red" face="Arial"> ${emptyData}</font><br />
		  <font color="red" face="Arial"> ${errorCantRemoveMessage}</font><br />
    
    <input type="submit" value="Save" />
</form:form> -->


<s:form modelAttribute="genre" method="POST" action="${saveUrl}" >
   <fieldset> 
        
            <label for="genre">Genre:</label>
            <s:errors path="genre" cssStyle="color: red" /><br>
            <s:input path="genre" name="genre" value="" /><br>
          <font color="red" face="Arial">${errorDataMessage}</font><br />
		  <font color="red" face="Arial"> ${emptyData}</font><br />
		  <font color="red" face="Arial"> ${errorCantRemoveMessage}</font><br />
    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}">
    <input type="submit" value="Save" />
    </fieldset>
</s:form>





<!-- 
<form action="addGenre" method="POST">

		<input type="hidden" name="command" value="addGenre" />
		 <input type="text" name="a2" maxlength="50">Genre<br />
		 <font color="red" face="Arial">${errorDataMessage}</font><br />
		  <font color="red" face="Arial"> ${emptyData}</font><br />
		  <font color="red" face="Arial"> ${errorCantRemoveMessage}</font><br />
		  <br>
		<input type="submit" name="ok" value="ok ">

	</form>
	-->
	
	
<br>
<form action="/web_s/adminMenu" method = "GET">
	 <input	type="submit" value="Menu">
	</form>
 <br>
 

	<form action="/web_s/logout" method="post">
	 <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}">
			<input type="submit" value="Logout">
	</form>








</body>
</html>