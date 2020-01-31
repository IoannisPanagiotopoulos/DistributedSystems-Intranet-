package gr.hua.ds.users.dao;

import java.util.List;

import gr.hua.ds.users.model.Enums;
import gr.hua.ds.users.model.User;

public interface UserDAO {
	public User getUserByUsernameAndPass(String username, String password);
	public User getUserByUsername(String username);
	
	public List<User> getUsers();
	
	public List<User> getStudents();
	public List<User> getStudentsByDept(Enums.Dept department);
	
	public User getOfficerByUsernameAndPass(String username, String password);
	public User getOfficerByUsername(String username);
	
	public User getStudentByUsername(String username);
	
	public List<User> getOfficers();
	public List<User> getAllOfficers();
	public List<User> getOfficersByDept(Enums.Dept department);
	
	public void insertUserOnly(User u);
	public void insertUser(User u);
	public void updateUser(User oldUser, User newUser);
	public void deleteUser(User u);
}
