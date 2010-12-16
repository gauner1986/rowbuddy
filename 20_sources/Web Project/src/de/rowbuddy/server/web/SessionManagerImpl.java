package de.rowbuddy.server.web;

import java.util.LinkedList;

import javax.servlet.annotation.WebServlet;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.rowbuddy.boundary.dtos.MemberDTO;
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
	
	public MemberDTO getMember() {
		return getRowBuddyFacade().getMember();
//		Member convMember = new Member();
		
//		convMember.setId(rawMember.getId());
//		
//		try {
//			convMember.setEmail(rawMember.getEmail());
//			convMember.setBirthdate(rawMember.getBirthdate());
//			convMember.setCity(rawMember.getCity());
//			convMember.setDeleted(rawMember.getDeleted());
//			convMember.setGivenname(rawMember.getGivenname());
//			convMember.setMemberId(rawMember.getMemberId());
//			convMember.setMobilePhone(rawMember.getMobilePhone());
//			convMember.setPasswordHash(rawMember.getPasswordHash());
//			convMember.setPhone(rawMember.getPhone());
//			convMember.setStreet(rawMember.getStreet());
//			convMember.setSurname(rawMember.getSurname());
//			convMember.setZipCode(rawMember.getZipCode());
//		} catch (RowBuddyException e) {
//			e.printStackTrace();
//		}
//		
//		LinkedList<Trip> trips = new LinkedList<Trip>();
//		for(Trip t : rawMember.getPublishedTrips()){
//			trips.add(t);
//		}
//		
//		convMember.setPublishedTrips(trips);
//		
//		
//		LinkedList<Role> roleList = new LinkedList<Role>();
//		for(Role r: rawMember.getRoles()){
//			roleList.add(r);
//		}
//		
//		convMember.setRoles(roleList);
//		return convMember;
	}

	public void logout() {
		getRowBuddyFacade().logout();
	}
}
