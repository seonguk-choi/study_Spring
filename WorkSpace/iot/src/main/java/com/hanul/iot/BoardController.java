package com.hanul.iot;

import java.io.File;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import board.BoardCommentVO;
import board.BoardPage;
import board.BoardServiceImpl;
import board.BoardVO;
import common.CommonService;
import member.MemberVO;

@Controller
public class BoardController {
	
	@Autowired private BoardServiceImpl service;
	@Autowired private BoardPage page;
	@Autowired private CommonService common;
	
	// 방명록 글에 대한 댓글 삭제 처리 요청
	@ResponseBody
	@RequestMapping("/board/comment/delete/{id}")
	public void comment_delete(@PathVariable int id) {
		// 해당 댓글을 DB에서 삭제
		service.board_comment_delete(id);
	}
	
	
	
	// 방명록 글에 대한 댓글 수정처리 요청
	// 컨트롤러를 통해 보내고 있는 응답의 유형을 나타내기 위해 produces 를 사용하여 한글 깨짐 해결
	// 이 "produces" 키워드는 ajax 요청에서 가장 유용하게 사용됨.
	@ResponseBody 
	@RequestMapping (value = "/board/comment/update", produces="application/text; charset=utf-8")
					// json 형태의 값을 vo 에 담기 위해선 @RequestBody 를 선언
	public String comment_update(@RequestBody BoardCommentVO vo) {
		return service.board_comment_update(vo) == 1 ? "성공!!" : "실패!!";
	}
	
	
	// 방명록 글에 대한 댓글 목록조회 요청
	
	@RequestMapping("/board/comment/list/{pid}")
	public String comment_list(@PathVariable int pid, Model model) {
		// 해당 글에 대한 댓글들을 DB에서 조회해 온다.
		model.addAttribute("list", service.board_comment_list(pid));
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("lf", "\n");
		return "board/comment/comment_list";
	}

	// 방명록 글에 대한 댓글 저장처리 요청
	@ResponseBody
	@RequestMapping ("/board/comment/regist")
	public boolean comment_regist(BoardCommentVO vo, HttpSession session) {		
		// 작성자의 경우 member 의 id 값을 담아야 하므로 로그인 정보 확인
		MemberVO member = (MemberVO) session.getAttribute("loginInfo");
		vo.setWriter(member.getId());
		
		// 화면에서 입력한 댓글 정보를 DB에 저장한 후 저장 여부를 반환
		return service.board_comment_insert(vo) == 1 ? true : false;
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
	
	
	
	// 방명록 글 수정 화면 요청
	@RequestMapping("/modify.bo")
	public String modify(int id, Model model) {
		// 해당 글의 정보를 DB에서 조회해와 수정화면에 출력
		model.addAttribute("vo", service.board_detail(id) ) ;
		return "board/modify";
	}
	
	// 방명록 글 삭제처리 요청
	@RequestMapping("/delete.bo")
	public String delete(int id, HttpSession session, Model model) {
		// 첨부 파일이 있는 글에 대해서는 해당 파일을 서버의 물리적 영역에서 삭제
		BoardVO vo = service.board_detail(id);
		if ( vo.getFilepath() != null) {
			File file = new File( session.getServletContext().getRealPath("resources") 
					+ "/" + vo.getFilepath() );
			if ( file.exists() ) file.delete();
		}
		
		// 해당 방명록 글을 DB에서 삭제한 후 목록화면으로 연결
		service.board_delete(id);
	//	return "redirect:list.bo";
		model.addAttribute("uri", "list.bo");
		model.addAttribute("page", page);
		return "board/redirect";
	}
	
	
	// 방명록 첨부파일 다운로드 요청
	@RequestMapping ("/download.bo")
	public void download(int id, HttpSession session, HttpServletResponse response) {
		// 해당 글의 첨부 파일 정보를 DB에서 조회해
		BoardVO vo = service.board_detail(id);		
		// 해당 파일을 서버로부터 다운로드 함.
		common.fileDownload(vo.getFilename(), vo.getFilepath(), session, response);
		// 클라이언트에 응답을 해줘야 하기 때문에 response 전달		
	}
		
	// 방명록 상세화면 요청
	@RequestMapping ("/detail.bo")
	public String detail(int id, Model model) {
		
		// 상세화면 요청 전 조회수 증가
		service.board_read(id);
		
		// 해당 방명록 글을 DB에서 조회해와 상세화면에 출력
		model.addAttribute("vo", service.board_detail(id) ) ;
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", page);
		return "board/detail";
	}
	
	
	// 방명록 신규 저장 처리 요청
	@RequestMapping ("/insert.bo")
	public String insert(BoardVO vo, MultipartFile file, HttpSession session) {
		
		// 파일 정보가 있다면
		if ( ! file.isEmpty() ) {
			vo.setFilename( file.getOriginalFilename() );
			vo.setFilepath( common.fileupload("board", file, session) );
		}
		MemberVO member = (MemberVO) session.getAttribute("loginInfo");
		vo.setWriter( member.getId() );
		
		service.board_insert(vo);
		return "redirect:list.bo";
	}
	
	// 방명록 신규 글 작성 화면 요청
	@RequestMapping ("/new.bo")
	public String board() {
		return "board/new";
	}
	
	
	// 방명록 목록화면 요청
	@RequestMapping ("/list.bo")
	public String list(HttpSession session , Model model
			, String search, String keyword
			, @RequestParam (defaultValue = "1") int curPage
			, @RequestParam (defaultValue = "10") int pageList
			, @RequestParam (defaultValue = "list") String viewType ) {
		session.setAttribute("category", "bo");
		
		// DB에서 방명록 정보를 조회해와 목록화면에 출력
		page.setCurPage(curPage);	// 현재 페이지 정보를 page에 담음
		page.setSearch(search);		// 검색 조건 값을 page에 담음
		page.setKeyword(keyword);	// 검색 키워드 값을 page에 담음
		page.setPageList(pageList);	// 페이지당 보여질 글 목록 수를 page에 담음
		page.setViewType(viewType);	// 게시판 형태를 page에 담음
		
		model.addAttribute("page", service.board_list(page));		
		return "board/list";
	}
}



