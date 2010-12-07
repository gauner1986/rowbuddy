package de.rowbuddy.server.web;

import java.util.List;

import de.rowbuddy.boundary.dtos.PersonalTripDTO;
import de.rowbuddy.boundary.dtos.TripDTO;
import de.rowbuddy.client.services.LogbookRemoteService;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

public class LogbookRemoteServiceImpl extends AbstractRemoteService implements
		LogbookRemoteService {
	
	@Override
	public void logRowedTrip(Trip rowedTrip) throws RowBuddyException {
		getRowBuddyFacade().logRowedTrip(rowedTrip);
	}

	@Override
	public void startTrip(Trip startedTrip) throws RowBuddyException {
		getRowBuddyFacade().startTrip(startedTrip);
	}

	@Override
	public List<TripDTO> getOpenTrips() {
		return getRowBuddyFacade().getOpenTrips();
	}

	@Override
	public void finishTrip(Trip openTrip) throws RowBuddyException {
		getRowBuddyFacade().finishTrip(openTrip);
	}
	
	@Override
	public Trip getTrip(Long id) throws RowBuddyException {
		return getRowBuddyFacade().getTrip(id);
	}

	@Override
	public List<PersonalTripDTO> getPersonalTrips() {
		return getRowBuddyFacade().getPersonalTrips();
	}

	@Override
	public List<PersonalTripDTO> getPersonalOpenTrips() {
		return getRowBuddyFacade().getPersonalTrips();
	}
}
