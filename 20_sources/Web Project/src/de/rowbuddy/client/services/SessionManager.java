package de.rowbuddy.client.services;

import com.google.gwt.user.client.rpc.RemoteService;

import de.rowbuddy.boundary.dtos.MemberDTO;

public interface SessionManager extends RemoteService {
	public MemberDTO getMember();
}
