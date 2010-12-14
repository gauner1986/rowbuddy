package de.rowbuddy.exceptions;

import java.io.Serializable;

public class NotLoggedInException extends RowBuddyException implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public NotLoggedInException(){}

	public NotLoggedInException(String message) {
		super(message);
	}
}
