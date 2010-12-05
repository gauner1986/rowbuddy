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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public boolean isOpen() {
		return fixed;
	}
	public void setOpen(boolean open) {
		this.fixed = open;
	}
	
	
}
