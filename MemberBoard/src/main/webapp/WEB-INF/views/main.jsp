<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function updateBtn(){
		mid = ${sessionScope.loginId}
		location.href = "memberupdate?mid="+mid;
	}
	function logout(){
		location.href="memberlogout";
	}
	function board(){
		location.href = "boardlist";
	}
</script>
</head>
<body>
	<h2>main.jsp</h2>
	<h2>${sessionScope.loginId}님 환영합니다.</h2>
	<button onclick = "updateBtn()">정보수정</button>
	<c:if test="${sessionScope.loginId eq 'admin'}">
	<a href = "memberlist">회원목록 조회</a>
	</c:if>
	<button onclick = "board('${sessionScope.loginId}')">게시판</button>
	<button onclick="logout()">로그아웃</button>
	
</body>
</html>