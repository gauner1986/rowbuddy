package de.rowbuddy.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.rowbuddy.entities.Member;

public interface SessionManagerAsync {

	void getMember(AsyncCallback<Member> callback);
	
	void logout(AsyncCallback<Void> callback);;
	
}
