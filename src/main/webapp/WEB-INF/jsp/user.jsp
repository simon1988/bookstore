<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>customer page</title>
<link type="text/css" rel="stylesheet" href="/resources/style/bookstore.css">
</head>
<body>
<%@ include file="header.jsp"%>
Hello ${customer.name}, you have
$<fmt:formatNumber value="${customer.balance}" pattern="##.##" minFractionDigits="2" /> in your account;
</body>
</html>