package gr.hua.ds.users.daoimpl;

import java.util.List;

import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import gr.hua.ds.users.dao.ApplicationDAO;
import gr.hua.ds.users.model.Application;
import gr.hua.ds.users.model.Enums.*;

@Repository
public class ApplicationDAOImpl implements ApplicationDAO {

	private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Application.class).buildSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getApplications(Dept departmentName) {
		
		Session session = this.sessionFactory.openSession();

		List<Application> applications = session
				.createQuery("from Application a where a.departmentName=:departmentName and a.active=:active")
				.setParameter("departmentName", departmentName).setParameter("active", Activable.inactive).getResultList();

		session.close();
		
		return applications;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getApplicationsByDepartment(Dept departmentName){
		Session session = this.sessionFactory.openSession();

		List<Application> applications = session.createQuery("from Application a where a.departmentName=:departmentName"
				+ " and active=null")
				.setParameter("departmentName", departmentName).getResultList();
		
		session.close();
		return applications;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getActivatedApplicationsByDept(Dept department) {
		
		Session session = this.sessionFactory.openSession();

		List<Application> applications = session
				.createQuery("from Application a where a.departmentName=:departmentName and a.active=:active")
				.setParameter("departmentName", department).setParameter("active", Activable.active).getResultList();

		session.close();
		
		return applications;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public  List<Application> getActivatedApplications() {
		Session session = this.sessionFactory.openSession();
		
		List<Application> applications = session.createQuery("from Application a where a.active=:active")
				.setParameter("active", Activable.active).getResultList();
		
		session.close();
		return applications;
	}

	@Override
	public Application getApplicationActiveByUsername(String username) {
		Session session = this.sessionFactory.openSession();
		Application application = null;
		
		try {
			application = session.createQuery("from Application a where a.username=:username", Application.class)
					.setParameter("username", username).getSingleResult();
		} catch (NoResultException nre) {
			
		}
		
		session.close();
		
		return application;
	}

	@Override
	public void updateApplication(Application app) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(app);
		tx.commit();
		session.close();
	}
	
	@Override
	public void deleteApplication(Application app) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(app);
		tx.commit();
		session.close();
	}

	@Override
	public void insertApplication(Application app) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(app);
		tx.commit();
		session.close();
	}

}
