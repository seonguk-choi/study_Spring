<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#popup {
	width: 450xp;
	height: 450px;
	border: 2px solid #666;
	display: none;
}

#popup-background {
	position: absolute; 
	left:0; 
	top: 0; 
	width: 100%; 
	height: 100%;
	background-color:  #000; 
	opacity: 0.3; 
	display:none;
}

.popup {
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>
<h3>방명록</h3>
<table>
	<tr>
		<th class='w-px120'>제목</th>
		<td class='left'  colspan='5'>${vo.title }</td>
	</tr>
	<tr>
		<th class='w-px120'>작성자</th>
		<td class='left'>${vo.writer}</td>
		<th class='w-px120'>작성일자</th>
		<td class='w-px120'></td>
		<th class='w-px80'>조회수</th>
		<td class='w-px80'>${vo.readcnt}</td>
	</tr>
	<tr>
		<th class='w-px120'>내용</th>
		<td class='left' colspan="5">${fn:replace(vo.content, crlf, '<br/>')}</td>
	</tr>
	<tr>
		<th class='w-px120'>첨부파일</th>
		<td class='left' colspan='5'>${vo.filename }
		
		
		<!-- 파일이 있는 경우에만 파일 정보를 나타냄 -->
		<!-- 페이지 이동하는 형태가 아니므로 id 값을 get 방식을 전달한다. -->
		<c:if test="${!empty vo.filename }">
			<a id='preview'></a>
			<a href="download.bo?id=${vo.id}"><i class="fas fa-download"></i></a>
		</c:if>
		</td>
	</tr>
</table>

<!-- 이미지를 크게 볼 수 있도록 popup처리 -->
<div id='popup-background'></div>
<div id='popup' class='center'></div>

<script type="text/javascript" src='js/file_check.js'></script>
<script type="text/javascript">
$(function(){
	if(${!empty vo.filename}){
		if(isImage('${vo.filename}')){
			$("#preview").html("<img src='' id='preview-img'/>")
			var reader = new FileReader();
			reader.onload = function(e){
				$("#preview").attr('scr', e.target.result)			
			}
	
		}
	}
});

$(document).on('click', '#preview-img', function(){
	$('#popup, #popup-background').css('display', 'block');
		$("#preview").html("<img src='' id='preview-img'/>")
		var reader = new FileReader();
		reader.onload = function(e){
			$("#preview").attr('scr', e.target.result)			
		}
	
}).on('click', '#popup-background', function(){
	$('#popup, #popup-background').css('display', 'none');
});

</script>
</body>
</html>