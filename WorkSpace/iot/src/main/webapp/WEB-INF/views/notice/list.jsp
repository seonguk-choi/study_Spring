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
<h3>공지사항</h3>
<form action="list.no" method="get">
	<input type="hidden" name='curPage' value='1'/>
</form>
<div id='list-top'>
	<ul>
		<c:if test="${loginInfo.admin eq 'y' }">
			<li><a class='btn-fill' href='new.no'>글쓰기</a></li>
		</c:if>
	</ul>
</div>
<table>
	<thead>
		<tr>
			<th class='w-px70'>번호</th>
			<th>제목</th>
			<th class='w-px100'>작성자</th>
			<th class='w-px100'>작성일자</th>
			<th class='w-px80'>첨부파일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="vo" items="${page.list}">
			<tr>
				<td>${vo.no}</td>
				<td class='left'>
					<a href='detail.no?id=${vo.id}'>${vo.title}</a>
				</td>
				<td>${vo.name}</td>
				<td>${vo.writedate}</td>
				<td>${empty vo.filename ? '' : '<img src="imgs/attach.png" class="file-img"/>'}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<!-- jsp 표준 include 를 사용하여 설정 버튼 형태로 동작하기 위해 div로 묶음 -->
<div class='btnSet'>
	<jsp:include page="/WEB-INF/views/include/page.jsp"/>
</div>
</body>
</html>