package de.rowbuddy.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: BoatReservation
 *
 */
@Entity
public class BoatReservation implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToMany
	private List<Boat> boats;
	@Temporal(TemporalType.TIMESTAMP)
	private Date from;
	@Temporal(TemporalType.TIMESTAMP)
	private Date until;
	@ManyToOne
	private Member reserver;
	private String description = "";
	private static final long serialVersionUID = 1L;

	public BoatReservation() {
		super();
	} 
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Boat> getBoats() {
		return boats;
	}

	public void setBoats(List<Boat> boats) {
		this.boats = boats;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getUntil() {
		return until;
	}

	public void setUntil(Date until) {
		this.until = until;
	}

	public Member getReserver() {
		return reserver;
	}

	public void setReserver(Member reserver) {
		this.reserver = reserver;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
