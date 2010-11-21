package de.rowbuddy.business.dtos;

import java.util.Collection;
import java.util.LinkedList;

import de.rowbuddy.entities.Boat;

public class BoatDTOConverter {
	
	public BoatDTO getObject(Boat boat){
		BoatDTO bo = new BoatDTO();
		bo.setId(boat.getId());
		bo.setName(boat.getName());
		bo.setCoxed(boat.isCoxed());
		bo.setNumberOfSeats(boat.getNumberOfSeats());
		bo.setLocked(boat.isLocked());
		
		return bo;
	}
	
	public Collection<BoatDTO> getCollection(Collection<Boat>  boats){
		Collection<BoatDTO> collection = new LinkedList<BoatDTO>();
		for (Boat b : boats){
			collection.add(getObject(b));
		}
		return collection;
	}
}
