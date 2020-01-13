package gr.hua.ds.users.dao;

import java.util.List;

import gr.hua.ds.users.model.Department;
import gr.hua.ds.users.model.Enums.Dept;

public interface DepartmentDAO {
	public List<Department> getDepartments();
	public Department getDepartment(Dept department);
	public void updateDepartment(Department department);

}
