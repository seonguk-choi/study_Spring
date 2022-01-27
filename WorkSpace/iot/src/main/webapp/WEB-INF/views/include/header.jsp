<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel='stylesheet' type="text/css" href="css/common.css?v=<%= new Date().getTime() %>" >
<!-- <script type="text/javascript" src='js/jquery-3.6.0.min.js'></script> -->
<script type="text/javascript" src='https://code.jquery.com/jquery-3.6.0.min.js'></script>
<style>										<%-- css?v=<%= new java.util.Date().getTime() %> --%>
	header ul, header ul li {
		margin :0;
		padding : 0;
		display: inline;
	}
	
	header .category{
		font-size : 18px;
	}
	
	header .category ul li:not(:first-child) {
		padding-left: 30px;
	}
	
	header .category ul li a:hover, header .category ul li a.active {
		font-weight: bold;
		color : #0000cd;
	}
</style>
<header style="border-bottom: 1px solid #ccc; padding : 15px 0; text-align : left">
	<div class='category' style="margin-left: 200px;">
		<ul>
			<li><a href='<c:url value="/" />'> <img src="imgs/hanul.logo.png"></a></li>
			<li><a href="list.cu" ${category eq 'cu' ? "class='active'" : '' } >고객관리</a></li>
			<li><a href="list.hr" ${category eq 'hr' ? "class='active'" : '' } >사원정보</a></li>
			<li><a href="list.no" ${category eq 'no' ? "class='active'" : '' }>공지사항</a></li>
			<li><a href="list.bo" ${category eq 'bo' ? "class='active'" : '' }>방명록</a></li>
			<li><a href="list.da">공공 데이터</a></li>
		</ul>
		<div style='position: absolute; right: 0; top: 20px; margin-right: 200px;'>
		<ul>
			<!-- 로그인하지 않은 상태 -->
				<c:if test="${ empty loginInfo }">
					<li>				
						<a class='btn-fill' href='login'>로그인</a>
						<a class='btn-fill' href='member'>회원가입</a>
					</li>
				</c:if>
			<!-- 로그인한 상태 -->
				<c:if test="${ !empty loginInfo }">
					<li>				
						<strong>${loginInfo.name }</strong> 님 
						<a class='btn-fill' href='logout'>로그아웃</a>
					</li>
				</c:if>
					
		</ul>
		
		</div>
		
		
	</div>
</header>





