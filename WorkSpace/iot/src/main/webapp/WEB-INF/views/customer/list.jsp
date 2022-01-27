<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <jsp:include page="/WEB-INF/views/include/header.jsp" />
<div id = 'content'> --%>
	<h3>고객 목록</h3>
	<table>
		<tr>
			<th>번호</th>
			<th>고객명</th>
			<th>전화번호</th>
		</tr>
		<c:forEach items="${list }" var="vo">
			<tr>
				<td>${vo.no }</td>
				<td><a href='detail.cu?id=${vo.id}'> ${vo.name }</a></td>
				<td>${vo.phone }</td>
			</tr>
		</c:forEach>
	</table>
<%-- </div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" /> --%>
</body>
</html>