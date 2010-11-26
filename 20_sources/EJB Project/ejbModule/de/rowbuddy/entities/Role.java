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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	private String name = "";

	private static final long serialVersionUID = 1L;

	public Role() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws RowBuddyException {
		if (name == null) {
			throw new NullPointerException("Name of role must not be null");
		}
		if (name.isEmpty()) {
			throw new RowBuddyException("Name of role has to be set");
		}
		this.name = name;
	}
}
