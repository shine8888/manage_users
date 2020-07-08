<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/View/css/style.css"
	rel="stylesheet" type="text/css" />
<title>Header</title>
</head>

<body>
	<%@ include file="Header.jsp"%>
	<div class="row">
		<c:forEach items="${listItems}" var="listItems">
			<table>
				<tr>
					<td>
						<div class="col-sm-4">
							<div class="item">
								<img src="${listItems.getSrc()}">
								<p>
									<a href="#">${listItems.getName()}</a>
								</p>
								<p style="color: red;">${listItems.getPrice()}</p>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</c:forEach>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>