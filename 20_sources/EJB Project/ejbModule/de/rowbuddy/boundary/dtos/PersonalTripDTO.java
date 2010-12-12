package de.rowbuddy.boundary.dtos;

import java.io.Serializable;
import java.util.Date;

public class PersonalTripDTO implements Serializable {

	private static final long serialVersionUID = 5901330792234801831L;

	private Long id = null;
	private Date startDate = null;
	private Date endDate = null;
	private BoatDTO boat = null;
	private TripMemberDTO memberRole = null;
	private MemberDTO lastEditor = null;
	private RouteDTO route = null;
	private boolean finished = false;
	private TripMemberDTO userMember = null;

	public PersonalTripDTO() {
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

	public BoatDTO getBoat() {
		return boat;
	}

	public TripMemberDTO getMemberRole() {
		return memberRole;
	}

	public MemberDTO getLastEditor() {
		return lastEditor;
	}

	public RouteDTO getRoute() {
		return route;
	}

	public boolean isFinished() {
		return finished;
	}

	public TripMemberDTO getUserMember() {
		return userMember;
	}

	public void setBoat(BoatDTO boat) {
		this.boat = boat;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLastEditor(MemberDTO lastEditor) {
		this.lastEditor = lastEditor;
	}

	public void setMemberRole(TripMemberDTO memberRole) {
		this.memberRole = memberRole;
	}

	public void setRoute(RouteDTO route) {
		this.route = route;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setUserMember(TripMemberDTO userMember) {
		this.userMember = userMember;
	}
}
