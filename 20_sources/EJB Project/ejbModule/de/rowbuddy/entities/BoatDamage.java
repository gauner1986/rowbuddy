package de.rowbuddy.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import de.rowbuddy.exceptions.RowBuddyException;

/**
 * Entity implementation class for Entity: BoatDamage
 * 
 */
@Entity
public class BoatDamage implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	@Temporal(TemporalType.TIMESTAMP)
	private Date logDate = new Date();
	private boolean fixed = false;
	private String damageDescription = "";
	@ManyToOne
	private Member logger = null;
	@ManyToOne
	private Boat boat = null;
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
		if (logDate == null) {
			throw new NullPointerException("LogDatum darf nicht null sein");
		}
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

	public void setDamageDescription(String damageDescription)
			throws RowBuddyException {
		if (damageDescription == null) {
			throw new NullPointerException("Beschreibung darf nicht null sein");
		}
		if (damageDescription.isEmpty()) {
			throw new RowBuddyException(
					"Es muss eine Beschreibung angegeben werden");
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

	public void removeBoat() {
		this.boat = null;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		if (additionalInformation == null) {
			throw new NullPointerException(
					"Zusaetzliche Information duerfen nicht null sein");
		}
		this.additionalInformation = additionalInformation;
	}

	public void validate() throws RowBuddyException {
		if (damageDescription.isEmpty()) {
			throw new RowBuddyException("Beschreibung darf nicht leer sein");
		}
		if (logger == null) {
			throw new NullPointerException("Mitglied angegeben werden");
		}
	}
}
