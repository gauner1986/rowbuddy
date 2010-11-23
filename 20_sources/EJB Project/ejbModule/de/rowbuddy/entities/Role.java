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
	private Long id;
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
		if(name.length() < 1 || name.equals("") || name == null) {
			throw new RowBuddyException("name of role has to be set");
		}
		this.name = name;
	}
   
}
