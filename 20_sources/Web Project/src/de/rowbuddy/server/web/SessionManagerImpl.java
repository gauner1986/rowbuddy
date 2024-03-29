package de.rowbuddy.server.web;

import javax.servlet.annotation.WebServlet;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.services.SessionManager;
import de.rowbuddy.exceptions.NotLoggedInException;

/**
 * Servlet implementation class SessionManaager
 */
@WebServlet("/SessionManagerImpl")
public class SessionManagerImpl extends AbstractRemoteService implements
		SessionManager {
	private static final long serialVersionUID = 1L;

	/**
	 * @see RemoteServiceServlet#RemoteServiceServlet()
	 */
	public SessionManagerImpl() {
		super();
	}

	public MemberDTO getMember() throws NotLoggedInException {
		return getRowBuddyFacade().getMember();
	}

	public void logout() throws NotLoggedInException {
		getRowBuddyFacade().logout();
	}
}
