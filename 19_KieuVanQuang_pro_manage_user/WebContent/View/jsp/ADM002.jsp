<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="View/css/style.css" rel="stylesheet" type="text/css" />
<title>ユーザ管理</title>
</head>
<body>
	<!-- Begin vung header -->
	<%@ include file="Header.jsp"%>
	<!-- End vung header -->

	<!-- Begin vung dieu kien tim kiem -->
	<form action="ListUser.do?mode=default" method="get" name="mainform">
		<table class="tbl_input" border="0" width="90%" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>会員名称で会員を検索します。検索条件無しの場合は全て表示されます。</td>
			</tr>
			<tr>
				<td width="100%">
					<table class="tbl_input" cellpadding="4" cellspacing="0">
						<tr>
							<td class="lbl_left">氏名:</td>
							<td align="left"><input class="txBox" type="text"
								name="searchingName" value="${searchingName}" size="20"
								onfocus="this.style.borderColor='#0066ff';"
								onblur="this.style.borderColor='#aaaaaa';" /></td>
							<td></td>
						</tr>
						<tr>
							<td class="lbl_left">グループ:</td>
							<td align="left" width="80px"><select name="groupId">
									<option value="0">全て</option>
									<c:forEach var="listMstGroup" items="${listMstGroup}">
										<option value="${listMstGroup.getGroupId()}"
											${listMstGroup.getGroupId() == groupId ? 'selected="selected"' : ''}>${listMstGroup.getGroupName()}</option>
									</c:forEach>
							</select></td>
							<input type="hidden" name="mode" value="Search" />
							<td align="left"><input class="btn" type="submit" value="検索" />
								<input class="btn" type="button" value="新規追加"
								onclick="
								window.location.href='AddUser.do?mode=default'" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- End vung dieu kien tim kiem -->
	</form>
	<c:if test="${empty listUserInfor}">
		<td class="lbl_left">${mess}</td>
	</c:if>
	<!-- Begin vung hien thi danh sach user -->
	<c:if test="${not empty listUserInfor}">
		<table class="tbl_list" border="1" cellpadding="4" cellspacing="0"
			width="80%">

			<tr class="tr2">
				<th align="center" width="20px">ID</th>
				<c:choose>
					<c:when test="${sortName != 'DESC'}">
						<th align="left">氏名 <c:url value="ListUser.do" var="sortUrl">
								<c:param name="mode" value="Sort" />
								<c:param name="sortType" value="fullName" />
								<c:param name="sortName" value="DESC" />
								<c:param name="searchingName" value="${searchingName}" />
								<c:param name="groupId" value="${groupId}" />
								<c:param name="currentPage" value="${currentPage}" />
							</c:url> <a href="${ sortUrl}">▲▽</a>
						</th>
					</c:when>
					<c:otherwise>
						<th align="left">氏名 <c:url value="ListUser.do" var="sortUrl">
								<c:param name="mode" value="Sort" />
								<c:param name="sortType" value="fullName" />
								<c:param name="sortName" value="ASC" />
								<c:param name="searchingName" value="${searchingName}" />
								<c:param name="groupId" value="${groupId}" />
								<c:param name="currentPage" value="${currentPage}" />
							</c:url> <a href="${sortUrl }">△▼</a>
						</th>
					</c:otherwise>
				</c:choose>
				<th align="left">生年月日</th>
				<th align="left">グループ</th>
				<th align="left">メールアドレス</th>
				<th align="left" width="70px">電話番号</th>
				<c:choose>
					<c:when test="${sortLevel != 'DESC'}">
						<th align="left">日本語能力 <c:url value="ListUser.do" var="sortUrl">
								<c:param name="mode" value="Sort" />
								<c:param name="sortType" value="nameLevel"/>
								<c:param name="sortLevel" value="DESC" />
								<c:param name="searchingName" value="${searchingName}" />
								<c:param name="groupId" value="${groupId}" />
								<c:param name="currentPage" value="${currentPage}" />
							</c:url> <a href="${ sortUrl}">▲▽</a>

						</th>
					</c:when>
					<c:otherwise>
						<th align="left">日本語能力 <c:url value="ListUser.do"
								var="sortUrl">
								<c:param name="mode" value="Sort" />
								<c:param name="sortType" value="nameLevel"/>
								<c:param name="sortLevel" value="ASC" />
								<c:param name="searchingName" value="${searchingName}" />
								<c:param name="groupId" value="${groupId}" />
								<c:param name="currentPage" value="${currentPage}" />
							</c:url> <a href="${ sortUrl}">△▼</a>
						</th>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${sortEndDate != 'ASC'}">
						<th align="left">失効日 <c:url value="ListUser.do" var="sortUrl">
								<c:param name="mode" value="Sort" />
								<c:param name="sortType" value="endDate" />
								<c:param name="sortEndDate" value="ASC" />
								<c:param name="searchingName" value="${searchingName}" />
								<c:param name="groupId" value="${groupId}" />
								<c:param name="currentPage" value="${currentPage}" />
							</c:url> <a href="${ sortUrl}">△▼</a>

						</th>
					</c:when>
					<c:otherwise>
						<th align="left">失効日 <c:url value="ListUser.do" var="sortUrl">
								<c:param name="mode" value="Sort" />
								<c:param name="sortType" value="endDate" />
								<c:param name="sortEndDate" value="DESC" />
								<c:param name="searchingName" value="${searchingName}" />
								<c:param name="groupId" value="${groupId}" />
								<c:param name="currentPage" value="${currentPage}" />
							</c:url> <a href="${sortUrl}">▲▽</a>

						</th>
					</c:otherwise>
				</c:choose>
				<th align="left">点数</th>
			</tr>
			<c:forEach items="${listUserInfor}" var="listUserInfor">
				<tr>
					<td align="right"><a
						href="ViewDetailUser.do?id=${listUserInfor.getUserId()}">${listUserInfor.getUserId()}</a></td>
					<td>${fn:escapeXml(listUserInfor.getFullName())}</td>
					<td align="center">${fn:escapeXml(fn:replace(listUserInfor.getBirthday(),"-","/"))}</td>
					<td>${fn:escapeXml(listUserInfor.getGroupName())}</td>
					<td>${fn:escapeXml(listUserInfor.getEmail())}</td>
					<td>${fn:escapeXml(listUserInfor.getTel())}</td>
					<td>${fn:escapeXml(listUserInfor.getNameLevel())}</td>
					<td align="center">${fn:escapeXml(fn:replace(listUserInfor.getEndDate(),"-","/"))}</td>
					<c:choose>
						<c:when test="${listUserInfor.getTotal() == 0}">
							<td align="right"></td>
						</c:when>
						<c:otherwise>
							<td align="right">${listUserInfor.getTotal()}</td>
						</c:otherwise>
					</c:choose>

				</tr>
			</c:forEach>

		</table>
	</c:if>
	<!-- End vung hien thi danh sach user -->

	<!-- Begin vung paging -->
	<table>
		<tr>
			<td class="lbl_paging"><c:url value="ListUser.do"
					var="paggingUrl">
					<c:param name="mode" value="Paging" />
					<c:param name="currentPage" value="${currentPageBack}" />
					<c:param name="sortType" value="${sortType}" />
					<c:param name="sortName" value="${sortName}" />
					<c:param name="sortLevel" value="${sortLevel}" />
					<c:param name="sortEndDate" value="${sortEndDate}" />
					<c:param name="searchingName" value="${searchingName}" />
					<c:param name="groupId" value="${groupId}" />
				</c:url> <a href="${paggingUrl}">${paggingBack}</a></td>
			<c:forEach var="listPaging" items="${listPaging}">
				<c:choose>

					<c:when test="${listPaging != currentPage}">
						<td class="lbl_paging"><c:url value="ListUser.do"
								var="paggingUrl">
								<c:param name="mode" value="Paging" />
								<c:param name="searchingName" value="${searchingName}" />
								<c:param name="groupId" value="${groupId}" />
								<c:param name="currentPage" value="${listPaging}" />
								<c:param name="sortType" value="${sortType}" />
								<c:param name="sortName" value="${sortName}" />
								<c:param name="sortLevel" value="${sortLevel}" />
								<c:param name="sortEndDate" value="${sortEndDate}" />
							</c:url> <a href="${paggingUrl}">${listPaging}</a></td>
					</c:when>
					<c:otherwise>
						<td class="lbl_paging">${listPaging}</td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<td class="lbl_paging"><c:url value="ListUser.do"
					var="paggingUrl">
					<c:param name="mode" value="Paging" />
					<c:param name="currentPage" value="${currentPageNext}" />
					<c:param name="sortType" value="${sortType}" />
					<c:param name="sortName" value="${sortName}" />
					<c:param name="sortLevel" value="${sortLevel}" />
					<c:param name="sortEndDate" value="${sortEndDate}" />
					<c:param name="searchingName" value="${searchingName}" />
					<c:param name="groupId" value="${groupId}" />
				</c:url> <a href="${paggingUrl}">${paggingNext}</a></td>
		</tr>
	</table>
	<!-- End vung paging -->

	<!-- Begin vung footer -->
	<%@ include file="Footer.jsp"%>
	<!-- End vung footer -->

</body>

</html>