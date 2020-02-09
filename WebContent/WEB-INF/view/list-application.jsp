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