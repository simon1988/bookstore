<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
</head>
<body>
<form action="/login.do?fromurl=${param.fromurl}" method="post">  
       username: <input type="text" name="username" id="username"><br>  
       password: <input type="password" name="password" id="password"><br>  
       <input type="submit" value="Login">  
</form>
</body>
</html>