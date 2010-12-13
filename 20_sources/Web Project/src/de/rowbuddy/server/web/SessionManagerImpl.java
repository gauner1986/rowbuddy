package de.rowbuddy.server.web;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.rowbuddy.boundary.RowBuddyFacade;
import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.boundary.dtos.PersonalTripDTO;
import de.rowbuddy.boundary.dtos.TripDTO;
import de.rowbuddy.client.services.SessionManager;
import de.rowbuddy.entities.Boat;
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
	}

	public Boat addBoat(Boat boat) throws RowBuddyException {
		return getRowBuddyFacade().addBoat(boat);
	}

	public List<BoatDTO> getBoatOverview() {
		return getRowBuddyFacade().getBoatOverview();
	}

	public Boat getBoat(Long id) throws RowBuddyException {
		return getRowBuddyFacade().getBoat(id);
	}

	public Boat updateBoat(Boat updateBoat) throws RowBuddyException,
			RowBuddyException {
		return getRowBuddyFacade().updateBoat(updateBoat);
	}

	public void deleteBoat(Long id) throws RowBuddyException {
		getRowBuddyFacade().deleteBoat(id);
	}

	public void logRowedTrip(Trip rowedTrip) throws RowBuddyException {
		getRowBuddyFacade().logRowedTrip(rowedTrip);
	}

	public void startTrip(Trip startedTrip) throws RowBuddyException {
		getRowBuddyFacade().startTrip(startedTrip);
	}

	public Trip getTrip(Long id) throws RowBuddyException {
		return getRowBuddyFacade().getTrip(id);
	}

	public List<TripDTO> getOpenTrips() {
		return getRowBuddyFacade().getOpenTrips();
	}

	public void finishTrip(Trip openTrip) throws RowBuddyException {
		getRowBuddyFacade().finishTrip(openTrip);
	}

	public List<PersonalTripDTO> getPersonalTrips() {
		return getRowBuddyFacade().getPersonalTrips();
	}

	public List<PersonalTripDTO> getPersonalOpenTrips() {
		return getRowBuddyFacade().getPersonalTrips();
	}
	
	public void logout() {
		getRowBuddyFacade().logout();
	}
}
