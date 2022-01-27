package com.hanul.iot;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import employee.EmployeeServiceImpl;
import employee.EmployeeVO;

@Controller
public class EmployeeController {
	
	@Autowired private EmployeeServiceImpl service;
	
	// 선택한 사원의 상세 정보 조회 및 화면 출력
	@RequestMapping ("/detail.hr")
	public String detail(int id, Model model) {
		// 조회 결과(EmployeeVO) 값을 vo 변수로 지정하여 detail.jsp에 전달
		model.addAttribute("vo", service.employee_detail(id) );
		return "employee/detail";
	}
	
	// 사원목록 화면 요청
	@RequestMapping("list.hr")
	public String list(HttpSession session, Model model) {
		session.setAttribute("category", "hr");
		// 전체 사원 목록 조회
		model.addAttribute("list", service.employee_list());
		return "employee/list";
	}
	
}




