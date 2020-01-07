<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Officer</title>
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
		<h2>Add Officer</h2>
		<br> <br>

		<div>
			<table class="table">
				<tr>
					<th>Username</th>
					<th>Password</th>
					<th>Department</th>
					<th>Role</th>
				</tr>
					<form action="addOfficer" method="post">
						<tr>
							<input type="hidden" name="id" autocomplete="off"
								value="">
							<td><input type="text" name="username" autocomplete="off"
								value=""></td>
							<td><input type="text" name="password" autocomplete="off"
								value=""></td>
							<td><select name="department">
									<option value="All">All</option>
									<option value="Informatics" selected>Informatics</option>
									<option value="Geography">Geography</option>
									<option value="Dietics">Dietics</option>
									<option value="Economics">Economics</option>
							</select></td>
							<td><select name="role">
									<option value="Officer" selected>Officer</option>
									<option value="Supervisor">Supervisor</option>
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