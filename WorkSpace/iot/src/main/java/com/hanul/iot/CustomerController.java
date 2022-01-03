package com.hanul.iot;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import customer.CustomerServiceImpl;
import customer.CustomerVO;


// 고객관리 페이지처리 @Controller 생성
@Controller
public class CustomerController {
	
	@Autowired private CustomerServiceImpl service;
	
	//클라이언트 - 컨드롤러 - 서비스 - DAO - 맴퍼 순으로 연결
	
	// 고객 관리 목록
	@RequestMapping("/list.cu")
	
	public String list(HttpSession session, Model model) {
		
		session.setAttribute("category", "cu");
		
		List<CustomerVO> list = service.customer_list();
		
		model.addAttribute("list", list);
		
		return "customer/list";
	}
	
	
	//고객 상세 정보
	@RequestMapping("/detail.cu")
	public String detail(int id, Model model) {
		//선택한 고객 정보를 DB에서 조회
		
		model.addAttribute("vo", service.customer_detail(id));
		
		return "customer/detail";
	}
	
	@RequestMapping("/new.cu")
	public String new_customer(Model model) {
		return "customer/new";
	}
}