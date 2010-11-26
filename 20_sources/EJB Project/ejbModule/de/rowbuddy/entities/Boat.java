package de.rowbuddy.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import de.rowbuddy.exceptions.RowBuddyException;

/**
 * Entity implementation class for Entity: Boat
 *
 */
@Entity
public class Boat implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id = null;
	private String name = "";
	private int numberOfSeats = 0;
	private boolean coxed = false;
	@OneToMany
	private List<BoatDamage> boatDamages = new LinkedList<BoatDamage>();
	@OneToMany
	private List<BoatReservation> boatReservations = new LinkedList<BoatReservation>();
	private boolean locked = false;
	private boolean deleted = false;
	private static final long serialVersionUID = 1L;

	public Boat() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) throws RowBuddyException {
		if (id == null){
			throw new NullPointerException("Id cannot be null");
		}
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) throws RowBuddyException {
		if (name == null){
			throw new NullPointerException("Name cannot be null");
		}
		if(name.isEmpty()) {
			throw new RowBuddyException("Name has to be set");
		}
		this.name = name;
	} 
	
	public int getNumberOfSeats() {
		return this.numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) throws RowBuddyException {
		if(numberOfSeats <= 0) {
			throw new RowBuddyException("There has to be at least 1 seat");
		}
		this.numberOfSeats = numberOfSeats;
	}   
	
	public boolean isCoxed() {
		return this.coxed;
	}

	public void setCoxed(boolean coxed) {
		this.coxed = coxed;
	}
	
	public List<BoatDamage> getBoatDamages() {
		return boatDamages;
	}
	
	public void setBoatDamages(List<BoatDamage> boatDamages)  {
		if (boatDamages == null){
			throw new NullPointerException("BoatDamages cannot be null");
		}
		this.boatDamages = boatDamages;
	}
	
	public void addBoatDamage(BoatDamage boatDamage){
		if (boatDamage == null){
			throw new NullPointerException("Damage must contain a value");
		}
		boatDamages.add(boatDamage);
	}
	
	public List<BoatReservation> getBoatReservations() {
		return boatReservations;
	}
	
	public void addBoatReservation(BoatReservation boatReservation){
		if (boatReservation == null){
			throw new NullPointerException("BoatReservation cannot be null");
		}
		boatReservations.add(boatReservation);
	}
	
	public void setBoatReservations(List<BoatReservation> boatReservations) throws RowBuddyException {
		if (boatReservations == null){
			throw new NullPointerException("BoatReservations cannot be null");
		}
		this.boatReservations = boatReservations;
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
	
	public void validate() throws RowBuddyException{
		if (name.isEmpty()){
			throw new RowBuddyException("Name has to be set");
		}
		if(numberOfSeats <= 0) {
			throw new RowBuddyException("There has to be at least 1 seat");
		}
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
		Boat other = (Boat) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
