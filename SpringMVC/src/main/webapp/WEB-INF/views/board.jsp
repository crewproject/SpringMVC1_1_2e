<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
		<c:forEach var="v" items="${board}" >
			<tr>
				<td>${v.id }</td>
				<td>${v.title }</td>
				<td>${v.name }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>