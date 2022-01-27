<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- tiels 라이브러리를 사용할 수 있도록 선언 -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
	<c:when test="${category eq 'login' }"><c:set var='title' value="로그인"/> </c:when>
</c:choose>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IoT 과정 > ${title } </title>

<link rel="icon" type="image/x-icon" href="imgs/hanul.ico" />
<link rel="stylesheet" type="text/css" href="css/common.css?v=<%=new Date().getTime() %>">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div>
		<tiles:insertAttribute name="content" />
	</div>
</body>
</html>