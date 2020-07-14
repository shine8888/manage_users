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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/View/js/main.js"></script>
<title>Your Cart</title>
</head>
<body>
	<%@ include file="Header.jsp"%>

	<table id="table-infor">
		<tr>
			<th>Products in cart</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Total Amount</th>
			<th>Delete</th>
		</tr>

		<jsp:useBean id="sessionCart" class="manager.model.Cart"
			scope="session" />
		<%
			int sum = sessionCart.getItems().size();
		%>
		<c:forEach var="i" begin="0" end="<%=sum - 1%>">
			<tr>
				<td>${sessionCart.getItems().get(i).getName() }</td>

				<td id="price">${sessionCart.getItems().get(i).getPrice() }</td>
				<td><input id="inputChangeClass" class="is-valid" type="number"
					min="1" max="20"
					value="${sessionCart.getItems().get(i).getNumber() }"
					oninput="Calculation()" /></td>
				<td id="total-amount">${sessionCart.getItems().get(i).getNumber()*sessionCart.getItems().get(i).getPrice()}</td>
				<td><input type="button" value="Delete Item"
					onclick="deleteRow(this)"></td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="Footer.jsp"%>
</body>
</html>