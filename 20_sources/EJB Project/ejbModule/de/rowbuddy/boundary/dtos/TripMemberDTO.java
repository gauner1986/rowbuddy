package de.rowbuddy.boundary.dtos;

import java.io.Serializable;

import de.rowbuddy.entities.TripMemberType;

public class TripMemberDTO implements Serializable {

	private static final long serialVersionUID = 77739021871193187L;

	private Long id;
	private MemberDTO member;
	private TripMemberType tripMemberType;

	public Long getId() {
		return id;
	}

	public MemberDTO getMember() {
		return member;
	}

	public TripMemberType getTripMemberType() {
		return tripMemberType;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMember(MemberDTO member) {
		this.member = member;
	}

	public void setTripMemberType(TripMemberType tripMemberType) {
		this.tripMemberType = tripMemberType;
	}
}
