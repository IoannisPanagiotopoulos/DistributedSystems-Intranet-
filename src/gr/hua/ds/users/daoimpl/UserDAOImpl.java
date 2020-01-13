package gr.hua.ds.users.daoimpl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import gr.hua.ds.users.dao.AuthorityDAO;
import gr.hua.ds.users.dao.UserDAO;
import gr.hua.ds.users.dao.UserInformationDAO;
import gr.hua.ds.users.model.Enums.*;
import gr.hua.ds.users.model.User;

public class UserDAOImpl implements UserDAO {
	
	private SessionFactory sessionFactory =  new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
			.buildSessionFactory();

   	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
   	
   	AuthorityDAO authorityDAO = new AuthorityDAOImpl();
   	UserInformationDAO userInformationDAO = new UserInformationDAOImpl();

	public void setAd(AuthorityDAO authorityDAO) {
		this.authorityDAO = authorityDAO;
	}
	
	public void setUid(UserInformationDAO userInformationDAO) {
		this.userInformationDAO = userInformationDAO;
	}

	@Override
	public User getUserByUsernameAndPass(String username, String password) {
		Session session = this.sessionFactory.openSession();
		User user = null;
		try {
			user = session.createQuery("from User u where u.username=:username "
					+ "and u.password=:password", User.class).setParameter("username", username)
					.setParameter("password", password).getSingleResult();
		}
		catch (NoResultException nre) {
		
		}
		session.close();
		return user;
	}
	
	@Override
	public User getOfficerByUsernameAndPass(String username, String password) {
		Session session = this.sessionFactory.openSession();
		User user = null;
		try {
			user = session.createQuery("select u from User u inner join u.authority a "
					+ "where u.username=:username "
					+ "and u.password=:password and a.authorityRole!=:authority", User.class)
					.setParameter("username", username)
					.setParameter("password", password)
					.setParameter("authority", Role.ROLE_STUDENT).getSingleResult();
		}
		catch (NoResultException nre) {
		
		}
		session.close();
		return user;
	}
	
	@Override
	public User getUserByUsername(String username) {
		Session session = this.sessionFactory.openSession();
		List<User> users = session.createQuery("from User",User.class).getResultList();
		User selectedUser = null;
		
		for(User user : users) {
			if(user.getUsername().equals(username)) {
				selectedUser = user;
			}
		}
		
		session.close();
		return selectedUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getStudents() {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("select u from User u inner join u.authority a inner join u.userInformation ui "
				+ "where a.authorityRole=:role order by ui.departmentName asc");
		query.setParameter("role", Role.ROLE_STUDENT);
		
		List<User> students = query.getResultList();
		session.close();
		return students;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getStudentsByDept(Dept department) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("select u from User u inner join u.authority a inner join u.userInformation ui "
				+ "where ui.departmentName=:department and a.authorityRole=:role");
		query.setParameter("department", department);
		query.setParameter("role", Role.ROLE_STUDENT);

		List<User> users = query.getResultList();
		
		session.close();
		return users;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getOfficers() {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("select u from User u inner join u.authority a inner join u.userInformation ui "
				+ "where a.authorityRole=:role1 "
				+ "or a.authorityRole=:role2 "
				+ "order by ui.departmentName asc");
		
		query.setParameter("role1", Role.ROLE_SUPERVISOR);
		query.setParameter("role2", Role.ROLE_OFFICER);
		
		List<User> officers = query.getResultList();
		session.close();
		return officers;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllOfficers() {
		Session session = this.sessionFactory.openSession();
		List<User> students = session.createQuery("select u from User u inner join u.authority a "
				+ "inner join u.userInformation ua "
				+ "where a.authorityRole!=role "
				+ "order by ua.departmentName asc").setParameter("role", Role.ROLE_STUDENT).getResultList();
		session.close();
		return students;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getOfficersByDept(Dept department) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("select u from User u inner join u.authority a "
				+ "inner join u.userInformation ua "
				+ "where ua.departmentName=:department and a.authorityRole=:role1 and a.authorityRole=:role2");
		query.setParameter("department", department);
		query.setParameter("role1", Role.ROLE_OFFICER);
		query.setParameter("role2", Role.ROLE_SUPERVISOR);

		List<User> users = query.getResultList();
		
		session.close();
		return users;
	}

	@Override
	public void insertUser(User user) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(user);
		
		tx.commit();
		session.close();
		
		this.authorityDAO.insertAuthority(user.getAuthority());
		this.userInformationDAO.insertUserInformation(user.getUserInformation());
	}
	
	@Override
	public void updateUser(User oldUser, User newUser) {	
		deleteUser(oldUser);
	
		insertUser(newUser);
//		Session session = this.sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
//		session.saveOrUpdate(user);
//		tx.commit();
//		session.close();
	}

	@Override
	public void deleteUser(User user) {
		
		this.authorityDAO.deleteAuthority(user.getAuthority());
		this.userInformationDAO.deleteUserInformation(user.getUserInformation());
		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(user);
		tx.commit();
		session.close();
	}

	@Override
	public User getOfficerByUsername(String username) {
		Session session = this.sessionFactory.openSession();
		User user = null;
		try {
			user = session.createQuery("select u from User u inner join u.authority a "
					+ "where u.username=:username "
					+ "and a.authorityRole!=:authority", User.class)
					.setParameter("username", username)
					.setParameter("authority", Role.ROLE_STUDENT).getSingleResult();
		}
		catch (NoResultException nre) {
		
		}
		session.close();
		return user;
	}
	
	@Override
	public User getStudentByUsername(String username) {
		Session session = this.sessionFactory.openSession();
		User user = null;
		try {
			user = session.createQuery("select u from User u inner join u.authority a "
					+ "where u.username=:username "
					+ "and a.authorityRole=:authority", User.class)
					.setParameter("username", username)
					.setParameter("authority", Role.ROLE_STUDENT).getSingleResult();
		}
		catch (NoResultException nre) {
		
		}
		session.close();
		return user;
	}
	
}
