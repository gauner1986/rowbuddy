package de.rowbuddy.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

@Stateless
@LocalBean
public class Logbook {

	@PersistenceContext
	EntityManager em;

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
		rowedTrip.finish();

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
		startedTrip.start();

		em.persist(startedTrip);
	}

	/**
	 * @return List of trips that are not finished yet.
	 */
	public List<Trip> getOpenTrips() {
		TypedQuery<Trip> q = em.createQuery(
				"SELECT t FROM Trip t WHERE t.finished=false", Trip.class);
		return q.getResultList();
	}

	/**
	 * stops
	 * 
	 * @param openTrip
	 * @param editor
	 * @throws RowBuddyException
	 */
	public void finishTrip(Trip openTrip, Member editor)
			throws RowBuddyException {

		if (openTrip.getId() == null) {
			throw new RowBuddyException("Cannot finish unsaved trip");
		}

		Trip dbTrip = em.find(Trip.class, openTrip.getId());
		if (dbTrip == null) {
			throw new RowBuddyException("Trip does not exist");
		}

		// todo: check permissions (last editor, trip members, admin)

		openTrip.validateFinishedTrip();

		openTrip.setLastEditor(editor);
		openTrip.finish();

		em.merge(openTrip);
	}
}
