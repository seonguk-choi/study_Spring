package customer;

import java.util.List;

public interface CustomerService {
	// CRUD (Create / Read / Update / Delete)
	
	// 고객 정보 삽입 저장
	void customer_insert(CustomerVO vo);
	// 고객 정보 조회
	List<CustomerVO> customer_list();
	// 고객 상세 정보 조회 (1건)
	CustomerVO cusotomer_detail(int id);
	// 고객 정보 변경 저장
	void customer_update(CustomerVO vo);
	// 고객 정보 삭제
	void customer_delete(int id);
}
