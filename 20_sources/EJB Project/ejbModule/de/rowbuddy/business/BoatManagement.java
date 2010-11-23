package de.rowbuddy.business;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import de.rowbuddy.business.dtos.BoatDTO;
import de.rowbuddy.business.dtos.BoatDTOConverter;
import de.rowbuddy.dao.BoatDAO;
import de.rowbuddy.dao.EntityUtil;
import de.rowbuddy.entities.Boat;

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

	public List<BoatDTO> getBoatOverview() {
		TypedQuery<Boat> q = em.createQuery(
				"SELECT b FROM Boat b WHERE b.deleted = false", Boat.class);
		return boatDTO.getList(q.getResultList());
	}

	public Boat getBoat(Long id) throws FinderException {
		if (id == null) {
			throw new FinderException("id must be specified");
		}

		return em.getReference(Boat.class, id);
	}

	public Boat addBoat(Boat addBoat) throws CreateException {

		try {
			checkConsistency(addBoat);
		} catch (RowBuddyException ex) {
			throw new CreateException(ex.toString());
		}

		if (addBoat.getId() != null) {
			throw new CreateException(
					"boat is not allowed to have a predefined id");
		}

		em.persist(addBoat);
		return addBoat;
	}

	public Boat updateBoat(Boat updateBoat) throws FinderException,
			RowBuddyException {

		checkConsistency(updateBoat);

		if (updateBoat.getId() == null) {
			throw new FinderException("You must specify an id");
		}

		Boat dbBoat = em.find(Boat.class, updateBoat.getId());
		if (dbBoat == null) {
			throw new FinderException("Boat does not exist");
		}

		if (dbBoat.isDeleted()) {
			throw new FinderException("Boat was deleted and cannot be updated");
		}
		em.merge(updateBoat);

		return updateBoat;
	}

	public void deleteBoat(Long id) throws RemoveException {
		if (id == null) {
			throw new RemoveException("You must specify an id");
		}

		Boat boat = em.find(Boat.class, id);
		if (boat == null) {
			throw new RemoveException("Boat does not exist");
		}

		if (boat.isDeleted()) {
			throw new RemoveException("Boat is already deleted");
		}
		boat.setDeleted(true);
	}

	private void checkConsistency(Boat boat) throws RowBuddyException {

		// TODO: Entity bezogene Checks in Entities auslagern

		if (boat.getName().isEmpty()) {
			throw new RowBuddyException("Boat must have a name");
		}
		if (boat.getNumberOfSeats() <= 0) {
			throw new RowBuddyException(
					"Boat must have a positive number of seats");
		}
		if (boat.isDeleted()) {
			throw new RowBuddyException("Cannot save deleted boat");
		}
	}
}
