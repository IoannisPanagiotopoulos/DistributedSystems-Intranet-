package gr.hua.ds.users.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import gr.hua.ds.users.model.Enums.*;

@Entity
@Table(name = "application")
public class Application {

	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "department_name")
	@Enumerated(EnumType.STRING)
	private Dept departmentName;
	
	@Column(name = "city")
	private String city;
    
	@Column(name = "personalIncome")
	private int personalIncome;

	@Column(name = "familyIncome")
	private int familyIncome;
	
	@Column(name = "parent1_employmentStatus")
	@Enumerated(EnumType.STRING)
	private empstatus parent1_employmentStatus;
	
	@Column(name = "parent2_employmentStatus")
	@Enumerated(EnumType.STRING)
	private empstatus parent2_employmentStatus;

	@Column(name = "siblingsStudents")
	private int siblingsStudents;
	
	@Column(name = "active")
	@Enumerated(EnumType.STRING)
	private Activable active;

	public Application() {
		
	}
	
	public Application(String username, Dept departmentName, String city, int personalIncome, int familyIncome,
			empstatus parent1_employmentStatus, empstatus parent2_employmentStatus, int siblingsStudents,
			Activable active) {
		super();
		this.username = username;
		this.departmentName = departmentName;
		this.city = city;
		this.personalIncome = personalIncome;
		this.familyIncome = familyIncome;
		this.parent1_employmentStatus = parent1_employmentStatus;
		this.parent2_employmentStatus = parent2_employmentStatus;
		this.siblingsStudents = siblingsStudents;
		this.active = active;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Dept getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(Dept departmentName) {
		this.departmentName = departmentName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPersonalIncome() {
		return personalIncome;
	}

	public void setPersonalIncome(int personalIncome) {
		this.personalIncome = personalIncome;
	}

	public int getFamilyIncome() {
		return familyIncome;
	}

	public void setFamilyIncome(int familyIncome) {
		this.familyIncome = familyIncome;
	}

	public empstatus getParent1_employmentStatus() {
		return parent1_employmentStatus;
	}

	public void setParent1_employmentStatus(empstatus parent1_employmentStatus) {
		this.parent1_employmentStatus = parent1_employmentStatus;
	}

	public empstatus getParent2_employmentStatus() {
		return parent2_employmentStatus;
	}

	public void setParent2_employmentStatus(empstatus parent2_employmentStatus) {
		this.parent2_employmentStatus = parent2_employmentStatus;
	}

	public int getSiblingsStudents() {
		return siblingsStudents;
	}

	public void setSiblingsStudents(int siblingsStudents) {
		this.siblingsStudents = siblingsStudents;
	}

	public Activable getActive() {
		return active;
	}

	public void setActive(Activable active) {
		this.active = active;
	}

	
}