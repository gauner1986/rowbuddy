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
	private Long id = null;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate = null;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate = null;
	@ManyToOne
	private Boat boat = null;
	@OneToMany(cascade = CascadeType.ALL)
	private Collection<TripMember> tripMembers = new LinkedList<TripMember>();
	@ManyToOne
	private Member lastEditor = null;
	@ManyToOne
	private Route route = null;
	private boolean finished = false;
	private static final long serialVersionUID = 1L;

	public Trip() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long newId) {
		this.id = newId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date newStartDate) {
		if (newStartDate == null){
			throw new NullPointerException("Start date must not be null");
		}
		this.startDate = newStartDate;
	}
	
	public void startNow(){
		setStartDate(new Date());
	}
	
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date newEndDate) {
		if (newEndDate == null){
			throw new NullPointerException("End date must not be null");
		}
		this.endDate = newEndDate;
		this.finished = true;
	}

	public Boat getBoat() {
		return this.boat;
	}

	public void setBoat(Boat newBoat) {
		if (newBoat == null) {
			throw new NullPointerException("Boat must not be null");
		}
		this.boat = newBoat;
	}

	public void removeBoat() {
		if (boat == null) {
			throw new IllegalStateException("Boat is not set");
		}
		this.boat = null;
	}

	public Collection<TripMember> getTripMembers() {
		return tripMembers;
	}

	public void setTripMembers(Collection<TripMember> newTripMembers) {
		if (newTripMembers == null) {
			throw new NullPointerException("Tripmembers must not be null");
		}
		this.tripMembers = newTripMembers;
	}

	public void addTripMember(TripMember newMember) {
		if (newMember == null) {
			throw new NullPointerException("Tripmember must not be null");
		}
		tripMembers.add(newMember);
	}

	public Member getLastEditor() {
		return lastEditor;
	}

	public void setLastEditor(Member newLastEditor) {
		if (newLastEditor == null) {
			throw new NullPointerException("Last editor must not be null");
		}
		this.lastEditor = newLastEditor;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route newRoute) {
		if (newRoute == null) {
			throw new NullPointerException("Route must not be null");
		}
		this.route = newRoute;
	}

	public void removeRoute() {
		if (this.route == null) {
			throw new IllegalStateException("Route is not set");
		}
		this.route = null;
	}

	public boolean isFinished() {
		return finished;
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
	
	public void validateStartedTrip() throws RowBuddyException{
		
		if (startDate == null){
			throw new RowBuddyException("Start date must be set");
		}
	}
	
	public void validateFinishedTrip() throws RowBuddyException {

		validateStartedTrip();
		
		if (endDate == null){
			throw new RowBuddyException("End date must be set");
		}
		
		if (startDate.after(endDate)) {
			throw new RowBuddyException(
					"End date must be before start date");
		}
		
		if (route == null) {
			throw new RowBuddyException("A Route must be set");
		}

		if (boat == null) {
			throw new RowBuddyException("A boat must be set");
		}

		if (tripMembers.size() == 0) {
			throw new RowBuddyException("Please add tripmembers");
		}
		
	}
}
