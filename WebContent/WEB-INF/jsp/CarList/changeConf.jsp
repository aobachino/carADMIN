<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>車情報変更確認</title>
</head>
<body>
		<form action="changeDo.html" method="post">
		<table border="1">
			<tr>
				<th>カテゴリ</th>
				<td>
					<c:choose>
						<c:when test="${infoConf.categNum == 1}">普通</c:when>
						<c:when test="${infoConf.categNum == 2}">軽自動車</c:when>
						<c:when test="${infoConf.categNum == 3}">大型車</c:when>
					</c:choose>				
					<input type="hidden" name="categNum" value="${infoConf.categNum}">
				</td>
			</tr>
			<tr>
				<th>車名</th>
				<td><c:out value="${infoConf.carName}"/><input type="hidden" name="carName" value="${infoConf.carName}"></td>
			</tr>
			<tr>
				<th>メーカー</th>
				<td><c:out value="${infoConf.maker}"/><input type="hidden" name="maker" value="${infoConf.maker}"></td>
			</tr>
			<tr>
				<th>排気量</th>
				<td><c:out value="${infoConf.gas}"/><input type="hidden" name="gas" value="${infoConf.gas}"></td>
			</tr>
			<tr>
				<th>レンタル金額</th>
				<td><c:out value="${infoConf.price}"/><input type="hidden" name="price" value="${infoConf.price}"></td>
			</tr>
			<tr>
				<th>メモ</th>
				<td><c:out value="${infoConf.memo}"/><input type="hidden" name="memo" value="${infoConf.memo}"></td>
			</tr>
		</table><br>
			<input type="hidden" name="carNum" value="${infoConf.carNum}">
		<input type="submit" value="実行">　<input type="button" value="戻る" onClick="history.back()">
	</form>
</body>
</html>