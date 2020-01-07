<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Student</title>
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
				<li class="nav-item active"><a class="nav-link"
					href="${pageContext.request.contextPath}/loginRegister">User
						Catalogue <span class="sr-only">(current)</span>
				</a></li>
			</ul>
			User: ${sessionScope.officerName} <a
				class="btn btn-outline-danger my-2 my-sm-0"
				href="${pageContext.request.contextPath}/logoutSession">Logout</a>
		</div>
	</nav>
	<div class="container">
		<h2>Update Student</h2>
		<br> <br>

		<div>
			<table class="table">
				<tr>
					<th>Username</th>
					<th>Password</th>
					<th>Name</th>
					<th>Email</th>
					<th>Department</th>
					<th>Activate</th>
				</tr>
					<form action="updateStudent" method="post">
					<span name="errorrun">${errorun}</span>
						<tr>
							<input type="hidden" name="id" autocomplete="off"
								value="${id}">
							<td><input type="text" name="username" autocomplete="off"
								value="${username}"></td>
							<td><input type="text" name="password" autocomplete="off"
								value="${password}"></td>
							<td><input type="text" name="name" autocomplete="off"
								value="${name}"></td>
							<td><input type="text" name="email" autocomplete="off"
								value="${email}"></td>
							<td><select name="department">
									<option value="Informatics" selected>Informatics</option>
									<option value="Geography">Geography</option>
									<option value="Economics">Economics</option>
									<option value="Dietics">Dietics</option>
							</select></td>
							<td><select name="activated">
									<option value="active" selected>Activated</option>
									<option value="inactive">Not Activated</option>
							</select>
							<td><button class="btn btn-outline-success" type="submit"
									name="action" value="submit">Submit</button></td>
						</tr>
					</form>
			</table>
		</div>
	</div>

</body>
</html>