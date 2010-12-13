package de.rowbuddy.boundary.converter;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.entities.Boat;

public class BoatDTOConverter extends DtoConverter<Boat, BoatDTO> {

	@Override
	public BoatDTO getDto(Boat boat) {
		BoatDTO bo = new BoatDTO();
		bo.setId(boat.getId());
		bo.setName(boat.getName());
		bo.setCoxed(boat.isCoxed());
		bo.setNumberOfSeats(boat.getNumberOfSeats());
		bo.setLocked(boat.isLocked());

		return bo;
	}
}
