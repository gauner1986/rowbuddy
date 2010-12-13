package de.rowbuddy.client.services;

import com.google.gwt.user.client.rpc.RemoteService;

import de.rowbuddy.entities.Member;

public interface SessionManager extends RemoteService {
	public Member getMember();
	
	public void logout();
}
