package de.rowbuddy.entities;

import java.io.Serializable;
import java.lang.String;
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
	private Long id;
	private String name = "";
	private int numberOfSeats = 0;
	private boolean coxed = false;
	@OneToMany
	private List<BoatDamage> boatDamages;
	@OneToMany
	private List<BoatReservation> boatReservations;
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

	public void setName(String name) throws RowBuddyException {
		if (name == null){
			throw new RowBuddyException("name of boat has to be set");
		}
		if(name.length() < 1 || name.equals("")) {
			throw new RowBuddyException("name of boat has to be set");
		}
		this.name = name;
	} 
	
	public int getNumberOfSeats() {
		return this.numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) throws RowBuddyException {
		if(numberOfSeats <= 0) {
			throw new RowBuddyException("there has to be at least 1 seat");
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
	
	public void setBoatDamages(List<BoatDamage> boatDamages) {
		this.boatDamages = boatDamages;
	}
	
	public List<BoatReservation> getBoatReservations() {
		return boatReservations;
	}
	
	public void setBoatReservations(List<BoatReservation> boatReservations) {
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
