<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/View/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/View/js/javascript.js"></script>
<title>ユーザ管理</title>
</head>

<body>
	<!-- Begin vung header -->
	<%@ include file="Header.jsp"%>
	<!-- End vung header -->

	<!-- Begin vung input-->

	<c:choose>
		<c:when test="${type == 'edit'}">
			<c:set var="destination" scope="request"
				value="EditUserValidate.do?mode=Edit_03&type=edit&id=${userInf.getUserId()}" />
		</c:when>
		<c:otherwise>
			<c:set var="destination" scope="request"
				value="AddUserValidate.do?mode=submit&type=add" />
		</c:otherwise>
	</c:choose>
	<form action="${destination}" method="post" name="inputform" />
	<table class="tbl_input" border="0" width="75%" cellpadding="0"
		cellspacing="0">
		<tr>
			<th align="left">
				<div style="padding-left: 100px;">会員情報編集</div>
			</th>
		</tr>
		<tr>
			<td class="errMsg">
				<div style="padding-left: 120px">
					<c:forEach items="${listError}" var="listError">${listError}</br>
					</c:forEach>
				</div>
			</td>
		</tr>
		<tr>
			<td align="left">
				<div style="padding-left: 100px;">
					<table border="0" width="100%" class="tbl_input" cellpadding="4"
						cellspacing="0">
						<table>
							<tr>
								<td class="lbl_left"><font color="red">*</font> アカウント名:</td>
								<c:choose>
									<c:when test="${type == 'edit'}">
										<td align="left"><input class="txBox" type="text"
											readonly="readonly" name="loginName"
											value="${fn:escapeXml(userInf.getLoginName() )}" size="15"
											onfocus="this.style.borderColor='#0066ff';"
											onblur="this.style.borderColor='#aaaaaa';" /></td>
									</c:when>
									<c:otherwise>
										<td align="left"><input class="txBox" type="text"
											name="loginName"
											value="${fn:escapeXml(userInf.getLoginName() )}" size="15"
											onfocus="this.style.borderColor='#0066ff';"
											onblur="this.style.borderColor='#aaaaaa';" />
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> グループ:</td>
								<td align="left"><select name="group_id">
										<option value="0">選択してください</option>
										<c:forEach var="listMstGroup" items="${listMstGroup}">
											<option value="${listMstGroup.getGroupId()}"
												${listMstGroup.getGroupId() == userInf.getGroupId() ? 'selected="selected"' : ''}>${listMstGroup.getGroupName()}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="name" value="${fn:escapeXml(userInf.getFullName() )}"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="nameKana"
									value="${fn:escapeXml(userInf.getFullNameKana() )}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 生年月日:</td>
								<td align="left"><select name="birthYear">

										<c:forEach var="listYear" items="${year}">
											<option value="${listYear}"
												${listYear == userInf.getListBirthday()[0] ? 'selected=""' : ''}>${listYear}</option>
										</c:forEach>

								</select>年 <select name="birthMonth">
										<c:forEach var="listMonth" items="${listMonth}">
											<option value="${listMonth}"
												${listMonth == userInf.getListBirthday()[1] ? 'selected=""' : ''}>${listMonth}</option>
										</c:forEach>
								</select>月 <select name="birthDay">
										<c:forEach var="listDay" items="${listDay}">
											<option value="${listDay}"
												${listDay == userInf.getListBirthday()[2] ? 'selected=""' : ''}>${listDay}</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> メールアドレス:</td>
								<td align="left"><input class="txBox" type="text"
									name="email" value="${fn:escapeXml(userInf.getEmail() )}"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font>電話番号:</td>
								<td align="left"><input class="txBox" type="text"
									name="tel" value="${fn:escapeXml(userInf.getTel() )}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>

								<c:choose>
									<c:when test="${type != 'edit'}">
										<td class="lbl_left"><font color="red">*</font> パスワード:</td>
										<td align="left"><input class="txBox" type="password"
											name="password" value="" size="30"
											onfocus="this.style.borderColor='#0066ff';"
											onblur="this.style.borderColor='#aaaaaa';" /></td>
									</c:when>
								</c:choose>

							</tr>
							<tr>
								<c:choose>
									<c:when test="${type != 'edit'}">
										<td class="lbl_left">パスワード（確認）:</td>
										<td align="left"><input class="txBox" type="password"
											name="confirmPasssword" value="" size="30"
											onfocus="this.style.borderColor='#0066ff';"
											onblur="this.style.borderColor='#aaaaaa';" /></td>
									</c:when>

								</c:choose>
							</tr>
							<tr>
								<th align="left" colspan="2"><a href="#" onclick="show()">日本語能力</a></th>
							</tr>
						</table>
						<table id="showLevelJapan" style="display: none">
							<tr>
								<td class="lbl_left">資格:</td>
								<td align="left"><select name="kyu_id">
										<option value="N0">選択してください</option>

										<c:forEach var="listMstJapan" items="${listMstJapan}">
											<option value="${listMstJapan.getCodeLevel()}"
												${listMstJapan.getCodeLevel() == userInf.getCodeLevel() ? 'selected="selected"' : ''}>${listMstJapan.getNameLevel()}</option>
										</c:forEach>

								</select></td>
							</tr>
							<tr>
								<td class="lbl_left">資格交付日:</td>
								<td align="left"><select name="yearBeginning">
										<c:forEach var="listYear" items="${year}">
											<option value="${listYear}"
												${listYear == userInf.getListStartDate()[0] ? 'selected="selected"' : ''}>${listYear}</option>
										</c:forEach>

								</select>年 <select name="monthBeginning">
										<c:forEach var="listMonth" items="${listMonth}">
											<option value="${listMonth}"
												${listMonth == userInf.getListStartDate()[1] ? 'selected="selected"' : ''}>${listMonth}</option>
										</c:forEach>
								</select>月 <select name="dayBeginning">
										<c:forEach var="listDay" items="${listDay}">
											<option value="${listDay}"
												${listDay == userInf.getListStartDate()[2] ? 'selected="selected"' : ''}>${listDay}</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left">失効日:</td>
								<td align="left"><select name="yearEnding">
										<c:forEach var="listYear" items="${listYear}">
											<option value="${listYear}"
												${listYear == userInf.getListEndDate()[0] ? 'selected="selected"' : ''}>${listYear}</option>
										</c:forEach>

								</select>年 <select name="monthEnding">
										<c:forEach var="listMonth" items="${listMonth}">
											<option value="${listMonth}"
												${listMonth == userInf.getListEndDate()[1] ? 'selected="selected"' : ''}>${listMonth}</option>
										</c:forEach>
								</select>月 <select name="dayEnding">
										<c:forEach var="listDay" items="${listDay}">
											<option value="${listDay}"
												${listDay == userInf.getListEndDate()[2] ? 'selected="selected"' : ''}>${listDay}</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left">点数:</td>
								<td align="left"><input class="txBox" type="text"
									name="total" value="${fn:escapeXml(userInf.getScore()) }"
									size="5" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
						</table>
					</table>
				</div>
			</td>
		</tr>
	</table>
	<div style="padding-left: 100px;">&nbsp;</div>
	<!-- Begin vung button -->
	<div style="padding-left: 45px;">
		<table border="0" cellpadding="4" cellspacing="0" width="300px">
			<tr>
				<th width="200px" align="center">&nbsp;</th>
				<td><input class="btn" type="submit" value="確認" /></td>
				<td><c:choose>
						<c:when test="${type == 'edit'}">
							<c:set var="dest" scope="request"
								value="ViewDetailUser.do?id=${userInf.getUserId()}" />
						</c:when>
						<c:otherwise>
							<c:set var="dest" scope="request" value="ListUser.do?mode=Back" />
						</c:otherwise>
					</c:choose> <input class="btn" type="button" value="戻る"
					onclick="window.location.href = '${dest}'" /></td>
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