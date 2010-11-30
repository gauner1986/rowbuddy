package de.rowbuddy.business;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.FinderException;
import javax.ejb.LocalBean;
import javax.ejb.RemoveException;
import javax.ejb.Stateful;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.rowbuddy.boundary.BoatBoundary;
import de.rowbuddy.boundary.LogbookBoundary;
import de.rowbuddy.business.dtos.BoatDTO;
import de.rowbuddy.business.dtos.TripDTO;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.Member;
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

	/**
	 * Default constructor.
	 */
	public RowBuddyFacade() {

	}

	@ExcludeClassInterceptors
	public void login(Member member) throws NotLoggedInException {
		if (member.getEmail() == null || member.getPassword() == null)
			throw new NotLoggedInException("You didn't specify an E-Mail address and/or a password");

		Query q = em.createQuery("SELECT m FROM Member m WHERE m.email = :email");
		q.setParameter("email", member.getEmail());
		Member m = null;
		try {
			m = (Member) q.getSingleResult();
		} catch (NoResultException nre) {
			throw new NotLoggedInException("This member doesn't exist in our database");
		}
		if (m == null)
			throw new NotLoggedInException("This member doesn't exist in our database");

		if (!m.getPassword().equals(member.getPassword())) {
			throw new NotLoggedInException("The password you specified is not correct");
		} else {
			this.member = m;
		}
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
	
	@AllowedRoles(values = {"admin"})
	public Boat addBoat(Boat addBoat) throws RowBuddyException {
		return boatManagement.addBoat(addBoat);
	}

	@AllowedRoles(values = {"admin"})
	public Boat updateBoat(Boat updateBoat) throws RowBuddyException,
			RowBuddyException {
		return boatManagement.updateBoat(updateBoat);
	}
	
	@AllowedRoles(values = {"admin"})
	public void deleteBoat(Long id) throws RowBuddyException {
		boatManagement.deleteBoat(id);
	}

	public void logRowedTrip(Trip rowedTrip) throws RowBuddyException{
		logbook.logRowedTrip(rowedTrip, this.member);
	}

	public void startTrip(Trip startedTrip) throws RowBuddyException{
		logbook.startTrip(startedTrip, this.member);
	}
	
	public Trip getTrip(Long id) throws RowBuddyException{
		return logbook.getTrip(id);
	}

	public List<TripDTO> getOpenTrips(){
		return logbookBoundary.getOpenTrips(this.member);
	}

	public void finishTrip(Trip openTrip) throws RowBuddyException{
		logbook.finishTrip(openTrip, this.member);
	}
}
