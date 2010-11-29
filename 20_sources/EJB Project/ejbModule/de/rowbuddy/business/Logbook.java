package de.rowbuddy.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.rowbuddy.business.dtos.TripDTO;
import de.rowbuddy.business.dtos.TripDTOConverter;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

@Stateless
@LocalBean
public class Logbook {

	@PersistenceContext
	EntityManager em;
	TripDTOConverter tripConverter = new TripDTOConverter();

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

		em.persist(rowedTrip);
	}

	/**
	 * Start rowing a trip. Only the start date needs to be specified
	 * 
	 * @param startedTrip
	 *            the trip
	 * @param editor
	 *            the person who is editing the trip
	 * @throws RowBuddyException
	 *             Thrown if validation of trip failed
	 */
	public void startTrip(Trip startedTrip, Member editor)
			throws RowBuddyException {

		if (startedTrip.getId() != null) {
			throw new RowBuddyException("Cannot start existing trip");
		}

		startedTrip.validateStartedTrip();

		startedTrip.setLastEditor(editor);

		em.persist(startedTrip);
	}

	/**
	 * @return List of trips that are not finished yet.
	 */
	public List<TripDTO> getOpenTrips(Member currentUser) {
		TypedQuery<Trip> q = em.createQuery(
				"SELECT t FROM Trip t WHERE t.finished=false", Trip.class);
		List<Trip> trips =q.getResultList();
		List<TripDTO> dtoList = tripConverter.getList(trips);
		for (TripDTO dto : dtoList){
			dto.canEditTrip = canEditTrip(dto.trip, currentUser);
		}
		return dtoList; 
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
			throw new RowBuddyException("Cannot finish unsaved trip");
		}

		Trip dbTrip = em.find(Trip.class, openTrip.getId());
		if (dbTrip == null) {
			throw new RowBuddyException("Trip does not exist");
		}

		if (!canEditTrip(dbTrip, currentUser)) {
			throw new RowBuddyException("You are not allowed to edit this trip");
		}

		openTrip.validateFinishedTrip();

		openTrip.setLastEditor(currentUser);

		em.merge(openTrip);
	}

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
		if (persistedMember.isInRole("admin")) {
			return true;
		}
		return false;
	}
}
