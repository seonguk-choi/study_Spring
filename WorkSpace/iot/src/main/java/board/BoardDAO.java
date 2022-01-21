package board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import notice.NoticeVO;

@Repository
public class BoardDAO implements BoardService {

	@Autowired @Qualifier("hanul") SqlSession sql;
	
	@Override
	public int board_insert(BoardVO vo) {
		
		return sql.insert("board.mapper.insert", vo);
	}

	@Override
	public BoardPage board_list(BoardPage page) {
		// 전체 게시글 수 조회
		int pagecnt =sql.selectOne("board.mapper.totalList", page);
		page.setTotalList(pagecnt);
		
		// 페이징 처리된 전체 게시글 목록 조회
		List<BoardVO> list = sql.selectList("board.mapper.list", page);
		page.setList(list);
		return page;
	}

	@Override
	public BoardVO board_detail(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int board_read(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int board_update(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int board_delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
