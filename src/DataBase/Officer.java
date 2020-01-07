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

import DataBase.Enums.Dept;
import DataBase.Enums.Role;

	@Entity
	@Table(name = "officers" ,uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
	public class Officer {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private int id;
		
		@Column(name = "username")
		private String username;
		
		@Column(name = "password")
		private String password;
		
		
		@Enumerated(EnumType.STRING)
		private Dept department;
		
		@Enumerated(EnumType.STRING)
		private Role role;
		
		public Officer() {
			
		}

		public Officer(String username, String password, Dept department, Role role) {
			super();
			this.username = username;
			this.password = password;
			this.department = department;
			this.role = role;
		};
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id=id;
		}
		
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public Dept getDepartment() {
			return department;
		}

		public void setDepartment(Dept department) {
			this.department = department;
		}
		
		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		@Override
		public String toString() {
			return "Officer [id=" + id + ", username=" + username + ", password=" + password + ", department=" + department
					+ ", role=" + role + "]";
		}
}
