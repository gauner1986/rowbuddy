package de.rowbuddy.server.web;

import java.util.List;

import de.rowbuddy.boundary.dtos.PersonalTripDTO;
import de.rowbuddy.boundary.dtos.TripDTO;
import de.rowbuddy.client.services.LogbookRemoteService;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.NotLoggedInException;
import de.rowbuddy.exceptions.RowBuddyException;

public class LogbookRemoteServiceImpl extends AbstractRemoteService implements
		LogbookRemoteService {

	private static final long serialVersionUID = 1L;

	@Override
	public void logRowedTrip(Trip rowedTrip) throws NotLoggedInException {
		try {
			getRowBuddyFacade().logRowedTrip(rowedTrip);
		} catch (RowBuddyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(NullPointerException e){
			throw new NotLoggedInException("rowBuddyFacade = null");
		}
	}

	@Override
	public void startTrip(Trip startedTrip) throws NotLoggedInException {
		try {
			getRowBuddyFacade().startTrip(startedTrip);
		} catch (RowBuddyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(NullPointerException e){
			throw new NotLoggedInException("rowBuddyFacade = null");
		}
	}

	@Override
	public List<TripDTO> getOpenTrips() throws NotLoggedInException {
		try{
			return getRowBuddyFacade().getOpenTrips();
		} catch(NullPointerException e){
			throw new NotLoggedInException("rowBuddyFacade = null");
		}
	}

	@Override
	public void finishTrip(Trip openTrip) throws NotLoggedInException {
		try {
			getRowBuddyFacade().finishTrip(openTrip);
		} catch (RowBuddyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(NullPointerException e){
			throw new NotLoggedInException("rowBuddyFacade = null");
		}
	}

	@Override
	public Trip getTrip(Long id) throws NotLoggedInException {
		try {
			return getRowBuddyFacade().getTrip(id);
		} catch (RowBuddyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch(NullPointerException e){
			throw new NotLoggedInException("rowBuddyFacade = null");
		}
	}

	@Override
	public List<PersonalTripDTO> getPersonalTrips() throws NotLoggedInException {
		try {
		return getRowBuddyFacade().getPersonalTrips();
		} catch(NullPointerException e){
			throw new NotLoggedInException("rowBuddyFacade = null");
		}
	}

	@Override
	public List<PersonalTripDTO> getPersonalOpenTrips() throws NotLoggedInException {
		try{
		return getRowBuddyFacade().getPersonalOpenTrips();
		} catch(NullPointerException e){
			throw new NotLoggedInException("rowBuddyFacade = null");
		}
	}
}
