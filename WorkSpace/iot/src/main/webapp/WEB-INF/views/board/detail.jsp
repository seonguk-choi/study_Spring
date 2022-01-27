<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#popup { width: 450px; height: 450px; border: 2px solid #666; display: none; }
	#popup-background {
		position: absolute; left:0; top:0; width: 100%; height: 100%;
		background-color: #000; opacity: 0.3; display: none;
	}
	.popup { width: 100%; height: 100%;}
	.comment { margin: 0 auto; padding-top: 20px; width: 500px; }
	#comment_regist { width: 100%}
	#comment_regist span {width: 50%; float: left;}
	textarea#comment { width: 96%; height: 60px; margin-top: 5px; resize: none; }	
	
</style>


</head>
<body>
<h3>방명록</h3>
<table>
	<tr>
		<th class='w-px120'>제목</th>
		<td colspan="5" class='left'>${vo.title }</td>
	</tr>
	<tr>
		<th class='w-px120'>작성자</th>
		<td class='left'>${vo.name }</td>
		<th class='w-px120'>작성일자</th>
		<td class='w-px120'>${vo.writedate }</td>
		<th class='w-px80'>조회수</th>
		<td class='w-px80'>${vo.readcnt }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="5" class='left'>${fn:replace(vo.content, crlf, '<br>')}</td>
	</tr>
	<tr>
		<th class='w-px120'>첨부파일</th>
		<td colspan="5" class='left'>${vo.filename }
			<!-- 파일이 있는 경우에만 파일 정보를 나타냄 -->
			<!-- 페이지 이동하는 형태가 아니므로 id 값을 get 방식으로 전달 -->
			<c:if test="${ ! empty vo.filename }">
				<a id='preview'></a>
				<a href='download.bo?id=${vo.id}'><i class='fas fa-download font-img' ></i></a>
			</c:if>
		</td>
	</tr>
	<tr>
		<td class='left' colspan="6">
			<c:if test="${vo.prev ne 0}">
				<a href='detail.bo?id=${vo.prev}'>[ 이전 글] ${vo.prev_title }</a><br />
			</c:if>
			<c:if test="${vo.next ne 0}">
				<a href='detail.bo?id=${vo.next}'>[ 다음 글] ${vo.next_title }</a>
			</c:if>
		</td>
	</tr>
</table>

<div class='btnSet'>
	<a class='btn-fill' onclick='$("form").submit()'>목록으로</a>
	<!-- 글쓴이만 수정/삭제 권한을 가짐 -->
	<c:if test="${vo.writer eq loginInfo.id }">
		<a class='btn-fill' onclick='$("form").attr("action", "modify.bo"); $("form").submit()' >수정</a>
		<a class='btn-fill' onclick='if ( confirm("정말 삭제?") ) {href="delete.bo?id=${vo.id }" }' >삭제</a>
	</c:if>
</div>

<div class='comment'> 
	<div id='comment_regist'> <!-- 댓글 등록 부분 -->
		<span class='left'><strong>댓글작성</strong></span>
		<span class='right'><a class='btn-fill-s' onclick='comment_regist()'>댓글등록</a></span>
		<textarea id='comment'></textarea> <!-- 글을 작성할 부분 -->	
	</div>
	<div id='comment_list'></div>
</div>


<!-- 목록 요청에 필요한 데이터를 post 방식으로 전달하는 방법 -->
<form action="list.bo" method="post">
	<input type="hidden" name="id" value="${vo.id }" />	<!-- 해당 글의 id  -->
	<input type="hidden" name="search" value="${page.search }" /> <!-- 검색조건 -->
	<input type="hidden" name="keyword" value="${page.keyword }" /> <!-- 검색어 -->
	<input type="hidden" name="curPage" value="${page.curPage }" /> <!-- 현재 페이지 -->
	<input type="hidden" name="pageList" value="${page.pageList }" /> <!-- 한 페이지당 보여질 목록 수 -->
	<input type="hidden" name="viewType" value="${page.viewType }" /> <!-- 게시판 형태 -->
</form>

<!-- 이미지를 크게 볼 수 있도록 처리 (popup 형태) -->
<div id='popup-background'></div>
<div id='popup' class='center'></div>

<script type="text/javascript" src='js/file_check.js' ></script>
<script type="text/javascript">
// 댓글 등록하기
function comment_regist() {
	if ( ${empty loginInfo } ) { // 로그인 정보가 없으면
		alert('댓글을 등록하려면 로그인하세요!');
		return;
	} else if ( $.trim( $('#comment').val() ) == '' ) { // 로그인은 되어 있는데 댓글을 적지 않았다면
		alert('댓글을 입력하세요!');
		$('#comment').val('');
		$('#comment').focus();
		return;
	}
	
	$.ajax ({
		/* 경로 형태로 url 지정 */
		url: 'board/comment/regist'
		, data : { pid : ${vo.id} , content: $('#comment').val()}
			/* 원 글의 id, 입력한 댓글을 데이터로 보냄 */
		, success : function( response ) {
			if ( response ) {	// true
				alert('댓글이 등록되었습니다.');
				$('#commnet').val('');
				comment_list();			// 댓글 목록 조회 요청
			} else	// false
				alert('댓글 등록 실패!');
		}, error : function (req, text) {
			alert (text + ':' + req.status);
		}
	});
}
function comment_list() {
	$.ajax({
		url : 'board/comment/list/${vo.id}'
		// , data : { pid:801 }
		, success : function ( response ) {
			$('#comment_list').html( response );
		// 성공한 결과를 #comment_list 영역에 출력하는데 
		// 결과는 html 코드로 작성된 text 이므로
		// .html( response ); 형태로 값을 출력할수 있도록 지정
		}, error : function (req, text) {
			alert(text + ':' + req.status);
		}
	});
}



</script>
<script type="text/javascript">
$(function () { // $(document).ready() 와 같은 의미
	// 첨부된 파일이 이미지 파일인 경우 미리보기 함.
	if ( ${ ! empty vo.filename}) {	// 첨부 파일이 있는 경우
		if ( isImage( '${vo.filename}' ) ) {	// 이미지 파일인 경우
			$('#preview').html("<img src='${vo.filepath}' id='preview-img' />");
		}		
	}
	comment_list();		// 댓글 목록 조회 함수 호출
});

$(document).on('click', '#preview-img', function () {
	$('#popup, #popup-background').css('display', 'block');
	$('#popup').html("<img src='${vo.filepath}' class='popup' />");
}).on('click', '#popup-background', function () {
	$('#popup, #popup-background').css('display', 'none');
});

</script>


</body>
</html>













