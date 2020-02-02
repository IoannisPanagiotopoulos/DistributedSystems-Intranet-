package gr.hua.ds.users.daoimpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import gr.hua.ds.users.dao.DepartmentDAO;
import gr.hua.ds.users.model.Department;
import gr.hua.ds.users.model.Enums.Dept;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {
	
	public SessionFactory getSessionFactory() {
		Properties properties = new Properties();
		try {
			properties.load(getClass().getResourceAsStream("/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addProperties(properties).addAnnotatedClass(Department.class).buildSessionFactory();
		
		return sessionFactory;
	}
	
	
	

	private SessionFactory sessionFactory = getSessionFactory();

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