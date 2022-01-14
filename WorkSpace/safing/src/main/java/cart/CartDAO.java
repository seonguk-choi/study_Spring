package cart;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CartDAO {
	
	@Autowired @Qualifier("bteam") private SqlSession sql;
	
	//장바구니 넣기 
	public boolean insert(CartVO vo) {
		return sql.insert("product.mapper.insert", vo) == 1 ? true : false;
	}
	
	//장바구니 목록
	public List<CartVO> list(CartVO vo) {
		return sql.selectList("product.mapper.list", vo);
	}
	
	//장바구니 상세정보
	public CartVO detail(CartVO vo) {
		return sql.selectOne("product.mapper.detail", vo);
	}
	
	//장바구니 정보수정
	public boolean update(CartVO vo) {
		return sql.update("product.mapper.update", vo) == 1 ? true : false;
	}
	
	//장바구니 삭제
	public boolean delete(CartVO vo) {
		return sql.delete("product.mapper.delete", vo) == 1 ? true : false;
	}
	
	
}
