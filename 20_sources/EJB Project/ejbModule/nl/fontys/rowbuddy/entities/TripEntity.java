package nl.fontys.rowbuddy.entities;

import java.io.Serializable;
import java.lang.Long;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Trip
 *
 */
@Entity
public class TripEntity implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	@ManyToOne
	private BoatEntity boat;
	@OneToMany
	private Collection<TripMemberEntity> tripMembers;
	@ManyToOne
	private MemberEntity lastEditor;
	@ManyToOne
	private RouteEntity route;
	private static final long serialVersionUID = 1L;

	public TripEntity() {
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

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}   
	public BoatEntity getBoat() {
		return this.boat;
	}

	public void setBoat(BoatEntity boat) {
		this.boat = boat;
	}
	
	public Collection<TripMemberEntity> getTripMembers() {
		return tripMembers;
	}
	
	public void setTripMembers(Collection<TripMemberEntity> tripMembers) {
		this.tripMembers = tripMembers;
	}
	
	public MemberEntity getLastEditor() {
		return lastEditor;
	}
	
	public void setLastEditor(MemberEntity lastEditor) {
		this.lastEditor = lastEditor;
	}
	
	public RouteEntity getRoute() {
		return route;
	}
	
	public void setRoute(RouteEntity route) {
		this.route = route;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TripEntity other = (TripEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
