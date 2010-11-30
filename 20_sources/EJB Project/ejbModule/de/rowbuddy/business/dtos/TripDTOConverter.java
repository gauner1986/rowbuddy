package de.rowbuddy.business.dtos;

import de.rowbuddy.entities.Trip;

public class TripDTOConverter extends DtoConverter<Trip, TripDTO>	 {

	@Override
	public TripDTO getDto(Trip trip) {
		TripDTO dto = new TripDTO();

		dto.setId(trip.getId());
		dto.setStartDate(trip.getStartDate());
		dto.setEndDate(trip.getEndDate());
		dto.setBoat(trip.getBoat());
		dto.setTripMembers(trip.getTripMembers());
		dto.setLastEditor(trip.getLastEditor());
		dto.setRoute(trip.getRoute());
		dto.setFinished(trip.isFinished());
		dto.setCanEditTrip(false);
		return dto;
	}
	
}
