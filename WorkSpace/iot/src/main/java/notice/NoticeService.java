package notice;

import java.util.HashMap;
import java.util.List;

public interface NoticeService {
	//CRUD 형태를 가짐
	
	//공지글 신규 저장(C)
	void notice_insert(NoticeVO vo);
	
	//공지글 목록조회(R)
    List<NoticeVO> notice_list();
    
    //공지글 목록 조회 -페이지 처리된
    NoticePage notice_list(NoticePage page); 
    
    //공지글 상세조회(R)
    NoticeVO notice_detail(int id);
    
    //공지글 변경저장(U)
    void notice_update(NoticeVO vo);
    
    //공지글 조회시 조회수 증가 처리(U)
    void notice_readcnt(int id);
    
    //공지글 수정
    void notice_modify(NoticeVO vo);
    
    //공지글 삭제
    void notice_delete(int id);
    
}
