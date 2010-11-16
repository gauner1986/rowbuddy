package de.rowbuddy.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Boat
 *
 */
@Entity
public class Boat implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name = "";
	private int numberOfSeats = 0;
	private boolean coxed = false;
	private boolean locked = false;
	private boolean deleted = false;
	private static final long serialVersionUID = 1L;

	public Boat() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public int getNumberOfSeats() {
		return this.numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}   
	public boolean isCoxed() {
		return this.coxed;
	}

	public void setCoxed(boolean coxed) {
		this.coxed = coxed;
	}   
	public boolean isLocked() {
		return this.locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}   
	public boolean isDeleted() {
		return this.deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
   
}
