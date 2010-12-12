package de.rowbuddy.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.rowbuddy.boundary.BoatBoundary;
import de.rowbuddy.boundary.LogbookBoundary;
import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.DamageDTO;
import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.boundary.dtos.MemberDTOConverter;
import de.rowbuddy.boundary.dtos.PersonalTripDTO;
import de.rowbuddy.boundary.dtos.TripDTO;
import de.rowbuddy.business.Logbook.ListType;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.BoatDamage;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role;
import de.rowbuddy.entities.Route;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.NotLoggedInException;
import de.rowbuddy.exceptions.RowBuddyException;

/**
 * Session Bean implementation class RowBuddyFacade
 */
@Interceptors(SecuritySalamander.class)
@Stateful
@LocalBean
public class RowBuddyFacade {

	private Member member;

	@PersistenceContext
	EntityManager em;
	@EJB
	private BoatManagement boatManagement;
	@EJB
	private BoatBoundary boatBoundary;
	@EJB
	private Logbook logbook;
	@EJB
	private LogbookBoundary logbookBoundary;
	@EJB
	private RouteManagement routeManagement;

	/**
	 * Default constructor.
	 */
	public RowBuddyFacade() {

	}

	@ExcludeClassInterceptors
	public void login(Member member) throws NotLoggedInException {
		if (member.getEmail() == null || member.getPassword() == null)
			throw new NotLoggedInException(
					"You didn't specify an E-Mail address and/or a password");

		Query q = em
				.createQuery("SELECT m FROM Member m WHERE m.email = :email");
		q.setParameter("email", member.getEmail());
		Member m = null;
		try {
			m = (Member) q.getSingleResult();
		} catch (NoResultException nre) {
			throw new NotLoggedInException(
					"This member doesn't exist in our database");
		}
		if (m == null)
			throw new NotLoggedInException(
					"This member doesn't exist in our database");

		if (!m.getPassword().equals(member.getPassword())) {
			throw new NotLoggedInException(
					"The password you specified is not correct");
		} else {
			this.member = m;
		}
	}

	public MemberDTO getMember() {
		return new MemberDTOConverter().getDto(member);
	}

	@ExcludeClassInterceptors
	public void logout() {
		member = null;
	}

	@ExcludeClassInterceptors
	public boolean isLoggedIn() {
		return (member != null);
	}

	public List<BoatDTO> getBoatOverview() {
		return boatBoundary.getBoatOverview();
	}

	public Boat getBoat(Long id) throws RowBuddyException {
		return boatManagement.getBoat(id);
	}

	@AllowedRoles(values = { Role.RoleName.ADMIN })
	public Boat addBoat(Boat addBoat) throws RowBuddyException {
		return boatManagement.addBoat(addBoat);
	}

	@AllowedRoles(values = { Role.RoleName.ADMIN })
	public Boat updateBoat(Boat updateBoat) throws RowBuddyException,
			RowBuddyException {
		return boatManagement.updateBoat(updateBoat);
	}

	@AllowedRoles(values = { Role.RoleName.ADMIN })
	public void deleteBoat(Long id) throws RowBuddyException {
		boatManagement.deleteBoat(id);
	}

	public void logRowedTrip(Trip rowedTrip) throws RowBuddyException {
		logbook.logRowedTrip(rowedTrip, this.member);
	}

	public void startTrip(Trip startedTrip) throws RowBuddyException {
		logbook.startTrip(startedTrip, this.member);
	}

	public Trip getTrip(Long id) throws RowBuddyException {
		return logbook.getTrip(id);
	}

	public List<TripDTO> getOpenTrips() {
		return logbookBoundary.getOpenTrips(this.member);
	}

	public void finishTrip(Trip openTrip) throws RowBuddyException {
		logbook.finishTrip(openTrip, this.member);
	}

	public List<PersonalTripDTO> getPersonalTrips() {
		return logbook.getPersonalTrips(this.member, ListType.All);
	}

	public List<PersonalTripDTO> getPersonalOpenTrips() {
		return logbook.getPersonalTrips(this.member, ListType.OpenOnly);
	}

	public List<DamageDTO> getOpenDamages() {
		return boatBoundary.getOpenDamages();
	}

	public List<DamageDTO> getAllDamages() {
		return boatBoundary.getAllDamages();
	}

	public BoatDamage getDamage(Long id) throws RowBuddyException {
		return boatManagement.getDamage(id);
	}

	public void updateDamage(BoatDamage damage) {
		try {
			boatManagement.updateDamage(damage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<BoatDTO> search(String search) {
		return boatBoundary.searchBoat(search);
	}

	public void addDamage(BoatDamage damage, Long boatId)
			throws RowBuddyException {
		boatManagement.addDamage(damage, member, boatId);
	}

	public Route addRoute(Route newRoute) throws RowBuddyException {
		return routeManagement.addRoute(newRoute, member);
	}

	public List<Route> getRouteList() throws RowBuddyException {
		return routeManagement.getRouteList();
	}

	public Route editRoute(Route route) throws RowBuddyException {
		return routeManagement.editRoute(route, member);
	}

	public Route getRoute(Long id) throws RowBuddyException {
		return routeManagement.getRoute(id);
	}

	public void deleteRoute(Long id) throws RowBuddyException {
		routeManagement.deleteRoute(id, member);
	}

	public boolean canEditRoute(Route route) {
		return routeManagement.canEditRoute(route, member);
	}

}
