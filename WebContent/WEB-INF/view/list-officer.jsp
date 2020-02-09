	<div class="container">

		<h2>Officers</h2>
		<br> <br>
		
		<div id="error_placeholder"></div>
		<div id="success_placeholder"></div>
		
		<input type="hidden" name="error" id="error" value="${error}" />
		<input type="hidden" name="success" id="success" value="${success}" />

		<div>
			<table class="table">
				<tr>
					<th>Username</th>
					<th>Name</th>
					<th>Email</th>
					<th>Department</th>
					<th>Role</th>
					<form method="post" action="${pageContext.request.contextPath}/officer/add">
						<th>
							<button class="btn btn-success" type="submit" name="action"
								value="add">+</button>
						</th>
					</form>
				</tr>
				<c:forEach items="${officers}" var="officer">
					<form class="table" method="post" action="${pageContext.request.contextPath}/officer/id/${officer.username}">
						<tr>
							<td>${officer.username}</td>
							<td>${officer.userInformation.name}</td>
							<td>${officer.userInformation.email}</td>
							<td>${officer.userInformation.departmentName}</td>
							<td>${String.valueOf(officer.authority.authorityRole)}</td>
							<td><button class="btn btn-outline-primary" type="submit"
									name="action" value="edit">Edit</button></td>
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