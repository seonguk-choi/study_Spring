<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>공지글 안내</h3>
	<table>
		<tr>
			<th class='w-px120'>제목</th>
			<td class='left' colspan="5">${vo.title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${vo.name}</td>
			<th class='w-px100'>작성일자</th>
			<td class='w-px100'>${vo.writedate}</td>
			<th class='w-px80'>조회수</th>
			<td class='w-px80'>${vo.readcnt}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td class='left' colspan="5">${fn:replace(vo.content, crlf, '<br/>')}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td class='left' colspan="5">${vo.filename}
				<c:if test="${!empty vo.filename }">
					<a href="download.no?id=${vo.id}"><i class="far fa-save"></i></a>
				</c:if>					
			</td>
		</tr>
	</table>
</body>
</html>