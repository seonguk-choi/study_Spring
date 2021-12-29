package com.hanul.hello;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.MemberVO;

@Controller
public class FormController {
	
	//값을 입력받을 페이지 .. 회원가입 페이지를 생성 및 찾아가기
	@RequestMapping("/join")
	public String join() {
		return "member/join"; //forward 방식
		//return"redirect:/"; //redirect 방식 insert update delete DB변화	
	}
	
	//1. HttpServletRequest 사용 -> 잘 사용되지 않는다. 사용빈도 △
	@RequestMapping("/joinRequest")
	public ModelAndView member(HttpServletRequest request, HttpServletResponse response, Model model) {
		String name = request.getParameter("name");
		String gender =request.getParameter("gender");
		String email =request.getParameter("email");
		
//		model.addAttribute("name", name);
//		model.addAttribute("gender", gender);
//		model.addAttribute("email", email);
//		return "member/info";
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", name);
		mav.addObject("gender", gender);
		mav.addObject("email", email);
		mav.setViewName("member/info");
		
		return mav;
	}
	
	//2. @RequestParam 사용 -> @RequestParam 생략가능 사용빈도 ★
	@RequestMapping("/joinParam")
	public String member(String name, String gender,String email, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("gender", gender);
		model.addAttribute("email", email);
		model.addAttribute("method", "RequestParam 방식");		
		return "member/info";
	}
	
	//3. VO(Value Object) 사용빈도 ★★★
	@RequestMapping("/joinDataObject")
	public String member(MemberVO vo, Model model) {
		model.addAttribute("method", "데이터객체");		
		model.addAttribute("member", vo);
		return "member/info";
	}
}
