package gr.hua.ds.users.daoimpl;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.ds.users.dao.UserInformationDAO;
import gr.hua.ds.users.model.User;
import gr.hua.ds.users.model.UserInformation;


@Repository
public class UserInformationDAOImpl implements UserInformationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void updateUserInformation(UserInformation u) {
		
		Session session = this.sessionFactory.getCurrentSession();

		session.saveOrUpdate(u);


	}

	@Transactional
	@Override
	public void deleteUserInformation(UserInformation ui) {
		Session session = this.sessionFactory.getCurrentSession();

		session.delete(ui);

	}

	@Transactional
	@Override
	public void insertUserInformation(UserInformation ui) {
		Session session = this.sessionFactory.getCurrentSession();

		session.save(ui);

	}
	
	@SuppressWarnings("unchecked")
	public List<User> SortByPoints() {
		Session session =this.sessionFactory.getCurrentSession();
		String hql = "FROM Student f ORDER BY f.points DESC ";
		Query query = session.createQuery(hql);
		List<User> users = query.getResultList();
		return users;
	}
}
