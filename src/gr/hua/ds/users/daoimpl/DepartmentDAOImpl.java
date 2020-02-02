package gr.hua.ds.users.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.ds.users.dao.DepartmentDAO;
import gr.hua.ds.users.model.Department;
import gr.hua.ds.users.model.Enums.Dept;


@Repository
public class DepartmentDAOImpl implements DepartmentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getDepartments() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Department> applications = session.createQuery("from Department").getResultList();

		return applications;
	}

	@Override
	public void updateDepartment(Department app) {
		Session session = this.sessionFactory.getCurrentSession();

		session.saveOrUpdate(app);

	}

	@SuppressWarnings("unchecked")
	@Override
	public Department getDepartment(Dept department) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Department> departments = session.createQuery("from Department d where d.departmentName=:department")
				.setParameter("department", department).getResultList();
		Department selectedDepartment = null;
		
		for(Department a : departments) {
			if(a.getDepartmentName().equals(department)) {
				selectedDepartment = a;
			}
		}
		
		return selectedDepartment;
	}

}