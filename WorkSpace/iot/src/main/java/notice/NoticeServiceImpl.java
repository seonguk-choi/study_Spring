package notice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired private NoticeDAO dao;

	@Override
	public void notice_insert(NoticeVO vo) {
		dao.notice_insert(vo);
	}

	@Override
	public List<NoticeVO> notice_list() {
		return dao.notice_list();
	}

	@Override
	public NoticeVO notice_detail(int id) {
		return null;
	}

	@Override
	public void notice_update(NoticeVO vo) {
	}

	@Override
	public void notice_readcnt(int id) {
		
	}





}
