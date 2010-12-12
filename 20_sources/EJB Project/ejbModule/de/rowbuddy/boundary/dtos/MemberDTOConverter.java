package de.rowbuddy.boundary.dtos;

import de.rowbuddy.entities.Member;

public class MemberDTOConverter extends DtoConverter<Member, MemberDTO> {

	@Override
	public MemberDTO getDto(Member entity) {
		MemberDTO dto = new MemberDTO();
		dto.setBirthdate(entity.getBirthdate());
		dto.setCity(entity.getCity());
		dto.setDeleted(entity.getDeleted());
		dto.setEmail(entity.getEmail());
		dto.setGivenname(dto.getGivenname());
		dto.setId(dto.getId());
		dto.setMemberId(dto.getMemberId());
		dto.setPassword(entity.getPassword());
		dto.setStreet(entity.getStreet());
		dto.setSurname(entity.getSurname());
		dto.setZipCode(entity.getZipCode());
		return dto;
	}

}
