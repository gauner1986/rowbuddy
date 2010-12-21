package de.rowbuddy.server.web;

import java.util.List;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.boundary.dtos.PersonalTripDTO;
import de.rowbuddy.boundary.dtos.RouteDTO;
import de.rowbuddy.boundary.dtos.TripDTO;
import de.rowbuddy.boundary.dtos.TripMemberDTO;
import de.rowbuddy.client.services.LogbookRemoteService;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.NotLoggedInException;
import de.rowbuddy.exceptions.RowBuddyException;

public class LogbookRemoteServiceImpl extends AbstractRemoteService implements
		LogbookRemoteService {

	private static final long serialVersionUID = 1L;

	@Override
	public void startTrip(TripDTO trip, long boatId, long routeId,
			List<TripMemberDTO> tripMembers) throws RowBuddyException {
		getRowBuddyFacade().startTrip(trip, boatId, routeId, tripMembers);
	}

	@Override
	public List<TripDTO> getOpenTrips() throws RowBuddyException {
		return getRowBuddyFacade().getOpenTrips();
	}

	@Override
	public void finishTrip(Trip openTrip) throws RowBuddyException,
			RowBuddyException {
		getRowBuddyFacade().finishTrip(openTrip);
	}

	@Override
	public Trip getTrip(Long id) throws RowBuddyException {
		return getRowBuddyFacade().getTrip(id);
	}

	@Override
	public List<PersonalTripDTO> getPersonalTrips() throws RowBuddyException {
		return getRowBuddyFacade().getPersonalTrips();
	}

	@Override
	public List<PersonalTripDTO> getPersonalOpenTrips()
			throws NotLoggedInException {
		return getRowBuddyFacade().getPersonalOpenTrips();
	}

	@Override
	public List<RouteDTO> searchRoute(String query) throws RowBuddyException {
		return getRowBuddyFacade().searchRoute(query);
	}

	@Override
	public List<MemberDTO> searchMember(String query)
			throws NotLoggedInException {
		return getRowBuddyFacade().searchMember(query);
	}

	@Override
	public List<BoatDTO> searchBoat(String query) throws RowBuddyException {
		return getRowBuddyFacade().searchBoatNotLocked(query);
	}

	@Override
	public void logRowedTrip(TripDTO trip, long boatId, long routeId,
			List<TripMemberDTO> tripMembers) throws RowBuddyException {
		getRowBuddyFacade().logRowedTrip(trip, boatId, routeId, tripMembers);
	}
}
