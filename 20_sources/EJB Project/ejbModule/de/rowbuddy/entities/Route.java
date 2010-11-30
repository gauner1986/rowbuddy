package de.rowbuddy.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

import de.rowbuddy.exceptions.RowBuddyException;

/**
 * Entity implementation class for Entity: Route
 *
 */
@Entity
public class Route implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name = "";
	private String shortDescription = "";
	private long versionNumber = 1;
	@OneToMany
	private List<GpsPoint> wayPoints;
	private double lengthKM = 0;
	private boolean mutable = false;
	private static final long serialVersionUID = 1L;

	public Route() {
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
			throw new NullPointerException("Name cannot be null");
		}
		if(name.isEmpty()) {
			throw new RowBuddyException("Name has to be set");
		}
		this.name = name;
	}
	
	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) throws RowBuddyException {
		if (shortDescription == null){
			throw new NullPointerException("Short description cannot be null");
		}
		if(shortDescription.isEmpty()) {
			throw new RowBuddyException("Short Description has to be set");
		}
		this.shortDescription = shortDescription;
	}  
	
	public Long getVersionNumber() {
		return this.versionNumber;
	}
	
	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}   
	
	public void setWayPoints(List<GpsPoint> wayPoints) {
		this.wayPoints = wayPoints;
	}
	
	public List<GpsPoint> getWayPoints() {
		return wayPoints;
	}
	
	public double getLengthKM() {
		return this.lengthKM;
	}
	
	public void setLengthKM(double lengthKM) throws RowBuddyException {
		if(lengthKM < 0) {
			throw new RowBuddyException("length of route has to bigger than 0 km");
		}
		this.lengthKM = lengthKM;
	}   
	
	public boolean getMutable() {
		return this.mutable;
	}
	public void setMutable(boolean mutable) {
		this.mutable = mutable;
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
		Route other = (Route) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
   
}
