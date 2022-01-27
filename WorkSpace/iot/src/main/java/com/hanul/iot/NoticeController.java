package com.hanul.iot;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	// 답글 저장 처리 요청
	@RequestMapping ("/reply_insert.no")
	public String reply_insert(NoticeVO vo, MultipartFile file, HttpSession session) {
		// 첨부 파일이 있을 경우
		if ( ! file.isEmpty() ) {
			vo.setFilename(file.getOriginalFilename());
			vo.setFilepath( common.fileupload("notice", file, session) );
		}
		
		// 로그인 된 사용자의 id를 가져와 글쓴이(writer)에 담기 위한 처리
		vo.setWriter( ((MemberVO) session.getAttribute("loginInfo")).getId() );
		service.notice_reply_insert(vo);
		return "redirect:list.no";
	}
	
	// 공지글 답글 작성 화면 요청
	@RequestMapping ("/reply.no")
	public String reply(int id, Model model) {
		// 원글의 상세 정보를 DB에서 조회하여 답글 화면에 출력
		model.addAttribute("vo", service.notice_detail(id));
		return "notice/reply";
	}
	
	
	// 공지글 수정 저장 처리 요청
	@RequestMapping ("/update.no")
	public String update(NoticeVO vo, String attach, MultipartFile file
					, HttpSession session) {
		
		// 원래 공지글의 첨부파일 정보를 조회해 온다.
 		NoticeVO notice = service.notice_detail(vo.getId());
		String uuid = session.getServletContext().getRealPath("resources") + "/" + notice.getFilepath(); 
		
		// 원래 파일이 첨부된 경우 이전 파일을 삭제하고 변경한 파일을 저장
		// 파일을 첨부한 경우
		if ( ! file.isEmpty() ) {
			// 원래 첨부 파일이 없었는데 수정시 첨부한 경우
			vo.setFilename(file.getOriginalFilename());
			vo.setFilepath( common.fileupload("notice", file, session) );
			
			// 원래 첨부된 파일이 있었는데 물리적인 디스크에서 해당 파일 삭제
			// 원래 첨부 파일이 있었고 수정시 변경하여 첨부한 경우 - 원래 파일을 물리적 영역에서 삭제
			if ( notice.getFilename() != null ) { // 서버에 파일이 있는지 파악
				File f = new File(uuid); 	// 파일 정보를 File 형태의 f 변수에 할당
				if (f.exists() ) f.delete(); // 기존 첨부 파일이 있다면 삭제
			}
		} else {
		// 파일을 첨부하지 않은 경우
			// 원래부터 첨부하지 않았고 수정시에도 첨부하지 않은 경우
			// 원래 첨부된 파일이 있었는데 삭제한 경우
			if ( attach.isEmpty()) {
				if (notice.getFilename() != null ) {	// 원래 첨부된 파일이 있었다면...
					File f = new File(uuid);	// 파일 정보를 File 형태의 f 변수에 할당	
					if (f.exists() ) f.delete();	// 물리 디스크의 파일을 삭제
				}
			} else {
				// 원래 첨부된 파일을 그대로 사용하는 경우
				vo.setFilename(notice.getFilename());
				vo.setFilepath(notice.getFilepath());
			}
		}
		
		// 화면에서 변경 입력한 정보를 DB에 변경 저장한 후 상세 화면으로 연결
		service.notice_update(vo);
		// 상세 화면 연결을 위하여 id를 기준으로 화면 이동
		return "redirect:detail.no?id=" + vo.getId();
	}
	
	
	// 공지글 수정 화면 요청
	@RequestMapping("/modify.no")
	public String modify(int id, Model model) {
		// 해당 공지글 정보를 DB에서 조회해와 수정화면에 출력
		model.addAttribute("vo", service.notice_detail(id));
		return "notice/modify";
	}
	
	
	// 공지글 삭제처리 요청
	@RequestMapping ("/delete.no")
	public String delete(int id, HttpSession session) {
		// 첨부 파일이 있는 글의 경우 디스크에서 첨부파일을 삭제한다.
		// 공지글에 대한 모든 정보 조회
		NoticeVO notice = service.notice_detail(id);
		String uuid = session.getServletContext().getRealPath("resources") + "/" + notice.getFilepath();
		// 파일명 또는 파일경로가 있는지 판단 (없지 않다면)
		if (notice.getFilename() != null) {
			// 디렉토리 접근 권한을 가진 File 개체를 통해 파일 위치 할당
			File file = new File(uuid);
			if ( file.exists()) file.delete();	// upload 폴더 내에 file 이 존재한다면 삭제 처리
		}
		
		// 해당 공지글 정보를 DB에서 삭제한 후 목록화면으로 이동
		service.notice_delete(id);
		return "redirect:list.no";
	}
	
	// 공지글의 첨부 파일 다운로드 요청
	@RequestMapping ("/download.no")
	public void download(int id, HttpSession session, HttpServletResponse response) {
		// 해당 공지글의 첨부파일 정보를 DB에서 조회해와 해당 파일을 서버로부터 다운로드.
		NoticeVO notice = service.notice_detail(id);
		// 파일 업,다운로드 처리를 다른 게시판에서 하기 위해서 CommonService ..
		common.fileDownload(notice.getFilename(), notice.getFilepath(), session, response);
	}
	
	
	// 공지사항 상세화면 요청
	@RequestMapping("/detail.no")
	public String detail (int id, Model model) {
		// 상세화면 요청 전 조회수 증가
		service.notice_read(id);
		
		// 선택한 공지사항의 정보를 DB에서 조회해와 상세화면에 출력
		model.addAttribute("vo", service.notice_detail(id));
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", page);
		return "notice/detail";
	}
	
	
	// 신규 공지사항 저장처리 요청
	@RequestMapping("/insert.no")
	public String insert(NoticeVO vo, MultipartFile file
			, HttpSession session) {
//		MemberVO member = (MemberVO)session.getAttribute("loginInfo");
//		vo.setWriter(member.getId());
		// 로그인 된 사용자의 id를 가져와 작성자(writer)가 됨.
		vo.setWriter( ((MemberVO) session.getAttribute("loginInfo")).getId() );
		
		if (! file.isEmpty() ) { // 파일이 있는 경우
			// 첨부 파일 처리 (DB 에 저장)
			vo.setFilename( file.getOriginalFilename() );
			// 전달받은 파일의 실제 이름을 vo에 할당
			
			vo.setFilepath( common.fileupload("notice", file, session) );
		}
		
		// 화면에서 입력한 정보를 DB에 저장한 후 화면으로 연결
		
		service.notice_insert(vo);
		return "redirect:list.no";
	}
	
	// 신규 공지사항 입력화면 요청
	@RequestMapping("/new.no")
	public String notice() {
		return "notice/new";
	}
	
	// 공지사항 글목록 화면 요청
	@RequestMapping("/list.no")
	public String list(HttpSession session, @RequestParam(defaultValue = "1") int curPage
			, Model model, String search, String keyword) {
		// 공지글 처리 중 로그인... 나중에 삭제
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", "hanul");
		map.put("pw", "hanul");
		session.setAttribute("loginInfo", member.member_login(map)) ;
		
		session.setAttribute("category", "no");
		// DB에서 공지글 목록을 조회해와 목록화면에 출력
		// model.addAttribute("list", service.notice_list());
		
		page.setCurPage(curPage);	// 현재 페이지에 대한 정보를 담기 위한 처리
		page.setSearch(search);		// 사용자가 선택한 검색조건을 담음
		page.setKeyword(keyword);	// 사용자가 입력한 검색어를 담음
		
		model.addAttribute("page", service.notice_list(page)) ;
		return "notice/list";
	}
}



