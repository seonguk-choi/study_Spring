package notice;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAO implements NoticeService{
	
	@Autowired @Qualifier("hanul") private SqlSession sql;

	@Override
	public void notice_insert(NoticeVO vo) {
		sql.insert("notice.mapper.insert", vo);
	}

	@Override
	public List<NoticeVO> notice_list() {
		return sql.selectList("notice.mapper.list");
	}

	@Override
	public NoticeVO notice_detail(int id) {
		return sql.selectOne("notice.mapper.detail", id);
	}

	@Override
	public void notice_update(NoticeVO vo) {
		sql.update("notice.mapper.update", vo);
	}

	@Override
	public void notice_readcnt(int id) {
		sql.update("notice.mapper.readcnt", id);
	}

	@Override
	public void notice_delete(int id) {
		sql.delete("notice.mapper.delete", id);
		
	}

	@Override
	public void notice_modify(NoticeVO vo) {
		// TODO Auto-generated method stub
		
	}


	
	
	
}
