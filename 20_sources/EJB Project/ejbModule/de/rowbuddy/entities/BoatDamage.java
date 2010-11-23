package de.rowbuddy.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

import de.rowbuddy.exceptions.RowBuddyException;

/**
 * Entity implementation class for Entity: BoatDamage
 *
 */
@Entity
public class BoatDamage implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date logDate;
	private boolean fixed = false;
	private String damageDescription = "";
	@ManyToOne
	private Member logger;
	@ManyToOne
	private Boat boat;
	private String additionalInformation = "";
	private static final long serialVersionUID = 1L;

	public BoatDamage() {
		super();
	} 
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public String getDamageDescription() {
		return damageDescription;
	}

	public void setDamageDescription(String damageDescription) throws RowBuddyException {
		if(damageDescription.length() < 1 || damageDescription.equals("") || damageDescription == null) {
			throw new RowBuddyException("damage description has to be set");
		}
		this.damageDescription = damageDescription;
	}

	public Member getLogger() {
		return logger;
	}

	public void setLogger(Member logger) {
		this.logger = logger;
	}

	public Boat getBoat() {
		return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	
}
