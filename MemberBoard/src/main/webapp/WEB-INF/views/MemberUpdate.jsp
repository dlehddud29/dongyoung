<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
 function updateBtn(){
	 updateform.submit();
 }
</script>
</head>
<body>
<form action="memberupdateprocess" method = "post" name = "updateform">
아이디 : <input type="text" name = "mid" id = "mid" value="${List.mid}" readonly><br> 
비밀번호 : <input type = "text" name = "mpw" id ="mpw" value = "${List.mpw}"><br>
이름 : <input type = "text" name = "mname" id = "mname" value="${List.mname}"><br>
생년월일 : <input type="text" name = "mbirth" id = "mbirth" value="${List.mbirth}"><br>
이메일 :  <input type = "text" id = "memail" name="memail" value = "${List.memail}"><br>
전화번호 : <input type = "text" id = "mphone" name = "mphone" value = "${List.mphone}"><br>      
</form>
<button onclick = "updateBtn()">수정 완료</button>
</body>
</html>