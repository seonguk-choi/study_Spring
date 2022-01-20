package com.hanul.iot;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.BoardPage;
import board.BoardServiceImpl;


@Controller
public class BoardController {
	
	@Autowired private BoardServiceImpl service;
	@Autowired private BoardPage page;
	
	//방명록 목록화면 요청
	@RequestMapping("/list.bo")
	public String list(HttpSession session, @RequestParam (defaultValue ="1") int curpage, Model model ) {
		session.setAttribute("category", "bo");
		
		//DB에서 방명록 정보를 조회해와 목록 화면에 출력
		page.setCurPage(curpage);
		
		page = service.board_list(page);
		
		model.addAttribute("page", service.board_list(page));
		
		return "board/list";
	}
}
