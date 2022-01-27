<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>공지사항</h3>
<form action="list.no" method="post">
	<input type="hidden" name="curPage" value="1" /> 

<div id = 'list-top'>
	<div>
	<ul>
		<li>
			<select name='search' class='w-px90'>
				<option value="all" ${page.search eq 'all' ? 'selected' : '' }>전체</option>
				<option value="title" ${page.search eq 'title' ? 'selected' : '' }>제목</option>
				<option value="content" ${page.search eq 'content' ? 'selected' : '' }>내용</option>
				<option value="writer" ${page.search eq 'writer' ? 'selected' : '' }>작성자</option>
			</select>
		</li>
		<li><input type="text" name='keyword' value="${page.keyword }" class='w-px300' /></li>
		<li><a class='btn-fill' onclick="$('form').submit()">검색</a></li>
	</ul>
	<ul>
		<!-- admin이 'Y'인 경우만 글쓰기 가능 -->
		<c:if test="${loginInfo.admin eq 'Y' }">
			<li><a class='btn-fill' href='new.no'>글쓰기</a></li>
		</c:if>
	</ul>
	</div>
</div>
</form>
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
		<c:forEach items="${page.list }" var="vo">
		<tr>
			<td>${vo.no }</td>
			<td class='left'>
				<c:forEach begin="1" end="${vo.indent }" var="i">
					${i eq vo.indent ? "<img src='imgs/re.gif' />" : "&nbsp;&nbsp;"}
				</c:forEach>
				<a href='detail.no?id=${vo.id }'>${vo.title }</a></td>
			<td>${vo.name }</td>
			<td>${vo.writedate }</td>
			<td>${empty vo.filename ? '' : '<img src="imgs/attach.png" class="file-img" />'}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<!-- jsp 표준 include 를 사용하여 설정 .. 버튼 형태로 동작을 하기 위해 div로 묶음 -->
<div class='btnSet'>
	<jsp:include page="/WEB-INF/views/include/page.jsp" />						
</div>
</body>
</html>








