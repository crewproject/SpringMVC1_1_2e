<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/View.css">
</head>
<body>
	<section>
		<table border="1">
			<tr>
				<td colspan="3">${vo.title}</td>
			</tr>
			<tr>
				<td>작성자 : ${vo.name}</td>
				<td>작성일 : ${vo.wdate}</td>
				<td>조회수 : ${vo.see}</td>
			</tr>
			<tr>
				<td colspan="3">${vo.comment}</td>
			</tr>
		</table>
		<form action="/delete" method="POST">
			<input type="hidden" name="id" value="${vo.id}" />
			<input type="submit" value="삭제">
		</form>
		<form action="/updateBBS" method="POST">
			<input type="hidden" name="id" value="${vo.id}" />
			<input type="submit" value="수정">
		</form>
		<button onclick="location.href='/list'">리스트</button>
	</section>
</body>
</html>