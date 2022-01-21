/* 파일 첨부 스크립트*/
$(document).on('change', '#attach-file', function(){
	//현재 태그의 0번지 값을 attached에 할당
	var attached = this.files[0];
	
	//첨부된 파일이 있을 경우 파일명, delete이미지 표시
	  if ( attached  ) {   // 첨부된 파일이 있을 경우 파일명을 나타내고 delete 이미지도 표시함.
      $('#file-name').text(attached.name);
      $('#delete-file').css('display', 'inline');
      
      // 이미지 파일인 경우 미리보기(#preview)에 보여지게 처리
      // 태그 존재 여부는 길이(length)로 판단 , 0보다 크면 태그가 있음
      if ($('#preview').length   > 0) {
         if ( isImage( attached.name ) ) {   // 이미지 파일이면 미리보기 처리 함
            $ ('#preview').html('<img src="" id="preview-img" />');
            
            var reader = new FileReader();   //파일 정보를 읽기 위한 객체(FileReader) 를 선언
            reader.onload = function(e) {   //파일 읽기 처리
               $('#preview-img').attr('src', e.target.result)
            }
            reader.readAsDataURL ( attached );
         } else {
            $('#preview').html('');
			}
		} 
	} else {
		$('#file-name').text("");
		$('#delete-file').css('display', 'none');		
	}
}).on('click', '#delete-file', function(){
	if ($('#preview').length > 0) $('#preview').html('');
	$('#file-name').text("");
	$('#delete-file').css('display', 'none');		
	
	//파일 태그의 첨부된 파일 정보 없애기
	$('#attach-file').val('');
});

/* 이미지 파일 확인 */
function isImage( filename ) {

	// 확장자로 확인
	 var ext = filename.substring ( filename.lastIndexOf('.') +1).toLowerCase();
	// 확장자 jpg, png, tiff, gif 등 처리
   var imgs = ['jpg', 'png', 'tiff', 'gif', 'jpeg', 'webp', 'bmp', 'pcx' ];
	
   if (imgs.indexOf(ext) > -1) return true;
   else return false;

		
}
