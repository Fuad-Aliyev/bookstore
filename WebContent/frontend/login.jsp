<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Evergreen Books</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h3>Please login</h3>
		Email: <input type="text" size="10"><br/>
		Password: <input type="password" size="10"/>
		<input type="submit" value="login" />
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
</html>