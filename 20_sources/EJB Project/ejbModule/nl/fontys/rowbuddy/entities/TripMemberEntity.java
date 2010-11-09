package nl.fontys.rowbuddy.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TripMemberEntity
 *
 */
@Entity
public class TripMemberEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private MemberEntity member;
	
	private TripMemberType tripMemberType;
	private static final long serialVersionUID = 1L;

	public TripMemberEntity() {
		super();
	}   
	public MemberEntity getMember() {
		return this.member;
	}

	public void setMember(MemberEntity member) {
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
