package de.rowbuddy.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.rowbuddy.exceptions.RowBuddyException;

/**
 * Entity implementation class for Entity: Role
 * 
 */
@Entity
public class Role implements Serializable {

	public enum RoleName {
		ADMIN, MEMBER
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	private RoleName roleName = RoleName.MEMBER;

	private static final long serialVersionUID = 1L;

	public Role() {
		super();
	}

	public Long getId() {
		return id;
	}

	public RoleName getName() {
		return roleName;
	}

	public void setName(RoleName name) throws RowBuddyException {
		if (name == null) {
			throw new NullPointerException("Rollenname darf nicht null sein");
		}
		this.roleName = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
