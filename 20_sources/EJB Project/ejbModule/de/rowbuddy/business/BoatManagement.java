package de.rowbuddy.business;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
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
	EntityUtil entityUtil;

	public BoatManagement() {
		boatDTO = new BoatDTOConverter();
		entityUtil = new EntityUtil(em);
	}

	public Collection<BoatDTO> getBoatOverview() {
		TypedQuery<Boat> q = em.createQuery("SELECT b FROM Boat b WHERE b.deleted = false", Boat.class);
		return boatDTO.getCollection(q.getResultList());
	}

	public Boat getBoat(Long id) {
		if (id == null){
			throw new IllegalArgumentException("id must be specified");
		}
		
		return em.getReference(Boat.class, id);
	}

	public Boat addBoat(Boat addBoat) {

		checkConsistency(addBoat);
		
		if (addBoat.getId() != null) {
			throw new IllegalArgumentException("boat is not allowed to have a predefined id");
		}

		em.persist(addBoat);
		return addBoat;
	}

	public Boat updateBoat(Boat updateBoat) {

		checkConsistency(updateBoat);

		if (updateBoat.getId() == null) {
			throw new EntityNotFoundException("you must specify an id");
		}

		Boat dbBoat = em.find(Boat.class, updateBoat.getId());
		if (dbBoat == null){
			throw new EntityNotFoundException("boat does not exist");
		}
		
		if (dbBoat.isDeleted()) {
			throw new EntityNotFoundException("boat is deleted and cannot be updated");
		}
		em.merge(updateBoat);

		return updateBoat;
	}

	public void deleteBoat(Long id) {
		if (id == null){
			throw new IllegalArgumentException("id must be specified");
		}
		
		Boat boat = em.find(Boat.class, id);
		if (boat == null){
			throw new EntityNotFoundException("boat does not exist");
		}
		
		if (boat.isDeleted()){
			throw new EntityNotFoundException("boat is already deleted");
		}
		boat.setDeleted(true);
	}

	private void checkConsistency(Boat boat) {

		if (boat.getName().isEmpty()) {
			throw new IllegalArgumentException("please specify a name");
		}
		if (boat.getNumberOfSeats() <= 0) {
			throw new IllegalArgumentException(
					"please specify the number of seats");
		}
		if (boat.isDeleted()) {
			throw new IllegalArgumentException("cannot save deleted boat");
		}
	}
}
