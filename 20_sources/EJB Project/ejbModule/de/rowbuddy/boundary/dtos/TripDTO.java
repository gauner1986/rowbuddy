package de.rowbuddy.boundary.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.Route;

public class TripDTO implements Serializable {

	private static final long serialVersionUID = 1201358638320472393L;

	private Long id = null;
	private Date startDate = null;
	private Date endDate = null;
	private Boat boat = null;
	private List<TripMemberDTO> tripMembers = null;
	private MemberDTO lastEditor = null;
	private Route route = null;
	private boolean finished = false;
	private boolean canEditTrip = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boat getBoat() {
		return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	public List<TripMemberDTO> getTripMembers() {
		return tripMembers;
	}

	public void setTripMembers(List<TripMemberDTO> tripMembers) {
		this.tripMembers = tripMembers;
	}

	public MemberDTO getLastEditor() {
		return lastEditor;
	}

	public void setLastEditor(MemberDTO lastEditor) {
		this.lastEditor = lastEditor;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public boolean isCanEditTrip() {
		return canEditTrip;
	}

	public void setCanEditTrip(boolean canEditTrip) {
		this.canEditTrip = canEditTrip;
	}

}
