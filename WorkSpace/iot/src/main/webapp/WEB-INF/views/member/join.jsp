<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>
<style type="text/css">
table tr td {text-align: left;}
.addr input {margin-bottom: 5px;}
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
					<input type="text" name="id"/>
					<a class='btn-fill-s' id='btn-id'>아이디 중복확인</a>
				</td>
			</tr>
			<tr>
				<th>*비밀번호</th>
				<td>
					<input type="password" name="pw"/>
				</td>
			</tr>
			<tr>
				<th>*비밀번호확인</th>
				<td>
					<input type="password" name="pw_ck"/>
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
					<input type="text" name="email"/>
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>
					<input type="text" name="birth"/>
					<a id='delete' style="display: none; color : red; position : relative; , right : 40px; cursor: pointer;"><i class="fas fa-minus-circle"></i></a>
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
					<input type="text" name="post" class="w-px80"/><br/>
					<input type="text" name="addr" /><br/>
					<input type="text" name="addr" />
				</td>
			</tr>
		</table>
		<div class='btnSet'>
			<a class='btn-fill' onclick="go_join()">회원가입</a>
			<a class='btn-empty' onclick="<c:url value="/"/>">가입취소</a>
		</div>
	</form>
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
	        $('[name=post]').attr('readonly', true);
	        $('[name=addr]').eq(0).attr('readonly', true);
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
  
} );

</script>	

</body>
</html>