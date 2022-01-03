package customer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//@Repository DB 조회결과를 저장하는 클래스를 객체로 지정

@Repository
public class CustomerDAO implements CustomerService {
	
	//SqlSession : PreparedStatement 와 기능기 같음.
	//@Autowired : 메모리에 올려둔 주소들이 자동으로 연결 됨.
	
	@Autowired private SqlSession sql;
	
	@Override
	public void customer_insert(CustomerVO vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CustomerVO> customer_list() {
		
		return sql.selectList("customer.mapper.list");
	}

	@Override
	public CustomerVO customer_detail(int id) {
	
		return sql.selectOne("customer.mapper.detail", id);
	}

	@Override
	public void customer_update(CustomerVO vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void customer_Delete(int id) {
		// TODO Auto-generated method stub

	}

}
