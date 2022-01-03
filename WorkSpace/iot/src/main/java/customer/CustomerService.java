package customer;

import java.util.List;

public interface CustomerService {
	//CRUD (Create / Read / Update / Delete)
	
	//고객 정보 삽입 정장
	void customer_insert(CustomerVO vo);
	
	//고객 정보 조회
	List<CustomerVO> customer_list();
	
	//고객 상태 정보 조회
	CustomerVO customer_detail(int id);
	
	//고객 정보 변경 저장
	void customer_update(CustomerVO vo);
	
	//고객 정보 삭제
	void customer_Delete(int id);
	
}
