package member;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired private MemberDAO dao;

	@Override
	public boolean member_join(MemberVO vo) {
		return false;
	}

	@Override
	public MemberVO member_login(HashMap<String, String> map) {
		return dao.member_login(map);
	}


	@Override
	public boolean member_update(MemberVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean member_delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean member_id_check(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean member_socail_email(MemberVO vo) {
		
		return dao.member_socail_email(vo);
	}

	@Override
	public boolean member_socail_insert(MemberVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean member_social_update(MemberVO vo) {
		// TODO Auto-generated method stub
		return false;
	}



}
