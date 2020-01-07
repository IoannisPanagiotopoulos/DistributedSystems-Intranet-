<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" >
<link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet" >
</head>
<body>
<img src="/home/ioannispanagiotopoulos/Desktop/logo.png">
	<div class="wrapper fadeInDown">
		<div id="formContent">
			<!-- Tabs Titles -->

			<!-- Icon -->
			<div class="fadeIn first"><h2>Harokopio University of Athens</h2></div>

			<!-- Login Form -->
			<form action="loginRegister" method="post">
				<table>
					<tr>
						<td><h3 style="color: red;">${message}</h3></td>
						<td></td>
					</tr>
				</table>

				<input type="text" id="login" name="username" class="fadeIn second"
					placeholder="username" autocomplete="off"> 
				<input type="password" id="password" name="password" class="fadeIn third password"
					placeholder="password" autocomplete="off"> 
				<input class="button" type="submit"
					name="submit" value="login" class="fadeIn fourth">
				<%--
				<td><a href="register.jsp">Registration</a></td>
				--%>
			</form>

		</div>
	</div>
</body>
</html>