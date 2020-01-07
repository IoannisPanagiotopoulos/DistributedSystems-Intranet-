package Services;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import DataBase.ApplicationDetails;
import DataBase.Enums;
import DataBase.Enums.Activable;
import DataBase.Enums.Dept;
import iDAO.ApplicationDetailsDAO;

public class ApplicationDetailsDAOImpl implements ApplicationDetailsDAO {

	private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(ApplicationDetails.class).buildSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationDetails> getApplications(Dept department) {
		
		Session session = this.sessionFactory.openSession();
		
		Query query = session.createQuery("from ApplicationDetails a where a.dept=:department and a.active=:active").setParameter("department", department).setParameter("active", Enums.Activable.inactive);

		List<ApplicationDetails> applications = query.getResultList();

		session.close();
		
		return applications;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationDetails> getApplicationsByDepartment(Dept department){
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from ApplicationDetails a where a.dept=:department ").setParameter("department", department);
		List<ApplicationDetails> applications = query.getResultList();
		session.close();
		return applications;
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationDetails> getActivatedApplicationsByDept(Dept department) {
		
		Session session = this.sessionFactory.openSession();
		
		Query query = session.createQuery("from ApplicationDetails a where a.dept=:department and a.active=:active").setParameter("department", department).setParameter("active", Enums.Activable.active);

		List<ApplicationDetails> applications = query.getResultList();

		session.close();
		
		return applications;
	}
	public  List<ApplicationDetails> getActivatedApplications() {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from ApplicationDetails a where a.active=:active").setParameter("active", Enums.Activable.active);
		List<ApplicationDetails> applications=query.getResultList();
		 session.close();
		return applications;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ApplicationDetails getApplication(int userId) {
		Session session = this.sessionFactory.openSession();
		
		Query query = session.createQuery("from ApplicationDetails a where a.id=:id").setParameter("id", userId);

		ApplicationDetails selectedApplication = null;
		List<ApplicationDetails> applications = query.getResultList();
		for(ApplicationDetails ap: applications) {
			selectedApplication = ap;
			
		}

		session.close();
		
		return selectedApplication;
	}

	@Override
	public void updateApplication(ApplicationDetails app) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(app);
		tx.commit();
		session.close();
	}
	@Override
	public void deleteApplication(ApplicationDetails app) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(app);
		tx.commit();
		session.close();
	}

}
