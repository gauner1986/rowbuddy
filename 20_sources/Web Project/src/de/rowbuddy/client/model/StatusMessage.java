package de.rowbuddy.client.model;

public class StatusMessage {
	public enum Status{
		POSITIVE, NEGATIVE
	}
	private String message;
	private Status status;
	private boolean attached = true;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	public boolean isAttached() {
		return attached;
	}
	public void setAttached(boolean attached) {
		this.attached = attached;
	}
	@Override
	public boolean equals(Object msg){
		if(msg instanceof StatusMessage){
			
			return ((StatusMessage) msg).getMessage().equals(getMessage());	
		}
		return false;
	}
	
}
