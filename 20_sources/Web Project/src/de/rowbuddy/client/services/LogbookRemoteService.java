package de.rowbuddy.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

import de.rowbuddy.boundary.dtos.PersonalTripDTO;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

public interface LogbookRemoteService extends RemoteService {

	public void logRowedTrip(Trip rowedTrip) throws RowBuddyException;

	public void startTrip(Trip startedTrip) throws RowBuddyException;

	public List<Trip> getOpenTrips();

	public void finishTrip(Trip openTrip) throws RowBuddyException;

	public Trip getTrip(Long id) throws RowBuddyException;

	public List<PersonalTripDTO> getPersonalTrips();

	public List<PersonalTripDTO> getPersonalOpenTrips();

}
