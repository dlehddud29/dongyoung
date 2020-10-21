<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	home.jsp
</h1>



<button onclick = "location.href = 'memberloginform'">로그인</button>
<button onclick = "location.href = 'memberjoinform'">회원가입</button>
<!-- 여기서 바로 jsp로 갈 수 없음 무조건 controller를 거쳐서 가야한다. -->

<h3>카카오로 회원가입</h3>
	<a href = "kakaojoin">
	<img src="${pageContext.request.contextPath}/resources/kakao_login_medium_narrow.png"></a>

<h3>네이버로 회원가입</h3>
	<a href = "naverjoin">
	<img src = "${pageContext.request.contextPath}/resources/네이버 아이디로 로그인_축약형_Green.PNG"></a>
	

</body>
</html>
