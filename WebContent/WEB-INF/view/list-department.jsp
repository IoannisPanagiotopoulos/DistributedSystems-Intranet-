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