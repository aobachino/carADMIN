<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>車一覧</title>
</head>
<body>
	<span ><c:out value="${gdZero}" /></span>
	<span ><c:out value="${priceError}" /></span>
	<form action="serchDo.html" method="post">
		<table border="1">
			<tr>
				<th>車登録コード</th>
				<td><input type="text" name="carNum"></td>
			</tr>
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
			<tr>
				<th>車名</th>
				<td><input type="text" name="carName"></td>
			</tr>
			<tr>
				<th>メーカー</th>
				<td><input type="text" name="maker"></td>
			</tr>
			<tr>
				<th>排気量</th>
				<td><input type="text" name="gas"></td>
			</tr>
			<tr>
				<th>登録日</th>
				<td><input type="text" name="regDate"></td>
			</tr>
			<tr>
				<th>レンタル金額上限</th>
				<td><input type="text" name="maxPrice"></td>
			</tr>
			<tr>
				<th>レンタル金額下限</th>
				<td><input type="text" name="minPrice"></td>
			</tr>
		</table><br>
		<input type="submit" value="検索">　<input type="reset" value="クリア">
	</form><hr>
	<br>
	
	<form action="" method="post">
				<table border="1">
			<tr>
				<th>選択</th>
				<th>車登録コード</th>
				<th>カテゴリ</th>
				<th>車名</th>
				<th>メーカー</th>				
				<th>レンタル金額(1日)</th>
			</tr>
			<c:forEach var="obj" items="${carInfo.carList}">
				<tr>
					<td><input type="checkbox" value="${obj.carNum}" name="carNum"></td>
					<td><c:out value="${obj.carName}" /></td>
					<td>
						<c:choose>
							<c:when test="${obj.categNum == 1}">普通</c:when>
							<c:when test="${obj.categNum == 2}">軽自動車</c:when>
							<c:when test="${obj.categNum == 3}">大型社</c:when>
						</c:choose>
					</td>
					<td><c:out value="${obj.carName}" /></td>

					<td><c:out value="${obj.maker}" /></td>

					<td><c:out value="${obj.price}" /></td>
				</tr>
			</c:forEach>
		</table><br>
		<input type="submit" value="新規登録" formaction="carRegForm.html" formmethod="post">　<input type="submit" value="詳細" formaction="carDetail.html" formmethod="post">　
		<input type="submit" value="変更" formaction="carInfoChange.html" formmethod="post">　<input type="submit" value="削除" formaction="deleteConf.html" formmethod="post">
	</form>
</body>
</html>