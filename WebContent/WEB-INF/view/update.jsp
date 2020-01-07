<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Page</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" >
<link href="${pageContext.request.contextPath}/resources/css/welcome.css" rel="stylesheet" >
</head>
<body>

	<div class="container">
		<div style="position: absolute; right: 10px; top: 0;">
			${sessionScope.officerName} <a href="${pageContext.request.contextPath}/logoutSession">Logout</a>
		</div>
		<h2>Welcome to the Site!!</h2>
		<br> <br>
		
		<div>
		<span name="authorizationerror">${error}</span>
			<form class="table" action="updatePost" method="post">
				<table class="table">
					<tr>
						<th>Username</th>
						<th>Password</th>
						<th>Name</th>
						<th>Department</th>
						<th>Activated</th>
					</tr>
					<tr>
						<input type="hidden" name="id" autocomple="off" value="${id}">
						<td><input type="hidden" name="username" autocomplete="off" value="${user}">${user}</td>
						<td><input type="hidden" name="password" autocomplete="off" value="${password}">${password}</td>
						<td><input type="hidden" name="name" autocomplete="off" value="${name}">${name}</td>
						<!--<td><select name="department">
							<option value="Informatics" selected>Informatics</option>
							<option value="Nutrition">Nutrition</option>
						</select></td> -->
						<td><input type="hidden" name="department" autocomplete="off" value="${dept}">${dept}</td>
						<td><input type="hidden" name="email" autocomplete="off" value="${email}">${email}</td>
						<td>
						<select name="activated">
							<option value="Activated" selected>Activated</option>
							<option value="Not Activated">Not Activated</option>
						</select></td>
						<td style="border: none; width: 50px">
		                	<input class="btn btn-primary" type="submit" name="submit" value="Submit" /> 
	                   	</td>   
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>