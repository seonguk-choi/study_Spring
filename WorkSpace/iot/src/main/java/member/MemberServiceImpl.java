package member;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired private MemberDAO dao;

	@Override
	public boolean member_join(MemberVO vo) {
		return dao.member_join(vo);
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
		return dao.member_id_check(id);
	}

	@Override
	public boolean member_socail_email(MemberVO vo) {
		
		return dao.member_socail_email(vo);
	}

	@Override
	public boolean member_socail_insert(MemberVO vo) {
		// 소셜회원정보 신규저장Create
		return dao.member_socail_insert(vo);
	}

	@Override
	public boolean member_social_update(MemberVO vo) {
		// 소셜회원 정보 변경 저장 Update
		return dao.member_social_update(vo);
	}



}
