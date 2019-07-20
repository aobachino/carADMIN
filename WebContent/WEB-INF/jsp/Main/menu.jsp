<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者メニュー</title>
</head>
<body>
	<form action="serchForm.html">
		<input type="submit" value="車貸出/返却">
	</form><br>
	
	<form action="carMenu.html">
	<c:if test="${staffInfo.clerck == 'm'}">
		<input  type="submit" value="車管理">
	</c:if>	
	</form>
	
	<br>
	<form action="">
		<input type="submit" value="ログアウト">
	</form>
</body>
</html>