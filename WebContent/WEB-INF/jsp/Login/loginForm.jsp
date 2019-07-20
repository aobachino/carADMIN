<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者ログイン</title>
</head>
<body>
		
	<form action="loginCheck.html" method="POST">
	<table border="1">
		<tr>
			<th class="form">会員NO</th>
			<td><input type="text" name= "staffId"><form:errors cssClass="error" path="loginModel.staffId" /></td>
		</tr>
		<tr >
			<th class="form">パスワード</th>
			<td><input type="password" name="pass"><form:errors cssClass="error" path="loginModel.pass" /></td>
		</tr>
	</table><br>
		<input type="submit" value="ログイン">　<input type="reset" value="クリア"> <br>
	</form>
</body>
</html>