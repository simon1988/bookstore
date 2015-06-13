<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>${username}'s shopping cart</title>
    <link type="text/css" rel="stylesheet" href="/resources/style/bookstore.css">
	<script type="text/javascript" src="/resources/js/bookstore.js"></script>
</head>
<body>
<%@ include file="header.jsp"%>
<c:if test="${cart.size()==0}">Your cart is empty!</c:if>
<table>
	<c:forEach var="book" items="${cart}">
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
		<td>
			<button type="button" onclick="deleteFromCart('${username}',${book.id})">Delete</button>
		</td>
		</tr>
	</c:forEach>
</table>
<button type="button" onclick="placeNewOrder('${username}')">Place Order</button>
<div id="result"></div>
</body>
</html>