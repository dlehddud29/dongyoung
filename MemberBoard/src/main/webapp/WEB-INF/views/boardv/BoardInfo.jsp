<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border = "1">
<tr><th>아이디</th>
	<th>이름</th>
	<th>전화번호</th>
	<th>이메일</th>
	<th>생일</th></tr>
<c:forEach var="list" items="${list}" >
	<tr>
	<td>${list.mid}</td> 
	<td>${list.mname}</td>
	<td>${list.mphone}</td>
	<td>${list.memail}</td>
	<td>${list.mbirth}</td>
	</tr>
</c:forEach>
</table>

	

</body>
</html>