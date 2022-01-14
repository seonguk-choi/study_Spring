package order;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderDAO {
	
	@Autowired @Qualifier("bteam") private SqlSession sql;
	
	//상품 등록 
	public boolean insert(OrderVO vo) {
		return sql.insert("product.mapper.insert", vo) == 1 ? true : false;
	}
	
	//상품정보 목록
	public List<OrderVO> list(OrderVO vo) {
		return sql.selectList("product.mapper.list", vo);
	}
	
	//상품 상세정보
	public OrderVO detail(OrderVO vo) {
		return sql.selectOne("product.mapper.detail", vo);
	}
	
	//상품 정보수정
	public boolean update(OrderVO vo) {
		return sql.update("product.mapper.update", vo) == 1 ? true : false;
	}
	
	//상품 삭제
	public boolean delete(OrderVO vo) {
		return sql.delete("product.mapper.delete", vo) == 1 ? true : false;
	}
	
	
}
