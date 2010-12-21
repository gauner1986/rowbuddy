package de.rowbuddy.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private List<TripMember> tripMembers = new LinkedList<TripMember>();
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
		this.startDate = newStartDate;
	}

	public void startNow() {
		setStartDate(new Date());
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date newEndDate) {
		this.endDate = newEndDate;
	}

	public Boat getBoat() {
		return this.boat;
	}

	public void setBoat(Boat newBoat) {
		if (newBoat == null) {
			throw new NullPointerException("Boot darf nicht null sein");
		}
		this.boat = newBoat;
	}

	public void removeBoat() {
		if (boat == null) {
			throw new IllegalStateException("Es gibt kein Boot");
		}
		this.boat = null;
	}

	public List<TripMember> getTripMembers() {
		return tripMembers;
	}

	public void setTripMembers(List<TripMember> newTripMembers) {
		if (newTripMembers == null) {
			throw new NullPointerException(
					"Tripteilnehmer darf nicht null sein");
		}
		this.tripMembers = newTripMembers;
	}

	public void addTripMember(TripMember newMember) {
		if (newMember == null) {
			throw new NullPointerException(
					"Tripteilnehmer darf nicht null sein");
		}
		if (newMember.getMember() == null) {
			throw new NullPointerException(
					"Tripteilnehmer 'member' darf nicht null sein");
		}
		tripMembers.add(newMember);
	}

	public Member getLastEditor() {
		return lastEditor;
	}

	public void setLastEditor(Member newLastEditor) {
		if (newLastEditor == null) {
			throw new NullPointerException("Mitglied darf nicht null sein");
		}
		this.lastEditor = newLastEditor;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route newRoute) {
		if (newRoute == null) {
			throw new NullPointerException("Route darf nicht null sein");
		}
		this.route = newRoute;
	}

	public void removeRoute() {
		if (this.route == null) {
			throw new IllegalStateException("Route ist null");
		}
		this.route = null;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
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

	public void validateStartedTrip() throws RowBuddyException {

		if (startDate == null) {
			throw new RowBuddyException("Startdatum darf nicht null sein");
		}
	}

	public void validateFinishedTrip() throws RowBuddyException {

		validateStartedTrip();

		if (endDate == null) {
			throw new RowBuddyException("Enddatum darf nicht null sein");
		}

		if (startDate.after(endDate)) {
			throw new RowBuddyException(
					"Startdatum muss vor dem Enddatum liegen");
		}

		if (route == null) {
			throw new RowBuddyException("Route darf nicht null sein");
		}

		if (boat == null) {
			throw new RowBuddyException("Boat darf nicht null sein");
		}

		if (tripMembers.size() == 0) {
			throw new RowBuddyException(
					"Es muessen Tripteilnehmer angegeben werden");
		}

	}

	public TripMember getTripMemberFor(Member member) {
		for (TripMember tm : tripMembers) {
			if (tm.getMember().equals(member)) {
				return tm;
			}
		}
		return null;
	}
}
