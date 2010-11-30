package de.rowbuddy.boundary.dtos;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Route;
import de.rowbuddy.entities.TripMember;

public class PersonalTripDTO {

	private Long id = null;
	private Date startDate = null;
	private Date endDate = null;
	private Boat boat = null;
	private Collection<TripMember> tripMembers = null;
	private Member lastEditor = null;
	private Route route = null;
	private boolean finished = false;
	private TripMember userMember = null;
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
	public Collection<TripMember> getTripMembers() {
		return tripMembers;
	}
	public void setTripMembers(Collection<TripMember> tripMembers) {
		this.tripMembers = tripMembers;
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
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public TripMember getUserMember() {
		return userMember;
	}
	public void setUserMember(TripMember userMember) {
		this.userMember = userMember;
	}	
}
