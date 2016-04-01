<%@ page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.List"%>
<%@ page import="it.by.library.entity.Books"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>admin page</title>
</head>
<body>


	<h3>All books </h3>
	<table border="1px">
		<tr>
			<th>Id</th>
			<th>Book</th>
			<th>Author</th>
			<th>Publisher</th>
			<th>Date publication</th>

		</tr>
		<c:forEach var="b" items="${books}">
			<tr>
				<td><c:out value="${b.getId()}" />
				<td><c:out value="${b.getName_book()}" />
				<td><c:out value="${b.getAuthor()}" />
				<td><c:out value="${b.getPublisher()}" />
				<td><c:out value="${b.getPublication_date()}" />
				<td><a href="/web_s/adminPages/deleteBook?id=${b.getId()}">delete</a>
					     
			<tr />
		</c:forEach>
	
	</table>
<br>
 
 
 
    <c:if test="${currentPage != 1}">
        <td><a href="/web_s/adminPages/adminPage/${currentPage - 1}">Previous</a></td>
    </c:if>
 
 <table >
        <tr>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="/web_s//adminPages/adminPage/${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
     
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="/web_s/adminPages/adminPage/${currentPage + 1}" > Next </a> </td>
    </c:if>



<br /> 

<s:url var="saveUrl" value="/adminPages/addBook" />
<form:form modelAttribute="book" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="name_book">Book:</form:label></td>
            <td><form:input path="name_book" maxlength="70"/></td>
            <td><form:errors path="name_book" cssStyle="color: red"/></td>
            
        </tr>

        <tr>
            <td><form:label path="author">Author</form:label></td>
            <td><form:input path="author" maxlength="50"/></td>
             <td><form:errors path="author" cssStyle="color: red"/></td>
        </tr>

        <tr>
            <td><form:label path="publisher">Publisher</form:label></td>
            <td><form:input path="publisher" maxlength="50" /></td>
             <td><form:errors path="publisher" cssStyle="color: red"/></td>
        </tr>
        
        <tr>
            <td><form:label path="publication_date">Date publication</form:label></td>
            <td><form:input path="publication_date" maxlength="50"/></td>
             <td><form:errors path="publication_date" cssStyle="color: red"/></td>
        </tr>
        
        <tr>
            <td><form:label path="count">number</form:label></td>
            <td><form:input path="count" maxlength="2"/></td>
              <td><form:errors path="count" cssStyle="color: red"/></td>
        </tr>
      
    </table>
   		 <br>
     <select name="g" >
			 <option disabled>choose genre</option>
		 <c:forEach items="${genre}" var="d">
	 		 <option value="${d.genre}">${d.genre}</option>
		 </c:forEach>
	 </select><br />
	   <font color="red" face="Arial">${errorDataMessage}</font><br />
	     <font color="red" face="Arial">${errorCantRemoveMessage}</font><br />
	     <font color="red" face="Arial">${errorDigitMessage}</font><br />
    <input type="submit" value="Save" />
</form:form>

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