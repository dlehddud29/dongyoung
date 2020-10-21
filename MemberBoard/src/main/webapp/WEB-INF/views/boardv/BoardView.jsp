<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
	function boardUpdate(loginId, writer, bnumber){
		if(loginId == writer){
			location.href = "boardupdate?bnumber="+bnumber;
		}else{
			alert('수정할 수 없는 아이디입니다.');
		}
	}
	

			function commentInsert(cnumber){
			var cwriter = $("#cwriter").val();
			var ccontents = $("#ccontents").val();
			var cbnumber = "${boardDTO.bnumber}";
			$.ajax({
				type : "post",
				url : "comment/commentwrite",
				data : {
					"cwriter" : cwriter,
					"ccontents" : ccontents,
					"cbnumber" : cbnumber},
				dataType : "json",
				success : function(result){
					console.log("댓글 등록 성공");
					console.log(result);
					var output = "<table border = '1'>";
					output += "<tr><th>작성자</th>";
					output += "<th>내용</th>";
					output += "<th>삭제</th></tr>";
					for(var i in result){
					    output += "<tr>";
					    output += "<td>"+result[i].cwriter+"</td>";
					    output += "<td>"+result[i].ccontents+"</td>";
					    output += "<td><button "+"onclick = 'deleteBtn("+result[i].cnumber+")'>삭제</button>";
					    output += "</tr>";
					}
					output += "</table>";
					$("#commentArea").html(output);
					$("#cwriter").val("");
					$("#ccontents").val("");
				},
				error : function(){
					console.log("댓글 등록 실패");
				}
			});
			}6
			
			
	function commentList(cnumber){
		var cbnumber = "${boardDTO.bnumber}";
		$.ajax({
			type : "post",
			url : "comment/commentlist",
			data : {
				"cbnumber" : cbnumber},
			dataType : "json",
			success : function(result){
				var output = "<table border = '1'>";
				output += "<tr><th>작성자</th>";
				output += "<th>내용</th>";
				output += "<th>삭제</th></tr>";
				for(var i in result){
				    output += "<tr>";
				    output += "<td>"+result[i].cwriter+"</td>";
				    output += "<td>"+result[i].ccontents+"</td>";
				    output += "<td><button onclick = 'deleteBtn("+result[i].cnumber+")'>삭제</button>";
				    output += "</tr>";
				}
				output += "</table>";
				$("#commentArea").html(output);
			},
			error : function(){
				console.log("댓글 등록 실패");
			}
		});
	}

			
	function deleteBtn(cnumber){
		console.log(cnumber);
			$.ajax({
				type : "post",
				url : "comment/commentdelete",
				data : {
					"cnumber" : cnumber},
				dataType : "json",
				success : function(result){
					commentList(cnumber);
				},
				error : function(){
					console.log("댓글 삭제 실패");
				}
			});
	}
	function boarddelete(loginId, bnumber, writer){
		if(loginId == writer || loginId == 'admin'){
			location.href = "boarddeletewriter?bnumber="+bnumber;
		}else{
			alert('삭제할 수 없는 아이디입니다.')
		}
		
	}
</script>
</head>
<body>
<h2>BoardView</h2>
		<table border="1">
		<tr>
		<th>글번호</th> <th>작성자</th> <th>제목</th>
		<th>내용</th><th>작성날짜</th><th>조회수</th>
		<tr>
			<td>${boardDTO.bnumber}</td>
			<td>${boardDTO.bwriter}</td>
			<td>${boardDTO.btitle}</td>
			<td>${boardDTO.bcontents}</td>
			<td>${boardDTO.bdate}</td>
			<td>${boardDTO.bhits}</td> 
		</tr>
	</table>
	<button onclick = "boardUpdate('${sessionScope.loginId}', '${boardDTO.bwriter}','${boardDTO.bnumber}')">수정</button>
	<button onclick = "boarddelete('${sessionScope.loginId}','${boardDTO.bnumber}', '${boardDTO.bwriter}')">삭제</button>
	<button onclick="location.href='boardlist?page=${page}'">목록</button>

<div id = "commentWrite">
<h4>댓글</h4>
		<input type = "hidden" id = "cwriter" value = "${sessionScope.loginId}" readonly><br>
		내용 : <input type = "text" id = "ccontents"><br>
		<button id = "commentWriteBtn" onclick = "commentInsert('${List.cnumber}')">댓글입력</button>
	</div>
	<div id = "commentArea">
	<table border = '1'>
		<tr>
		<th>작성자</th><th>내용</th><th>삭제</th>
		</tr>
		<c:forEach var="List" items="${commentlist}" > 
		<tr>
		<th>${List.cwriter}</th>
		<th>${List.ccontents}</th>
		<th><button id = "deletereview" onclick = "deleteBtn('${List.cnumber}')">삭제</button></th>
		</tr>	
		</c:forEach>
		</table>
	</div>
</body>
</html>