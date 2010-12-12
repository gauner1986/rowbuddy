package de.rowbuddy.boundary.dtos;

import de.rowbuddy.entities.Trip;

public class TripDTOConverter extends DtoConverter<Trip, TripDTO> {

	private final static TripMemberDTOConverter tmConverter = new TripMemberDTOConverter();
	private final static MemberDTOConverter mConverter = new MemberDTOConverter();

	@Override
	public TripDTO getDto(Trip trip) {
		TripDTO dto = new TripDTO();

		dto.setId(trip.getId());
		dto.setStartDate(trip.getStartDate());
		dto.setEndDate(trip.getEndDate());
		dto.setBoat(trip.getBoat());
		dto.setTripMembers(tmConverter.getList(trip.getTripMembers()));
		dto.setLastEditor(mConverter.getDto(trip.getLastEditor()));
		dto.setRoute(trip.getRoute());
		dto.setFinished(trip.isFinished());
		dto.setCanEditTrip(false);
		return dto;
	}

}
