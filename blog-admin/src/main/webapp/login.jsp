<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
</head>
<body>

<h1>user login</h1>  
<form action="login" method="post">  
username:<input type="text" name="username"><p>  
password:<input type="password" name="password">  
<p>  
${msg }  
<input type="submit" value="submit">  
</form>  

</body>
</html>