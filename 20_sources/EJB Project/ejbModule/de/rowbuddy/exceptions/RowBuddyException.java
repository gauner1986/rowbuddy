package de.rowbuddy.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class RowBuddyException extends Exception {

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
