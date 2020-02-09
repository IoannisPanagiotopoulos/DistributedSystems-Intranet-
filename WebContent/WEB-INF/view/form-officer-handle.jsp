	<div class="container">
		<h2>Update Officer</h2>
		<br> <br>

		<div>
			<table class="table">
				<tr>
					<th>Username</th>
					<th>Password</th>
					<th>Name</th>
					<th>Email</th>
					<th>Department</th>
					<th>Role</th>
					<th>Action</th>
				</tr>
					<form action="${pageContext.request.contextPath}/officer/handle" method="post">
						<tr>
							<td><input type="hidden" name="username" autocomplete="off"
								value="${officer.username}">${officer.username}</td>	
							<td><input type="password" name="password" autocomplete="off"
								value=""></td>
							<td><input type="text" name="name" autocomplete="off"
								value="${officer.userInformation.name}" required></td>
							<td><input type="email" name="email" autocomplete="off"
								value="${officer.userInformation.email}" required></td>
							<td><select name="department" required>
									<option value="Informatics" selected>Informatics</option>
									<option value="Geography">Geography</option>
									<option value="Dietics">Dietics</option>
									<option value="Economics">Economics</option>
							</select></td>
							<td><select name="role" required>
									<option value="Officer" selected>Officer</option>
									<option value="Supervisor">Supervisor</option>
							</select>
							<td><button class="btn btn-outline-success" type="submit"
									name="action" value="submit">Submit</button>
								<button class="btn btn-outline-danger" type="submit"
									name="action" value="delete">Delete</button></td>
						</tr>
						<span name="error">${errorun}</span>
						Change Password <input type="checkbox" name="checkbox">
					</form>
			</table>
		</div>
	</div>
	
	<footer style="position: fixed; left: 0; bottom: 25px; width: 100%; text-align: center;">
		<hr />
		Harokopio @ 2020
	</footer>	
	
</body>
<script>
	function alertName(){
    	alert("Form has been submitted");
    }
</script>
</html>

