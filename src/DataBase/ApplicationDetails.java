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
import DataBase.Enums.empstatus;
import DataBase.*;
@Entity
@Table(name = "applicationdetails", uniqueConstraints = {@UniqueConstraint(columnNames = {"email","username"})})
public class ApplicationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String username;
	

	@Enumerated(EnumType.STRING)
	private Dept dept;

	@Column(name = "fullName")
	private String name;

	@Column(name = "city")
	private String city;
    
	@Column (name= "email")
    private String email;
    
	@Column(name = "income")
	private int income;

	@Column(name = "familyIncome")
	private int familyIncome;

	
	@Enumerated(EnumType.STRING)
	private empstatus parent1_employmentStatus;

	
	@Enumerated(EnumType.STRING)
	private empstatus parent2_employmentStatus;

	@Column(name = "siblingsStudents")
	private int siblingsStudents;

	
	@Enumerated(EnumType.STRING)
	private Activable active;

	public ApplicationDetails() {

	}

	
	public ApplicationDetails(String username, Dept department, String name,String email, String city, int income, int familyIncome,
			empstatus parent1EmploymentStatus, empstatus parent2EmploymentStatus, int siblingsStudents,
			Activable active) {
		super();
		this.username = username;
		this.dept = department;
		this.name = name;
		this.city = city;
		this.income = income;
		this.familyIncome = familyIncome;
		this.parent1_employmentStatus = parent1EmploymentStatus;
		this.parent2_employmentStatus = parent2EmploymentStatus;
		this.siblingsStudents = siblingsStudents;
		this.active = active;
		this.email=email;
	}


   public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Dept getDept() {
		return dept;
	}


	public void setDept(Dept dept) {
		this.dept = dept;
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


	public String getEmail() {
		return email;
	}


public String  getemail() {
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

	public Dept getDepartment() {
		return dept;
	}

	public void setDepartment(Dept department) {
		this.dept = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getIncome() {
		return income;
	}


	public void setIncome(int income) {
		this.income = income;
	}

	public int getFamilyIncome() {
		return familyIncome;
	}

	public void setFamilyIncome(int familyIncome) {
		this.familyIncome = familyIncome;
	}

	public empstatus getParent1EmploymentStatus() {
		return parent1_employmentStatus;
	}

	public void setParent1EmploymentStatus(empstatus parent1EmploymentStatus) {
		this.parent1_employmentStatus = parent1EmploymentStatus;
	}

	public empstatus getParent2EmploymentStatus() {
		return parent2_employmentStatus;
	}

	public void setParent2EmploymentStatus(empstatus parent2EmploymentStatus) {
		this.parent2_employmentStatus = parent2EmploymentStatus;
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
		System.out.println(active);
		this.active = active;
	}


	@Override
	public String toString() {
		return "ApplicationDetails [id=" + id + ", username=" + username + ", name=" + name + ", city=" + city
				+ ", income=" + income + ", familyIncome=" + familyIncome + ", parent1EmploymentStatus="
				+ parent1_employmentStatus + ", parent2EmploymentStatus=" + parent2_employmentStatus
				+ ", siblingsStudents=" + siblingsStudents + "]";
	}

}