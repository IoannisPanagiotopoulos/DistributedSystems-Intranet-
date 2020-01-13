package Services;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import DataBase.Authority;
import DataBase.User;
import iDAO.AuthorityDAO;

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
			if(authority.getUsername().equals(user.getUser())) {
				selectedAuthority = authority;
			}
		}
		
		session.close();
		return selectedAuthority;
	}
   	
	@Override
	public void insertAuthority(User user) {
		Authority authority = new Authority();
		authority.setUsername(user.getUser());
		authority.setAuthority(user.getAuthority().getAuthority());
		
		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(authority);
		tx.commit();
		session.close();
	}

	@Override
	public void updateAuthority(User user) {
		
		Authority authority = new Authority();
		authority.setUsername(user.getUser());
		authority.setAuthority(user.getAuthority().getAuthority());
		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(authority);
		tx.commit();
		session.close();
	}

	@Override
	public void deleteAuthority(User user) {
		
		Authority authority = findAuthority(user);
		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(authority);
		tx.commit();
		session.close();

	}

}
