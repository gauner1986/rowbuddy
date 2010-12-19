package de.rowbuddy.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.boundary.dtos.PersonalTripDTO;
import de.rowbuddy.boundary.dtos.RouteDTO;
import de.rowbuddy.boundary.dtos.TripDTO;
import de.rowbuddy.boundary.dtos.TripMemberDTO;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

public interface LogbookRemoteServiceAsync {

	public void startTrip(Trip startedTrip, AsyncCallback<Void> callback)
			throws RowBuddyException;

	public void getOpenTrips(AsyncCallback<List<TripDTO>> callback);

	public void finishTrip(Trip openTrip, AsyncCallback<Void> callback)
			throws RowBuddyException;

	public void getTrip(Long id, AsyncCallback<Trip> callback)
			throws RowBuddyException;

	public void getPersonalTrips(AsyncCallback<List<PersonalTripDTO>> callback);

	public void getPersonalOpenTrips(
			AsyncCallback<List<PersonalTripDTO>> callback);

	void searchRoute(String query, AsyncCallback<List<RouteDTO>> callback);
	
	void searchMember(String query, AsyncCallback<List<MemberDTO>> callback);
	
	void searchBoat(String query, AsyncCallback<List<BoatDTO>> callback);
	
	void logRowedTrip(TripDTO trip, long boatId, long routeId, List<TripMemberDTO> tripMembers, AsyncCallback<Void> callback);
}
