<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/welcome.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet" >
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Harokopio</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Home Page
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
			User: <sec:authentication property="principal.username" /> Name: ${officerName} <a
				class="btn btn-outline-danger my-2 my-sm-0"
				href="${pageContext.request.contextPath}/logout"> Logout</a>
		</div>
	</nav>

	<div class="container">
		<sec:authorize access="hasRole('ADMIN')">
			<div class="alert alert-success mt-5">
				<h3 class="alert-heading">This is the Admin Home Page!</h3>
				<hr>
				<ul>
					<li>Navigate to Show Officers, to create/read/update/delete Officers</li>
					<li>Navigate to Show Students, to create/read/update/delete Students</li>
				</ul>
			</div>
		</sec:authorize>
		<sec:authorize access="hasRole('SUPERVISOR')">
			<div class="alert alert-success mt-5">
				<h3 class="alert-heading">This is the Supervisor Home Page!</h3>
				<hr>
				<ul>
					<li>Navigate to Show Students, to read Students</li>
					<li>Navigate to Show Departments link, to read/activate Departments For Application</li>
				</ul>
			</div>
		</sec:authorize>
		<sec:authorize access="hasRole('OFFICER')">
			<div class="alert alert-success mt-5">
				<h3 class="alert-heading">This is the Officer Home Page!</h3>
				<hr>
				<ul>
					<li>Navigate to Show Students, to read/activate Students</li>
					<li>Navigate to Show Applications, to activate/reject Applications</li>
				</ul>
			</div>
		</sec:authorize>
	</div>
</body>
</html>