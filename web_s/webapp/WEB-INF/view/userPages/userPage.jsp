 <%@ page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>  
 <%@ page isELIgnored="false"%>
 <%@ page import="java.util.List" %> 
 <%@ page import="it.by.library.entity.Books" %>    

 
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Books</title>
</head>
<body>
 		

<h3>select books </h3>


<form action="/web_s/userPages/selectBooks">
<input type="hidden" name="command" value="SelectBook" />
 <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}">
        <table  border="1px">
        	<tr >
        		<th>Id</th>
        		<th>Book</th>
        		<th>Author</th>
        		<th>Publisher</th>
        		<th>Date publication</th>
        		<th>Genre</th>
        		
        	</tr>
        	<%--  <c:forEach var="b" items="${sessionScope.books}"> --%>
        	<c:forEach var="b" items="${books}">
            	<tr>
            		<td><c:out value="${b.getId()}"/>
            		<td><c:out value="${b.getName_book()}"/>  
            		<td><c:out value="${b.getAuthor()}"/>  
            		<td><c:out value="${b.getPublisher()}"/>  
            		<td><c:out value="${b.getPublication_date()}"/>  
            		<td><c:out value="${b.getGenres().getGenre()}"/>  
            		 <td><input type="checkbox" name="idBook" value="${b.id}">   		            		
        		<tr/>
        	</c:forEach>
        </table>
        <br>
        <input type = "submit" name = "ok" value="ok ">

<br>
    <c:if test="${currentPage != 1}">
        <td><a href="/web_s/userPages/userPage/${currentPage - 1}">Previous</a></td>
    </c:if>
 
 <table >
        <tr>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="/web_s/userPages/userPage/${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
     
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="/web_s/userPages/userPage/${currentPage + 1}" > Next </a> </td>
    </c:if>

</form>




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