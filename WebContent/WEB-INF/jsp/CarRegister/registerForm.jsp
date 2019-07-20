<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>車情報入力</title>
</head>
<body>
	<form action="registerConf.html" method="post">
		<table border="1">
			<tr>
				<th>カテゴリ</th>
				<td>
					<select name="categNum">
						<option value=""></option>
						<c:forEach var="obj" items="${category.categList}">
							<option value="${obj.categNum}"><c:out value="${obj.name}" /></option>
						</c:forEach>
					</select><form:errors cssClass="error" path="carRegisterModel.categNum" />
				</td>
			</tr>
			<tr>
				<th>車名</th>
				<td><input type="text" name="carName"><form:errors cssClass="error" path="carRegisterModel.carName" /></td>
			</tr>
			<tr>
				<th>メーカー</th>
				<td><input type="text" name="maker"><form:errors cssClass="error" path="carRegisterModel.maker" /></td>
			</tr>
			<tr>
				<th>排気量</th>
				<td><input type="text" name="gas"><form:errors cssClass="error" path="carRegisterModel.gas" /></td>
			</tr>
			<tr>
				<th>レンタル金額</th>
				<td><input type="text" name="price"><form:errors cssClass="error" path="carRegisterModel.price" /></td>
			</tr>
			<tr>
				<th>メモ</th>
				<td><textarea name="memo"></textarea><form:errors cssClass="error" path="carRegisterModel.memo" /></td>
			</tr>			
		</table><br>
		<input type="submit" value="確認">　 <input type="reset" value="クリア">　<input type="button" value="戻る" onClick="history.back()">
	</form>
</body>
</html>