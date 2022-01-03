package customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service 이용 해당 클래스를 서비스 레이어클래스로 지정

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired CustomerDAO dao;
	
	@Override
	public void customer_insert(CustomerVO vo) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public List<CustomerVO> customer_list() {
		return dao.customer_list();
	}

	@Override
	public CustomerVO customer_detail(int id) {
		
		return dao.customer_detail(id);
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
