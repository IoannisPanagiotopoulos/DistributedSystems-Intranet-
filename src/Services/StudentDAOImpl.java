package Services;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import DataBase.Enums;
import DataBase.Student;
import DataBase.Enums.Dept;
import iDAO.StudentDAO;

public class StudentDAOImpl implements StudentDAO {
	
	private SessionFactory sessionFactory =  new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
	.buildSessionFactory();

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public void updateUser(Student s) {
		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(s);
		tx.commit();
		session.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public Student getUser(String username, String password) {


		Session session = this.sessionFactory.openSession();
		List<Student> users = session.createQuery("from Student",Student.class).getResultList();
		Student selectedUser = null;
		
		for(Student user : users) {
			if(user.getUser().equals(username) && user.getPassword().equals(password)) {
				selectedUser = user;
			}
		}
		
		session.close();
		return selectedUser;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getUsers() {	
		Session session = this.sessionFactory.openSession();
		List<Student> users = session.createQuery("from Student order by dept asc").getResultList();
		session.close();
		return users;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getUsers(Dept department) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from Student s where s.dept=:department");
		query.setParameter("department", department);

		List<Student> users = query.getResultList();
		
		session.close();
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Student getUser(int userId) {
		
		Session session = this.sessionFactory.openSession();
		Query query =  session.createQuery("from Student s where s.id=:userId");
		query.setParameter("userId", userId);
		List<Student> users = query.getResultList();
		Student selectedUser = null;
		
		for(Student user : users) {
			selectedUser = user;
		}
		session.close();
		return selectedUser;
	}

	@Override
	public void deleteUser(Student s) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(s);
		tx.commit();
		session.close();
	}

	@Override
	public void insertUser(Student s) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(s);
		tx.commit();
		session.close();
	}
	public List<Student> SortByPoints() {
		Session session =this.sessionFactory.openSession();
		String hql = "FROM Student f ORDER BY f.points DESC ";
		Query query = session.createQuery(hql);
		List<Student> users = query.getResultList();
		session.close();
		return users;
	}

	@Override
	public Student getUser(String username) {
		Session session = this.sessionFactory.openSession();
		List<Student> users = session.createQuery("from Student",Student.class).getResultList();
		Student selectedUser = null;
		
		for(Student user : users) {
			if(user.getUser().equals(username)) {
				selectedUser = user;
			}
		}
		
		session.close();
		return selectedUser;
	}
}
