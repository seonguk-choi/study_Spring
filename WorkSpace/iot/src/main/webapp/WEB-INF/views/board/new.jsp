<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src='js/file_check.js?v=<%=new Date().getTime() %>'></script>
</head>
<body>
<h3>신규 방명록</h3>

<!-- 
파일 첨부하여 submint 하는 경우 
1. method는 post로 지정
2. form 에는 반드시 enctype='multipart/form-data' 지정
-->

<form action="insert.bo" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<th class="w-px120">제목</th>
			<td>
				<input type="text" name="title" title='제목' class="chk"/>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<!-- textarea 는 태그를 닫으면 안된다. -->
				<textarea name="content" class="chk" title="내용"></textarea>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td class='left'>
				<label>
					<a><img src="imgs/select.png" class='file-img'/></a>
					<input type="file" id='attach-file' name='file' />
				</label>
				<span id='file-name'></span>
				<!-- 이미지파일 미리보기 -->
				<span id='preview'></span>
				<a id='delete-file' style='display: ${empty vo.filename ? "none" : "inline"}'><i class="fas fa-minus-circle"></i></a>
			</td>
		</tr>
	</table>
	<div class="btnSet">
		<a class="btn-fill" onclick='if ( emptyCheck()) $("form").submit()'>저장하기</a>
		<a class="btn-empty" href='list.bo'>취소하기</a>
	</div>
</form>
</body>
</html>