<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function boardUpdateProcess(){
		boardupdateform.submit();
	}
</script>
</head>
<body>
${boardDTO.bnumber}번 입니다.
	<form action = "boardupdateprocess" method = "post" name = "boardupdateform" enctype = "multipart/form-data">  
		<input type = "hidden" id = "bnumber" name = "bnumber" value = "${boardDTO.bnumber}">
		작성자 : <input type = "text" id = "bwriter" name = "bwriter" value = "${sessionScope.loginId}" readonly><br>
		<input type = "hidden" id = "bpassword" name = "bpassword"><br>
		제목: <input type = "text" id = "btitle" name = "btitle" value = "${boardDTO.btitle}"><br>
		글내용 : <textarea id = "bcontents" name = "bcontents" rows = "5">${boardDTO.bcontents}</textarea><br>
		<img src="${pageContext.request.contextPath}/resources/uploadfile/${boardDTO.bfilename}" style = "width : 50px; hegith : 50px"alt="사진">
		첨부파일 : <input type = "file" name = "bfile" id = "bfile"><br>
	</form>
	<button onclick = "boardUpdateProcess()">수정완료</button>
</body>
</html>