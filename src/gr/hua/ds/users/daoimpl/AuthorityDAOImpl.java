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

import gr.hua.ds.users.dao.AuthorityDAO;
import gr.hua.ds.users.model.Authority;
import gr.hua.ds.users.model.User;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO {

	public SessionFactory getSessionFactory() {
		Properties properties = new Properties();
		try {
			properties.load(getClass().getResourceAsStream("/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addProperties(properties).addAnnotatedClass(Authority.class).buildSessionFactory();
		
		return sessionFactory;
	}
	
	
	
	
	private SessionFactory sessionFactory =  getSessionFactory();

   	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
   	
   	@Override
   	public Authority findAuthority(User user) {
   		Session session = this.sessionFactory.openSession();
		List<Authority> authorities = session.createQuery("from Authority",Authority.class).getResultList();
		Authority selectedAuthority = null;
		
		for(Authority authority : authorities) {
			if(authority.getUsername().equals(user.getUsername())) {
				selectedAuthority = authority;
			}
		}
		
		session.close();
		return selectedAuthority;
	}
   	
	@Override
	public void insertAuthority(Authority authority) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(authority);
		tx.commit();
		session.close();
	}

	@Override
	public void updateAuthority(Authority authority) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(authority);
		tx.commit();
		session.close();
	}

	@Override
	public void deleteAuthority(Authority authority) {		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(authority);
		tx.commit();
		session.close();

	}

}
