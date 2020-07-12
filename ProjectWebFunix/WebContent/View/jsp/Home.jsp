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
		<c:forEach items="${listProducts}" var="listProducts">
			<table>

				<td>
				<tr>
					<div class="col-sm-4">
						<div class="item">
							 <img src="${listProducts.getSrc()}">
							<p>
								<a href="InforProductController?id=${listProducts.getId() }">${listProducts.getName()}</a>
							</p>
							<p style="color: red;">${listProducts.getPrice()} $</p>
						</div>
					</div>
				</tr>

				</td>

			</table>
		</c:forEach>

	</div>
	<table class="paging-table">
		<tr>
			<td class="lbl_paging"><c:url value="ListController"
					var="paggingUrl">
					<c:param name="mode" value="paging" />
					<c:param name="currentPage" value="${PageBack}" />
					<c:param name="brandName" value="${brandName}" />
					<c:param name="nameProduct" value="${nameProduct}" />
				</c:url><a href="${paggingUrl}">${SymbolBack}</a></td>
			<c:forEach var="listPaging" items="${listPaging}">
				<c:choose>

					<c:when test="${listPaging != currentPage}">
						<td class="lbl_paging"><c:url value="ListController"
								var="paggingUrl">
								<c:param name="currentPage" value="${listPaging}" />
								<c:param name="mode" value="paging" />
								<c:param name="brandName" value="${brandName}" />
								<c:param name="nameProduct" value="${nameProduct}" />
							</c:url><a href="${paggingUrl}">${listPaging}</a></td>
					</c:when>
					<c:otherwise>
						<td class="lbl_paging">${listPaging}</td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<td class="lbl_paging"><c:url value="ListController"
					var="paggingUrl">
					<c:param name="mode" value="paging" />
					<c:param name="currentPage" value="${PageNext}" />
					<c:param name="brandName" value="${brandName}" />
					<c:param name="nameProduct" value="${nameProduct}" />
				</c:url><a href="${paggingUrl}">${SymbolNext}</a></td>
		</tr>
	</table>
	<%@ include file="Footer.jsp"%>
</body>
</html>