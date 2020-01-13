package gr.hua.ds.users.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import gr.hua.ds.users.dao.UserInformationDAO;
import gr.hua.ds.users.model.User;
import gr.hua.ds.users.model.UserInformation;

public class UserInformationDAOImpl implements UserInformationDAO {
	
	//TODO Check this class, Student is UserInformation now
	
	private SessionFactory sessionFactory =  new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserInformation.class)
	.buildSessionFactory();

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public void updateUserInformation(UserInformation u) {
		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(u);
		tx.commit();
		session.close();

	}

	@Override
	public void deleteUserInformation(UserInformation ui) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(ui);
		tx.commit();
		session.close();
	}

	@Override
	public void insertUserInformation(UserInformation ui) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(ui);
		tx.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> SortByPoints() {
		Session session =this.sessionFactory.openSession();
		String hql = "FROM Student f ORDER BY f.points DESC ";
		Query query = session.createQuery(hql);
		List<User> users = query.getResultList();
		session.close();
		return users;
	}
}
