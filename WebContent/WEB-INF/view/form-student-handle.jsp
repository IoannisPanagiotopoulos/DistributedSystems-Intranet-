<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

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
					<form action="${pageContext.request.contextPath}/student/handle" method="post">
					<span name="errorrun">${errorun}</span>
						<tr>
						<sec:authorize access="hasRole('ADMIN')">
							<td><input type="hidden" name="username" autocomplete="off"
								value="${student.username}">${student.username}</td>
							<td><input type="text" name="password" autocomplete="off"
								value="${student.password}"></td>
							<td><input type="text" name="name" autocomplete="off"
								value="${student.userInformation.name}"></td>
							<td><input type="text" name="email" autocomplete="off"
								value="${student.userInformation.email}"></td>
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
									name="action" value="submit">Submit</button>
								<button class="btn btn-outline-danger" type="submit"
									name="action" value="delete">Delete</button></td>
						</sec:authorize>
						<sec:authorize access="hasRole('OFFICER')">
							<td><input type="hidden" name="username" autocomplete="off"
								value="${student.username}">${student.username}</td>
							<td><input type="hidden" name="password" autocomplete="off"
								value="${student.password}">${student.password}</td>
							<td><input type="hidden" name="name" autocomplete="off"
								value="${student.userInformation.name}">${student.userInformation.name}</td>
							<td><input type="hidden" name="email" autocomplete="off"
								value="${student.userInformation.email}">${student.userInformation.email}</td>
							<td><input type="hidden" name="departmentName" autocomplete="off"
								value="${student.userInformation.departmentName}">${student.userInformation.departmentName}</td>
							<c:if test="${student.userInformation.activated='inactive'}">
							<td><select name="activated">
									<option value="active" selected>Activated</option>
									<option value="inactive">Not Activated</option>
							</select></td>
							</c:if>
							<c:if test="${student.userInformation.activated='active'}">
							<td><input type="hidden" name="active" autocomplete="off"
								value="${student.userInformation.activated}">${student.userInformation.activated}</td>
							</c:if>
							<td>
								<c:if test="${student.userInformation.activated='inactive'}">
								<button class="btn btn-outline-success" type="submit"
									name="action" value="submit">Submit</button>
								</c:if>
								<button class="btn btn-outline-danger" type="submit"
									name="action" value="delete">Delete</button>
							</td>
						</sec:authorize>
						</tr>
					</form>
			</table>
		</div>
	</div>

</body>
</html>