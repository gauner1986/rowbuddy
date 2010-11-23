package de.rowbuddy.exceptions;

import java.io.Serializable;

import javax.ejb.ApplicationException;

@ApplicationException
public class RowBuddyException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public RowBuddyException() {
	}
	
	public RowBuddyException(String message) {
		super(message);
	}
}
