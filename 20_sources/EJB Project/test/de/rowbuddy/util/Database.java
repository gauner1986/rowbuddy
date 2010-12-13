package de.rowbuddy.util;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import de.rowbuddy.EntityManagerBean;
import de.rowbuddy.EntityManagerBeanLocal;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.BoatDamage;
import de.rowbuddy.entities.GpsPoint;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Route;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.entities.TripMember;
import de.rowbuddy.exceptions.RowBuddyException;

public class Database {

	private static Database singleton;

	public static Database getInstance() {
		if (singleton == null) {
			singleton = new Database();
		}
		return singleton;
	}

	private EntityManagerBeanLocal em;
	private List<Boat> boats;
	private List<Member> members;
	private List<Route> routes;
	private List<Trip> trips;

	private Database() {
		em = (EntityManagerBeanLocal) Ejb.lookUp(EntityManagerBean.class,
				EntityManagerBeanLocal.class);
	}

	public void clearDatabase() {
		List<Trip> trips = em.getAllEntities(Trip.class);
		for (Trip trip : trips) {
			em.remove(Trip.class, trip.getId());
		}

		List<BoatDamage> damages = em.getAllEntities(BoatDamage.class);
		for (BoatDamage damage : damages) {
			damage.removeBoat();
			em.merge(damage);
		}
		List<Boat> boats = em.getAllEntities(Boat.class);
		for (Boat boat : boats) {
			boat.setBoatDamages(new LinkedList<BoatDamage>());
			em.merge(boat);
		}

		clearAllEntities(BoatDamage.class);
		clearAllEntities(Boat.class);
		clearAllEntities(Route.class);
		clearAllEntities(Member.class);
	}

	public <T> void clearAllEntities(Class<T> entityType) {
		em.queryExecuteUpdate("delete from " + entityType.getSimpleName());
	}

	public void setupBoats() throws RowBuddyException {
		Boat boat1 = new Boat();
		boat1.setName("Boat 1");
		boat1.setNumberOfSeats(5);
		em.persist(boat1);

		Boat boat2 = new Boat();
		boat2.setName("Boat 2");
		boat2.setNumberOfSeats(3);
		em.persist(boat2);

		boats = em.getAllEntities(Boat.class);
	}

	public void setupMembers() throws RowBuddyException {
		Member member1 = new Member();
		member1.setSurname("Pan");
		member1.setGivenname("Peter");
		member1.setEmail("test@test.de");
		member1.setMemberId("ID123");
		em.persist(member1);

		Member member2 = new Member();
		member2.setSurname("Fridolin");
		member2.setGivenname("Freud");
		member2.setEmail("fridolin@freud.de");
		member2.setMemberId("ID124");
		em.persist(member2);

		members = em.getAllEntities(Member.class);
	}

	public void setupRoutes() throws RowBuddyException {
		Route route1 = new Route();
		route1.setName("Testroute 1");
		route1.setLengthKM(5.5);
		route1.setMutable(true);
		route1.setShortDescription("This is a unit-test route 1");
		route1.setLastEditor(getMembers().get(0));
		em.persist(route1);

		Route route2 = new Route();
		route2.setName("Testroute 2");
		route2.setLengthKM(6.4);
		route2.setMutable(true);
		route2.setShortDescription("This is a unit-test route 2");
		route2.setLastEditor(getMembers().get(0));
		em.persist(route2);

		Route route3 = new Route();
		route3.setName("Deleted Testroute 3");
		route3.setLengthKM(6.4);
		route3.setMutable(false);
		route3.setShortDescription("This is a unit-test route 3");
		route3.setDeleted(true);
		route3.setLastEditor(getMembers().get(0));
		em.persist(route3);

		Route route4 = new Route();
		route4.setName("Deleted Testroute 4");
		route4.setLengthKM(6.4);
		route4.setMutable(false);
		route4.setShortDescription("This is a unit-test route 4");
		route4.setDeleted(true);
		route4.setLastEditor(getMembers().get(0));
		List<GpsPoint> p = new LinkedList<GpsPoint>();
		p.add(new GpsPoint(51.25509323774028, 6.182534694671631));
		p.add(new GpsPoint(51.353364886551454, 6.153985261917114));
		route4.setWayPoints(p);
		em.persist(route4);

		Route route5 = new Route();
		route5.setName("Testroute 5");
		route5.setLengthKM(6.4);
		route5.setMutable(false);
		route5.setShortDescription("This is a unit-test route 2");
		route5.setLastEditor(getMembers().get(0));
		em.persist(route5);

		routes = em.getAllEntities(Route.class);
	}

	public void setupTrips() {
		Trip trip1 = new Trip();
		trip1.setBoat(boats.get(0));
		trip1.setStartDate(new Date());
		trip1.setFinished(false);
		trip1.setRoute(routes.get(0));
		TripMember tm1 = new TripMember();
		tm1.setMember(members.get(0));
		trip1.addTripMember(tm1);
		em.persist(trip1);

		trips = em.getAllEntities(Trip.class);
	}

	public List<Boat> getBoats() {
		return boats;
	}

	public List<Member> getMembers() {
		return members;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public List<Trip> getTrips() {
		return trips;
	}

}
