package it.by.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import it.by.library.entity.enums.ListRole;

@Entity
@Table(name = "role")
public class UserRole extends Model {

	private static final long serialVersionUID = 6903368164195148637L;
	
	@Column(name="TYPE", length=15, unique=true, nullable=false)
	private String type = ListRole.USER.getType();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserRole() {
		super();
	}

	public UserRole(Long id) {
		super(id);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		UserRole other = (UserRole) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
