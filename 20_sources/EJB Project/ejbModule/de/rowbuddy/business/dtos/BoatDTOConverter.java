package de.rowbuddy.business.dtos;

import java.util.List;
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
	
	public List<BoatDTO> getList(List<Boat>  boats){
		List<BoatDTO> collection = new LinkedList<BoatDTO>();
		for (Boat b : boats){
			collection.add(getObject(b));
		}
		return collection;
	}
}
