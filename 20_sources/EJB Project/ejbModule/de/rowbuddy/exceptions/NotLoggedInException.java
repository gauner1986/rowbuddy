package de.rowbuddy.exceptions;

public class NotLoggedInException extends RowBuddyException {

	private static final long serialVersionUID = 1L;

	public NotLoggedInException(String message) {
		super(message);
	}
}
