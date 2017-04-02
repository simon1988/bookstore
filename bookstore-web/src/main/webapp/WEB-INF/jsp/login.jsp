<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
</head>
<body>
<form action="/doLogin?fromurl=${param.fromurl}" method="post">  
       username: <input type="text" name="username" id="username"><br>  
       password: <input type="password" name="password" id="password"><br>  
       <input type="submit" value="Login">  
</form>
</body>
</html>