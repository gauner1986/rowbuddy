package de.rowbuddy.boundary.dtos;

import java.io.Serializable;

public class RouteDTO implements Serializable {

	private static final long serialVersionUID = -5945798880823033691L;

	private Long id;
	private Long parentId;
	private String name;
	private String shortDescription;
	// private List<GpsPoint> wayPoints = new LinkedList<GpsPoint>();
	private double lengthKM;
	private MemberDTO lastEditor;
	private boolean mutable;
	private boolean deleted;

	public Long getId() {
		return id;
	}

	public MemberDTO getLastEditor() {
		return lastEditor;
	}

	public double getLengthKM() {
		return lengthKM;
	}

	public String getName() {
		return name;
	}

	public Long getParentId() {
		return parentId;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLastEditor(MemberDTO lastEditor) {
		this.lastEditor = lastEditor;
	}

	public void setLengthKM(double lengthKM) {
		this.lengthKM = lengthKM;
	}

	public void setMutable(boolean mutable) {
		this.mutable = mutable;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public boolean isMutable() {
		return mutable;
	}

}
