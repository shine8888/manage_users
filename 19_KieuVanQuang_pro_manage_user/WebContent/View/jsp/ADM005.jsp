<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/View/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/View/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/View/js/javascript.js"></script>
<title>ユーザ管理</title>
</head>
<body>
	<!-- Begin vung header -->
	<%@ include file="Header.jsp"%>
	<!-- End vung header -->

	<!-- Begin vung input-->
	<form method="post" name="inputform" action="">
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">情報確認</div>
					<div style="padding-left: 100px;">&nbsp;</div>
				</th>
			</tr>
			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="1" width="70%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<table border="1" width="70%" class="tbl_input" cellpadding="4"
								cellspacing="0">
								<tr>
									<td class="lbl_left">アカウント名:</td>
									<td align="left">${fn:escapeXml(user.getLoginName()) }</td>
								</tr>
								<tr>
									<td class="lbl_left">グループ:</td>
									<td align="left">${fn:escapeXml(user.getGroupName()) }</td>
								</tr>
								<tr>
									<td class="lbl_left">氏名:</td>
									<td align="left">${fn:escapeXml(user.getFullName()) }</td>
								</tr>
								<tr>
									<td class="lbl_left">カタカナ氏名:</td>
									<td align="left">${fn:escapeXml(user.getFullNameKana()) }</td>
								</tr>
								<tr>
									<td class="lbl_left">生年月日:</td>
									<td align="left">${fn:escapeXml(user.getBirthday()) }</td>
								</tr>
								<tr>
									<td class="lbl_left">メールアドレス:</td>
									<td align="left">${fn:escapeXml(user.getEmail()) }</td>
								</tr>
								<tr>
									<td class="lbl_left">電話番号:</td>
									<td align="left">${fn:escapeXml(user.getTel()) }</td>
								</tr>
								<tr>
									<th colspan="2"><a href="#" onclick="show()">日本語能力</a></th>
								</tr>
							</table>
							<table id="showLevelJapan" border="1" width="70%"
								class="tbl_input" cellpadding="4" cellspacing="0"
								style="display: none">
								<tr>
									<td class="lbl_left">資格:</td>
									<td align="left">${fn:escapeXml(user.getNameLevel()) }</td>
								</tr>
								<tr>
									<td class="lbl_left">資格交付日:</td>
									<td align="left">${fn:escapeXml(user.getStartDate()) }</td>
								</tr>
								<tr>
									<td class="lbl_left">失効日:</td>
									<td align="left">${fn:escapeXml(user.getEndDate()) }</td>
								</tr>
								<tr>
									<td class="lbl_left">点数:</td>
									<td align="left">${fn:escapeXml(user.getScore()) }</td>
								</tr>
							</table>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<div style="padding-left: 100px;">&nbsp;</div>
		<!-- Begin vung button -->
		<div style="padding-left: 100px;">
			<table border="0" cellpadding="4" cellspacing="0" width="300px">
				<tr>
					<th width="200px" align="center">&nbsp;</th>
					<td><input class="btn" value="編集" type="button"
						onclick="window.location.href = 'EditDetailUser.do?mode=Edit_05&id=${user.getUserId() }'" />
					</td>
					<fmt:bundle basename="message_msg_ja">
						<fmt:message key="MSG004" var="messDelete"></fmt:message>
					</fmt:bundle>
					<td><input class="btn" type="button" value="削除"
						onclick="showAlertDelete('${messDelete}', ${user.getUserId() })" /></td>
					<td><input class="btn" type="button" value="戻る"
						onclick="window.location.href = 'ListUser.do?mode=default'" /></td>
				</tr>
			</table>
			<!-- End vung button -->
	</form>
	<!-- End vung input -->

	<!-- Begin vung footer -->
	<%@ include file="Footer.jsp"%>
	<!-- End vung footer -->
</body>
</html>