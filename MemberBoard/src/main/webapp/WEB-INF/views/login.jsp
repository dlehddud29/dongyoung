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
<h2>login.jsp</h2>
	<form action = "loginform">
		아이디 : <input type="text" id = "mid" name = "mid">
		비밀번호 : <input type = "password" id = "mpw" name = "mpw"><br>
		<input type="submit" value="로그인">
	</form>
	
	<h3>카카오로 로그인</h3>
	<a href = "kakaologin">
			<img src = "${pageContext.request.contextPath}/resources/kakao_login_medium_narrow.png"></a>
			
	<h3>네이버로 로그인</h3>
	<a href = "naverlogin">
			<img src = "${pageContext.request.contextPath}/resources/네이버 아이디로 로그인_축약형_Green.PNG"></a>		
</body>
</html>