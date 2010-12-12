package de.rowbuddy.boundary.dtos;

import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Trip;

public class PersonalTripDTOConverter extends
		DtoConverter<Trip, PersonalTripDTO> {

	private static final transient TripMemberDTOConverter tmConverter = new TripMemberDTOConverter();
	private static final transient MemberDTOConverter mConverter = new MemberDTOConverter();
	private static final transient BoatDTOConverter bConverter = new BoatDTOConverter();
	private static final transient RouteDTOConverter rConverter = new RouteDTOConverter();

	private Member member;

	public PersonalTripDTOConverter(Member member) {
		super();
		this.member = member;
	}

	@Override
	public PersonalTripDTO getDto(Trip entity) {
		PersonalTripDTO dto = new PersonalTripDTO();
		dto.setBoat(bConverter.getDto(entity.getBoat()));
		dto.setEndDate(entity.getEndDate());
		dto.setFinished(entity.isFinished());
		dto.setId(entity.getId());
		dto.setLastEditor(mConverter.getDto(entity.getLastEditor()));
		dto.setMemberRole(tmConverter.getDto(entity.getTripMemberFor(member)));
		dto.setRoute(rConverter.getDto(entity.getRoute()));
		dto.setStartDate(entity.getStartDate());
		return dto;
	}
}
