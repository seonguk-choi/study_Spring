package com.hanul.iot;

import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.CommonService;
import member.MemberServiceImpl;
import member.MemberVO;

@Controller
public class MemberController {
	
	@Autowired private MemberServiceImpl service;
	@Autowired private CommonService common;	
	
	private String naver_client_id = "tKFERuAp7YT7bM2YR2Ag";
	
	// 회원 가입 처리 요청
	@RequestMapping ("/join")
	public String join (MemberVO vo) {
		
		service.member_join(vo);
		
		return "redirect:/";
	}
	
	
	
	// 아이디 중복확인 요청
	@ResponseBody
	@RequestMapping("/id_check")
	public boolean id_check(String id) {
		return service.member_id_check(id);
	}
	
	
	// 회원 가입 페이지 요청
	@RequestMapping ("/member")
	public String member(HttpSession session) {
		session.setAttribute("category", "join");
		return "member/join";
	}
	
	
	// 네이버 로그인 요청
	@RequestMapping ("/naverLogin")
	public String naverLogin(HttpSession session) {
		
		// 3.4.2 네아로 연동 URL 생성하기
		String state = UUID.randomUUID().toString();
		
		session.setAttribute("state", state);

		// https://nid.naver.com/oauth2.0/authorize?response_type=code
		// &client_id=CLIENT_ID
		// &state=STATE_STRING
		// &redirect_uri=CALLBACK_URL

		StringBuffer url = new StringBuffer("https://nid.naver.com/oauth2.0/authorize?response_type=code");
		url.append("&client_id=").append(naver_client_id);
		url.append("&state=").append(state);
		url.append("&redirect_uri=http://localhost/iot/navercallback");
		return "redirect:" + url.toString();
	}
	
	// 동의하기 버튼 클릭시 데이터를 가져오는 처리를 해야 하는데 
	// redirect 에 의해 주소가 변경됨.
	
	// 네이버 콜백 메소드 선언
	// 3.4.3 네이버 로그인 연동 결과 Callback 정보
	// API 요청 성공시 : http://콜백URL/redirect?code={code값}&state={state값}
	// API 요청 실패시 : http://콜백URL/redirect?state={state값}&error={에러코드값}
	//									&error_description={에러메시지}
	@RequestMapping("/navercallback")
	public String navercallback(@RequestParam(required = false) String code, 
					HttpSession session, String state, 
					@RequestParam(required = false) String error) {
		
		// 상태 토큰이 일치하지 않거나 콜백 실패시 
		// 오류가 발생시 토큰 발급 불가 (정상처리 되지 않음)
		
		if ( ! state.equals(session.getAttribute("state")) || error != null ) {
			// state 값이 맞지 않거나 error가 발생해도 토큰 발급 불가
			return "redirect:/";
		}
		
		// 3.4.4 접근 토큰 발급 요청
		// https://nid.naver.com/oauth2.0/token?grant_type=authorization_code
		// &client_id=jyvqXeaVOVmV
		// &client_secret=527300A0_COq1_XV33cf
		// &code=EIc5bFrl4RibFls1
		// &state=9kgsGTfH4j7IyAkg  

		StringBuffer url = new StringBuffer("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code");
		url.append("&client_id=").append(naver_client_id);
		url.append("&client_secret=07wh8Uv8ys");
		url.append("&code=").append(code);
		url.append("&state=").append(state);
		
		// 3.4.5 접근 토큰을 이용하여 프로필 API 호출하기
		JSONObject json = new JSONObject(common.requstAPI(url));
		String token = json.getString("access_token");
		String type = json.getString("token_type");
		
		// curl  -XGET "https://openapi.naver.com/v1/nid/me" \
	    // -H "Authorization: Bearer AAAAPIuf0L+qfDkMABQ3IJ8heq2mlw71DojBj3oc2Z6OxMQESVSrtR0dbvsiQbPbP1/cxva23n7mQShtfK4pchdk/rc="
		
		url = new StringBuffer("https://openapi.naver.com/v1/nid/me");
		json = new JSONObject( common.requestAPI(url, type + " " + token) );		
		
//		{
//			  "resultcode": "00",
//			  "message": "success",
//			  "response": {
//			    "email": "openapi@naver.com",
//			    "nickname": "OpenAPI",
//			    "profile_image": "https://ssl.pstatic.net/static/pwe/address/nodata_33x33.gif",
//			    "age": "40-49",
//			    "gender": "F",
//			    "id": "32742776",
//			    "name": "오픈 API",
//			    "birthday": "10-01"
//			  }
//			}
		
		if (json.getString("resultcode").equals("00")) {
			json = json.getJSONObject("response");
			
			// 회원 정보 DB에 값을 담아서 관리 : MemberVO
			MemberVO vo = new MemberVO();
			vo.setSocial_type("naver");
			vo.setSocial_email(json.getString("email"));
			vo.setId(json.getString("id"));
			vo.setName(json.getString("name"));
			vo.setGender(json.has("gender") && json.getString("gender").equals("F") ? "여" : "남");
			
			// 네이버 최초 로그인인 경우 회원정보 저장(insert)
			// 네이버 로그인 이력이 있어 회원정보가 있다면 변경 저장
			if (service.member_social_email(vo))
				service.member_social_update(vo);
			else 
				service.member_social_insert(vo);
			
			session.setAttribute("loginInfo", vo);
		}
		
		return "redirect:/"; // 로그인 시 루트(home.jsp)로 이동
	}
	
	
	// 로그아웃 처리 요청
	@RequestMapping ("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginInfo");
		return "redirect:/"; // 로그아웃시 루트(home.jsp)로 이동
	}
	
	// 로그인 처리 요청
	@ResponseBody
	@RequestMapping ("/iotLogin")
	public boolean login(String id, String pw, HttpSession session) {
		// 화면에서 전송한 아이디, 비밀번호가 일치하는 회원정보를 DB에서 조회
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		MemberVO vo = service.member_login(map);
		session.setAttribute("loginInfo", vo);
		return vo == null ? false : true;
		// 결과값이 null 이면 false / null 이 아니면 true
	}
	
	// 로그인 화면 요청
	@RequestMapping ("/login")
	public String login(HttpSession session) {
		session.setAttribute("category", "login");
		return "member/login";
	}
	
}
