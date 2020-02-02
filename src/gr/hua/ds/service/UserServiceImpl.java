package gr.hua.ds.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.ds.users.dao.UserDAO;
import gr.hua.ds.users.model.Enums.Dept;
import gr.hua.ds.users.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;

	@Transactional
	@Override
	public User getUserByUsernameAndPass(String username, String password) {
		return userDao.getUserByUsernameAndPass(username, password);
	}

	@Transactional
	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Transactional
	@Override
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	@Transactional
	@Override
	public List<User> getStudents() {
		return userDao.getStudents();
	}

	@Transactional
	@Override
	public List<User> getStudentsByDept(Dept department) {
		return userDao.getStudentsByDept(department);
	}

	@Transactional
	@Override
	public User getOfficerByUsernameAndPass(String username, String password) {
		return userDao.getOfficerByUsernameAndPass(username, password);
	}

	@Transactional
	@Override
	public User getOfficerByUsername(String username) {
		return userDao.getOfficerByUsername(username);
	}

	@Transactional
	@Override
	public User getStudentByUsername(String username) {
		return userDao.getStudentByUsername(username);
	}

	@Transactional
	@Override
	public List<User> getOfficers() {
		return userDao.getOfficers();
	}

	@Transactional
	@Override
	public List<User> getAllOfficers() {
		return userDao.getAllOfficers();
	}

	@Transactional
	@Override
	public List<User> getOfficersByDept(Dept department) {
		return userDao.getOfficersByDept(department);
	}

	@Transactional
	@Override
	public void insertUserOnly(User u) {
		userDao.insertUserOnly(u);
	}

	@Transactional
	@Override
	public void insertUser(User u) {
		userDao.insertUser(u);
	}

	@Transactional
	@Override
	public void updateUser(User oldUser, User newUser) {
		userDao.updateUser(oldUser, newUser);
	}

	@Transactional
	@Override
	public void deleteUser(User u) {
		userDao.deleteUser(u);
	}
}
