package de.rowbuddy.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TripMember
 *
 */
@Entity
public class TripMember implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Member member;
	
	private TripMemberType tripMemberType;
	private static final long serialVersionUID = 1L;

	public TripMember() {
		super();
	}   
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}   
	public TripMemberType getTripMemberType() {
		return this.tripMemberType;
	}

	public void setTripMemberType(TripMemberType tripMemberType) {
		this.tripMemberType = tripMemberType;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
   
}
