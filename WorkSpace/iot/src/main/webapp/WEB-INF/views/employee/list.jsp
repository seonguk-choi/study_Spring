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
<h3>사원 정보 목록</h3>
<table>
	<thead>
		<tr class='w-px120'>
			<th>사번</th>
			<th>성명</th>
			<th>부서코드</th>
			<th>부서명</th>
			<th>업무코드</th>
			<th>업무명</th>
			<th>급여</th>
		</tr>
	</thead>
	<tbody>
	<!-- core tag의 forEach 를 사용하여 추출  -->
		<c:forEach items="${list }" var="vo">
		<tr>
			<td>${vo.employee_id}</td>
			<td><a href='detail.hr?id=${vo.employee_id }'>${vo.last_name} ${vo.first_name }</a></td>
			<td>${vo.department_id }</td>
			<td>${vo.department_name }</td>
			<td>${vo.job_id }</td>
			<td>${vo.job_title }</td>
			<td>${vo.salary }</td>
		</tr>
		</c:forEach>
	</tbody>

</table>
<%-- </div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" /> --%>
</body>
</html>