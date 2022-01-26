v<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${list }" var="vo">
	<div class='left'>${vo.name} [ ${vo.writedate } ]
		<div class=''>${vo.content }</div>	
	</div>
	<hr />
</c:forEach>
