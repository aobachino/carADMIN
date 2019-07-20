<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>車情報変更</title>
</head>
<body>
	<form action="changeConf.html" method="post">
		<table border="1">
			<tr>
				<th>カテゴリ</th>
				<td>
					<select name="categNum">
						<option value=""></option>
						<c:forEach var="obj" items="${category.categList}">
							<option value="${obj.categNum}"><c:out value="${obj.name}" /></option>
						</c:forEach>
					</select>				
				</td>
			</tr>
			<c:forEach var="obj" items="${carDetail.carList}">
			<tr>
				<th>車名</th>
				<td><input type="text" name="carName" value="${obj.carName}"></td>
			</tr>
			<tr>
				<th>メーカー</th>
				<td><input type="text" name="maker" value="${obj.maker}"></td>
			</tr>
			<tr>
				<th>排気量</th>
				<td><input type="text" name="gas" value="${obj.gas}"></td>
			</tr>
			<tr>
				<th>レンタル金額</th>
				<td><input type="text" name="price" value="${obj.price}"></td>
			</tr>
			<tr>
				<th>メモ</th>
				<td><textarea name="memo"><c:out value="${obj.memo}"/></textarea></td>
			</tr>
			<input type="hidden" name="carNum" value="${obj.carNum}">
			</c:forEach>
		</table><br>
		
		<input type="submit" value="確認">　 <input type="reset" value="元に戻る">　<input type="button" value="戻る" onClick="history.back()">
	</form>
</body>
</html>