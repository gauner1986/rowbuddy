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
	private TripMember memberRole = null;
	private Member lastEditor = null;
	private Route route = null;
	private boolean finished = false;
	private TripMember userMember = null;

	public PersonalTripDTO(Long id, Date startDate, Date endDate, Boat boat,
			TripMember memberRole, Member lastEditor, Route route,
			boolean finished) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.boat = boat;
		this.memberRole = memberRole;
		this.lastEditor = lastEditor;
		this.route = route;
		this.finished = finished;
	}

	public Long getId() {
		return id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Boat getBoat() {
		return boat;
	}

	public TripMember getMemberRole() {
		return memberRole;
	}

	public Member getLastEditor() {
		return lastEditor;
	}

	public Route getRoute() {
		return route;
	}

	public boolean isFinished() {
		return finished;
	}

	public TripMember getUserMember() {
		return userMember;
	}
}
