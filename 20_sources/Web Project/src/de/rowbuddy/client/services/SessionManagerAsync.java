package de.rowbuddy.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.boundary.dtos.PersonalTripDTO;
import de.rowbuddy.boundary.dtos.TripDTO;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.Trip;

public interface SessionManagerAsync {

	void getMember(AsyncCallback<MemberDTO> callback);

	void addBoat(Boat boat, AsyncCallback<Boat> callback);

	void getBoatOverview(AsyncCallback<List<BoatDTO>> callback);

	void getBoat(Long id, AsyncCallback<Boat> callback);

	void updateBoat(Boat updateBoat, AsyncCallback<Boat> callback);

	void deleteBoat(Long id, AsyncCallback<Void> callback);

	void logRowedTrip(Trip rowedTrip, AsyncCallback<Void> callback);

	void startTrip(Trip startedTrip, AsyncCallback<Void> callback);

	void getTrip(Long id, AsyncCallback<Trip> callback);

	void getOpenTrips(AsyncCallback<List<TripDTO>> callback);

	void finishTrip(Trip openTrip, AsyncCallback<Void> callback);

	void getPersonalTrips(AsyncCallback<List<PersonalTripDTO>> callback);

	void getPersonalOpenTrips(AsyncCallback<List<PersonalTripDTO>> callback);

	void logout(AsyncCallback<Void> callback);;
	
}
