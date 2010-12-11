package de.rowbuddy.entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import de.rowbuddy.exceptions.RowBuddyException;

/**
 * Entity implementation class for Entity: Route
 * 
 */
@Entity
public class Route implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	private Long parentId = null;
	private String name = "";
	private String shortDescription = "";
	@OneToMany(cascade = CascadeType.ALL)
	private List<GpsPoint> wayPoints = new LinkedList<GpsPoint>();
	private double lengthKM = 0;
	private Member lastEditor = null;
	private boolean mutable = true;
	private boolean deleted = false;
	private static final long serialVersionUID = 1L;

	public Route() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		if (id == null) {
			throw new NullPointerException("Id cannot be null");
		}
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		if (parentId == null) {
			throw new NullPointerException("Parent id cannot be null");
		}
		this.parentId = parentId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) throws RowBuddyException {
		if (name == null) {
			throw new NullPointerException("Name cannot be null");
		}
		if (name.isEmpty()) {
			throw new RowBuddyException("Name has to be set");
		}
		this.name = name;
	}

	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription)
			throws RowBuddyException {
		if (shortDescription == null) {
			throw new NullPointerException("Short description cannot be null");
		}
		if (shortDescription.isEmpty()) {
			throw new RowBuddyException("Short Description has to be set");
		}
		this.shortDescription = shortDescription;
	}

	public Member getLastEditor() {
		return lastEditor;
	}

	public void setLastEditor(Member lastEditor) {
		this.lastEditor = lastEditor;
	}

	public void setWayPoints(List<GpsPoint> wayPoints) throws RowBuddyException {
		if (wayPoints == null) {
			throw new NullPointerException("Waypoints cannot be null");
		}
		if (wayPoints.size() < 2) {
			throw new RowBuddyException("At least two waypoints are needed");
		}
		double length = 0;
		for (int i = 0; i < wayPoints.size() - 1; i++) {
			length += wayPoints.get(i).distanceKmTo(wayPoints.get(i + 1));
		}
		setLengthKM(length);

		this.wayPoints = wayPoints;
	}

	public List<GpsPoint> getWayPoints() {
		return wayPoints;
	}

	public double getLengthKM() {
		return this.lengthKM;
	}

	public void setLengthKM(double lengthKM) throws RowBuddyException {
		if (lengthKM < 0) {
			throw new RowBuddyException(
					"length of route has to be at least 0 km");
		}
		this.lengthKM = lengthKM;
	}

	public boolean isMutable() {
		return mutable;
	}

	public void setMutable(boolean mutable) {
		this.mutable = mutable;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void validate() throws RowBuddyException {
		if (name.trim().isEmpty()) {
			throw new RowBuddyException("Name must not be empty");
		}
		if (lengthKM < 0) {
			throw new RowBuddyException("Lenght must be at least 0 km");
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
		Route other = (Route) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
