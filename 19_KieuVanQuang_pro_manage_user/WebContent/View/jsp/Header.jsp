<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="View/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="View/js/jquerry.min.js"></script>
<script type="text/javascript" src="View/js/javascript.js"></script>
<title>Header</title>
</head>
<body>
	<div>
		<div>
			<table>
				<tr>
					<td width="80%"><img src="View/images/logo-manager-user.gif"
						alt="Luvina" />
					<td>
					<td align="left"><a
						href="<%=request.getContextPath()%>/Logout.do">ログアウト</a> &nbsp; <a
						href="<%=request.getContextPath()%>/ListUser.do?mode=default">トップ</a>
					<td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>