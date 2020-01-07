package iDAO;


import java.util.List;

import DataBase.Enums;
import DataBase.Student;
import DataBase.Enums.Dept;

public interface StudentDAO {
	
	public Student getUser(String username, String password);
	public List<Student> getUsers();
	public List<Student> getUsers(Dept department);
	public Student getUser(int userId);
	public void updateUser(Student s);
	public void deleteUser(Student s);
	public void insertUser(Student s);
	public Student getUser(String username);
}