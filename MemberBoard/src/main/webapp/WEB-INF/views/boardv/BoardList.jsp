<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function boardwrite(){
		location.href = "boardwrite";	
	}
	function boardSearch(){
		searchform.submit();
	}
	function boardtest(bwriter, page){
		var option = "width = 100, height = 100, top = 300, left = 500, location = no";
		window.open('boardinfo?bwriter='+bwriter, 'popup', 'option');
	}
</script>
</head>
<body>
<h2>BoardList</h2>
		<form action = "boardsearch" method = "get" name = "searchform">
			<select id ="searchtype" name = "searchtype">
				<option value = "searchtitle">제목</option>
				<option value = "searchwrite">작성자</option>
			</select>
			<input type = "text" name = "keyword" placeholder = "검색어 입력">
			<input type = "button" onclick = "boardSearch()" value = "검색">
		</form>
<table border = "1">
<tr><th>번호</th>
	<th>작성자</th>
	<th>제목</th>
	<th>내용</th>
	<th>날짜</th></tr>
<c:forEach var="List" items="${boardList}" >
	<tr>
	<td>${List.bnumber}</td> 
	<td><a href = "javascript:boardtest('${List.bwriter}','${paging.page}')">${List.bwriter}</a></td>
	<td><a href = "boardview?bnumber=${List.bnumber}&page=${paging.page}">${List.btitle}</a></td>
	<td>${List.bcontents}</td>
	<td>${List.bdate}</td>
	<td><img src="${pageContext.request.contextPath}/resources/uploadfile/${List.bfilename}" style = "width : 50px; hegith : 50px"alt="사진"></td>
	</tr>
</c:forEach>
</table>
<button onclick = "boardwrite()">글쓰기</button>
<c:if test="${paging.page<=1}">
	[이전]&nbsp;
	</c:if>
	
	<c:if test="${paging.page>1}">
		<a href="boardlist?page=${paging.page-1}">[이전]</a>&nbsp;
	</c:if>
	
	<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
		<c:choose>
			<c:when test="${i eq paging.page}">
			${i}
			</c:when>
			<c:otherwise>
				<a href="boardlist?page=${i}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:if test="${paging.page>=paging.maxPage}">
		[다음]
	</c:if>
	
	<c:if test="${paging.page<paging.maxPage}">
		<a href="boardlist?page=${paging.page+1}">[다음]</a>
	</c:if>
</body>
</html>