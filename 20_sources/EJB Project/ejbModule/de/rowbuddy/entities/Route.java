package de.rowbuddy.entities;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Route
 *
 */
@Entity
public class Route implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String shortDescription;
	private Long versionNumber;
	private Double lengthKM;
	private Boolean mutable;
	private static final long serialVersionUID = 1L;

	public Route() {
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
	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}   
	public Long getVersionNumber() {
		return this.versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}   
	public Double getLengthKM() {
		return this.lengthKM;
	}

	public void setLengthKM(Double lengthKM) {
		this.lengthKM = lengthKM;
	}   
	public Boolean getMutable() {
		return this.mutable;
	}

	public void setMutable(Boolean mutable) {
		this.mutable = mutable;
	}
   
}
