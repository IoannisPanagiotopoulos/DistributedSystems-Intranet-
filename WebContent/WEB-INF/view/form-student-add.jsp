	<div class="container">
		<h2>Add Student</h2>
		<br> <br>
	<span class="error">${error}</span>

		<div>
			<table class="table">
				<tr>
					<th>Username</th>
					<th>Password</th>
					<th>Name</th>
					<th>Email</th>
					<th>Department</th>
				</tr>
					<form action="${pageContext.request.contextPath}/student/addPost" method="post">
						<tr>
							<td><input type="text" name="username" autocomplete="off"
								value="" required></td>
							<td><input type="password" name="password" autocomplete="off"
								value="" required></td>
							<td><input type="text" name="name" autocomplete="off"
								value="" required></td>
							<td><input type="email" name="email" autocomplete="off"
								value="" required></td>
							<td><select name="department" required>
									<option value="Informatics" selected>Informatics</option>
									<option value="Geography">Geography</option>
									<option value="Dietics">Dietics</option>
									<option value="Economics">Economics</option>
							</select></td>	
							<td><button class="btn btn-outline-success" type="submit"
									name="action" value="submit">Submit</button></td>
						</tr>
					</form>
			</table>
		</div>
	</div>
	
	<footer style="position: fixed; left: 0; bottom: 25px; width: 100%; text-align: center;">
		<hr />
		Harokopio @ 2020
	</footer>	

</body>
</html>