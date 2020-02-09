	<div class="container">
		<h2>Update Student</h2>
		<br> <br>

		<div>
			<table class="table">
				<tr>
					<th>Username</th>
					<sec:authorize access="hasRole('ADMIN')">
						<th>Password</th>
					</sec:authorize>
					<th>Name</th>
					<th>Email</th>
					<th>Department</th>
					<th>Activate</th>
					<th>Action</th>
				</tr>
					<form action="${pageContext.request.contextPath}/student/handle" method="post">
					<span name="errorrun">${errorun}</span>
						<tr>
						<sec:authorize access="hasRole('ADMIN')">
							<td><input type="hidden" name="username" autocomplete="off"
								value="${student.username}">${student.username}</td>
							<td><input type="password" name="password" autocomplete="off"
								value=""></td>
							<td><input type="text" name="name" autocomplete="off"
								value="${student.userInformation.name}"  required></td>
							<td><input type="text" name="email" autocomplete="off"
								value="${student.userInformation.email}"  required></td>
							<td><select name="department"  required>
									<option value="Informatics" selected>Informatics</option>
									<option value="Geography">Geography</option>
									<option value="Economics">Economics</option>
									<option value="Dietics">Dietics</option>
							</select></td>
							<td><select name="activated"  required>
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
							<td><input type="hidden" name="name" autocomplete="off"
								value="${student.userInformation.name}">${student.userInformation.name}</td>
							<td><input type="hidden" name="email" autocomplete="off"
								value="${student.userInformation.email}">${student.userInformation.email}</td>
							<td><input type="hidden" name="department" autocomplete="off"
								value="${student.userInformation.departmentName}">${student.userInformation.departmentName}</td>
							<c:if test="${student.userInformation.activated == 'inactive'}">
							<td><select name="activated">
									<option value="active" selected>Activated</option>
									<option value="inactive">Not Activated</option>
							</select></td>
							</c:if>
							<c:if test="${student.userInformation.activated == 'active'}">
							<td><input type="hidden" name="active" autocomplete="off"
								value="${student.userInformation.activated}">${student.userInformation.activated}</td>
							</c:if>
							<td>
								<c:if test="${student.userInformation.activated == 'inactive'}">
								<button class="btn btn-outline-success" type="submit"
									name="action" value="submit">Submit</button>
								</c:if>
							</td>
						</sec:authorize>
						</tr>
						<sec:authorize access="hasRole('ADMIN')">
							Change Password <input type="checkbox" name="checkbox">
						</sec:authorize>
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