<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Simon's Bookstore</title>
    <link type="text/css" rel="stylesheet" href="/resources/style/bookstore.css">
</head>
<body>
<%@ include file="header.jsp"%>
<table>
	<c:forEach var="book" items="${books}">
		<s:url value="/book/{bookId}" var="book_url">
			<s:param name="bookId" value="${book.id}" />
		</s:url>
		<tr>
		<td>
			<a href="${book_url}">${book.name}</a>
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