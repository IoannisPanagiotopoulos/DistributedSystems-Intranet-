package gr.hua.ds.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.ds.users.dao.DepartmentDAO;
import gr.hua.ds.users.model.Department;
import gr.hua.ds.users.model.Enums.Dept;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDAO departmentDao;
	
	@Transactional
	@Override
	public List<Department> getDepartments() {
		return departmentDao.getDepartments();
	}

	@Transactional
	@Override
	public Department getDepartment(Dept department) {
		return departmentDao.getDepartment(department);
	}

	@Transactional
	@Override
	public void updateDepartment(Department department) {
		departmentDao.updateDepartment(department);
	}

}
