var join = {
	//tag의 상태를 확인 할 수 있는 함수를 선언(tag_status)하고 받아오는 값은 tag 변수로 설정
	tag_status : function ( tag ){
		var data = tag.val(); //tag 내 입력된 값을 data 변수에 할당
		tag = tag.attr('name'); //tag의 name 속성의 값을 확인 ex) id, pw, email...
		
	//tag에 입력한 값은 data에 들어있는데 tag 의 name 속성의 값으로  id,  pw, pw_chk, email
	//인지를 구분(판단)하여 유효성 검사 진행하고자 함
		if(tag == 'id') return this.id_status(data);	
		else if(tag == 'pw') return this.pw_status(data);	
	 	else if(tag == 'pw_ck') return this.pw_ck_status(data);	
		else if(tag == 'email') return this.email_status(data);	
	}
	, id_status : function(id){ //id 상태 확인
		var reg = /[^a-z0-9]/g;
	
		//입력값이 없을 경우
		if(id == '') return this.common.empty;
		
		//입력값에 공백이 있을 경우
		else if(id.match(space)) return this.common.space;
		
		//최대 10자 이하
		else if(id.length > 10) return this.common.max;
		
		//최소 5자 이상
		else if(id.length < 5) return this.common.min;
		
		//소문자, 숫자 외 문자 입력여부 확인
		else if(reg.test(id)) return this.id.invalid;
		
		//정규표현식 성공
		else return this.id.valid;
		
	}
	, pw_status : function(pw){
		var reg = /[^A-Za-z0-9]/g;
		var upper = /[A-Z]/g, lower = /[a-z]/g, digit = /[0-9]/g;
		
		//입력값이 없을 경우
		if(pw == '') return this.common.empty;
		
		//입력값에 공백이 있을 경우
		else if(pw.match(space)) return this.common.space;
		
		//최대 10자 이하
		else if(pw.length > 10) return this.common.max;
		
		//최소 5자 이상
		else if(pw.length < 5) return this.common.min;
		
		//대소문자, 숫자 외 문자 입력여부 확인
		else if(reg.test(pw)) return this.pass.invalid;
		
		//대소문자, 숫자 혼합여부
		else if(!upper.test(pw) ||!lower.test(pw) || !digit.test(pw)) return this.pass.lack;
		
		//정규표현식 성공
		else return this.pass.valid;
		
	}
	, pw_ck_status : function(pw_ck){
		var reg = /[^a-zA-Z0-9]/g;
		
		//입력값이 없을 경우
		if(pw_ck == '') return this.common.empty;
		
		else if(pw_ck == $('[name=pw]').val()) return this.pass.equal;
		
		else if(pw_ck != $('[name=pw]').val()) return this.pass.notequal;
		
	}
	, email_status : function(email){
		var reg =  /^([\w\.\_\-])*[a-zA-Z0-9]+([\w\.\_\-])*([a-zA-Z0-9])+([\w\.\_\-])+@([a-zA-Z0-9]+\.)+[a-zA-Z0-9]{2,8}$/g;
		
		//입력값이 없을 경우
		if(email == '') return this.common.empty;
		
		//입력값에 공백이 있을 경우
		else if(email.match(space)) return this.common.space;
		
		else if(!reg.test(email)) return this.email.invalid;
		
		//정규표현식 성공
		else return this.email.valid;
				
	}
	, id_usable : function( useable){
		if(useable){
			return this.id.usable;
		} else {
			return this.id.unUsable;			
		}
	}
	, id : {//id의 기준 설정
		invalid : {code : 'invalid', desc : '아이디는 영문 소문자, 숫자만 입력 가능'}
		, valid : {code : 'valid', desc : '아이디 중복을 확인하세요'}												
		, usable : {code : 'valid', desc : '사용가능한 아이디입니다.'}
		, unUsable : {code : 'invaild', desc : '이미 사용 중인 아이디입니다.'}
	}
	, pass : {
		invalid : {code : 'invalid', desc : '비밀번호는 영문대/소문자, 숫자을 혼합하여 입력 가능'}
		, valid : {code : 'valid', desc : '사용가능한 비밀번호 입니다.'}
		, lack : {code : 'invalid', desc : '영문 대/소문자 숫자를 모두 입력하세요.'}
		, notequal : {code : 'invalid', desc : '비밀번호가 일치하지 않습니다.'}
		, equal : {code : 'valid', desc : '비밀번호가 일치합니다.'}
	}
	, email : {
		invalid : {code : 'invalid', desc : '이메일이 올바르지 않습니다.'}
		, valid : {code : 'valid', desc : '사용가능한 이메일 입니다.'}
	}
	, common : { // 공통적으로 검사를 해야하는 항목 지정
		empty :{code : 'invalid', desc : '입력하세요'}
		, space : {code : 'invalid', desc : '공백없이 입력하세요'}
		, max : {code : 'invalid', desc : '최대 10자 이하로 입력하세요'}
		, min : {code : 'invalid', desc : '최소 5자 이상 입력하세요'}
	}	
}

var space = /\s/g; //전체 문자 내 공백이 있는지 정규표현식