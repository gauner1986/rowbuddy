package de.rowbuddy.client.services;

import com.google.gwt.user.client.rpc.RemoteService;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.exceptions.NotLoggedInException;

public interface SessionManager extends RemoteService {
	public MemberDTO getMember() throws NotLoggedInException;
	
	public void logout() throws NotLoggedInException;
}
