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
	
	//주문 등록 
	public boolean insert(OrderVO vo) {
		return sql.insert("order.mapper.insert", vo) == 1 ? true : false;
	}
	
	//상품정보 목록
	public List<OrderVO> list(OrderVO vo) {
		return sql.selectList("order.mapper.list", vo);
	}
	
	//상품 상세정보
	public OrderVO detail(OrderVO vo) {
		return sql.selectOne("order.mapper.detail", vo);
	}
	
	//상품 정보수정
	public boolean update(OrderVO vo) {
		return sql.update("order.mapper.update", vo) == 1 ? true : false;
	}
	
	//상품 삭제
	public boolean delete(OrderVO vo) {
		return sql.delete("order.mapper.delete", vo) == 1 ? true : false;
	}
	
	
}
