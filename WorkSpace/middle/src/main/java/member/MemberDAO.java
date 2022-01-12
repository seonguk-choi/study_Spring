package member;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MemberDAO {
	
	@Autowired @Qualifier("hanul") private SqlSession sql;
	
	//회원가입
	public int member_insert(MemberVO vo) {
		return sql.insert("member.mapper.insert", vo);
	}
	
	//회원 정보가져오기
	public MemberVO member_list(MemberVO vo) {
		return sql.selectOne("member.mapper.login", vo);
	}
	
	
}
