package gr.hua.ds.users.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority {
	
	@Id
	@Column(name= "username")
	private String username;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "authority", columnDefinition="ENUM('ROLE_ADMIN','ROLE_SUPERVISOR','ROLE_OFFICER','ROLE_STUDENT')")
	private Enums.Role authorityRole;
	
	public Authority() {
		
	}

	public Authority(String username, Enums.Role authorityRole) {
		super();
		this.username = username;
		this.authorityRole = authorityRole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Enums.Role getAuthorityRole() {
		return authorityRole;
	}

	public void setAuthorityRole(Enums.Role authorityRole) {
		this.authorityRole = authorityRole;
	}
	
}