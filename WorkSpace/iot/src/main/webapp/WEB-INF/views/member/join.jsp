<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
<style type="text/css">
table tr td {text-align: left;}
.addr input {margin-bottom: 5px;}
.valid, .invalid{font-size: 13px; font-weight: bold; font-style: italic;}
.valid{color : green;}
.invalid{color : red;}
</style>
</head>
<body>
<h3>회원가입</h3>
<p class='w-pct40' style='margin: 0 auto; padding-bottom: 10px; font-size: 12px; text-align:right;'>* 은 필수 입력 항목입니다.</p>
	<form action="join" method="post">
		<table class='w-pct40'>
			<tr>
				<th>*성명</th>
				<td>
					<input type="text" name="name"/>
				</td>
			</tr>
			<tr>
				<th>*아이디</th>
				<td>
					<input type="text" name="id" class='chk'/>
					<a class='btn-fill-s' id='btn-id'>아이디 중복확인</a>
					<div class='valid'>아이디를 입력하세요(영문소문자, 숫자만 입력 가능)</div>
				</td>
			</tr>
			<tr>
				<th>*비밀번호</th>
				<td>
					<input type="password" name="pw" class='chk'/>
					<div class='valid'>비밀번호를 입력하세요(영문대/소문자, 숫자를 모두 포함)</div>
				</td>
			</tr>
			<tr>
				<th>*비밀번호확인</th>
				<td>
					<input type="password" name="pw_ck" class='chk'/>
					<div class='valid'>비밀번호를 확인하세요</div>
				</td>
			</tr>
			<tr>
				<th>*성별</th>
				<td>
					<label><input type="radio" name="gender" value="남" checked/>남</label>
					<label><input type="radio" name="gender" value="여"/>여</label>
				</td>
			</tr>
			<tr>
				<th>*이메일</th>
				<td>
					<input type="text" name="email" class='chk'/>
					<div class='valid'>이메일을 입력하세요</div>
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>
					<input type="text" name="birth" readonly/>
					<a id='delete' style="display: none; color : red; position : relative; right : 40px; cursor : pointer;"><i class="fas fa-minus-circle"></i></a>
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>
					<input type="text" name="tel" maxlength="4" class="w-px40"/>
					- <input type="text" name="tel" maxlength="4" class="w-px40"/>
					- <input type="text" name="tel" maxlength="4" class="w-px40"/>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td class='addr'>
					<a class='btn-fill-s' onclick='daum_post()'>우편번호찾기</a>
					<input type="text" name="post" class="w-px80" readonly/><br/>
					<input type="text" name="addr" readonly/><br/>
					<input type="text" name="addr" />
				</td>
			</tr>
		</table>
		<div class='btnSet'>
			<a class='btn-fill' onclick="go_join()">회원가입</a>
			<a class='btn-empty' href='<c:url value="/"/>'>가입취소</a>
		</div>
	</form>
<script type="text/javascript" src="js/join_check.js"></script>	
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>	
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script type="text/javascript">
function daum_post(){
	new daum.Postcode({
	    oncomplete: function(response) {
	        // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	        // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	        // 조회된 우편번호를 입력하기 위한 제이쿼리 선언
	        
	        // name이 post인 태크의 val(값)을 받아온 변수(data) 내 zonecode 값을 담음
	        $('[name=post]').val(response.zonecode);
	        
	        // 검색된 기본 주소 타입 : R(도로명), J(지번)
	        var addr = response.userSelectType == 'J' ? response.jibunAddress : response.roadAddress;
	        
	        if(response.buildingName != "") addr += ' (' + response.buildingName + ')';
	     
	        
	        
	        
	        //name 이 addr 인 태그에 값을 할당
	        $('[name=addr]').eq(0).val(addr);	        
	        $('[name=addr]').eq(1).focus();        	       
	    }
	}).open();	
}



$( function() {
	//나이 제한을 두기 위한 처리 (만 13세 이상만 가입)
	var today = new Date(); //오늘 날짜 선언
	
	//오늘 날짜의 연도 데이터를 뽑아 13년을 뺌 월은 그래도 유지, 일은 오늘 날짜로 부터 -1
	var endDay = new Date(today.getFullYear() -13, today.getMonth(), today.getDate()-1);
	
  $('[name=birth]').datepicker({
	  dayNamesMin : ['일','월','화','수','목','금','토']
	  , monthNamesShort : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
  	  , changeMonth : true
  	  , changeYear : true
  	  , showMonthAfterYear : true
  	  , dateFormat : 'yy-mm-dd'
  	  , maxDate : endDay //달력에 나타날 최대 일자
  	  
  });
  
});

//생일이 선택되면 기호 나타남
$('[name=birth]').change(function(){
	$('#delete').css('display', 'inline');
});

// #delete 클릭시 생일 값 삭제 되면서 기호가 안 나타남
$('#delete').click(function(){
	$('[name=birth]').val('');
	$('#delete').css('display', 'none');
});

//class='chk' 에 대한 유효성 검사
$('.chk').on('keyup', function(e){
	// id 입력후에 아이디 중복확인 버튼 실행
	if($(this).attr('name') == 'id'){
		if(e.keyCode == 13) id_check();
		else $(this).removeClass('checked');
	}
	
	var data = join.tag_status ($(this)); //입력하고 있는 tag의 값을 data에 할당
	
	// 반환된 결과 값(data) 엔 code와 desc 가 있음.
	$(this).siblings('div').text(data.desc).removeClass().addClass(data.code);
	
});

$('#btn-id').on('click', function(){
	id_check();
});

function id_check(){
	var $id = $('[name=id]');
	
	//class가 checked 가 있다면 리턴
	if($id.hasClass('checked')) return;
	
	var data = join.tag_status($id);
	
	if(data.code == 'invalid') {
		alert('아이디 중복확인 불필요\n' + data.desc);	
		$id.focus();
		return;
	} else { //DB에서 id 값을 가져와 중복확인
		$.ajax({
			url : 'id_check'
			, data : {id:$id.val()}
			, success : function(response){//true : 사용 가능, false : 중복된 아이디
				var data = join.id_usable(response); //성공시 값이 있으면
				$id.siblings('div').text(data.desc).removeClass().addClass(data.code);
				$id.addClass('checked');
			}
			, error : function(req, text){
				alert(text + ";" + teq.status);
			}
		});
	}
}

function go_join() {
	if($('[name=name]').val() == ''){
		//alert('이름을 입력하세요.');
		$('[name=name]').focus;
		return;	
	} 
	//중복확인을 하여 이미 사용 중인 경우
	if($('[name=id]').hasClass('checked')){
		if(($('[name=id]').siblings('div').hasClass('invalid'))){
			alert('회원가입 불가! \n' + join.id.unUsable.desc);
			$('[name=id]').focus;
			return;
		}	
	} else {
		//중복확인 하지 않은 경우
		if(!item_check($('[name=id]'))) return;
		else {
			alert('회원가입 불가 \n' + join.id.valid.desc)
			$('[name=id]').focus();
			return;
		}
	}
	if(!item_check($('[name=pw]'))) return;
	if(!item_check($('[name=pw_ck]'))) return;
	if(!item_check($('[name=email]'))) return;
	
	$('form').submit();
}

//유효성 검사 항목을 체크할 함수
function item_check(item){
	var data = join.tag_status(item);
	if(data.code == 'invalid'){
		alert('회원가입 불가 \n' + data.desc);
		item.focus();
		return false;
	} else {
		return true;	
	}
}

</script>	
</body>
</html>