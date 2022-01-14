package board;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BoardDAO {
	
	@Autowired @Qualifier("bteam") private SqlSession sql;
	
	//게시글 등록 
	public boolean insert(BoardVO vo) {
		return sql.insert("board.mapper.insert", vo) == 1 ? true : false;
	}
	
	//게시글 정보 목록
	public List<BoardVO> list(BoardVO vo) {
		return sql.selectList("board.mapper.list", vo);
	}
	
	//게시글 상세정보
	public BoardVO detail(BoardVO vo) {
		return sql.selectOne("board.mapper.detail", vo);
	}
	
	//게시글 정보수정
	public boolean update(BoardVO vo) {
		return sql.update("board.mapper.update", vo) == 1 ? true : false;
	}
	
	//게시글 삭제
	public boolean delete(BoardVO vo) {
		return sql.delete("board.mapper.delete", vo) == 1 ? true : false;
	}
	
	
}
