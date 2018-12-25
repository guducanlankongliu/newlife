<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/addAuditingType" method="post">
		name：<input type="text" name="violationname"/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>