<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>入力情報確認</title>
</head>
<body>
		<form action="registerDo.html" method="post">
		<table border="1">
			<tr>
				<th>カテゴリ</th>
				<td>
					<c:choose>
						<c:when test="${formInfo.categNum == 1}">普通</c:when>
						<c:when test="${formInfo.categNum == 2}">軽自動車</c:when>
						<c:otherwise>大型車</c:otherwise>
					</c:choose>
					<input type="hidden" value="${formInfo.categNum}" name="categNum">
				</td>
			</tr>
			<tr>
				<th>車名</th>
				<td>
					<c:out value="${formInfo.carName}"></c:out>
					<input type="hidden" value="${formInfo.carName}" name="carName">
				</td>
			</tr>
			<tr>
				<th>メーカー</th>
				<td>
					<c:out value="${formInfo.maker}"></c:out>
					<input type="hidden" value="${formInfo.maker}" name="maker">					
				</td>
			</tr>
			<tr>
				<th>排気量</th>
				<td>
					<c:out value="${formInfo.gas}"></c:out>
					<input type="hidden" value="${formInfo.gas}" name="gas">				
				</td>
			</tr>
			<tr>
				<th>レンタル金額</th>
				<td>
					<c:out value="${formInfo.price}"></c:out>
					<input type="hidden" value="${formInfo.price}" name="price">				
				</td>
			</tr>
			<tr>
				<th>メモ</th>
				<td>
					<c:out value="${formInfo.memo}"></c:out>
					<input type="hidden" value="${formInfo.memo}" name="memo">				
				</td>
			</tr>			
		</table><br>
		<input type="submit" value="実行">　<input type="button" value="戻る" onClick="history.back()">
	</form>
</body>
</html>