package de.rowbuddy.boundary.dtos;

import java.io.Serializable;

public class BoatDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6280767844327716451L;
	
	private long id;
	private String name;
	private int numberOfSeats;
	private boolean coxed;
	private boolean locked;
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	public boolean isCoxed() {
		return coxed;
	}
	public boolean isLocked() {
		return locked;
	}
	
	public void setCoxed(boolean coxed) {
		this.coxed = coxed;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
}
