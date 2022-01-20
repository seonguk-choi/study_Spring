<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>방명록</h3>
	<table>
		<thead>
			<tr>
				<th class='w-px70'>번호</th>
				<th>제목</th>
				<th class='w-px100'>작성자</th>
				<th class='w-px100'>작성일자</th>
				<th class='w-px80'>조회수</th>
				<th class='w-px60'>첨부파일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${page.list}">
				<tr>
					<td>${vo.no}</td>			
					<td>${vo.title}</td>			
					<td>${vo.writer}</td>			
					<td>${vo.wrtiedate}</td>			
					<td>${vo.readcnt}</td>			
					<td>${empty vo.filename ? '' : '<img src="imgs/attach.png" class="file-img"/>'}</td>		
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class='btnSet'>
		<jsp:include page="/WEB-INF/views/include/page.jsp"/>
	</div>
</body>
</html>