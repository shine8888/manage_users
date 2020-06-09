<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/View/css/style.css"
	rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
	<div class="header">
		<%@ include file="SearchingBar.jsp"%>
	</div>
	<div style="height:48px;">
		<div class="topnav-left">
			<a href="#">Home</a> <a href="#">Products</a> <a href="#">About
				us</a>
		</div>
		<div class="topnav-right">
			<a href="#">Login</a>
		</div>
	</div>
</body>
</html>