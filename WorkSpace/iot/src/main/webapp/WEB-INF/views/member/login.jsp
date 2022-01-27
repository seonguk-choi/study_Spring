<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#login { width: 70%; padding: 50px 0; float: left; }
img.social { width : 200px; height: 45px; }
#userid, #userpw { width: 48%; padding: 5px 10%; margin-bottom: 10px;}
.join {float: right; width: 30%; padding: 210px 0; background: #c2c2c2;}
/* .join div {position: absolute;top: 50%; left: 85%; transform: translate(-50%, -50%); } */
</style>
</head>

<body>
<!-- <h3>로그인 페이지</h3> -->
<div style="width: 700px; border: 1px solid #ccc;" class='center'>
	<div id='login'>
		<div style='height: 70px;'>
			<a href='<c:url value="/" />'><img src="imgs/hanul.logo.png"></a>
		</div>
		<div>
			<a href='naverLogin'><img src='imgs/naver_login.png' class='social' /></a>
			<a href='kakaoLogin'><img src='imgs/kakao_login.png' class='social' /></a>
			<div style="width: 80%; margin: 25px auto; border: 1px solid #ccc"></div>
				<input type="text" placeholder="아이디" id='userid' autofocus />
				<input type="password" placeholder="비밀번호" id='userpw'
					onkeypress="if (event.keyCode == 13) { go_login() }" />
					<!-- onkeypress를 통해 Enter 키를 눌렀을 때 go_login() 동작 -->
		</div>
				<a class='btn-fill' onclick="go_login()">로그인</a>
			
	</div>
	<div class='join'>
		<div>Hello, Guest!<br>
			<a class="btn-fill">회원가입</a>
		</div>
	</div>
</div>

<script type="text/javascript">
function go_login() {
	if($('#userid').val() == '') { // 아이디 입력값이 없으면
		alert('아이디를 입력하세요!');
		$('#userid').focus();
		return;
	} else if ($('#userpw').val() == '') { // 비밀번호 입력값이 없으면
		alert('비밀번호를 입력하세요!');
		$('#userpw').focus();
		return;
	}
	
	$.ajax({
		url : 'iotLogin'
		, data : {id:$('#userid').val(), pw:$('#userpw').val()}
		, success: function( response ) {
			if (response) {
				location = '<c:url value="/" />';
			} else {
				alert('아이디나 비밀번호가 일치하지 않습니다.');
			}
		}, error : function (req, text) {
			alert(text + ':' + req.status);
		}
	});
	
}





</script>














</body>
</html>