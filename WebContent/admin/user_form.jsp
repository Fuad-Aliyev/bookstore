<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New User - Evergreen Bookstore Admin Panel</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<!-- This part will care of utilities for edit and create page -->
	<%
		String formHeaderText = "";
		String formUrl = "";
		if (request.getAttribute("user") == null) {
			formHeaderText = "Create New User";
			formUrl = "create_user";
		} else {
			formHeaderText = "Edit User";
			formUrl = "update_user";
		}
	%>
	
	<div align="center">
		<h2><%= formHeaderText %></h2>
	</div>
				
	<c:if test=${!empty sessionScope.user_error}>
		<div align="center">
			<h4><i>${sessionScope.user_error}</i></h4>
		</div>
	</c:if>
	
	<%-- This will remove session while refreshing page --%>
	<%
		request.getSession().invalidate();
	%>
	
	<div align="center">
		<form action=<%=formUrl %> method="post" onsubmit="return validateFormInput()">
		<input type="hidden" name="userId" value="${user.userId}">
			<table>
				<tr>
					<td align="right">Email: </td>
					<td align="left"><input type="text" id="email" name="email" size="20" value="${user.email}" /></td>
				</tr>
				<tr>
					<td align="right">Full name: </td>
					<td align="left"><input type="text" id="fullname" name="fullname" size="20" value="${user.fullName}" /></td>
				</tr>
				<tr>
					<td align="right">Password: </td>
					<td align="left"><input type="password" id="password" name="password" size="20" value="${user.password}" /></td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Save">
						<input type="button" value="Cancel" onclick="javascript:history.go(-1)">
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	function validateFormInput() {
		var fieldEmail = document.getElementById("email");
		var fieldFullname = document.getElementById("fullname");
		var fieldPassword = document.getElementById("password");
		
		if (fieldEmail.value.length == 0) {
			alert("Email is required!");
			fieldEmail.focus();
			
			return false;
		}
		
		if (fieldFullname.value.length == 0) {
			alert("Full name is required!");
			fieldFullname.focus();
			
			return false;
		}
		
		if (fieldPassword.value.length == 0) {
			alert("Password is required!");
			fieldPassword.focus();
			
			return false;
		}
		
		return true;
	}
</script>
</html>