<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/View.css">
<script type="text/javascript">

</script>
</head>
<body>
	<table border="1">
		<tr>
			<th colspan="5">
				<form action="/list" method="POST">
				<select name="searchType">
					<option value="TITLE" 
						<c:if test="${searchType eq 'TITLE'}">
							selected
						</c:if>
					>제목</option>
					<option value="NAME" 
						<c:if test="${searchType eq 'NAME'}">
							selected
						</c:if>
					>이름</option>
					<option value="COMMENT" 
						<c:if test="${searchType eq 'COMMENT'}">
							selected
						</c:if>
					>내용</option>
				</select>
				<input type="text" name="text" value="${text}"/>
				<input type="submit" value="검색"/>
			</form>
			</th>
		</tr>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="vo" items="${vo}">
			<tr>
				<td><a href='/getone/${vo["id"]}'>${vo["id"]}</a></td>
				<td>${vo["title"]}</td>
				<td>${vo["name"]}</td>
				<td>${vo["wdate"]}</td>
				<td>${vo["see"]}</td>
			</tr>
		</c:forEach>
	</table>
	<button onclick="location.href='/insertBBS'">글쓰기</button>
</body>
</html>