package de.rowbuddy.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.BoatDTOConverter;
import de.rowbuddy.dao.BoatDAO;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.exceptions.RowBuddyException;

@Stateless
public class BoatManagement {

	@PersistenceContext
	EntityManager em;

	@EJB
	BoatDAO boatDAO;
	BoatDTOConverter boatDTO;

	public BoatManagement() {

		// TODO: Security Salamander einbauen
		boatDTO = new BoatDTOConverter();
	}

	public List<Boat> getBoatOverview() {
		TypedQuery<Boat> q = em.createQuery(
				"SELECT b FROM Boat b WHERE b.deleted = false", Boat.class);
		return q.getResultList();
	}

	public Boat getBoat(Long id) throws RowBuddyException {
		if (id == null) {
			throw new RowBuddyException("Id must be specified");
		}

		return em.getReference(Boat.class, id);
	}

	public Boat addBoat(Boat addBoat) throws RowBuddyException {

		if (addBoat.getId() != null) {
			throw new RowBuddyException(
					"Boat is not allowed to have a predefined id");
		}

		if (addBoat.isDeleted()) {
			throw new RowBuddyException("Cannot add deleted boat");
		}
		
		addBoat.validate();

		em.persist(addBoat);
		return addBoat;
	}

	public Boat updateBoat(Boat updateBoat) throws RowBuddyException {

		if (updateBoat.getId() == null) {
			throw new RowBuddyException("You must specify an id");
		}

		if (updateBoat.isDeleted()) {
			throw new RowBuddyException("Cannot save deleted boat");
		}

		Boat dbBoat = em.find(Boat.class, updateBoat.getId());
		if (dbBoat == null) {
			throw new RowBuddyException("Boat does not exist");
		}

		if (dbBoat.isDeleted()) {
			throw new RowBuddyException(
					"Boat was deleted and cannot be updated");
		}
		
		updateBoat.validate();

		em.merge(updateBoat);

		return updateBoat;
	}

	public void deleteBoat(Long id) throws RowBuddyException {
		if (id == null) {
			throw new RowBuddyException("You must specify an id");
		}

		Boat boat = em.find(Boat.class, id);
		if (boat == null) {
			throw new RowBuddyException("Boat does not exist");
		}

		if (boat.isDeleted()) {
			throw new RowBuddyException("Boat is already deleted");
		}
		boat.setDeleted(true);
	}
}
