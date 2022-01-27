package member;

import java.util.HashMap;

public interface MemberService {
	// CRUD 형태를 가짐

	// 회원 가입 성공 여부 (C)
	boolean member_join(MemberVO vo);
	
	// 로그인 - 아이디, 비번 일치하는 회원정보 조회 (R)
	MemberVO member_login(HashMap<String, String> map);
	
	// 회원정보 변경저장 (U)
	boolean member_update(MemberVO vo);
	// 회원 탈퇴시 회원정보 삭제 (D)
	boolean member_delete(String id);
	// 아이디 중복확인 (R)
	boolean member_id_check(String id);
	
	// 소셜 회원 정보 존재여부 (R)
	boolean member_social_email(MemberVO vo);
	
	// 소셜 회원 정보 신규 저장 (C)
	boolean member_social_insert(MemberVO vo);
	
	// 소셜 회원 정보 변경 저장 (U)
	boolean member_social_update(MemberVO vo);

}
