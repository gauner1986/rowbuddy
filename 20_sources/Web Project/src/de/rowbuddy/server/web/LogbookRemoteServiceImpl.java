package de.rowbuddy.server.web;

import java.util.List;

import javax.ejb.EJB;

import nl.fontys.rowbuddy.EntityManagerBeanLocal;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.rowbuddy.boundary.dtos.PersonalTripDTO;
import de.rowbuddy.business.Logbook;
import de.rowbuddy.business.Logbook.ListType;
import de.rowbuddy.client.services.LogbookRemoteService;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

public class LogbookRemoteServiceImpl extends RemoteServiceServlet implements
		LogbookRemoteService {
	
	@EJB
	private Logbook rowbuddy;
	@EJB
	private EntityManagerBeanLocal em;
	
	@Override
	public void logRowedTrip(Trip rowedTrip) throws RowBuddyException {
		rowbuddy.logRowedTrip(rowedTrip, em.getAllEntities(Member.class).get(0));
	}

	@Override
	public void startTrip(Trip startedTrip) throws RowBuddyException {
		rowbuddy.startTrip(startedTrip, em.getAllEntities(Member.class).get(0));
	}

	@Override
	public List<Trip> getOpenTrips() {
		return rowbuddy.getOpenTrips(em.getAllEntities(Member.class).get(0));
	}

	@Override
	public void finishTrip(Trip openTrip) throws RowBuddyException {
		rowbuddy.finishTrip(openTrip, em.getAllEntities(Member.class).get(0));
	}
	
	@Override
	public Trip getTrip(Long id) throws RowBuddyException {
		return rowbuddy.getTrip(id);
	}

	@Override
	public List<PersonalTripDTO> getPersonalTrips() {
		return rowbuddy.getPersonalTrips(em.getAllEntities(Member.class).get(0), ListType.All);
	}

	@Override
	public List<PersonalTripDTO> getPersonalOpenTrips() {
		return rowbuddy.getPersonalTrips(em.getAllEntities(Member.class).get(0), ListType.OpenOnly);
	}
}
