package de.rowbuddy.server.web;

import java.util.LinkedList;

import javax.servlet.annotation.WebServlet;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.rowbuddy.client.services.SessionManager;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

/**
 * Servlet implementation class SessionManaager
 */
@WebServlet("/SessionManagerImpl")
public class SessionManagerImpl extends AbstractRemoteService implements SessionManager {
	private static final long serialVersionUID = 1L;

	/**
	 * @see RemoteServiceServlet#RemoteServiceServlet()
	 */
	public SessionManagerImpl() {
		super();
	}
	
	public Member getMember() {
		Member rawMember = getRowBuddyFacade().getMember();
		Member convMember = new Member();
		
		try {
			convMember.setEmail(rawMember.getEmail());
		} catch (RowBuddyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		convMember.setPublishedTrips(new LinkedList<Trip>());
		LinkedList<Role> roleList = new LinkedList<Role>();
		for(Role r: convMember.getRoles()){
			roleList.add(r);
		}
		convMember.setRoles(roleList);
		return convMember;
	}

	public void logout() {
		getRowBuddyFacade().logout();
	}
}
