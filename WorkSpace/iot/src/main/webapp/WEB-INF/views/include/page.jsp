<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page_list"> <!-- 페이지 목록 class 지정  -->

<c:if test="${page.curBlock gt 1 }">  <!-- gt  > 와 같은 의미 -->
	<a class="page_first" title="처음" onclick="go_page(1)">처음</a>
	<a class="page_prev" title="이전" onclick="go_page(${page.beginPage - page.blockPage})">이전</a>
</c:if>
<c:forEach var="no" begin="${page.beginPage }" end="${page.endPage }">
	<!-- 현재 페이지인 경우 -->
	<c:if test="${no eq page.curPage }">
		<span class='page_on'>${no }</span>
	</c:if>	
	<!-- 현재 페이지가 아닌 경우 -->
	<c:if test="${no ne page.curPage }">
		<a class='page_off' onclick="go_page(${no })">${no }</a>
	</c:if>
</c:forEach>

<!-- 마지막 블럭의 경우 다음, 마지막 표기가 나타나지 않게끔 처리 -->
<c:if test="${page.curBlock lt page.totalBlock }"> <!-- lt  < 같은 의미 -->
	<a class="page_next" title="다음" onclick="go_page(${page.endPage+1})">다음</a>
	<!-- 다음 블럭의 경우 현재 블럭의 끝 목록 번호에 +1 한 위치
	     즉, 끝 목록번호가 30일 경우 +1의 위치인 31번 페이지가 나오면 됨 -->
	<a class="page_last" title="마지막" onclick="go_page(${page.totalPage})">마지막</a>
	<!-- 마지막 블럭의 경우 총 페이지 수에 해당하는 페이지로 이동하면 됨 -->
</c:if>
</div>
<script>
function go_page( page ) {
	$('[name=curPage]').val(page);
	$('form').submit();
}
</script>
<style>
	.page_on, .page_off, .page_next, .page_last, .page_prev, .page_first {
		display: inline-block; line-height: 30px; margin: 0;
	}
	.page_on {
		border: 1px solid #777; background-color: #999; color: #fff; font-weight: bold;
	}
	.page_on, .page_off {
		min-width: 22px; padding: 0 5px 2px;
	}
	.page_prev, .page_next, .page_last, .page_first {
		width: 30px; height: 32px; color: #666; 
		border: 1px solid gray; text-indent: -999999999999999px;
	}
	.page_next { background: url("imgs/page_next.jpg") center no-repeat;}
	.page_last { background: url("imgs/page_last.jpg") center no-repeat;}
	.page_first { background: url("imgs/page_first.jpg") center no-repeat;}
	.page_prev { background: url("imgs/page_prev.jpg") center no-repeat;}

</style>


