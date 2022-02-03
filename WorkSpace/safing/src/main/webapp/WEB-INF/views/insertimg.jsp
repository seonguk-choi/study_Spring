<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src='js/file_check.js?v=<%=new Date().getTime()%>'></script>
<style type="text/css">
.btnSet { margin-top: 20px;}

a.btn-fill, a.btn-empty {
	text-align: center;
	padding: 3px 10px;
	border: 1px solid #3367d6;
	border-radius: 3px;
	box-shadow: 2px 2px 3px #ccc; /* 오른쪽, 아래쪽, 번짐 정도 */
}

a.btn-fill {
	background-color: #3367d6;
	color: #fff;
}
</style>
<script type="text/javascript">
function fnSubmit(){
		return true;
}

</script>
</head>
<body>
<h3>방명록 글쓰기</h3>
<!-- 파일 첨부하여 submit 하는 경우
	1. method 는 post 로 지정
	2. form 에는 반드시 enctype='multipart/form-data' 지정
 -->
<form action="insert_img.bo" method="post" onsubmit="return fnSubmit()" enctype="multipart/form-data">
	<table>
		<tr>
			<th>첨부파일</th>
			<td class='left'>
				<label>
				<a><img src='imgs/select.png' class='file-img' /></a>
				<input type="file" id='attach-file' name='file' />	
				</label>	
				<span id='file-name'></span>
				<!-- 이미지 파일인 경우 미리보기 적용 -->
				<span id='preview'></span>				
			</td>
		</tr>	
	</table>
	<div class='btnSet'>
	<input type="submit" value="수정하기">
</div>
</form>
</body>
</html>



