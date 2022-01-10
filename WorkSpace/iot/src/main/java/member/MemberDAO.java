package member;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO implements MemberService{
	@Autowired @Qualifier("hanul") private SqlSession sql;

	@Override
	public boolean member_join(MemberVO vo) {
		return false;
	}

	@Override
	public MemberVO member_login(HashMap<String, String> map) {
		return sql.selectOne("member.mapper.login", map);
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
		return sql.selectOne("member.mapper.social_email", vo);
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
