package de.rowbuddy.boundary.dtos;

import de.rowbuddy.entities.TripMember;

public class TripMemberDTOConverter extends
		DtoConverter<TripMember, TripMemberDTO> {

	private static final transient MemberDTOConverter mConv = new MemberDTOConverter();

	@Override
	public TripMemberDTO getDto(TripMember entity) {
		TripMemberDTO dto = new TripMemberDTO();
		dto.setId(entity.getId());
		dto.setMember(mConv.getDto(entity.getMember()));
		dto.setTripMemberType(entity.getTripMemberType());
		return dto;
	}
}
