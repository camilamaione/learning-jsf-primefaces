package model.entities;
// Generated 21/11/2019 18:42:30 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.validators.UsernameNotExists;

@Entity
@Table(name = "user")
public class User implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String password;
	private String username;
	private String firstname;
	private String lastname;

	public User() {
	}

	public User(int id, String password, String username) {
		this.id = id;
		this.password = password;
		this.username = username;
	}

	public User(int id, String password, String username, String firstname, String lastname) {
		this.id = id;
		this.password = password;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "username", unique = true, nullable = false)
	@UsernameNotExists(message = "Username already exists")
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "firstname")
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "lastname")
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
