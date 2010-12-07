package de.rowbuddy.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.rowbuddy.boundary.dtos.MemberDTO;

public interface SessionManagerAsync {

	void getMember(AsyncCallback<MemberDTO> callback);

}
