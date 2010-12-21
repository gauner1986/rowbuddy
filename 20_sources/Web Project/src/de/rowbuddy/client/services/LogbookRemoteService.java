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
import de.rowbuddy.exceptions.RowBuddyException;

public interface LogbookRemoteService extends RemoteService {

	public void startTrip(TripDTO trip, long boatId, long routeId,
			List<TripMemberDTO> tripMembers) throws RowBuddyException;

	public List<TripDTO> getOpenTrips() throws RowBuddyException;

	public void finishTrip(Trip openTrip) throws RowBuddyException;

	public Trip getTrip(Long id) throws RowBuddyException;

	public List<PersonalTripDTO> getPersonalTrips() throws RowBuddyException;

	public List<PersonalTripDTO> getPersonalOpenTrips()
			throws RowBuddyException;

	List<MemberDTO> searchMember(String query) throws RowBuddyException;

	List<RouteDTO> searchRoute(String query) throws RowBuddyException;

	List<BoatDTO> searchBoat(String query) throws RowBuddyException;

	void logRowedTrip(TripDTO trip, long boatId, long routeId,
			List<TripMemberDTO> tripMembers) throws RowBuddyException;

}
