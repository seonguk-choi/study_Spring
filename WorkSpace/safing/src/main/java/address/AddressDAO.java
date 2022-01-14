package address;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AddressDAO {
	
	@Autowired @Qualifier("bteam") private SqlSession sql;
	
	//주소 등록 
	public boolean insert(AddressVO vo) {
		return sql.insert("address.mapper.insert", vo) == 1 ? true : false;
	}
	
	//주소 정보 목록
	public List<AddressVO> list(AddressVO vo) {
		return sql.selectList("address.mapper.list", vo);
	}
	
	//주소 정보수정
	public boolean update(AddressVO vo) {
		return sql.update("address.mapper.update", vo) == 1 ? true : false;
	}
	
	//주소 삭제
	public boolean delete(AddressVO vo) {
		return sql.delete("address.mapper.delete", vo) == 1 ? true : false;
	}
	
	
}
