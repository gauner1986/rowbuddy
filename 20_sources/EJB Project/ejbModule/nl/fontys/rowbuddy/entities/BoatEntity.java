package nl.fontys.rowbuddy.entities;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Boat
 *
 */
@Entity
public class BoatEntity implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private Integer numberOfSeats;
	private Boolean coxed;
	private Boolean locked;
	private Boolean deleted;
	private static final long serialVersionUID = 1L;

	public BoatEntity() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public Integer getNumberOfSeats() {
		return this.numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}   
	public Boolean isCoxed() {
		return this.coxed;
	}

	public void setCoxed(Boolean coxed) {
		this.coxed = coxed;
	}   
	public Boolean isLocked() {
		return this.locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}   
	public Boolean isDeleted() {
		return this.deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
   
}
