package Services;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import DataBase.Enums;
import DataBase.Officer;
import DataBase.Enums.Role;
import iDAO.OfficerDAO;
public class OfficerDAOImpl implements OfficerDAO {
	private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Officer.class)
			.buildSessionFactory();
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@SuppressWarnings("unchecked")
	@Override
	public Officer getOfficer(String user, String pass) {
		
		Session session = this.sessionFactory.openSession();
		Officer selectedOfficer=null;
		List<Officer> officers = session.createQuery("from Officer").getResultList();
		
		for(Officer officer : officers) {
			if(officer.getUsername().equals(user) && officer.getPassword().equals(pass)) {
				selectedOfficer=officer;
			}
		}
		session.close();
		
		return selectedOfficer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Officer> getOfficers() {
		Session session = this.sessionFactory.openSession();
		List<Officer> officers = session.createQuery("from Officer o where o.role!=:role").setParameter("role", Enums.Role.Admin).getResultList();		
		session.close();
		return officers;
	}
	public List<Officer> getallOfficers() {
		Session session = this.sessionFactory.openSession();
		List<Officer> officers = session.createQuery("from Officer").getResultList();		
		session.close();
		return officers;
	}
	@Override
	public void removeOfficer(Officer o) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(o);
		tx.commit();
		session.close();
	}

	@Override
	public void updateOfficer(Officer o) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(o);
		tx.commit();
		session.close();
	}

	@Override
	public void insertOfficer(Officer o) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(o);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Officer getOfficer(int id) {
		Session session = this.sessionFactory.openSession();
		Officer selectedOfficer=null;
		List<Officer> officers = session.createQuery("from Officer o where o.id=:id").setParameter("id", id).getResultList();
		
		for(Officer officer : officers) {
			selectedOfficer=officer;
		}
		session.close();
		
		return selectedOfficer;
	}


}
