<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>${username}'s orders</title>
    <link type="text/css" rel="stylesheet" href="/resources/style/bookstore.css">
</head>
<body>
<%@ include file="header.jsp"%>
<table>
	<c:forEach var="order" items="${orders}">
		<tr>
		<td>
			${order.id}
		<td>
		<c:forEach var="book" items="${order.books}">
			<a href="/book/${book.id}">${book.name}</a><br>
		</c:forEach>
		</td>
		<td>
			<fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd hh:mm:ss" />
		</td>
		<td>
			$${order.getTotalPrice()}
		</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>