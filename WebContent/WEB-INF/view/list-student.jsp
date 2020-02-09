	<div class="container">
	
		<h2>Students</h2>
		<br> <br>
		
		<div id = "error_placeholder"></div>
		<div id = "success_placeholder"></div>
	
		<input type="hidden" name="error" id="error" value="${error}" />
		<input type="hidden" name="success" id="success" value="${success}" />
		
		<div>
			<table class="table">
				<tr>
					<th>Username</th>
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
				<c:forEach items="${students}" var="student">
					<form class="table" method="post" action="${pageContext.request.contextPath}/student/id/${student.username}">
						<tr>
							<td>${student.username}</td>
							<td>${student.userInformation.name}</td>
							<td>${student.userInformation.email}</td>
							<td>${student.userInformation.departmentName}</td>
							<td>${student.userInformation.activated}
							<sec:authorize access="hasRole('ADMIN') or hasRole('OFFICER')">
							<td><button class="btn btn-outline-primary" type="submit"
									name="action" value="edit">Edit</button></td>
							</sec:authorize>
						</tr>
					</form>
				</c:forEach>
			</table>
		</div>
	</div>

	<footer style="position: fixed; left: 0; bottom: 25px; width: 100%; text-align: center;">
		<hr />
		Harokopio @ 2020
	</footer>
	
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