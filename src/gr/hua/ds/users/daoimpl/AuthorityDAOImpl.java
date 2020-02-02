package gr.hua.ds.users.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.ds.users.dao.AuthorityDAO;
import gr.hua.ds.users.model.Authority;
import gr.hua.ds.users.model.User;


@Repository
public class AuthorityDAOImpl implements AuthorityDAO {

	@Autowired
	private SessionFactory sessionFactory;
   	
   	@Override
   	public Authority findAuthority(User user) {
   		Session session = this.sessionFactory.getCurrentSession();
		List<Authority> authorities = session.createQuery("from Authority",Authority.class).getResultList();
		Authority selectedAuthority = null;
		
		for(Authority authority : authorities) {
			if(authority.getUsername().equals(user.getUsername())) {
				selectedAuthority = authority;
			}
		}
		
		return selectedAuthority;
	}
   	
	@Transactional
	@Override
	public void insertAuthority(Authority authority) {
		Session session = this.sessionFactory.getCurrentSession();

		session.save(authority);

	}

	@Transactional
	@Override
	public void updateAuthority(Authority authority) {
		Session session = this.sessionFactory.getCurrentSession();

		session.saveOrUpdate(authority);

	}

	@Transactional
	@Override
	public void deleteAuthority(Authority authority) {		
		Session session = this.sessionFactory.getCurrentSession();

		session.delete(authority);


	}

}
