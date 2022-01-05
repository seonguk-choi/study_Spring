package customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service	// @Service 어노테이션을 이용하여 해당 클래스 서비스 레이어 클래스
			// 라는 것을 명확하게 지정
public class CustomerServiceImpl implements CustomerService {
	@Autowired CustomerDAO dao;
	
	@Override
	public void customer_insert(CustomerVO vo) {
		dao.customer_insert(vo);
	}

	@Override
	public List<CustomerVO> customer_list() {		
		return dao.customer_list();
	}

	@Override
	public CustomerVO cusotomer_detail(int id) {
		// TODO Auto-generated method stub
		return dao.cusotomer_detail(id);
	}

	@Override
	public void customer_update(CustomerVO vo) {
		dao.customer_update(vo);
	}

	@Override
	public void customer_delete(int id) {
		dao.customer_delete(id);
	}

}
