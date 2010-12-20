package de.rowbuddy.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.boundary.dtos.PersonalTripDTO;
import de.rowbuddy.boundary.dtos.RouteDTO;
import de.rowbuddy.boundary.dtos.TripDTO;
import de.rowbuddy.boundary.dtos.TripMemberDTO;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.NotLoggedInException;
import de.rowbuddy.exceptions.RowBuddyException;

public interface LogbookRemoteService extends RemoteService {

	public void startTrip(Trip startedTrip) throws RowBuddyException, NotLoggedInException;

	public List<TripDTO> getOpenTrips() throws NotLoggedInException;

	public void finishTrip(Trip openTrip) throws RowBuddyException, NotLoggedInException;

	public Trip getTrip(Long id) throws RowBuddyException, NotLoggedInException;

	public List<PersonalTripDTO> getPersonalTrips() throws NotLoggedInException;

	public List<PersonalTripDTO> getPersonalOpenTrips() throws NotLoggedInException;

	List<MemberDTO> searchMember(String query) throws NotLoggedInException;

	List<RouteDTO> searchRoute(String query) throws NotLoggedInException;

	List<BoatDTO> searchBoat(String query) throws NotLoggedInException;

	void logRowedTrip(TripDTO trip, long boatId, long routeId,
			List<TripMemberDTO> tripMembers);

}
