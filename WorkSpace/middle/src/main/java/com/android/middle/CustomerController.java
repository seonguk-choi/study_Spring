package com.android.middle;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import customer.CustomerDAO;
import customer.CustomerVO;

// 고객관리 페이지처리 @Controller 생성
@Controller
public class CustomerController {
	
	@Autowired private CustomerDAO service;	
	Gson gson = new Gson();
	@RequestMapping("/delete.cu")
	public void delete (HttpServletRequest req) {
		// 선택한 고객 정보를 DB에서 삭제한 후 목록 화면으로 연결
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			System.out.println(req.getParameter("id"));
			service.customer_delete(id);			
		} catch (Exception e) {
			System.out.println("값이 안들어 옴");
		}
		
	}
	
	// 고객 수정 정보 저장
	@RequestMapping ("/update.cu")
	public void update(HttpServletRequest req) {
		String strVo = req.getParameter("vo");
	    CustomerVO vo = gson.fromJson(strVo, CustomerVO.class);
		service.customer_update(vo);
	}
	
	// 고객정보 수정화면 요청
	@RequestMapping ("/modify.cu")
	public String modify(int id, Model model) {
		// 화면의 해당 고객정보를 조회해와 수정화면에 출력
		model.addAttribute("vo", service.customer_detail(id));
		return "customer/modify";
	}
	
	// 신규 고객 등록
	@RequestMapping ("/insert.cu")
	public String customer_insert(CustomerVO vo) {
		// 화면에서 입력한 정보를 DB에 저장한 후 목록화면으로 연결
		service.customer_insert(vo);
		return "redirect:list.cu";
		// 등록 이후 전체 회원 목록 조회를 통해 갱신 필요
	}
	
	
	// 신규 고객 등록 화면 요청
	@RequestMapping ("/new.cu")
	public String new_customer() {
		return "customer/new";
	}
	
	// 고객 상세 정보 화면 요청
	@RequestMapping ("/detail.cu")
	public String detail(int id, Model model) {
		// 선택한 고객 정보를 DB에서 조회
		model.addAttribute("vo", service.customer_detail(id));
		return "customer/detail";
	}
	
	@ResponseBody
	@RequestMapping ("/list.cu")
	public void list(HttpServletRequest req , HttpServletResponse res) throws IOException {
		//name, email, phone, 어떤 검색어를 넣어도 가능
		String search = req.getParameter("search");
		System.out.println(search);
		List<CustomerVO> list = service.customer_list(search);
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(list));
	}
}
