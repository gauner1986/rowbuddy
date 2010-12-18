package de.rowbuddy.server.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.rowbuddy.business.BoatManagement;
import de.rowbuddy.business.Logbook;
import de.rowbuddy.business.MemberManagement;
import de.rowbuddy.business.RouteManagement;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.BoatDamage;
import de.rowbuddy.entities.GpsPoint;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role.RoleName;
import de.rowbuddy.entities.Route;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.entities.TripMember;
import de.rowbuddy.entities.TripMemberType;
import de.rowbuddy.exceptions.RowBuddyException;

/**
 * Servlet implementation class DataInitialization
 */
@WebServlet("/DataInitialization")
public class DataInitialization extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BoatManagement boatManagement;

	@EJB
	private MemberManagement memberManagement;

	@EJB
	private RouteManagement routeManagement;
	@EJB
	private Logbook logbook;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataInitialization() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		try {
			memberManagement.setupRoles();
			Member member = createTestMember(true);
			createTestRoutes(member);
			createTestBoats(member);
			createTestTrips(member);
			// createAdminTestMember();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createTestRoutes(Member member) throws RowBuddyException {
		Route r1 = new Route();
		r1.setDeleted(false);
		r1.setLengthKM(4.3);
		r1.setMutable(true);
		r1.setName("Krefelder Trainingsstrecke");
		r1.setShortDescription("Eine sehr schöne Strecke zum trainieren.");
		routeManagement.addRoute(r1, member);

		Route r2 = new Route();
		r2.setDeleted(false);
		r2.setLengthKM(2.6);
		r2.setMutable(false);
		r2.setName("Rhein Duisburg-Wesel");
		r2.setShortDescription("perfekt zum rudern :-)");
		r2.setWayPoints(createWayPoints());
		routeManagement.addRoute(r2, member);
	}

	private List<GpsPoint> createWayPoints() {
		List<GpsPoint> p = new LinkedList<GpsPoint>();
		p.add(new GpsPoint(51.25509323774028, 6.182534694671631));
		p.add(new GpsPoint(51.353364886551454, 6.153985261917114));
		p.add(new GpsPoint(51.3333333, 6.5666667));
		return p;
	}

	private void createTestBoats(Member member) throws RowBuddyException {
		Boat b1 = new Boat();
		b1.setName("Test Boot");
		b1.setCoxed(false);
		b1.setNumberOfSeats(3);
		boatManagement.addBoat(b1);
		b1.setId(null);
		b1.setName("Unsinkbar");
		boatManagement.addBoat(b1);
		b1.setId(null);
		b1.setName("Unsinkbar 2");
		b1.setCoxed(true);
		b1.setLocked(true);
		boatManagement.addBoat(b1);
		b1.setId(null);
		b1.setNumberOfSeats(10);
		b1.setName("Dicke Berta");
		b1.setLocked(false);
		boatManagement.addBoat(b1);
		b1.setId(null);
		b1.setNumberOfSeats(1);
		b1.setName("Sonderzeichen Boot öüä");
		b1.setLocked(true);
		b1.setCoxed(false);
		boatManagement.addBoat(b1);

		BoatDamage damage = new BoatDamage();
		damage.setDamageDescription("Left side broken");
		boatManagement.addDamage(damage, member, b1.getId());

		BoatDamage damage2 = new BoatDamage();
		damage2.setFixed(true);
		damage2.setDamageDescription("Right side broken");
		boatManagement.addDamage(damage2, member, b1.getId());
	}

	private void createTestTrips(Member member) throws RowBuddyException,
			ParseException {
		TripMember tm1 = new TripMember();
		tm1.setMember(member);
		tm1.setTripMemberType(TripMemberType.Rower);

		Trip trip = new Trip();
		trip.addTripMember(tm1);
		trip.setRoute(routeManagement.getRouteList().get(0));
		trip.setBoat(boatManagement.getBoatOverview().get(0));
		trip.setStartDate(new Date());
		logbook.startTrip(trip, member);

		TripMember tm2 = new TripMember();
		tm2.setMember(member);
		tm2.setTripMemberType(TripMemberType.Cox);

		Trip trip2 = new Trip();
		trip2.addTripMember(tm2);
		trip2.setRoute(routeManagement.getRouteList().get(1));
		trip2.setBoat(boatManagement.getBoatOverview().get(1));

		trip2.setStartDate(DateFormat.getInstance()
				.parse("15.03.2010 13:12:00"));
		trip2.setEndDate(DateFormat.getInstance().parse("15.03.2010 14:16:00"));
		logbook.logRowedTrip(trip2, member);
	}

	private Member createTestMember(boolean admin) throws RowBuddyException {
		if (!admin) {
			Member testMember = new Member();
			testMember.setGivenname("Jan");
			testMember.setSurname("Trzeszkowski");
			testMember.setEmail("bla@bla.de");
			testMember.setBirthdate(new Date(System.currentTimeMillis()));
			testMember.setMemberId("XDSADSF-221");
			Member toReturn = memberManagement.addMember(testMember,
					new RoleName[] { RoleName.MEMBER });
			memberManagement.setPassword(toReturn.getId(), toReturn, "bla");
			return testMember;
		} else {
			Member testMember = new Member();
			testMember.setGivenname("Jan");
			testMember.setSurname("Trzeszkowski");
			testMember.setEmail("bla@bla.de");
			testMember.setBirthdate(new Date(System.currentTimeMillis()));
			testMember.setMemberId("XDSADSF-222");
			Member toReturn = memberManagement.addMember(testMember,
					new RoleName[] { RoleName.MEMBER, RoleName.ADMIN });
			memberManagement.setPassword(toReturn.getId(), toReturn, "bla");
			return toReturn;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
