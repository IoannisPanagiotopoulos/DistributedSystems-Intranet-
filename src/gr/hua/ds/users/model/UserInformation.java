package gr.hua.ds.users.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_information")
public class UserInformation {
	
	@Id
	@Column(name= "username")
	private String username;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "department_name")
	private Enums.Dept departmentName;
	
	@Column(name = "points")
	private int points;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "activated")
	private Enums.Activable activated;
	
	public UserInformation() {
		
	}

	public UserInformation(String username, String name, String email, Enums.Dept departmentName, int points, Enums.Activable activated) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.departmentName = departmentName;
		this.points = points;
		this.activated = activated;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Enums.Dept getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(Enums.Dept departmentName) {
		this.departmentName = departmentName;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Enums.Activable getActivated() {
		return activated;
	}

	public void setActivated(Enums.Activable activated) {
		this.activated = activated;
	}
	
}