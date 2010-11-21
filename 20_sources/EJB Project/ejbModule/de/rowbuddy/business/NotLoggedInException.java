package de.rowbuddy.business;

public class NotLoggedInException extends RuntimeException {

	public NotLoggedInException(String message) {
		super(message);
	}
}
