<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/header.jsp"/>
	<h3>신규 고객 입력</h3>
	<form action="">
		<table>
			<tr>
				<th>고객명</th>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					<label><input type="radio" name="gender" value="남"/>남</label>
				    <label><input type="radio" name="gender" value="남"/>남</label>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email"/></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="phone"/></td>
			</tr>
		</table>
	</form>
	<div class="btnSet">
		<a class='btn-fill'>저장</a>
		<a class='btn-empty'>취소</a>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>