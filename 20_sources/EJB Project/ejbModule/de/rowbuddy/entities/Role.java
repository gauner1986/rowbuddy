package de.rowbuddy.entities;

import java.io.Serializable;
import javax.persistence.*;

import de.rowbuddy.exceptions.RowBuddyException;

/**
 * Entity implementation class for Entity: Role
 * 
 */
@Entity
public class Role implements Serializable {
	
	public enum RoleName {ADMIN,MEMBER}

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
			throw new NullPointerException("Name of role must not be null");
		}
		this.roleName = name;
	}
}
