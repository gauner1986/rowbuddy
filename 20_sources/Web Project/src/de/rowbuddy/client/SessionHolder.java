package de.rowbuddy.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import de.rowbuddy.client.services.SessionManager;
import de.rowbuddy.client.services.SessionManagerAsync;

public class SessionHolder {
	 
	private static SessionManagerAsync sessionManager = null;
	
	public static SessionManagerAsync getSessionManager(){
		if(sessionManager == null){
		sessionManager = (SessionManagerAsync) GWT
		.create(SessionManager.class);
		((ServiceDefTarget) sessionManager).setServiceEntryPoint(GWT
				.getHostPageBaseURL() + "SessionManagerImpl");
			return sessionManager;
		} else {
			return sessionManager;
		}
	}
}
