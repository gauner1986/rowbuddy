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
import de.rowbuddy.exceptions.RowBuddyException;
import de.rowbuddy.server.web.AbstractRemoteService;

public class LogbookRemoteServiceImpl extends AbstractRemoteService implements
		LogbookRemoteService {

	private static final long serialVersionUID = 1L;

	@Override
	public void startTrip(Trip startedTrip) {
		try {
			getRowBuddyFacade().startTrip(startedTrip);
		} catch (RowBuddyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<TripDTO> getOpenTrips() {
		return getRowBuddyFacade().getOpenTrips();
	}

	@Override
	public void finishTrip(Trip openTrip) {
		try {
			getRowBuddyFacade().finishTrip(openTrip);
		} catch (RowBuddyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Trip getTrip(Long id) {
		try {
			return getRowBuddyFacade().getTrip(id);
		} catch (RowBuddyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<PersonalTripDTO> getPersonalTrips() {
		return getRowBuddyFacade().getPersonalTrips();
	}

	@Override
	public List<PersonalTripDTO> getPersonalOpenTrips() {
		return getRowBuddyFacade().getPersonalOpenTrips();
	}

	@Override
	public List<RouteDTO> searchRoute(String query) {
		return getRowBuddyFacade().searchRoute(query);
	}

	@Override
	public List<MemberDTO> searchMember(String query) {
		return getRowBuddyFacade().searchMember(query);
	}

	@Override
	public List<BoatDTO> searchBoat(String query) {
		return getRowBuddyFacade().searchBoatNotLocked(query);
	}

	@Override
	public void logRowedTrip(TripDTO trip, long boatId, long routeId, List<TripMemberDTO> tripMembers) {
		try {
			getRowBuddyFacade().logRowedTrip(trip, boatId, routeId, tripMembers);
		} catch (RowBuddyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
