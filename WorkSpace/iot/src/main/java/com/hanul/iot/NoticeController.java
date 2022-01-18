package com.hanul.iot;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import member.MemberServiceImpl;
import member.MemberVO;
import notice.NoticePage;
import notice.NoticeServiceImpl;
import notice.NoticeVO;

@Controller
public class NoticeController {

	@Autowired private NoticeServiceImpl service;
	@Autowired private MemberServiceImpl member;
	@Autowired private CommonService common;
	@Autowired private NoticePage page;
	
	//공지사항 글목록 화면 요청
	@RequestMapping("/list.no")
	public String logout(HttpSession session, @RequestParam(defaultValue = "1") int curPage, Model model) {
		//나중에 삭제
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", "admin");
		map.put("pw", "admin");
	    session.setAttribute("loginInfo", member.member_login(map));
		
		session.setAttribute("category", "no");
		//model.addAttribute("list", service.notice_list());
		
		//현재 페이지에 대한 정보를 담기 위한 처리
		page.setCurPage(curPage);
		
		model.addAttribute("page", service.notice_list(page));
		
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
	public String download(int id, HttpSession session, HttpServletResponse response) {
		//파일 업, 다운 처리를 다른 게시판에서 하기 위해서 CommonService 에 작업
		NoticeVO notice = service.notice_detail(id);
		
		//해당 공지글의 첨부파일 정보를 DB에서 파일을 다운로드
		common.fileDonwload(notice.getFilename(), notice.getFilepath(), session, response);
		
		return "";
	}
	
	//공지글 수정
	@RequestMapping("/modify.no")
	public String modify(int id, Model model) {
		model.addAttribute("vo", service.notice_detail(id));
		return "notice/modify";
	}
	
	//공지글 삭제
	@RequestMapping("/delete.no")
	public String delete(int id, HttpSession session) {
		//첨부파일이 있는 글의 경우 디스크에서 첨부파일을 삭제
		//공지글에 대한 모든 정보 조회
		NoticeVO notice = service.notice_detail(id);
		String uuid = session.getServletContext().getRealPath("resources")+ "/" + notice.getFilepath();
		//파일명 또는 파일 경로가 있는지 판단(없지 않다면)
		if(notice.getFilename() != null) {
			// 디렉토리 접근 권한을 가진 File 개체를 통해 파일 위치 할당
			File file = new File(uuid);
			if( file.exists()) {
				file.delete();
			}
		}
		
		service.notice_delete(id);
		return "redirect:list.no";
	}
	
	@RequestMapping("/update.no")
	public String update(NoticeVO vo, String attach, MultipartFile file, HttpSession session) {
		
		//원래 공지글의 첨부파일 정보 조회해 온다.
		NoticeVO notice = service.notice_detail(vo.getId());
		String uuid = session.getServletContext().getRealPath("resources") + "/" + notice.getFilepath();
		
		//원래 파일이 있는 경우 삭제하고 파일 변경
		if(!file.isEmpty()) {
			//원래 첨부파일이 없는데 수정시 첨부되는 경우
			vo.setFilename(file.getOriginalFilename());
			vo.setFilepath(common.fileupload("notice", file, session));
			
			//원래 첨부파일이 있었고 수정시 변경하여 첨부한 경우 - 원래 파일을 물리적 영역
			//원래 첨부파일이 있는데 물리적인 디스크에서 해당 파일 삭제
			if(notice.getFilename() != null) {
				//파일 정보를 file 형태의 f변수에 할당
				File f = new File(uuid);
				
				//기존 첨부파일이 있다면 삭제
				if(f.exists()) f.delete();
			}
		} else {
			//파일을 첨부하지 않은 경우
			if(attach.isEmpty()) {
				//원래 첨부된 파일이 있었는데 삭제한 경우
				if(notice.getFilename() != null) {
					//원래 첨부된 파일이 있다면 물리 디스크의 파일삭제
					File f = new File(uuid);
					if(f.exists()) f.delete();
				}
			} else {
				//원래부터 첨부하지 않았고 수정시에도 첨부하지 않은 경우
				vo.setFilename(notice.getFilename());
				vo.setFilepath(notice.getFilepath());
			}	
		}
		
		service.notice_update(vo);
		return "redirect:detail.no?id="+ vo.getId();
	}
}
