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
				<li class="nav-item active"><a class="nav-link" href="#">User
						Catalogue <span class="sr-only">(current)</span>
				</a></li>
			</ul>
			User: ${sessionScope.officerName} <a
				class="btn btn-outline-danger my-2 my-sm-0"
				href="${pageContext.request.contextPath}/logoutSession">Logout</a>
		</div>
	</nav>
	<div class="container">

		<h2>Officers</h2>
		<br> <br>

		<div>
			<table class="table">
				<tr>
					<th>Username</th>
					<th>Password</th>
					<th>Department</th>
					<th>Role</th>
					<form method="post" action="officerHandler">
						<th>
							<button class="btn btn-success" type="submit" name="action"
								value="add">+</button>
						</th>
					</form>
				</tr>
				<span name="error">${error}</span>
				<c:forEach items="${officerList}" var="officer">
					<form class="table" method="post" action="officerHandler">
						<tr>
							<input type="hidden" name="id" autocomplete="off"
								value="${officer.id}">
							<td><input type="hidden" name="username" autocomplete="off"
								value="${officer.username}">${officer.username}</td>
							<td><input type="hidden" name="password" autocomplete="off"
								value="${officer.password}">${officer.password}</td>
							<td><input type="hidden" name="department"
								autocomplete="off" value="${String.valueOf(officer.department)}">${String.valueOf(officer.department)}</td>
							<td><input type="hidden" name="role" autocomplete="off"
								value="${String.valueOf(officer.role)}">${String.valueOf(officer.role)}</td>
							<td><button class="btn btn-outline-primary" type="submit"
									name="action" value="edit">Edit</button>
								<button class="btn btn-outline-danger" type="submit"
									name="action" value="delete">Delete</button></td>
						</tr>
					</form>
				</c:forEach>
			</table>
		</div>

		<br> <br>
		<h2>Students</h2>
		<br> <br>

		<div>
			<table class="table">
				<tr>
					<th>Username</th>
					<th>Password</th>
					<th>Name</th>
					<th>Department</th>
					<th>Email</th>
					<th>Activate</th>
					<form method="post" action="studentHandler">
						<th>
							<button class="btn btn-success" type="submit" name="action"
								value="add">+</button>
						</th>
					</form>
				</tr>
				<span name="error">${error2}</span>
				<c:forEach items="${userList}" var="user">
					<form class="table" method="post" action="studentHandler">
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
							<td><input type="hidden" name="email"
								autocomplete="off" value="${user.email}">${user.email}</td>
							<td><input type="hidden" name="activated" autocomplete="off"
								value="${String.valueOf(user.activated)}"> <c:if
									test="${String.valueOf(user.activated)=='inactive'}">
									Not Activated
								</c:if> <c:if test="${String.valueOf(user.activated)=='active'}">
									Activated
								</c:if></td>
							<td><button class="btn btn-outline-primary" type="submit"
									name="action" value="edit">Edit</button>
								<button class="btn btn-outline-danger" type="submit"
									name="action" value="delete">Delete</button></td>
						</tr>
					</form>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>