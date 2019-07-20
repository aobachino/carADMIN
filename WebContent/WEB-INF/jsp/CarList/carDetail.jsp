<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>車情報詳細</title>
</head>
<body>
<form action="" method="post">
	<table border="1" >
	<c:forEach var="obj" items="${carDetail.carList}">
		<tr>
			<th>車登録コード</th>
			<td><c:out value="${obj.carNum}"/></td>
		</tr>
		<tr>
			<th>カテゴリ</th>
			<td>
				<c:choose>
					<c:when test="${obj.categNum == 1}">普通</c:when>
					<c:when test="${obj.categNum == 2}">軽自動車</c:when>
					<c:when test="${obj.categNum == 3}">大型車</c:when>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th>車名</th>
			<td><c:out value="${obj.carName}"/></td>
		</tr>
		<tr>
			<th>メーカー</th>
			<td><c:out value="${obj.maker}"/></td>
		</tr>			
		<tr>
			<th>排気量</th>
			<td><c:out value="${obj.gas}"/></td>
		</tr>
		<tr>
			<th>登録日</th>
			<td><c:out value="${obj.regDay}"/></td>
		</tr>
		<tr>
			<th>レンタル金額(1日)</th>
			<td><c:out value="${obj.price}"/></td>
		</tr>
		<tr>
			<th>メモ</th>
			<td><c:out value="${obj.memo}"/></td>
		</tr>
		<tr>
			<th>最終更新者</th>
			<td><c:out value="${obj.updStaff}"/></td>
		</tr>
		<tr>
			<th>最終更新日</th>
			<td><c:out value="${obj.lstDay}"/></td>
		</tr>
	</c:forEach>		
	</table><br>
	<input type="submit" value="変更" formaction="carInfoChange.html" formmethod="post">　<input type="submit" value="削除" formaction="deleteConf.html" formmethod="post">　
	<input type="submit" value="車一覧へ" formaction="carSerch.html" formmethod="post">
</form>
</body>
</html>