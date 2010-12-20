package de.rowbuddy.server.web;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.rowbuddy.boundary.RowBuddyFacade;
import de.rowbuddy.exceptions.NotLoggedInException;

public abstract class AbstractRemoteService extends RemoteServiceServlet {
	private static final long serialVersionUID = 1L;

	protected RowBuddyFacade getRowBuddyFacade() throws NotLoggedInException {
			RowBuddyFacade rbf = ((RowBuddyFacade) this.getThreadLocalRequest().getSession().getAttribute("rbf"));
			if(rbf==null)
				throw new NotLoggedInException("rbf=null");
			return rbf;
	}
	
}
