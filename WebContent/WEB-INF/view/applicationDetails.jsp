<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
				<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/loginRegister">Student
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

		<h2>Application Review</h2>
		<br> <br>

		<div>
			<table class="table">
				<tr>
					<th>Username</th>
					<th>Department</th>
					<th>Name</th>
					<th>City</th>
					<th>Income</th>
					<th>Family Income</th>
					<th>Parent 1 Employment</th>
					<th>Parent 2 Employment</th>
					<th>Siblings as Students</th>
					<th></th>
				</tr>
				<c:forEach items="${applicationList}" var="app">
				    <span name="authorizationerror">${error}</span>
					<form class="table" method="post" action="applicationDetailSubmit">
						<tr>
							<input type="hidden" name="id" autocomple="off"
								value="${app.id}">
							<td>${app.username}</td>
							<td>${app.dept}</td>
							<td>${app.name}</td>
							<td>${app.city}</td>
							<td>${app.income}</td>
							<td>${app.familyIncome}</td>
							<td>${app.parent1EmploymentStatus}</td>
							<td>${app.parent2EmploymentStatus}</td>
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
</html>