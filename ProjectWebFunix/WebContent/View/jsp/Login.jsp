<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link rel="stylesheet" type="text/css" href="View/css/style.css">
</head>

<body>
	<div class="loginForm">
		<div class="inform">
			<form action="Login.do" method="post">
				<label style="color: red"><c:forEach var="error" items="${listError}">${error}<br></c:forEach></label>
				<label for=""><b>Username</b></label> <br> <input type="text"
					class="login" name="username"> <br> <label for=""><b>Password</b></label><br>
				<input type="password" class="login" name="password" style="onfocus"> <br>
				<input type="submit" value="Login" class="btnLogin"><br>
			</form>
			<div class="addForm">
				<input type="checkbox" name="checkbox"
					style="display: inline-block; margin: 5px 0 0 -90px;"> <label
					for="checkbox" class="checkbox">Remember me</label> <br>
				<button>Cancel</button>
				<p>
					Forgot <a href="">password</a>
				</p>
			</div>
		</div>
	</div>
</body>

</html>