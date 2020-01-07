<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Student List</title>
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/welcome.css"
	rel="stylesheet">
</head>
<body>
		<!--<div style="position: absolute; right: 10px; top: 0;">
			${sessionScope.officerName} <a
				href="${pageContext.request.contextPath}/logoutSession">Logout</a>
		</div> -->
		
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Harokopio</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/loginRegister">Student
						List <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/application">Applications Status
					</a></li>
			</ul>
			User: ${sessionScope.officerName}
			<a
				class="btn btn-outline-danger my-2 my-sm-0"
				href="${pageContext.request.contextPath}/logoutSession">Logout</a>
		</div>
	</nav>
	<div class="container">
		<h2>Student List</h2>
		<br> <br>
	
		<div>
			<table class="table">
				<tr>
					<th>Username</th>
					<th>Password</th>
					<th>Name</th>
					<th>Department</th>
					<th>Points</th>
					<th>Activate</th>
				</tr>
				<span name="message">${message}</span>
				<c:forEach items="${userList}" var="user">
					<tr>
						<input type="hidden" name="id" autocomplete="off"
							value="${user.id}">
						<td><input type="hidden" name="username" autocomplete="off"
							value="${user.user}">${user.user}</td>
						<td><input type="hidden" name="password" autocomplete="off"
							value="${user.password}">${user.password}</td>
						<td><input type="hidden" name="name" autocomplete="off"
							value="${user.name}">${user.name}</td>
						<td><input type="hidden" name="department"
							autocomplete="off" value="${String.valueOf(user.dept)}">${String.valueOf(user.dept)}</td>
							<td><input type="hidden" name="points"
							autocomplete="off" value="${user.points}">${user.points}</td>
						<td><input type="hidden" name="activated"
							autocomplete="off" value="${String.valueOf(user.activated)}">
						
							<c:if test="${String.valueOf(user.activated)=='inactive'}">
								Not Activated
							</c:if>
							<c:if test="${String.valueOf(user.activated)=='active'}">
								Activated
							</c:if></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>