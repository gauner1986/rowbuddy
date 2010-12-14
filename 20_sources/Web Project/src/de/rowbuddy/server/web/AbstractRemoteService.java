package de.rowbuddy.server.web;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.rowbuddy.boundary.RowBuddyFacade;

public abstract class AbstractRemoteService extends RemoteServiceServlet {
	private static final long serialVersionUID = 1L;

	protected RowBuddyFacade getRowBuddyFacade() {
			RowBuddyFacade rbf = ((RowBuddyFacade) this.getThreadLocalRequest().getSession().getAttribute("rbf"));
			return rbf;
	}
	
}
