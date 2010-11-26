package de.rowbuddy.server.web;

import java.util.List;

import javax.ejb.EJB;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.rowbuddy.business.RowBuddyFacade;
import de.rowbuddy.client.services.LogbookRemoteService;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

public class LogbookServiceImpl extends RemoteServiceServlet implements
		LogbookRemoteService {

	@EJB
	RowBuddyFacade rowbuddy;
	
	@Override
	public void logRowedTrip(Trip rowedTrip) throws RowBuddyException {
		rowbuddy.logRowedTrip(rowedTrip);
	}

	@Override
	public void startTrip(Trip startedTrip) throws RowBuddyException {
		rowbuddy.startTrip(startedTrip);
	}

	@Override
	public List<Trip> getOpenTrips() {
		return rowbuddy.getOpenTrips();
	}

	@Override
	public void finishTrip(Trip openTrip) throws RowBuddyException {
		rowbuddy.finishTrip(openTrip);
	}
}
