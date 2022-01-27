<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- page 와 uri 값 활용 -->
<form method="post" action="${uri }">
	<input type="hidden" name="id" value="${id }" />
	<input type="hidden" name="curPage" value="${page.curPage }" />  
	<input type="hidden" name="search" value="${page.search }" />  
	<input type="hidden" name="keyword" value="${page.keyword }" />  
	<input type="hidden" name="viewType" value="${page.viewType }" />  
	<input type="hidden" name="pageList" value="${page.pageList }" />  
</form>

<script>
	$('form').submit();
</script>