package com.hanul.iot;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import member.MemberServiceImpl;
import member.MemberVO;

@Controller
public class MemberController {

	@Autowired private MemberServiceImpl service;
	
	//로그인 화면 요청
	@RequestMapping("/login")
	public String login() {
		return "member/login";
	}
	
	//로그인 처리요청
	@ResponseBody
	@RequestMapping("/iotLogin")
	public boolean login(String id, String pw, HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);	
		MemberVO vo = service.member_login(map);
		session.setAttribute("loginInfo", vo);

		return vo == null ? false : true;	
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginInfo");
		
		//로그아웃시 루트(home.jsp)으로 리셋해서 이동
		//정보가 변해서 다시 돌아가면 redirect로 보낸다.
		return "redirect:/"; 
	}
	
}
