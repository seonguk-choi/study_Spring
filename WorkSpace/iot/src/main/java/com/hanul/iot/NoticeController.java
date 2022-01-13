package com.hanul.iot;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import member.MemberServiceImpl;
import member.MemberVO;
import notice.NoticeServiceImpl;
import notice.NoticeVO;

@Controller
public class NoticeController {

	@Autowired private NoticeServiceImpl service;
	@Autowired private MemberServiceImpl member;
	@Autowired private CommonService common;
	
	//공지사항 글목록 화면 요청
	@RequestMapping("/list.no")
	public String logout(HttpSession session, Model model) {
		//나중에 삭제
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", "admin");
		map.put("pw", "admin");
	    session.setAttribute("loginInfo", member.member_login(map));
		
		
		session.setAttribute("category", "no");
		model.addAttribute("list", service.notice_list());
		return "notice/list"; 
	}
	
	@RequestMapping("new.no")
	//신규 공지사항 입력화면 요청
	public String notice() {
		
		return "notice/new";
	}
	
	@RequestMapping("insert.no")
	public String insert(NoticeVO vo, MultipartFile file, HttpSession session) {
		
		vo.setWriter( ((MemberVO) session.getAttribute("loginInfo")).getId());
		
		if(!file.isEmpty()) {
			//첨부 파일 처리 (DB에 저장)
			vo.setFilename(file.getOriginalFilename());
			
			//전달받은 파일의 실제 이름을 vo에 할당
			vo.setFilepath(common.fileupload("notice", file, session));
		}
		
		//화면에서 입력한 정보를 DB에 저장한 후 화면으로 연결
		
		service.notice_insert(vo);
		
		return "redirect:list.no";
		
	}
	
	//공지사항 상세화면 요청
	@RequestMapping("/detail.no")
	public String detail(int id, Model model) {
		//선택한 공지사항 정보 조회
		service.notice_readcnt(id);		
		
		model.addAttribute("vo", service.notice_detail(id));
		model.addAttribute("crlf", "\r\n");
		
		return "notice/detail";
	}
	
	//첨부파일 다운
	@RequestMapping("/download.no")
	public String download(int id) {
		NoticeVO notice = service.notice_detail(id);
		//파일 업, 다운 처리를 다른 게시판에서 하기 위해서 CommonService 에 작업
		return "";
	}
	
}
