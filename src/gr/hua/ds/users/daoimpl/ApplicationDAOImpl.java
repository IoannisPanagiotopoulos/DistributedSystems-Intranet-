package gr.hua.ds.users.daoimpl;

import java.util.List;

import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import gr.hua.ds.users.dao.ApplicationDAO;
import gr.hua.ds.users.model.Application;
import gr.hua.ds.users.model.Enums.*;

public class ApplicationDAOImpl implements ApplicationDAO {

	private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Application.class).buildSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getApplications(Dept departmentName) {
		
		Session session = this.sessionFactory.openSession();
		
		Query query = session
				.createQuery("from Application a where a.departmentName=:departmentName and a.active=:active")
				.setParameter("departmentName", departmentName).setParameter("active", Activable.inactive);

		List<Application> applications = query.getResultList();

		session.close();
		
		return applications;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getApplicationsByDepartment(Dept departmentName){
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from Application a where a.departmentName=:departmentName ")
				.setParameter("departmentName", departmentName);
		List<Application> applications = query.getResultList();
		session.close();
		return applications;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getActivatedApplicationsByDept(Dept department) {
		
		Session session = this.sessionFactory.openSession();
		
		Query query = session
				.createQuery("from Application a where a.departmentName=:departmentName and a.active=:active")
				.setParameter("departmentName", department).setParameter("active", Activable.active);

		List<Application> applications = query.getResultList();

		session.close();
		
		return applications;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public  List<Application> getActivatedApplications() {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from Application a where a.active=:active")
				.setParameter("active", Activable.active);
		List<Application> applications=query.getResultList();
		 session.close();
		return applications;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Application getApplicationByUsername(String username) {
		Session session = this.sessionFactory.openSession();
		
		Query query = session.createQuery("from Application a where a.username=:username")
				.setParameter("username", username);

		Application selectedApplication = null;
		List<Application> applications = query.getResultList();
		for(Application ap: applications) {
			selectedApplication = ap;
			
		}

		session.close();
		
		return selectedApplication;
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

}
