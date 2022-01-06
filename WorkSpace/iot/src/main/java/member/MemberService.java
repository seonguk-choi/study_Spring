package member;

import java.util.HashMap;

public interface MemberService {
	//CRUD 형태를 가짐
	
	//회원가입 성공 여부 Create
	boolean member_join(MemberVO vo);
	
	//로그인 아이디, 비번 일치하는 회원정보 조회 Read
	MemberVO member_login(HashMap<String, String> map);
	
	//회원정보 변경 Update
	boolean member_update(MemberVO vo);
	
	//회원 탈퇴시 회원정보 삭제 Delete
	boolean member_delete(String id);
	
	//아이디 중복확인 Read
	boolean member_id_check(String id);
}
