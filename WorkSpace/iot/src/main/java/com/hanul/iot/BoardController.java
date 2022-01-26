package com.hanul.iot;

import java.io.File;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import board.BoardPage;
import board.BoardServiceImpl;
import board.BoardVO;
import board.BoardCommentVO;
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
		model.addAttribute("page", page);
		
		
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
	
	//방명록 수정 화면
	@RequestMapping("/modify.bo")
	public String modify(int id , Model model) {
		
		model.addAttribute("vo", service.board_detail(id));
		model.addAttribute("page", page);
		
	
		return "board/modify";
	}
	
	// 방명록 글 수정 저장처리 요청
	@RequestMapping("/update.bo")
	public String update(BoardVO vo, MultipartFile file, String attach
			, HttpSession session, Model model) {
		
		// 원 글에 첨부 파일이 있었는지 조회
		BoardVO board = service.board_detail( vo.getId() );
		String uuid = session.getServletContext().getRealPath("resources") + "/" + board.getFilepath();
		
		// 파일을 첨부하지 않은 경우
		if ( file.isEmpty() ) {
			// 원래부터 첨부된 파일이 없는 경우
			// 원래 첨부된 파일이 있었는데 삭제한 경우
			if ( attach.isEmpty() ) {
				// 원래 첨부되어 있는 파일이 있다면 서버의 물리적 영역에서 삭제
				if ( board.getFilename() != null) {
					File f = new File(uuid); 
					if (f.exists() ) f.delete();	// 파일이 존재하면 파일을 삭제 처리
				}				
			} else { // 원래 첨부된 파일을 그대로 사용하는 경우
				vo.setFilename( board.getFilename() );
				vo.setFilepath( board.getFilepath() );				
			}
		} else { // 파일을 첨부한 경우
			vo.setFilename( file.getOriginalFilename() );
			vo.setFilepath( common.fileupload("board", file, session) );
			// 원래 첨부되어 있는 파일이 있다면 서버의 물리적 영역에서 삭제
			if ( board.getFilename() != null) {
				File f = new File(uuid); 
				if (f.exists() ) f.delete();	// 파일이 존재하면 파일을 삭제 처리
			}
		}
		
		
		// 화면에서 수정한 정보들을 DB에서 저장한 후 상세화면 연결
		service.board_update(vo);
		model.addAttribute("uri", "detail.bo");
		model.addAttribute("id", vo.getId());
		return "board/redirect";
	}
	
	//방명록 삭제
	@RequestMapping("/delete.bo")
	public String delete(int id, HttpSession session, Model model) {
		//첨부 파일이 있는 글에 대해서는 물리적 삭제
		BoardVO vo = service.board_detail(id);
		if(vo.getFilename() != null) {
			File file = new File(session.getServletContext().getRealPath("resources") + "/" + vo.getFilepath());
			if(file.exists()) file.delete();
			
		}
		
		service.board_delete(id);
		
		model.addAttribute("uri", "list.bo");
		model.addAttribute("page", page);
		
		
		return "board/redirect";
	}
	
	//댓글 등록
	@ResponseBody
	@RequestMapping("/board/comment/regist")
	public boolean comment_regist(BoardCommentVO vo, HttpSession session) {
		MemberVO member = (MemberVO) session.getAttribute("loginInfo");
		
		vo.setWriter(member.getId());
		
		
		
		return service.board_comment_insert(vo) == 1 ? true : false;
	}
	
	@RequestMapping("/board/comment/list/{pid}")
	public String comment_list(@PathVariable int pid, Model model) {
		// 해당 글에 대한 댓글들을 DB에서 조회해 온다.
		model.addAttribute("list", service.board_comment_list(pid));
		
		return "board/comment/comment_list";
	}
}
