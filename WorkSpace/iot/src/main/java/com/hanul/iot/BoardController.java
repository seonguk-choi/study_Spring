package com.hanul.iot;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import board.BoardPage;
import board.BoardServiceImpl;
import board.BoardVO;
import common.CommonService;
import member.MemberServiceImpl;
import member.MemberVO;
import notice.NoticeVO;

@Controller
public class BoardController {
	
	@Autowired private BoardServiceImpl service;
	@Autowired private MemberServiceImpl member;
	@Autowired private CommonService common;
	@Autowired private BoardPage page;
	
	// 방명록 목록화면 요청
	@RequestMapping ("/list.bo")
	public String list(HttpSession session, Model model
						, @RequestParam (defaultValue = "1") int curPage, String search, String keyword
						, @RequestParam (defaultValue ="10")int pageList
						, @RequestParam(defaultValue="list") String viewType) {
		
		session.setAttribute("category", "bo");
		
		// DB에서 방명록 정보를 조회해와 목록화면에 출력
		page.setCurPage(curPage);	// 현재 페이지를 담음
		page.setSearch(search);
		page.setKeyword(keyword);
		page.setPageList(pageList);
		page.setViewType(viewType);
		
		model.addAttribute("page", service.board_list(page));		
		return "board/list";
	}
	
	//신규 방명록 입력화면 요청
	@RequestMapping("new.bo")
	public String notice() {
		
		return "board/new";
	}
	
	//신규 방명록 등록
	@RequestMapping("/insert.bo")
	public String insert(BoardVO vo, MultipartFile file, HttpSession session) {
		
		vo.setWriter( ((MemberVO) session.getAttribute("loginInfo")).getId());
		
		if(!file.isEmpty()) {
			//첨부 파일 처리 (DB에 저장)
			vo.setFilename(file.getOriginalFilename());
			
			//전달받은 파일의 실제 이름을 vo에 할당
			vo.setFilepath(common.fileupload("board", file, session));
		}
		
		//화면에서 입력한 정보를 DB에 저장한 후 화면으로 연결
		
		service.board_insert(vo);
		
		return "redirect:list.bo";
		
	}
	
	//방명록 상세 정보
	@RequestMapping("/detail.bo")
	public String detail(int id, Model model) {
		
		service.board_read(id);
		
		model.addAttribute("vo", service.board_detail(id));
		model.addAttribute("crlf", "\r\n");
		
		
		return "board/detail";
	}
	
	//파일 다운로드
	@RequestMapping("/download.bo")
	public void download(int id, HttpSession session, HttpServletResponse response) {
		// 첨부파일 조회
		BoardVO vo = service.board_detail(id);
		
		// 다운로드
		common.fileDonwload(vo.getFilename(), vo.getFilepath(), session, response);
		
	}
	
}
