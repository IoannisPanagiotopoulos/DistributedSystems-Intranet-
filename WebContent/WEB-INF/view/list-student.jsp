<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="gr.hua.ds.users.model.Enums" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

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

		<h2>Students</h2>
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
					<sec:authorize access="hasRole('ADMIN')">
					<form method="post" action="${pageContext.request.contextPath}/student/add">
						<th>
							<button class="btn btn-success" type="submit" name="action"
								value="add">+</button>
						</th>
					</form>
					</sec:authorize>
				</tr>
				<span name="error">${error}</span>
				<c:forEach items="${students}" var="student">
					<form class="table" method="post" action="${pageContext.request.contextPath}/student/id/${student.username}">
						<tr>
							<td>${student.username}</td>
							<td>${student.password}</td>
							<td>${student.userInformation.name}</td>
							<td>${student.userInformation.email}</td>
							<td>${student.userInformation.departmentName}</td>
							<td>${student.userInformation.activated}
							<td><button class="btn btn-outline-primary" type="submit"
									name="action" value="edit">Edit</button></td>
						</tr>
					</form>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>