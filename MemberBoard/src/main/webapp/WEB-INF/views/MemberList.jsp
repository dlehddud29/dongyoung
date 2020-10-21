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
	 	function memberViewAjax(mid){
	 		console.log(mid);
	 		$.ajax({
	 			type : "post",
	 			url : "memberviewajax",
	 			data : {"mid" : mid},
	 			dataType : "json",//json은 DTO값으로 받아와야되니 사용
				success : function(result){
					console.log(result);
					/* console.log(result.mid); *///이렇게하면 넘어온 아이디값을 찍을 수 있음
					
					var output = "<table border = '1'>";
					output += "<tr><th>ID</th> <th>PASSWORD</th> <th>NAME</th>";
					output += "<th>PHONE</th> <th>EMAIL</th> <th>BIRTH</th></tr>";
					output += "<tr>";
					output += "<td>"+result.mid+"</td>";
					output += "<td>"+result.mpw+"</td>";
					output += "<td>"+result.mname+"</td>";
					output += "<td>"+result.mphone+"</td>";
					output += "<td>"+result.memail+"</td>";
					output += "<td>"+result.mbirth+"</td>";
					output += "</tr>";
					output += "</table>";
					
					$("#memberviewdiv").html(output);
				},
				error : function(){
					console.log("통신실패");
				}
	 		});
	 	}
	
	</script>
</head>
<body>
<table border = "1">
<c:forEach var="List" items="${memberList}" >
	<tr>
	<td>${List.mid}</td> 
	<td>${List.mpw}</td>
	<td>${List.mname}</td>
	<td>${List.mphone}</td>
	<td>${List.mbirth}</td>
	<td>${List.memail}</td>
	<td><a href = "memberview?mid=${List.mid}">조회</a></td>
	<td><a href = "memberdelete?mid=${List.mid}">삭제</a></td>
	<td><button onclick = "memberViewAjax('${List.mid}')">조회(ajax)</button></td>
	</tr>
</c:forEach>
</table>

<div id = "memberviewdiv">


</div>

</body>
</html>