package de.rowbuddy.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

@Stateless
@LocalBean
public class TripManagement {

	@PersistenceContext
	EntityManager em;
	
	public void logRowedTrip(Trip rowedTrip, Member editor) throws RowBuddyException {
		rowedTrip.validate();
		rowedTrip.setLastEditor(editor);
		
		em.persist(rowedTrip);
	}

}
