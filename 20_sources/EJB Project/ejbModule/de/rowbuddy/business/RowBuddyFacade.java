package de.rowbuddy.business;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.rowbuddy.entities.Member;

/**
 * Session Bean implementation class RowBuddyFacade
 */
@Interceptors(SecuritySalamander.class)
@Stateful
@LocalBean
public class RowBuddyFacade {

	//TODO: Refactor to UserSessionBean
	
	private Member member;
	@EJB
	private BoatManagement boatManagement;

	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public RowBuddyFacade() {

	}
	
	// TODO: Umstellen von Returnvalues auf Checked Exceptions;
	// TODO: void als return value

	@ExcludeClassInterceptors
	public boolean login(Member member) {
		if (member.getEmail() == null || member.getPassword() == null)
			return false;

		Query q = em
				.createQuery("SELECT m FROM Member m WHERE m.email = :email");
		q.setParameter("email", member.getEmail());
		Member m = null;
		try {
			m = (Member) q.getSingleResult();
		} catch (NoResultException nre) {
			return false;
		}
		if (m == null)
			return false;

		if (!m.getPassword().equals(member.getPassword())) {
			return false;
		} else {
			this.member = m;
			return true;
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

	public BoatManagement getBoatManagement() {
		return this.boatManagement;
	}

}
