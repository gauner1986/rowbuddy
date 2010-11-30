package de.rowbuddy.client.services;

import java.util.List;

import de.rowbuddy.business.dtos.TripDTO;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

public interface LogbookRemoteService {

	public void logRowedTrip(Trip rowedTrip) throws RowBuddyException;

	public void startTrip(Trip startedTrip) throws RowBuddyException;

	public List<TripDTO> getOpenTrips();

	public void finishTrip(Trip openTrip) throws RowBuddyException;

	public Trip getTrip(Long id) throws RowBuddyException;
}
