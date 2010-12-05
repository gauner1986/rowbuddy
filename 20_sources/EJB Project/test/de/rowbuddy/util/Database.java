package de.rowbuddy.util;

import java.util.List;

import nl.fontys.rowbuddy.EntityManagerBean;
import nl.fontys.rowbuddy.EntityManagerBeanLocal;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.BoatDamage;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Route;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

public class Database {

	private static Database singleton;
	public static Database getInstance(){
		if (singleton == null){
			singleton = new Database();
		}
		return singleton;
	}
	
	private EntityManagerBeanLocal em;
	private List<Boat> boats;
	private List<Member> members;
	private List<Route> routes;
	
	private Database(){
        em = (EntityManagerBeanLocal) Ejb.lookUp(EntityManagerBean.class, EntityManagerBeanLocal.class);
	}
	
	public void clearDatabase(){
		List<Trip> trips = em.getAllEntities(Trip.class);
		for (Trip trip : trips){
			em.remove(Trip.class, trip.getId());
		}
		
		List<BoatDamage> damages = em.getAllEntities(BoatDamage.class);
		for(BoatDamage damage : damages){
			damage.setBoat(null);
			damage.setLogger(null);
			em.merge(damage);
		}
		
		clearAllEntities(Boat.class);
		clearAllEntities(Route.class);
		clearAllEntities(Member.class);
	}

	public <T> void clearAllEntities(Class<T> entityType) {
        em.queryExecuteUpdate("delete from " + entityType.getSimpleName());
    }
		
	public void setupBoats() throws RowBuddyException{
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
	
	public void setupMembers() throws RowBuddyException{
		Member member1 = new Member();
		member1.setSurname("Pan");
		member1.setGivenname("Peter");
		member1.setEmail("test@test.de");
		em.persist(member1);
		
		members = em.getAllEntities(Member.class);
	}
	
	public void setupRoutes() throws RowBuddyException{
		Route route1 = new Route();
		route1.setName("Testroute 1");
		route1.setLengthKM(5.5);
		route1.setMutable(true);
		route1.setShortDescription("This is a unit-test route");
		em.persist(route1);
		
		routes = em.getAllEntities(Route.class);
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
	
}
