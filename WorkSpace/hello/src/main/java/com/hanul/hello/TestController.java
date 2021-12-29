package com.hanul.hello;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//컨트롤러 객체 생성
@Controller
public class TestController {
	
	//어떤 요청에 대한 연결할 것인지 지정 @RequestMapping()
	//어노테이션이 올바르게 되었을때와 아닐때의 차이는 
	//404에러 메세지를 통해 확인
	@RequestMapping("/first")
	public String view(Model model) {
		//포멧 형태 날짜로 지정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		String today = sdf.format(new Date());
		
		//화면의 출력할 데이터를 Model 타입의 atttribute로 담는다.
		model.addAttribute("today", today); //어떤 데이터를 어떻게 담을지 지정
		
		return "index"; //전환 화면 페이지명 기재
	}
	
	@RequestMapping("/second")
	public ModelAndView second() {
		//ModelAndView 를 활용하여 화면 전환 및 데이터 표시
		
		ModelAndView model = new ModelAndView();
		SimpleDateFormat sdf = new SimpleDateFormat("a hh시 mm분 ss초");
		String now = sdf.format(new Date());
		
		model.addObject("now", now);
		model.setViewName("index");
		return model;		
	}
}
