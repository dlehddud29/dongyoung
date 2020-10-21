<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function bfile2(){
		boardwriteform.submit();
	}
</script>
</head>
<body>

	<form action = "boardwritefile" method = "post" name = "boardwriteform" enctype = "multipart/form-data">  
		작성자 : <input type = "text" id = "bwriter" name = "bwriter" value = "${sessionScope.loginId}" readonly><br>
		<input type = "hidden" id = "bpassword" name = "bpassword"><br>
		제목: <input type = "text" id = "btitle" name = "btitle"><br>
		글내용 : <textarea id = "bcontents" name = "bcontents" rows = "5"></textarea><br>
		첨부파일 : <input type = "file" name = "bfile" id = "bfile"><br>
	</form>
	<button onclick = "bfile2()">글쓰기</button>
</body>
</html>