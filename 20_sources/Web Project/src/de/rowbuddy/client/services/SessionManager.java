package de.rowbuddy.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.boundary.dtos.PersonalTripDTO;
import de.rowbuddy.boundary.dtos.TripDTO;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

public interface SessionManager extends RemoteService {
	public MemberDTO getMember();

	public Boat addBoat(Boat boat) throws RowBuddyException;

	public List<BoatDTO> getBoatOverview();

	public Boat getBoat(Long id) throws RowBuddyException;

	public Boat updateBoat(Boat updateBoat) throws RowBuddyException,
			RowBuddyException;

	public void deleteBoat(Long id) throws RowBuddyException;

	public void logRowedTrip(Trip rowedTrip) throws RowBuddyException;

	public void startTrip(Trip startedTrip) throws RowBuddyException;

	public Trip getTrip(Long id) throws RowBuddyException;

	public List<TripDTO> getOpenTrips();

	public void finishTrip(Trip openTrip) throws RowBuddyException;

	public List<PersonalTripDTO> getPersonalTrips();

	public List<PersonalTripDTO> getPersonalOpenTrips();
	
	public void logout();
}
