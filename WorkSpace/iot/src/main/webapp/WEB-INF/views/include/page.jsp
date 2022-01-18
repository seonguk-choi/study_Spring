<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<div class='page_list'>
	<c:forEach var='no' begin="${page.beginPage}" end="${page.endPage}">
		<!-- 현재 페이지인 경우 -->
		<c:if test="${no eq page.curPage}">
			<span class='page_on'>${no}</span>
		</c:if>
		<!-- 현재 페이지가 아닌 경우 -->
		<c:if test="${no ne page.curPage }">
			<a class='page_off' onclick='form.submit()'>${no}</a>
		</c:if>
	</c:forEach>	
</div>

<style>
.page_on , .page_off {
	display: inline-block;
	line-height: 30px;
	margin: 0;
}
.page_on {
	border: 1px solid #777;
	background-color: #999;
	color: #fff;
	font-weight: bold;
}
.page_on, .page_off{
	min-width: 22px;
	padding: 0 5px 2px;
}
</style>