<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src='js/file_check.js'></script>
</head>
<body>
<h3>공지글 수정</h3>
<form action="update.no" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value="${vo.id }">
<table>
	<tr>
		<th class='w-px120'>제목</th>
		<td><input type='text' name='title' value="${vo.title }" title='제목' class='chk' /></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea name='content' title='내용' class='chk'>${vo.content }</textarea> </td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td class='left'>
			<label>
				<a><img src='imgs/select.png' class='file-img' /></a>
				<input type='file' name='file' id='attach-file' /> 
			</label>
			<span id='file-name'>${vo.filename }</span>
			<a id='delete-file' style="display: ${empty vo.filename ? 'none' : 'inline'}"><i class='font-img fas fa-minus-circle'></i></a>
		</td>
	</tr>
	<input type="hidden" name="attach" /> <!-- 첨부파일을 저장 시 사용 -->
</table>
<div class='btnSet'>
	<a class='btn-fill' onclick=" if ( emptyCheck() ) { $('[name=attach]').val($('#file-name').text());  $('form').submit() }">저장</a>
	<a class='btn-empty' href='detail.no?id=${vo.id }'>취소</a>
</div>
</form>
</body>
</html>







