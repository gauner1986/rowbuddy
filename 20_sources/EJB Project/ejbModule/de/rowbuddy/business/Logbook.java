package de.rowbuddy.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

@Stateless
@LocalBean
public class Logbook {

	@PersistenceContext
	EntityManager em;

	public enum ListType {
		All, OpenOnly
	}

	/**
	 * Add a finished trip to the logbook. A trip contains information about the
	 * used boat, the route, start and endtime and the tripmembers that went on
	 * this trip
	 * 
	 * @param rowedTrip
	 *            information about the trip
	 * @param editor
	 *            the person who entered logged the trip
	 * @throws RowBuddyException
	 *             Thrown if validation for trip fails
	 */
	public void logRowedTrip(Trip rowedTrip, Member editor)
			throws RowBuddyException {

		if (rowedTrip.getId() != null) {
			throw new RowBuddyException("Cannot log existing trip");
		}

		rowedTrip.validateFinishedTrip();

		rowedTrip.setLastEditor(editor);
		rowedTrip.setFinished(true);

		em.persist(rowedTrip);
	}

	/**
	 * Start rowing a trip. Only the start date needs to be specified
	 * 
	 * @param startedTrip
	 *            the trip
	 * @param currentUser
	 *            the person who is editing the trip
	 * @throws RowBuddyException
	 *             Thrown if validation of trip failed
	 */
	public void startTrip(Trip startedTrip, Member currentUser)
			throws RowBuddyException {

		if (startedTrip.getId() != null) {
			throw new RowBuddyException("Trip Id muss null sein");
		}

		startedTrip.validateStartedTrip();

		startedTrip.setLastEditor(currentUser);
		startedTrip.setFinished(false);

		em.persist(startedTrip);
	}

	/**
	 * stops
	 * 
	 * @param openTrip
	 * @param currentUser
	 * @throws RowBuddyException
	 */
	public void finishTrip(Trip openTrip, Member currentUser)
			throws RowBuddyException {

		if (openTrip.getId() == null) {
			throw new RowBuddyException("Trip Id muss null sein");
		}

		Trip dbTrip = em.find(Trip.class, openTrip.getId());
		if (dbTrip == null) {
			throw new RowBuddyException("Trip existiert nicht");
		}

		if (!canEditTrip(dbTrip, currentUser)) {
			throw new RowBuddyException("Du darfst diesen Trip nicht aendern");
		}

		openTrip.validateFinishedTrip();

		openTrip.setLastEditor(currentUser);
		openTrip.setFinished(true);

		em.merge(openTrip);
	}

	/**
	 * checks if a user has the permission to edit a trip
	 * 
	 * @param trip
	 *            the trip that should be edited
	 * @param currentUser
	 *            the current user
	 * @return true if the user can edit the trip, otherwise false
	 */
	public boolean canEditTrip(Trip trip, Member currentUser) {
		Trip persistedTrip = trip;
		if (!em.contains(trip)) {
			persistedTrip = em.getReference(Trip.class, trip.getId());
		}

		Member persistedMember = currentUser;
		if (!em.contains(currentUser)) {
			persistedMember = em
					.getReference(Member.class, currentUser.getId());
		}

		if (persistedTrip.getLastEditor().equals(persistedMember)) {
			return true;
		}
		if (persistedTrip.getTripMembers().contains(persistedMember)) {
			return true;
		}
		if (persistedMember.isInRole(Role.RoleName.ADMIN)) {
			return true;
		}
		return false;
	}

	/**
	 * assembles trips that have not ended yet
	 * 
	 * @param currentUser
	 *            the current user
	 * @return a list with trips
	 */
	public List<Trip> getOpenTrips(Member currentUser) {
		TypedQuery<Trip> q = em.createQuery(
				"SELECT t FROM Trip t WHERE t.finished=false", Trip.class);
		return q.getResultList();
	}

	/**
	 * Finds a trip for a given id
	 * 
	 * @param id
	 *            id of the trip
	 * @return the trip
	 * @throws RowBuddyException
	 *             if id is null or if the trip is not found
	 */
	public Trip getTrip(Long id) throws RowBuddyException {
		if (id == null) {
			throw new RowBuddyException("You must specify an id");
		}

		Trip trip = em.find(Trip.class, id);
		if (trip == null) {
			throw new RowBuddyException("Trip existiert nicht");
		}
		return trip;
	}

	/**
	 * determines all trips that member has participated in
	 * 
	 * @param member
	 * @param listType
	 * @return a list of Trips
	 */
	public List<Trip> getPersonalTrips(Member member, ListType listType) {
		if (member == null) {
			throw new NullPointerException(
					"Es muss ein Mitglied angegeben werden");
		}

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT trip ");
		sb.append("FROM Trip trip INNER JOIN trip.tripMembers memberRole ");
		sb.append("WHERE memberRole.member = :member ");
		if (listType == ListType.OpenOnly) {
			sb.append("AND trip.finished=false");
		}

		TypedQuery<Trip> q = em.createQuery(sb.toString(), Trip.class);
		q.setParameter("member", member);
		return q.getResultList();
	}
}
