<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>予約情報一覧</title>
</head>
<body>
	<form action="serchReserve.html" method="post">
		<table border="1">
			<tr>
				<th>予約貸出とりまとめNo</th>
				<td><input type="text" name="colleNum"></td>
			</tr>
			<tr>
				<th>貸出ステータス</th>
				<td><select name="status">
						<option></option>
						<option value="0">予約中</option>
						<option value="1">貸出中</option>
						<option value="2">予約キャンセル</option>
						<option value="3">返却済み</option>
				</select></td>
			</tr>

		</table><br>

		<input type="submit" value="検索">　<input type="reset" value="クリア">
	</form><hr>
	<br>

	<form action="statusChange.html" method="post">
		<table border="1">
			<tr>
				<th>選択</th>
				<th>会員名</th>
				<th>カテゴリ</th>
				<th>車名</th>
				<th>開始日</th>
				<th>終了日</th>
				<th>貸出ステータス</th>
			</tr>
			<c:forEach var="obj" items="${reserve.resList}">
				<tr>			
					<td><input type="checkbox" value="${obj.colleNum}" name="colleNum"></td>

					<c:forEach var="obj" items="${reserve.memList}">
						<td><c:out value="${obj.name}" /></td>
					</c:forEach>

					<c:forEach var="obj" items="${reserve.categList}">
						<td><c:out value="${obj.name}" /><input type="hidden"
							value="${obj.name}" name="categName"></td>
					</c:forEach>

					<c:forEach var="obj" items="${reserve.carList}">
						<td><c:out value="${obj.carName}" /><input type="hidden"
							value="${obj.carName}" name="carName"></td>]
					</c:forEach>
						<c:forEach var="obj" items="${reserve.resList}">
						<td><c:out value="${obj.startDay.substring(0, 10)}" /><input type="hidden"
							value="${obj.startDay}" name="startDay"></td>
						<td><c:out value="${obj.finishDay.substring(0, 10)}" /><input type="hidden"
							value="${obj.finishDay}" name="finishDay"></td>
						</c:forEach>
						<td>
						
							<c:choose>
								<c:when test="${obj.reserveState == 0}">予約中</c:when>
								<c:when test="${obj.reserveState == 1}">貸出中</c:when>
								<c:when test="${obj.reserveState == 2}">予約キャンセル</c:when>
								<c:otherwise>返却済み</c:otherwise>
							</c:choose>
							<input type="hidden" value="${obj.reserveState}" name="reserveState">
						</td>
					
				</tr>
			</c:forEach>
		</table><br>
	<input type="submit" value="貸出" formaction="statusChange.html" formmethod="post">　<input type="submit" value="返却" formaction="statusChange.html" formmethod="post">　<input type="button" value="戻る" onClick="history.back()">
	</form>
</body>
</html>