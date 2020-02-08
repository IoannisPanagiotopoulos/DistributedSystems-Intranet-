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
	@Column(name = "department_name", columnDefinition="ENUM('Informatics','Geography','Dietics','Economics)")
	private Enums.Dept departmentName;
	
	@Column(name = "points")
	private int points;
	
	@Column(name = "rank")
	private Integer rank;
	
	@Column(name = "totalRanks")
	private int totalRanks;
	
	@Column(name = "hasFreeMeal")
	private Integer hasFreeMeal;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "activated", columnDefinition="ENUM('active','inactive')")
	private Enums.Activable activated;
	
	public UserInformation() {
		
	}

	public UserInformation(String username, String name, String email, Enums.Dept departmentName, 
			int points, Integer rank, int totalRanks, Integer hasFreeMeal, Enums.Activable activated) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.departmentName = departmentName;
		this.points = points;
		this.rank = rank;
		this.totalRanks = totalRanks;
		this.hasFreeMeal = hasFreeMeal;
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
	
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public int getTotalRanks() {
		return totalRanks;
	}

	public void setTotalRanks(int totalRanks) {
		this.totalRanks = totalRanks;
	}

	public Integer getHasFreeMeal() {
		return hasFreeMeal;
	}

	public void setHasFreeMeal(Integer hasFreeMeal) {
		this.hasFreeMeal = hasFreeMeal;
	}

	public Enums.Activable getActivated() {
		return activated;
	}

	public void setActivated(Enums.Activable activated) {
		this.activated = activated;
	}
	
}