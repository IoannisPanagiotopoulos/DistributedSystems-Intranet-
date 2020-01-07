package Services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import DataBase.Application;
import DataBase.Enums;
import DataBase.Enums.Dept;
import iDAO.ApplicationDAO;

public class ApplicationDAOImpl implements ApplicationDAO {

	private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Application.class).buildSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getApplications() {
		Session session = this.sessionFactory.openSession();
		List<Application> applications = session.createQuery("from Application").getResultList();

		session.close();
		return applications;
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
	public Application getApplication(Dept department) {
		Session session = this.sessionFactory.openSession();
		List<Application> applications = session.createQuery("from Application a where a.dept=:department").setParameter("department", department).getResultList();
		Application selectedApplication = null;
		
		for(Application a : applications) {
			if(a.getDept().equals(department)) {
				selectedApplication = a;
			}
		}
		
		session.close();
		return selectedApplication;
	}

}