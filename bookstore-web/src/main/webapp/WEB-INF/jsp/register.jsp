<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>register</title>
</head>
<body>
<form action="/doRegister" method="post">  
       username: <input type="text" name="username" id="username"><br>
       password: <input type="password" name="password" id="password"><br>
       email: <input type="email" name="email" id="email"><br>
       <input type="submit" value="Register">  
</form>
</body>
</html>