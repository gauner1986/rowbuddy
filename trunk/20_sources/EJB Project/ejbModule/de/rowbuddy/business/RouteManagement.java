package de.rowbuddy.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Route;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

@LocalBean
@Stateless
public class RouteManagement {

	@PersistenceContext
	private EntityManager em;

	public Route addRoute(Route newRoute, Member currentUser)
			throws RowBuddyException {
		if (newRoute == null) {
			throw new RowBuddyException("Route must be specified");
		}

		if (currentUser == null) {
			throw new RowBuddyException("User must not be null");
		}

		if (newRoute.getId() != null) {
			throw new RowBuddyException("Id must not be specified");
		}

		if (newRoute.getParentId() != null) {
			throw new RowBuddyException("Parent id must not be specified");
		}

		if (newRoute.getLastEditor() != null) {
			throw new RowBuddyException("Last editor must not be set");
		}

		if (newRoute.isDeleted()) {
			throw new RowBuddyException("Cannot add a deleted route");
		}

		newRoute.validate();
		newRoute.setLastEditor(currentUser);

		em.persist(newRoute);
		return newRoute;
	}

	/**
	 * Liefert eine Liste mit allen ausw�hlbaren Routen. Dies sind alle nicht
	 * gel�schten Routen. �ltere Versionen werden nicht zur�ckgegeben.
	 * 
	 * @return Liste mit allen ausw�hlbaren Routen
	 */
	public List<Route> getRouteList() {
		TypedQuery<Route> q = em.createQuery(
				"SELECT r FROM Route r WHERE r.deleted=false", Route.class);
		return q.getResultList();
	}

	/**
	 * Speichert die �nderungen zu einer Route. Wenn die Route bereits in einem
	 * Trip referenziert wurde, dann wird eine neue Version der Route erstellt.
	 * Ist die Route unreferenziert, dann wird die gleiche Version mit den
	 * ge�nderten Werten wieder zur�ckgegeben. Wenn die Route als mutable
	 * gekennzeichnet ist, dann kann jeder Benutzer sie �ndern. Andernfalls kann
	 * nur der letzte Bearbeiter sie �ndern. Wird eine neue Version der Route
	 * erstellt, dann wird die alte als gel�scht markiert. So wird
	 * sichergestellt, dass immer nur die aktuellste Version einer Route
	 * existiert.
	 * 
	 * @return Die ge�nderte Version der Route.
	 * @throws RowBuddyException
	 *             Kann durch diverse Logikpr�fungen geworfen werden
	 */
	public Route editRoute(Route route, Member editor) throws RowBuddyException {

		if (route == null) {
			throw new RowBuddyException("Route must not be null");
		}

		if (editor == null) {
			throw new RowBuddyException("CurrentUser must not be null");
		}

		if (route.getId() == null) {
			throw new RowBuddyException("Id must not be null");
		}

		if (!canEditRoute(route, editor)) {
			throw new RowBuddyException("Route cannot be edited");
		}

		route.validate();

		Route fromDb = em.find(Route.class, route.getId());
		if (fromDb == null) {
			throw new RowBuddyException("Route does not exist");
		}

		Member persistedMember = editor;
		if (!em.contains(editor)) {
			persistedMember = em.getReference(Member.class, editor.getId());
		}

		Route newVersion = fromDb;
		if (hasTripReferences(fromDb)) {
			fromDb.setDeleted(true);

			newVersion = new Route();
			newVersion.setParentId(fromDb.getId());

			em.persist(newVersion);
		}
		newVersion.setLengthKM(route.getLengthKM());
		newVersion.setMutable(route.isMutable());
		newVersion.setName(route.getName());
		newVersion.setShortDescription(route.getShortDescription());
		newVersion.setWayPoints(route.getWayPoints());
		newVersion.setLastEditor(persistedMember);

		return newVersion;
	}

	/**
	 * Liefert die entsprechende Route zu einer id. Diese kann auch die ID einer
	 * �lteren Version einer Route sein.
	 * 
	 * @param id
	 *            id einer Route
	 * @return die Route
	 * @throws RowBuddyException
	 *             wenn die Route nicht mehr existiert
	 */
	public Route getRoute(Long id) throws RowBuddyException {
		if (id == null) {
			throw new RowBuddyException("id must not be null");
		}

		Route route = em.find(Route.class, id);
		if (route == null) {
			throw new RowBuddyException("Route was not found");
		}

		return route;
	}

	/**
	 * Determines, if a route is used in a trip.
	 * 
	 * @param route
	 *            the route to be checked for
	 * @return true, if the route is used in a trip, otherwise false
	 */
	public boolean hasTripReferences(Route route) {
		TypedQuery<Trip> trips = em.createQuery(
				"SELECT t FROM Trip t WHERE t.route = :route", Trip.class);
		trips.setParameter("route", route);
		if (trips.getResultList().size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public void deleteRoute(Long id, Member deleter) throws RowBuddyException {
		if (id == null) {
			throw new RowBuddyException("id must be specified");
		}

		if (deleter == null) {
			throw new RowBuddyException("deleter must be specified");
		}

		Route route = getRoute(id);

		if (!canEditRoute(route, deleter)) {
			throw new RowBuddyException("Route cannot be edited");
		}

		route.setDeleted(true);
		route.setLastEditor(deleter);
	}

	public boolean canEditRoute(Route route, Member currentUser) {
		Route persistedRoute = route;
		if (!em.contains(persistedRoute)) {
			persistedRoute = em.getReference(Route.class, route.getId());
		}

		Member persistedMember = currentUser;
		if (!em.contains(currentUser)) {
			persistedMember = em
					.getReference(Member.class, currentUser.getId());
		}

		if (persistedRoute.isDeleted()) {
			return false;
		}

		if (persistedRoute.isMutable()) {
			return true;
		}
		if (persistedMember.equals(persistedRoute.getLastEditor())) {
			return true;
		}

		return false;
	}
}
