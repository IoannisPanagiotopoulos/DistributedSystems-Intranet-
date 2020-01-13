package gr.hua.ds.users.daoimpl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import gr.hua.ds.users.dao.DepartmentDAO;
import gr.hua.ds.users.model.Department;
import gr.hua.ds.users.model.Enums.Dept;

public class DepartmentDAOImpl implements DepartmentDAO {

	private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Department.class).buildSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getDepartments() {
		Session session = this.sessionFactory.openSession();
		List<Department> applications = session.createQuery("from Department").getResultList();

		session.close();
		return applications;
	}

	@Override
	public void updateDepartment(Department app) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(app);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Department getDepartment(Dept department) {
		Session session = this.sessionFactory.openSession();
		List<Department> departments = session.createQuery("from Department d where d.departmentName=:department")
				.setParameter("department", department).getResultList();
		Department selectedDepartment = null;
		
		for(Department a : departments) {
			if(a.getDepartmentName().equals(department)) {
				selectedDepartment = a;
			}
		}
		
		session.close();
		return selectedDepartment;
	}

}