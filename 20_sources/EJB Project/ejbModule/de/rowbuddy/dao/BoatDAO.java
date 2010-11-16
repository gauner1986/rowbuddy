package de.rowbuddy.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import de.rowbuddy.entities.Boat;

/**
 * Boat DAO Bean
 * 
 * @author Jan Nikolai Trzeszkowski <info@j-n-t.de>
 * @version 1
 */
@Stateless
public class BoatDAO extends GenericDAO<Boat, Long> {
	
	public Boat add(Boat boat) throws EntityExistsException {
		getEntityManager().persist(boat);
		return boat;
	}
	
	public void delete(Long id) {
		// TODO
	}
	
	public Boat update(Boat boat) {
		boat = getEntityManager().merge(boat);
		return boat;
	}
	
	public Boat get(Long id) throws EntityNotFoundException {
		Boat boat = null;
		boat = getEntityManager().getReference(Boat.class, id);
		return boat;
	}
	
	@SuppressWarnings("unchecked")
	public List<Boat> getAll() {
		return (List<Boat>) getEntityManager().createQuery("select b from "+ Boat.class + " as b").getResultList();
	}
	
}
