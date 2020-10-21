<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
	function idOverlap(){
		var inputId = document.getElementById("mid").value;
		//var inputId = $("#mid").val(); 위에 꺼랑 똑같은거
		//AJAX(asynchronous javascript and XML)
		//JSON(JavaScript Object Notation)
		$.ajax({
			type : "post",
			url : "idoverlap",//컨트롤러에 주소
			data : {"mid" : inputId}, //inputId를 mid에 값을 넣어준다.
			dataType : "text",//데이터전송 형식
			success : function(result){ //통신이 정상적으로 되면 컨트롤러에서 리턴을 받아서 어떻게 할것이냐 
				if(result=="OK"){
					alert("사용가능한 ID입니다.");
				}else{
					alert("이미 사용중인 ID입니다.");
				}
			},
			error : function(){
				alert("ajax 실패!!");	
			}
		});
	}
	function idOnkeyup(){
		var inputId = document.getElementById("mid").value;
		 var idck = document.getElementById("onkey");
		//AJAX(asynchronous javascript and XML)
		//JSON(JavaScript Object Notation)
		$.ajax({
			type : "post",
			url : "idonkeyup",//컨트롤러에 주소
			data : {"mid" : inputId}, //inputId를 mid에 값을 넣어준다.
			dataType : "text",//데이터전송 형식
			success : function(result){ //통신이 정상적으로 되면 컨트롤러에서 리턴을 받아서 어떻게 할것이냐 
				if(result=="OK"){
					idck.style.color = "green";
					idck.innerHTML = "사용가능한 ID입니다.";
				}else{
					idck.style.color = "red";
					idck.innerHTML = "이미 사용중인 ID입니다.";
				}
			},
			error : function(){
				alert("ajax 실패!!");	
			}
		});
	}
	 function pwdCheck(){
         //비밀번호 검증을 위한 정규식
         // /^는 정규식이 시작한다라는 뜻 소문자,대문자,문자,특수문자 자릿수는 8~16자리
         var exp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;
         var pwd = document.getElementById("mpw").value;
         var pwdch = document.getElementById("pwdch");
         if(pwd.match(exp)){
             pwdch.style.color = "green";
             pwdch.innerHTML = "비밀번호 형식 맞음";
         }else{
             pwdch.style.color = "red";
             pwdch.innerHTML = "비밀번호 형식 맞지않음"
         }
     }
     function pwdeqFn(){
         console.log("pwdEq 함수 호출")
         var pwd = document.getElementById("mpw").value;
         var pwdch = document.getElementById("mpwok").value;
         var eqMsg = document.getElementById("pwdEq");
         if(pwd==pwdch){
             console.log('비밀번호 일치');
             // document.getElementById("pwdEq").style.color = "green";
             // document.getElementById("pwdEq").innerHTML = "비밀번호 일치";
             eqMsg.style.color = "green";
             eqMsg.innerHTML = "비밀번호 일치";
         }else{
             console.log('비밀번호 불일치');
             // document.getElementById("pwdEq").style.color = "red";
             // document.getElementById("pwdEq").innerHTML = "비밀번호 불일치";
             eqMsg.style.color = "red";
             eqMsg.innerHTML = "비밀번호 불일치";
         }
     }
</script>
</head>
<body>
<h2>join.jsp</h2>
	카카오 아이디 : ${kakaoId}<br>
	네이버 아이디 : ${naverId}
	<form action = joinform method = "post">
		<c:choose>
			<c:when test="${kakaoId ne null}">
				아이디 : <input type = "text" name ="mid" id ="mid" onkeyup="idOnkeyup()">
				<input type="hidden" name = "kakaoId" value="${kakaoId}"><br>
				<span id = "onkey"></span>
			</c:when>
			<c:when test="${naverId ne null}">
				아이디 : <input type = "text" name ="mid" id ="mid" onkeyup="idOnkeyup()">
				<input type="hidden" name = "naverId" value="${naverId}"><br>
				<span id = "onkey"></span>
			</c:when>
			<c:otherwise>
				아이디 : <input type = "text" name = "mid" id = "mid" onkeyup="idOnkeyup()">
				<span id = "onkey"></span>
			</c:otherwise>
		</c:choose>
	
		<!-- 아이디 : <input type ="text" id = "mid" name = "mid" onkeyup = "idOnkeyup()">
			  <input type = "button" value="아이디 중복확인" onclick="idOverlap()">
			  <span id = "onkey"></span> -->
			  <br>
		비밀번호 : <input type = "password" id = "mpw" name = "mpw" onkeyup="pwdCheck()">
		<span id = "pwdch"></span><br>
		비밀번호확인 : <input type = "text" id = "mpwok" name ="mpwok" onkeyup="pwdeqFn()">
		<span id = "pwdEq"></span><br>
		이름 : <input type = "text" id ="mname" name ="mname"><br>
		전화번호 : <input type = "text" id = "mphone" name = "mphone"><br>
		이메일 : <input type = "text" id = "memail" name = "memail"><br>
		생년월일 : <input type ="date" id = "mbirth" name = "mbirth"><br>
		
		<input type ="submit" value ="회원가입">
	</form>
</body>
</html>