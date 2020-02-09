	<div class="container">
		<sec:authorize access="hasRole('ADMIN')">
			<div class="alert alert-success mt-5">
				<h3 class="alert-heading">This is the Admin Home Page!</h3>
				<hr>
				<ul>
					<li>Navigate to Show Officers, to create/read/update/delete Officers</li>
					<li>Navigate to Show Students, to create/read/update/delete Students</li>
				</ul>
			</div>
		</sec:authorize>
		<sec:authorize access="hasRole('SUPERVISOR')">
			<div class="alert alert-success mt-5">
				<h3 class="alert-heading">This is the Supervisor Home Page!</h3>
				<hr>
				<ul>
					<li>Navigate to Show Students, to read Students</li>
					<li>Navigate to Show Departments link, to read/activate Departments For Application</li>
				</ul>
			</div>
		</sec:authorize>
		<sec:authorize access="hasRole('OFFICER')">
			<div class="alert alert-success mt-5">
				<h3 class="alert-heading">This is the Officer Home Page!</h3>
				<hr>
				<ul>
					<li>Navigate to Show Students, to read/activate Students</li>
					<li>Navigate to Show Applications, to activate/reject Applications</li>
				</ul>
			</div>
		</sec:authorize>
	</div>
	
	<footer style="position: fixed; left: 0; bottom: 25px; width: 100%; text-align: center;">
		<hr />
		Harokopio @ 2020
	</footer>	
	
</body>
</html>