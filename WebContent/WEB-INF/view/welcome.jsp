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
				<li class="nav-item active"><a class="nav-link" href="#">Student
						List <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/applicationDetails"
					aria-disabled="true">Applications</a></li>
			</ul>
			User: ${sessionScope.officerName} <a
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
					<th>Activate</th>
					<th></th>
				</tr>
				<span name="error" >${error}</span>
				<c:forEach items="${userList}" var="user">
					<form class="table" method="post" action="update">
						<tr>
							<input type="hidden" name="id" autocomple="off"
								value="${user.id}">
							<td><input type="hidden" name="username" autocomplete="off"
								value="${user.user}">${user.user}</td>
							<td><input type="hidden" name="password" autocomplete="off"
								value="${user.password}">${user.password}</td>
							<td><input type="hidden" name="name" autocomplete="off"
								value="${user.name}">${user.name}</td>
							<td><input type="hidden" name="department"
								autocomplete="off" value="${String.valueOf(user.dept)}">${String.valueOf(user.dept)}</td>

							<c:if test="${String.valueOf(user.activated)=='inactive'}">
								<td><input type="hidden" name="activated"
									autocomplete="off" value="active">Not
									Activated</td>
								<td><button class="btn btn-success" type="submit"
										name="activate" value="edit">Activate</td>
							</c:if>
							<c:if test="${String.valueOf(user.activated)=='active'}">
								<td><input type="hidden" name="activated"
									autocomplete="off" value="${String.valueOf(user.activated)}">Activated</td>
								<td></td>
							</c:if>
						</tr>
					</form>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>