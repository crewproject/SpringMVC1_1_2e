<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Writer Page</title>
<link rel="stylesheet" type="text/css" href="/resources/View.css">
</head>
<body>
	<form method="POST" action="/update">
		<table>
			<tr>
				<td>제목</td>
				<td>
					<input type="hidden" name="id" value="${id}" />
					<input type="text" name="title" value="${title}" />
				</td>
			</tr>
			<tr>
				<td>비밀번호 입력</td>
				<td><input type="text" name="pass" /></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><input type="text" name="comment" value="${comment}" /></td>
			</tr>
			<tr>

				<td><input type="submit" value="전송" /></td>
				<td><input type="reset" name="리셋" /></td>
				<td><button onclick="location.href='/list'">리스트</button></td>
			</tr>
		</table>
	</form>
</body>
</html>