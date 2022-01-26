<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>방명록 수정</h3>
<form action="update.bo" method="post" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${vo.id}"/>
	<input type="hidden" name="attach"/>
	<table>
		<tr>
			<th class='w-px120'>제목</th>
			<td>
				<input type="text" name="title" class="chk" value="${vo.title }" title='제목'/>
			</td>
		</tr>
		<tr>
			<th class='w-px120'>내용</th>
			<td>
				<textarea name="content" class="chk" title='내용'>${vo.content }</textarea>
			</td>
		</tr>
		<tr>
			<th class='w-px120'>첨부파일</th>
			<td class='left'>
				<!-- 미리보기 -->
				<label>
						<input type="file" name='file' id="attach-file"/>
						<a><img src='imgs/attach.png' class='file-img'/></a>
					</label>
					<a id='preview'></a>
					<span id='file-name'>${vo.filename }</span>	
					<a id="delete-file"><i class="fas fa-minus-circle"></i></a>
			</td>
		</tr>
	</table>
	<div class="btnSet">
		<a class='btn-fill' onclick="if ( emptyCheck() ) { $('[name=attach]').val( $('#file-name').text() );  $('form').submit()  } " >저장</a>
		<a class='btn-fill' onclick="history.go(-1)">취소</a> <!-- 이전 화면으로 이동 -->
	</div>
	<input type="hidden" name="attach"/>
</form>
<!-- 이미지를 크게 볼 수 있도록 popup처리 -->
<script type="text/javascript" src='js/file_check.js'></script>
<script type="text/javascript">
if(${!empty vo.filename}){
	$('#delete-flie').css('display', 'inline'){
		if(isImage('${vo.filename})'){
			#('#preview').html("<img src='${vo.filepath}' id='preview-img'/>")
		}
	}
}

</script>
</body>
</html>