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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/View/js/main.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Your Cart</title>
</head>
<body>
	<%@ include file="Header.jsp"%>


	<c:choose>
		<c:when test="${not empty sessionCart.getItems()}">
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

						<td id="price" class="price">${sessionCart.getItems().get(i).getPrice() }</td>
						<td class="quantity"><input id="quantity${sessionCart.getItems().get(i).getId()}" type="number"
							min="1" max="20"
							value="${sessionCart.getItems().get(i).getNumber() }"
							oninput="Calculation('${sessionCart.getItems().get(i).getId()}')" /></td>
						<td id="total" class="total"><fmt:formatNumber
								value="${sessionCart.getItems().get(i).getPrice()*sessionCart.getItems().get(i).getNumber() }"
								type="number" groupingUsed="false" maxFractionDigits="2"/></td>
						<td><input type="button" value="Delete Item"
							onclick="Delete(this, '${sessionCart.getItems().get(i).getId()}')"></td>
					</tr>



				</c:forEach>
				<tr>
				<td colspan="5">Total Amount: ${sessionCart.getAmount()}</td>
				</tr>
			</table>
			<table>
			<tr>
			<th>Total Price</th>
			<th><input type="button" value="Place Order" action""></th>
			</tr>
			</table>
		</c:when>
		<c:otherwise>
			<h2
				style="height: 400px; width: 600px; text-algin: center; font-size: 14px">Your
				cart is empty. Go to buy something</h2>
		</c:otherwise>
	</c:choose>
	<%@ include file="Footer.jsp"%>
</body>
</html>