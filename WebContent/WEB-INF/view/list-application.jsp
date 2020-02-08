<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Application Review</title>
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/welcome.css"
	rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
			User: <sec:authentication property="principal.username" /> <a
				class="btn btn-outline-danger my-2 my-sm-0"
				href="${pageContext.request.contextPath}/logout"> Logout</a>
		</div>
	</nav>

	<div class="container">

		<h2>Application Review</h2>
		<br> <br>

		<div id = "error_placeholder"></div>
		<div id = "success_placeholder"></div>

		<input type="hidden" name="error" id="error" value="${error}" />
		<input type="hidden" name="success" id="success" value="${success}" />

		<div>
			<table class="table">
				<tr>
					<th>Username</th>
					<th>Department</th>
					<th>City</th>
					<th>Income</th>
					<th>Family Income</th>
					<th>Parent 1 Employment</th>
					<th>Parent 2 Employment</th>
					<th>Siblings as Students</th>
					<th></th>
				</tr>
				<c:forEach items="${applications}" var="app">
				    <span name="authorizationerror">${error}</span>
					<form class="table" method="post" action="${pageContext.request.contextPath}/application/handle">
						<tr>
							<td><input type="hidden" name="username" autocomplete="off"
								value="${app.username}">${app.username}</td>
							<td>${app.departmentName}</td>
							<td>${app.city}</td>
							<td>${app.personalIncome}</td>
							<td>${app.familyIncome}</td>
							<td>${app.parent1_employmentStatus}</td>
							<td>${app.parent2_employmentStatus}</td>
							<td>${app.siblingsStudents}</td>
							<td><button class="btn btn-success" type="submit"
										name="submit" value="approve">Approve</button>
								<button class="btn btn-danger" type="submit"
										name="submit" value="reject">Reject</button>
							</td>
						</tr>
					</form>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
<script>
	var Msg = document.getElementById("error");
	if(Msg.value.length != 0) {
		$('#error_placeholder').html('<div class="alert alert-danger"><span>'+Msg.value+'</span></div>')
	}
	
	var Msg2 = document.getElementById("success");
	if(Msg2.value.length != 0) {
		console.log('hey')
		$('#success_placeholder').html('<div class="alert alert-success"><span>'+Msg2.value+'</span></div>')
	}
</script>
</html>