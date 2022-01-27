<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:forEach items="${list }" var="vo" varStatus="status">
<!-- 	<hr /> -->
	${status.index eq 0 ? '<hr>' : ''} <!-- 첫번째 순서 값에 hr 태그 부여 -->
	<div data-seq="${vo.id }" class='left'>${vo.name} [ ${vo.writedate } ]
	<!-- 로그인한 사용자가 작성한 댓글인 경우 수정/삭제 가능 -->
	<c:if test="${loginInfo.id eq vo.writer }">
		<span style="float: right;">
			<a class='btn-fill-s btn-modify-save'>수정</a>
			<a class='btn-fill-s btn-delete-cancel'>삭제</a>		
		</span>	
	</c:if>	
		<div class='original'>${fn:replace(fn:replace(vo.content, lf, '<br>'), crlf, '<br>')}</div>
		<div class='modify'></div>	
	</div>
	<hr />
</c:forEach>

<script>
/* $(document).on('click', '.btn-modify-save', function () {
}) */
$('.btn-modify-save').on('click', function () {
	var $div = $(this).closest('div');
	/* .closest() 메소드는 자신을 포함한 상위 요소 중에서 
	전달받은 선택자에 해당하는 요소의 집합에서 가장 첫번째 요소를 선택  */
	
	if ( $(this).text() == '수정') {
		var tag = "<textarea style='width:96%; height:90%;'>"
			+ $div.children('.original').html().replace(/<br>/g, '\n') + "</textarea>"
			/* .replace(/<br>/g, '\n') 모든 <br> 태그를 엔터키를 부여 */
		$div.children('.modify').html(tag);
		// 수정 상태 (false) : 저장/취소 버튼, 보기(목록) 상태(true) : 수정/삭제 버튼
		display(false, $div);
	} else { /* JSON.stringify : JSON 형태로 만들어서 보냄 */
		$.ajax({
			type : 'post'	// json방식으로 값을 보낼 땐 반드시 post 방식으로 전달
			, contentType : 'application/json'
			, url:'board/comment/update'			
			, data : JSON.stringify( {id : $div.data('seq')
							, content:$div.find('textarea').val() })
			, success: function ( response ) {
				alert ('댓글 변경' + response);
				comment_list();
			}, error: function (req, text) {
				alert(text + ':' + req.status);
			}
		});
		
		
	}
});

$('.btn-delete-cancel').on('click', function () {
	var $div = $(this).closest('div');
	if ($(this).text() == '취소')
		display(true, $div);
	else {
		if ( confirm('정말 삭제하시겠습니까?')) {
			$.ajax({
				url : 'board/comment/delete/' + $div.data('seq') 
				, success : function () {
					comment_list();
				}, error : function (req, text) {
					alert(text + ":" + req.status);
				}
			});		
		}
	}
});



/* 보여지는 버튼 형태와 글상자 노출 변경 */
function display(mode, div) {
	div.find('.btn-modify-save').text(mode ? '수정' : '저장');
	div.find('.btn-delete-cancel').text(mode ? '삭제' : '취소');
	
	// 수정상태 : .original 안 보이게, .modify 보이게
	// 목록상태 : .original 보이게, .modify 안 보이게
	div.children('.original').css('display', mode ? 'block' : 'none');
	div.children('.modify').css('display', mode ? 'none' : 'block');
}







</script>



