package gr.hua.ds.service;

import java.util.List;

import gr.hua.ds.users.model.Department;
import gr.hua.ds.users.model.Enums.Dept;

public interface DepartmentService {
	
	public List<Department> getDepartments();
	
	public Department getDepartment(Dept department);
	
	public void updateDepartment(Department department);

}
