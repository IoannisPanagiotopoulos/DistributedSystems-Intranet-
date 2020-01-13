package gr.hua.ds.users.daoimpl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import gr.hua.ds.users.dao.AuthorityDAO;
import gr.hua.ds.users.model.Authority;
import gr.hua.ds.users.model.User;

public class AuthorityDAOImpl implements AuthorityDAO {

	private SessionFactory sessionFactory =  new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Authority.class)
			.buildSessionFactory();

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
