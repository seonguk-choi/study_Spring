package employee;

import java.util.List;

public interface EmployeeService {
	// 사원목록 조회
	List<EmployeeVO> employee_list();
	
	// 사원 상세정보 조회
	EmployeeVO employee_detail(int id);
}
