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
	<form method="POST" action="/insert">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="email" name="email" /></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="text" name="pass" /></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" /></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><input type="text" name="comment" /></td>
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