package gr.hua.ds.users.daoimpl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.ds.users.dao.ApplicationDAO;
import gr.hua.ds.users.model.Application;
import gr.hua.ds.users.model.Enums.*;


@Repository
public class ApplicationDAOImpl implements ApplicationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getApplications(Dept departmentName) {
		
		Session session = this.sessionFactory.getCurrentSession();

		List<Application> applications = session
				.createQuery("from Application a where a.departmentName=:departmentName and a.active=:active")
				.setParameter("departmentName", departmentName).setParameter("active", Activable.inactive).getResultList();

		
		return applications;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getApplicationsByDepartment(Dept departmentName){
		Session session = this.sessionFactory.getCurrentSession();

		List<Application> applications = session.createQuery("from Application a where a.departmentName=:departmentName"
				+ " and active=null")
				.setParameter("departmentName", departmentName).getResultList();
		
		return applications;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getActivatedApplicationsByDept(Dept department) {
		
		Session session = this.sessionFactory.getCurrentSession();

		List<Application> applications = session
				.createQuery("from Application a where a.departmentName=:departmentName and a.active=:active")
				.setParameter("departmentName", department).setParameter("active", Activable.active).getResultList();

		
		return applications;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public  List<Application> getActivatedApplications() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Application> applications = session.createQuery("from Application a where a.active=:active")
				.setParameter("active", Activable.active).getResultList();
		
		return applications;
	}

	@Override
	public Application getApplicationActiveByUsername(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		Application application = null;
		
		try {
			application = session.createQuery("from Application a where a.username=:username", Application.class)
					.setParameter("username", username).getSingleResult();
		} catch (NoResultException nre) {
			
		}
		
		
		return application;
	}

	@Transactional
	@Override
	public void updateApplication(Application app) {
		Session session = this.sessionFactory.getCurrentSession();

		session.saveOrUpdate(app);

	}
	
	@Transactional
	@Override
	public void deleteApplication(Application app) {
		Session session = this.sessionFactory.getCurrentSession();

		session.delete(app);

	}

	@Transactional
	@Override
	public void insertApplication(Application app) {
		Session session = this.sessionFactory.getCurrentSession();

		session.saveOrUpdate(app);

	}

}
