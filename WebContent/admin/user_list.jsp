<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Users - Evergreen Bookstore Admin Panel</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2>Users Management</h2>
		<h3><a href="user_form.jsp">Create new User</a></h3>
	</div>
	
	<c:if test="${!empty sessionScope.user_success}">
		<div align="center">
			<h4><i>${sessionScope.user_success}</i></h4>
		</div>
		<c:remove var="user_success" scope="session" />
	</c:if>
	
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Email</th>
				<th>Full Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="user" items="${users}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${user.email}</td>
					<td>${user.fullName}</td>
					<td>
						<a href="edit_user?id=${user.userId}">Edit</a> &nbsp;
						<a href="delete_user?id=${user.userId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
</html>