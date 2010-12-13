package de.rowbuddy.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.rowbuddy.boundary.LogbookBoundary;
import de.rowbuddy.boundary.dtos.PersonalTripDTO;
import de.rowbuddy.business.Logbook.ListType;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.entities.TripMember;
import de.rowbuddy.entities.TripMemberType;
import de.rowbuddy.exceptions.RowBuddyException;
import de.rowbuddy.util.Ejb;
import de.rowbuddy.util.EjbTestBase;

public class LogbookTest extends EjbTestBase {

	Logbook logbook;
	LogbookBoundary lbBoundary;
	Trip rowedTrip;
	Trip startedTrip1;
	Trip startedTrip2;
	Trip startedTrip3;
	TripMember tripMember1;
	TripMember tripMember2;

	@Before
	public void setup() throws RowBuddyException, ParseException {

		logbook = Ejb.lookUp(Logbook.class, Logbook.class);
		lbBoundary = Ejb.lookUp(LogbookBoundary.class, LogbookBoundary.class);
		db.setupBoats();
		db.setupMembers();
		db.setupRoutes();

		tripMember1 = new TripMember();
		tripMember1.setMember(db.getMembers().get(0));
		tripMember1.setTripMemberType(TripMemberType.Rower);

		tripMember2 = new TripMember();
		tripMember2.setMember(db.getMembers().get(0));
		tripMember2.setTripMemberType(TripMemberType.Rower);

		rowedTrip = new Trip();
		rowedTrip.setBoat(db.getBoats().get(0));
		rowedTrip.setStartDate(DateFormat.getInstance().parse(
				"15.03.2010 13:12:00"));
		rowedTrip.setEndDate(DateFormat.getInstance().parse(
				"15.03.2010 14:16:00"));
		rowedTrip.setLastEditor(db.getMembers().get(0));
		rowedTrip.setRoute(db.getRoutes().get(0));
		rowedTrip.addTripMember(tripMember1);
		rowedTrip.addTripMember(tripMember2);

		rowedTrip.validateFinishedTrip();

		startedTrip1 = new Trip();
		startedTrip1.startNow();

		startedTrip2 = new Trip();
		startedTrip2.startNow();

		startedTrip3 = new Trip();
		startedTrip3.startNow();
	}

	@After
	public void teardown() {
		logbook = null;
	}

	@Test
	public void canLogRowedTrip() throws RowBuddyException {
		// when
		logbook.logRowedTrip(rowedTrip, db.getMembers().get(0));

		// then
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

	@Test
	public void canStartRowing() throws RowBuddyException {
		// when
		logbook.startTrip(startedTrip1, db.getMembers().get(0));

		// then
		List<Trip> dbTrips = em.getAllEntities(Trip.class);
		assertTrue(dbTrips.size() == 1);
		Trip trip = dbTrips.get(0);
		assertNull(trip.getBoat());
		assertNull(trip.getRoute());
		assertEquals(trip.getStartDate(), trip.getStartDate());
		assertNull(trip.getEndDate());
		assertEquals(db.getMembers().get(0), trip.getLastEditor());
		assertEquals(0, trip.getTripMembers().size());
	}

	@Test
	public void canStopRowing() throws RowBuddyException, ParseException {
		// given
		logbook.startTrip(startedTrip1, db.getMembers().get(0));
		List<Trip> openTrips = logbook.getOpenTrips(db.getMembers().get(0));
		assertNotNull(openTrips);
		assertThat(openTrips.size(), is(1));

		Trip openTrip = openTrips.get(0);

		openTrip.setBoat(db.getBoats().get(1));
		openTrip.addTripMember(tripMember1);
		openTrip.addTripMember(tripMember2);
		openTrip.setRoute(db.getRoutes().get(0));
		openTrip.setEndDate(DateFormat.getInstance().parse(
				"15.03.2012 13:12:00"));

		// when
		logbook.finishTrip(openTrip, db.getMembers().get(0));

		// then
		Trip dbTrip = em.getReference(Trip.class, openTrip.getId());
		assertThat(dbTrip.isFinished(), is(true));

		openTrips = logbook.getOpenTrips(db.getMembers().get(0));
		assertThat(openTrips.size(), is(0));
	}

	@Test
	public void canGetOpenTrips() throws RowBuddyException {
		// given
		logbook.logRowedTrip(rowedTrip, db.getMembers().get(0));
		logbook.startTrip(startedTrip1, db.getMembers().get(0));
		logbook.startTrip(startedTrip2, db.getMembers().get(0));
		logbook.startTrip(startedTrip3, db.getMembers().get(0));

		// when
		List<Trip> startedTrips = logbook.getOpenTrips(db.getMembers().get(0));

		// then
		assertNotNull(startedTrips);
		assertThat(startedTrips.size(), is(3));
	}

	@Test
	public void canGetPersonalTrips() throws RowBuddyException {
		// given
		logbook.logRowedTrip(rowedTrip, db.getMembers().get(0));
		logbook.startTrip(startedTrip1, db.getMembers().get(0));
		logbook.startTrip(startedTrip2, db.getMembers().get(0));
		logbook.startTrip(startedTrip3, db.getMembers().get(0));

		// when
		List<PersonalTripDTO> personalTrips = lbBoundary.getPersonalTrips(db
				.getMembers().get(0), ListType.All);

		// then
	}

	@Test
	public void canGetPersonalOpenTrips() throws RowBuddyException {
		// given
		logbook.logRowedTrip(rowedTrip, db.getMembers().get(0));
		logbook.startTrip(startedTrip1, db.getMembers().get(0));
		logbook.startTrip(startedTrip2, db.getMembers().get(0));
		logbook.startTrip(startedTrip3, db.getMembers().get(0));

		// when
		List<PersonalTripDTO> personalOpenTrips = lbBoundary.getPersonalTrips(
				db.getMembers().get(0), ListType.OpenOnly);

		// then
	}

}
