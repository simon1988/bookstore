<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Bookstore</title>
    <link type="text/css" rel="stylesheet" href="/resources/style/bookstore.css">
</head>
<body>
<%@ include file="header.jsp"%>
<table>
	<c:forEach var="book" items="${books}">
		<tr>
		<td>
			<a href="/book/${book.id}">${book.name}</a>
		</td>
		<td>
			${book.author}
		</td>
		<td>
			${book.price}
		</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>