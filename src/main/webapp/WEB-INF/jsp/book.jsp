<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book page</title>
<link type="text/css" rel="stylesheet" href="/resources/style/bookstore.css">
<script type="text/javascript" src="/resources/js/bookstore.js"></script>
</head>
<body>
<%@ include file="header.jsp"%>
<ul>
	<li>${book.name}</li>
	<li>${book.author}</li>
	<li>$${book.price}</li>
	<li>${book.description}</li>
</ul>
<input type="button" value="Add To Cart" onclick="addToCart('${username}',${book.id});" />
<div id="result"></div>
</body>
</html>