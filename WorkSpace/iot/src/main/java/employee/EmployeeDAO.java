package employee;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO implements EmployeeService {

	@Autowired @Qualifier("hr") private SqlSession sql;
	@Override
	public List<EmployeeVO> employee_list() {
		return sql.selectList("employee.mapper.emplist");
	}

	@Override
	public EmployeeVO employee_detail(int id) {
		return sql.selectOne("employee.mapper.detail", id);
	}

}
