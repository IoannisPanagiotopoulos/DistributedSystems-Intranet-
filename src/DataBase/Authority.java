package DataBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority {

	@Id
	@Column(name = "user_id")
	private int id;
	
	@Column(name= "username")
	private String username;
	
	@Column(name = "authority_role")
	private Enums.Role authority_role;
	
	public Authority() {
		
	}

	public Authority(String username, Enums.Role authority) {
		super();
		this.username = username;
		this.authority_role = authority;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Enums.Role getAuthority() {
		return authority_role;
	}

	public void setAuthority(Enums.Role authority) {
		this.authority_role = authority;
	}
	
}