<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<div class='page_list'>
	<!-- 첫블럭의 경우 다음, 마지막 페이지 나타나지 않게 처리 -->
	<c:if test="${page.curBlock gt 1}">
		<a class='page_first' title='처음' onclick='go_page(1)'>처음</a>
		<a class='page_prev' title='이전' onclick='go_page(${page.beginPage-page.blockPage})'>이전</a>
	</c:if>	
	<c:forEach var='no' begin="${page.beginPage}" end="${page.endPage}">
		<!-- 현재 페이지인 경우 -->
		<c:if test="${no eq page.curPage}">
			<span class='page_on'>${no}</span>
		</c:if>
		<!-- 현재 페이지가 아닌 경우 -->
		<c:if test="${no ne page.curPage }">
			<a class='page_off' onclick="go_page(${no})">${no}</a>
		</c:if>
	</c:forEach>
	<!-- 마지막블럭의 경우 다음, 마지막 페이지 나타나지 않게 처리 -->
	<c:if test="${page.curBlock lt page.totalBlock}">
		<a class='page_next' title='다음' onclick='go_page(${page.endPage+1})'>다음</a>
		<a class='page_last' title='마지막' onclick='go_page(${page.totalPage})'>마지막</a>	
	</c:if>		
</div>
<script>
function go_page(page){
	$('[name=curPage]').val(page);
	$('form').submit(); 
}
</script>
<style>
.page_on , .page_off, .page_next, .page_last, .page_prev, .page_first{
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
.page_next, .page_last, .page_prev, .page_first {
	width: 30px; height: 32px; color: #666;
	border: 1px solid gray;
	text-indent: -99999999px;
}
.page_first {
	background: url('imgs/page_first.jpg') no-repeat center;
}
.page_prev{
	background: url('imgs/page_prev.jpg') no-repeat center;
}
.page_next {
	background: url('imgs/page_next.jpg') no-repeat center;
}
.page_last {
	background: url('imgs/page_last.jpg') no-repeat center;
}
</style>