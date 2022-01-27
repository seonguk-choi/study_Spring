<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>방명록 수정</h3>
<form action="update.bo" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value="${vo.id }" />
<input type="hidden" name="attach" />
<table>
	<tr>
		<th class='w-px120'>제목</th>
		<td>
			<input type="text" name='title' class='chk' value="${vo.title }" title='제목' />
		</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>
			<textarea name="content" class='chk' title='내용'>${vo.content }</textarea>
		</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td class='left'>
			<label>
				<input type="file" name='file' id='attach-file'/>
				<a><img src='imgs/attach.png' class='file-img' /></a>
			</label>
			<span id='file-name'>${vo.filename }</span>
			<span id='preview'></span> <!-- 미리보기 이미지가 보이게 끔 -->
			<a id='delete-file'><i class='fas fa-times font-img'></i></a>
		</td>
	</tr>
</table>
<div class='btnSet'>
	<a class='btn-fill' onclick="if ( emptyCheck() ) { $('[name=attach]').val( $('#file-name').text() );  $('form').submit()  } " >저장</a>
	<a class='btn-fill' onclick="history.go(-1)">취소</a> <!-- 이전 화면으로 이동 -->
</div>
</form>
<script type="text/javascript" src='js/file_check.js'></script>
<script type="text/javascript">
if ( ${ ! empty vo.filename } ) {
	// 첨부파일이 있는 글인 경우 삭제버튼 보이게
	$('#delete-file').css('display', 'inline');
	// 첨부파일이 이미지 파일인 경우 미리보기
	if ( isImage( '${vo.filename}' ) )
		$('#preview').html("<img src='${vo.filepath}' id='preview-img' /> ");
}	
</script>
</body>
</html>










