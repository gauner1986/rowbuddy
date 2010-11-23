package de.rowbuddy.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class RowBuddyException extends Exception {

	public RowBuddyException(String message) {
		super(message);
	}
}
