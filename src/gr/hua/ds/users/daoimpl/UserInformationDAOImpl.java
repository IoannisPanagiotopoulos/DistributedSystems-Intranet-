package gr.hua.ds.users.daoimpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import gr.hua.ds.users.dao.UserInformationDAO;
import gr.hua.ds.users.model.User;
import gr.hua.ds.users.model.UserInformation;

@Repository
public class UserInformationDAOImpl implements UserInformationDAO {
	
	public SessionFactory getSessionFactory() {
		Properties properties = new Properties();
		try {
			properties.load(getClass().getResourceAsStream("/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addProperties(properties).addAnnotatedClass(UserInformation.class).buildSessionFactory();
		
		return sessionFactory;
	}
	
	private SessionFactory sessionFactory =  getSessionFactory();

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
