package de.rowbuddy.boundary.dtos;

import java.io.Serializable;
import java.util.Date;

public class DamageDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String bootName;
	private Date date;
	private boolean fixed;
	private String member;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public boolean isFixed() {
		return fixed;
	}
	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getBootName() {
		return bootName;
	}
	public void setBootName(String bootName) {
		this.bootName = bootName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
