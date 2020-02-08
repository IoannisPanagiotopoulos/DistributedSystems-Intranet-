<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Officer</title>
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
				<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/">Home Page
						<span class="sr-only">(current)</span>
				</a></li>
				<sec:authorize access="hasRole('ADMIN')">
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/officer/list"
					aria-disabled="true">Show Officers</a></li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ADMIN', 'SUPERVISOR', 'OFFICER')">
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/student/list"
					aria-disabled="true">Show Students</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('SUPERVISOR')">
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/department/list"
					aria-disabled="true">Show Departments</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('OFFICER')">
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/application/list"
					aria-disabled="true">Show Applications</a></li>
				</sec:authorize>
			</ul>
			User: <sec:authentication property="principal.username" /> <a
				class="btn btn-outline-danger my-2 my-sm-0"
				href="${pageContext.request.contextPath}/logout"> Logout</a>
		</div>
	</nav>
	<div class="container">
		<h2>Update Officer</h2>
		<br> <br>

		<div>
			<table class="table">
				<tr>
					<th>Username</th>
					<th>Password</th>
					<th>Name</th>
					<th>Email</th>
					<th>Department</th>
					<th>Role</th>
					<th>Action</th>
				</tr>
					<form action="${pageContext.request.contextPath}/officer/handle" method="post">
						<tr>
							<td><input type="hidden" name="username" autocomplete="off"
								value="${officer.username}">${officer.username}</td>	
							<td><input type="password" name="password" autocomplete="off"
								value=""></td>
							<td><input type="text" name="name" autocomplete="off"
								value="${officer.userInformation.name}" required></td>
							<td><input type="email" name="email" autocomplete="off"
								value="${officer.userInformation.email}" required></td>
							<td><select name="department" required>
									<option value="Informatics" selected>Informatics</option>
									<option value="Geography">Geography</option>
									<option value="Dietics">Dietics</option>
									<option value="Economics">Economics</option>
							</select></td>
							<td><select name="role" required>
									<option value="Officer" selected>Officer</option>
									<option value="Supervisor">Supervisor</option>
							</select>
							<td><button class="btn btn-outline-success" type="submit"
									name="action" value="submit">Submit</button>
								<button class="btn btn-outline-danger" type="submit"
									name="action" value="delete">Delete</button></td>
						</tr>
						<span name="error">${errorun}</span>
						Change Password <input type="checkbox" name="checkbox">
					</form>
			</table>
		</div>
	</div>
</body>
<script>
	function alertName(){
    	alert("Form has been submitted");
    }
</script>
</html>

