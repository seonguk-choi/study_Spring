/* 파일 첨부 스크립트*/
$(document).on('change', '#attach-file', function(){
	//현재 태그의 0번지 값을 attached에 할당
	var attached = this.files[0];
	
	//첨부된 파일이 있을 경우 파일명, delete이미지 표시
	if(attached){
		$('#file-name').text(attached.name);
		$('#delete-file').css('display', 'inline');
	} else {
		$('#file-name').text("");
		$('#delete-file').css('display', 'none');		
	}
}).on('click', '#delete-file', function(){
	$('#file-name').text("");
	$('#delete-file').css('display', 'none');		
	
	//파일 태그의 첨부된 파일 정보 없애기
	$('#attach-file').val('');
});

