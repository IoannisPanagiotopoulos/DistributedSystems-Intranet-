 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Applications Status</title>
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
		<h2>Departments</h2>
		<br> <br>

		<div>
			<table class="table">
				<tr>
					<th>Department</th>
					<th>Department Status</th>
					<th>Acceptance Percentage</th>
				</tr>
				<c:forEach items="${departments}" var="dpt" varStatus="count">
					<form class="table" name="form" method="post" action="department/activate"
						onsubmit="return validateForm(${count.index})">
						<tr>
							<td><input type="hidden" name="department"
								autocomplete="off" value="${String.valueOf(dpt.departmentName)}">${String.valueOf(dpt.departmentName)}</td>
							<td><input type="hidden" name="active" autocomplete="off"
								value="${dpt.active}">${dpt.active}</td>

							<c:if test="${String.valueOf(dpt.active)=='inactive'}">
								<td><input type="text" name="percentage" autocomplete="off"
									value="${dpt.percentage}" id="${count.index}"></td>
								<td><button class="btn btn-success" type="submit"
										name="activate" value="start" id="activate${count.index}">Start Application</td>
							</c:if>
							<c:if test="${String.valueOf(dpt.active)=='active'}">
								<td><input type="hidden" name="percentage" autocomplete="off"
									value="${dpt.percentage}" id="${count.index}">${dpt.percentage} %</td>
								<td><button class="btn btn-danger" type="submit"
										name="activate" value="stop" id="activate${count.index}">Stop Application</td>
							</c:if>
						</tr>
					</form>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
<script>
	function validateForm(id) {
		var activateValue=document.getElementById("activate"+id).value;
		var percentageValue = document.getElementById(id).value;
		if(activateValue!=="stop"){
			if (isNaN(percentageValue) || percentageValue<=0 || percentageValue>100) {
				alert("Percentage must be an integer between 0-100");
				return false;	
			}
			else{
				return true;
			}
		}else {
			return true;
		}
	}
</script>
</html>