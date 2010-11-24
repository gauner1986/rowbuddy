package de.rowbuddy.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.persistence.*;

import de.rowbuddy.exceptions.RowBuddyException;

/**
 * Entity implementation class for Entity: Trip
 * 
 */
@Entity
public class Trip implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate = null;
	@ManyToOne
	private Boat boat = null;
	@OneToMany(cascade=CascadeType.ALL)
	private Collection<TripMember> tripMembers = new LinkedList<TripMember>();
	@ManyToOne
	private Member lastEditor = null;
	@ManyToOne
	private Route route = null;
	private static final long serialVersionUID = 1L;

	public Trip() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) throws RowBuddyException {
		if (endDate.before(startDate)) {
			throw new RowBuddyException("enddate has to be after the startdate");
		}
		this.endDate = endDate;
	}

	public Boat getBoat() {
		return this.boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	public Collection<TripMember> getTripMembers() {
		return tripMembers;
	}

	public void setTripMembers(Collection<TripMember> tripMembers)
			throws RowBuddyException {
		if (tripMembers == null) {
			throw new RowBuddyException("tripmembers must not be null");
		}
		this.tripMembers = tripMembers;
	}

	public void addTripMember(TripMember member) throws RowBuddyException {
		if (member == null) {
			throw new RowBuddyException("tripmember must not be null");
		}
		tripMembers.add(member);
	}

	public Member getLastEditor() {
		return lastEditor;
	}

	public void setLastEditor(Member lastEditor) {
		this.lastEditor = lastEditor;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Trip other = (Trip) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	public void validate() throws RowBuddyException {
		if (route == null) {
			throw new RowBuddyException("Route must be set");
		}
		
		if (boat == null){
			throw new RowBuddyException("A boat must be selected");
		}
		
		if (tripMembers.size() == 0){
			throw new RowBuddyException("Please select tripmembers");
		}
		
		if (endDate != null){
			if (startDate.after(endDate)){
				throw new RowBuddyException("End date must be before start date");
			}
		}
	}
}
