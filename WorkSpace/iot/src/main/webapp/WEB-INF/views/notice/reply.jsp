<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src='js/file_check.js'></script>
</head>
<body>
<h3>답글 작성</h3>
<!-- 파일 첨부하여 submit 하는 경우
	1. method 는 post 로 지정
	2. form 에는 반드시 enctype='multipart/form-data' 지정
 -->
<form action="reply_insert.no" method="post" enctype="multipart/form-data">
	<!-- 답글에 필요한 root, step, indent 값을 hidden 으로 전달 -->
	<input type="hidden" name="root" value="${vo.root }">
	<input type="hidden" name="step" value="${vo.step }">
	<input type="hidden" name="indent" value="${vo.indent }">
	<table>
		<tr>
			<th class='w-px120'>제목</th>
			<td>
				<input type='text' name="title" title='제목' class='chk' />
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea name='content' class='chk' title='내용' ></textarea>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td class='left'>
				<label>
				<a><img src='imgs/select.png' class='file-img' /></a>
				<input type="file" id='attach-file' name='file' />	
				</label>	
				<span id='file-name'></span>						
			</td>
		</tr>
	
	</table>
	<div class='btnSet'>
		<a class='btn-fill' onclick='if ( emptyCheck() ) $("form").submit()'>저장하기</a>
		<a class='btn-empty' href='list.no'>취소</a>
	</div>

</form>
</body>
</html>