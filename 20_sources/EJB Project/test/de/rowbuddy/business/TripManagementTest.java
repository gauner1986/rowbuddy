package de.rowbuddy.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import de.rowbuddy.entities.Trip;
import de.rowbuddy.entities.TripMember;
import de.rowbuddy.entities.TripMemberType;
import de.rowbuddy.exceptions.RowBuddyException;
import de.rowbuddy.util.Ejb;
import de.rowbuddy.util.EjbTestBase;

public class TripManagementTest extends EjbTestBase {

	TripManagement tripManagement;
	Trip rowedTrip;

	@Before
	public void setup() throws RowBuddyException, ParseException {

		tripManagement = Ejb.lookUp(TripManagement.class, TripManagement.class);
		db.setupBoats();
		db.setupMembers();
		db.setupRoutes();

		rowedTrip = new Trip();
		rowedTrip.setBoat(db.getBoats().get(0));
		rowedTrip.setStartDate(DateFormat.getInstance().parse(
				"15.03.2010 13:12:00"));
		rowedTrip.setEndDate(DateFormat.getInstance().parse(
				"15.03.2010 14:16:00"));
		rowedTrip.setLastEditor(db.getMembers().get(0));
		rowedTrip.setRoute(db.getRoutes().get(0));

		TripMember tm1 = new TripMember();
		tm1.setMember(db.getMembers().get(0));
		tm1.setTripMemberType(TripMemberType.Rower);

		TripMember tm2 = new TripMember();
		tm2.setMember(db.getMembers().get(0));
		tm2.setTripMemberType(TripMemberType.Rower);

		rowedTrip.addTripMember(tm1);
		rowedTrip.addTripMember(tm2);
		
		rowedTrip.validate();
	}

	@After
	public void teardown() {
		tripManagement = null;
	}

	@Test
	public void canLogRowedTrip() throws RowBuddyException {
		tripManagement.logRowedTrip(rowedTrip, db.getMembers().get(0));
		
		List<Trip> dbTrips = em.getAllEntities(Trip.class);
		assertTrue(dbTrips.size() == 1);
		Trip trip = dbTrips.get(0);
		assertEquals(rowedTrip.getBoat(), trip.getBoat());
		assertEquals(rowedTrip.getRoute(), trip.getRoute());
		assertEquals(rowedTrip.getStartDate(), trip.getStartDate());
		assertEquals(rowedTrip.getEndDate(), trip.getEndDate());
		assertEquals(db.getMembers().get(0), trip.getLastEditor());
		assertEquals(2, trip.getTripMembers().size());		
	}
}
