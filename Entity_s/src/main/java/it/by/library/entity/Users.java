package it.by.library.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.cfg.context.Cascadable;

@Entity
@Table(name="users")                             
public class Users extends Model{
	
	private static final long serialVersionUID = 8337526330718771366L;
	@Column(name="name", unique=true , nullable=false  )
	private String name;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="valid")
	private boolean valid;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_reole",joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<UserRole> userRoles = new HashSet<>();
	
	
	
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public  Users(){}
	
	public Users(String name, String password, boolean valid) {
		super();
		this.name = name;
		this.password = password;
		this.valid = valid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userRoles == null) ? 0 : userRoles.hashCode());
		result = prime * result + (valid ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userRoles == null) {
			if (other.userRoles != null)
				return false;
		} else if (!userRoles.equals(other.userRoles))
			return false;
		if (valid != other.valid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Users [name=" + name + ", password=" + password + ", valid=" + valid + ", userRole=" + userRoles + "]";
	}
	

}
