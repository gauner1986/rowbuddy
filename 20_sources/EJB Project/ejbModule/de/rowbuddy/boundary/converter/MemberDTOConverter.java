package de.rowbuddy.boundary.converter;

import de.rowbuddy.boundary.dtos.MemberDTO;
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
		dto.setStreet(entity.getStreet());
		dto.setSurname(entity.getSurname());
		dto.setZipCode(entity.getZipCode());
		dto.setPhone(entity.getPhone());
		dto.setMobilePhone(entity.getMobilePhone());
		dto.setFullName(entity.getFullName());
		dto.setAddress(entity.getAddress());
		return dto;
	}
}
