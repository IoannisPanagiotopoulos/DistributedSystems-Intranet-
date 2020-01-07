package DataBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import DataBase.Enums.Activable;
import DataBase.Enums.Dept;

@Entity
@Table(name = "students",uniqueConstraints = {@UniqueConstraint(columnNames = {"username","email"})})
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "username")
	private String user;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	@Column(name= "dept")
	@Enumerated(EnumType.STRING)
	private Dept dept;
	
	@Enumerated(EnumType.STRING)
	private Activable activated;
	
	@Column(name= "email")
	private String email;
	@Column(name="points" , columnDefinition = "int default 0")
	private int points;
	
	public Student() {
		
	}

	public Student(String user, String password, String name, Dept department, Activable activated,String email) {
		super();
		this.user = user;
		this.password = password;
		this.name = name;
		this.dept = department;
		this.activated = activated;
		this.email=email;
	}
	
    public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getEmail() {
    	return email;
    }
    public void setEmail(String email) {
    	this.email=email;
    }
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept department) {
		this.dept = department;
	}

	public Activable getActivated() {
		return activated;
	}

	public void setActivated(Activable activated) {
		this.activated = activated;
	}

	
	
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", user=" + user + ", password=" + password + ", name=" + name + ", department="
				+ dept + ", activated=" + activated + "]";
	}
	
}