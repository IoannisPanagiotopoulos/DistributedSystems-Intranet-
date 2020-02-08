 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
		
		<div id = "error_placeholder"></div>
		<div id = "success_placeholder"></div>
		
		<input type="hidden" name="error" id="error" value="${error}" />
		<input type="hidden" name="success" id="success" value="${success}" />
		<div>
			<table class="table">
				<tr>
					<th>Department</th>
					<th>Department Status</th>
					<th>Activated Applications</th>
					<th>Completed Ranking</th>
					<th>Acceptance Percentage</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${departments}" var="dpt" varStatus="count">
					<form class="table" name="form" method="post" action="${pageContext.request.contextPath}/department/activate"
						onsubmit="return validateForm(${count.index})">
						<tr>
							<td><input type="hidden" name="department"
								autocomplete="off" value="${String.valueOf(dpt.departmentName)}">${String.valueOf(dpt.departmentName)}</td>
							<td align="center"><input type="hidden" name="active" autocomplete="off"
								value="${dpt.active}">${dpt.active}</td>
							<td align="center"><input type="hidden" name="totalApplications" autocomplete="off"
								value="${totalApplications[count.index]}">${totalApplications[count.index]}</td>
							<td align="center"><input type="hidden" name="hasBegun" autocomplete="off"
								value="${dpt.hasBegun}">
								<c:choose>
									<c:when test="${dpt.hasBegun==1}">
										Yes
									</c:when>
									<c:otherwise>
										No
									</c:otherwise>
								</c:choose>
									
							</td>
							<c:if test="${String.valueOf(dpt.active)=='inactive'}">
							
								<c:if test="${dpt.hasBegun==1}">
									<td align="center"><input type="hidden" name="percentage" autocomplete="off"
									value="${dpt.percentage}" id="${count.index}">${dpt.percentage} %</td>
									<td><button class="btn btn-secondary" disabled>Start Application</button></td>
								</c:if>
								<c:if test="${dpt.hasBegun==0}">
									<td align="center"><input type="hidden" style="text-align:center;" name="percentage" autocomplete="off" size="4"
										value="${dpt.percentage}" id="${count.index}"></td>
									<td><button class="btn btn-success" type="submit"
										name="activate" value="start" id="activate${count.index}">Start Application</button></td>
								</c:if>
							</c:if>
							<c:if test="${String.valueOf(dpt.active)=='active'}">
								<td align="center"><input type=text style="text-align:center;" name="percentage" autocomplete="off" size="4"
									value="${dpt.percentage}" id="${count.index}">%</td>
								<td><button class="btn btn-danger" type="submit"
										name="activate" value="stop" id="activate${count.index}">Stop Application</button></td>
							</c:if>
						</tr>
					</form>
				</c:forEach>
			</table>
		</div>
		
		<h2>New Year</h2>
		<br> <br>
		<p><strong>Reset If you want to start a new Year.
			<br>
			Warning!</strong> Doing this will delete all Students and Applications and will reset the Departments
		</p>
		<form name="form" method="post" action="${pageContext.request.contextPath}/department/reset">
			<button class="btn btn-danger" type="submit"
										name="reset" value="stop" id="activate${count.index}">Reset Application</button>
		</form>
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

	function validateForm(id) {
		var activateValue=document.getElementById("activate"+id).value;
		var percentageValue = document.getElementById(id).value;
		if(activateValue!=="start"){
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