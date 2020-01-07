package DataBase;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import DataBase.Enums.Activable;
import DataBase.Enums.Dept;
import DataBase.Enums.Role;
import DataBase.Enums.empstatus;

@Entity
@Table(name = "applications")
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Enumerated(EnumType.STRING)
	private Dept dept;
	
	@Column(name= "percentage")
	private int percentage;
	
	@Enumerated(EnumType.STRING)
	private Activable  active;

	public Application() {
		
	}
	
	public Application(Dept department, Activable  active, int percentage) {
		super();
		this.dept = department;
		this.active = active;
		this.percentage = percentage;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept department) {
		this.dept = department;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	
	public Activable getActive() {
		return active;
	}

	public void setActive(Activable active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "Application [id=" + id + ", department=" + dept + ", percentage="+ percentage + ", active=" + active + "]";
	}
	
}