<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Information Products</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<h3>${product.getName()}</h3>
	<hr>
	<table>

		<tr>
			<th rowspan="2"><img src="${product.getSrc()}"></th>
			<td valign="top"><b style="color: red; font-size: 40px;">${product.getPrice()} $</b><br>
				${product.getDescription()}<br> <input type="button"
				class="add-cart" value="Add to Cart"
				onclick="window.location.href='AddToCartController?id=${product.getId() }'">
			</td>


		</tr>

	</table>

	<%@ include file="Footer.jsp"%>
</body>
</html>