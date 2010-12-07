package de.rowbuddy.server.web;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.rowbuddy.business.RowBuddyFacade;

public abstract class AbstractRemoteService extends RemoteServiceServlet {
	private static final long serialVersionUID = 1L;
	
	protected RowBuddyFacade getRowBuddyFacade(){
		return ((RowBuddyFacade) this.getThreadLocalRequest().getSession().getAttribute("rbf"));
	}
}
