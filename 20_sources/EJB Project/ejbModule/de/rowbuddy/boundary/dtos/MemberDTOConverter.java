package de.rowbuddy.boundary.dtos;

import java.util.List;

import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Trip;

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
		TripDTOConverter conv = new TripDTOConverter();
//		ArrayList<TripDTO> pubTrips = new ArrayList<TripDTO>();
//		for(Trip t : entity.getPublishedTrips()){
//			pubTrips.add(conv.getDto(t));
//		}
		dto.setPublishedTrips(conv.getList((List<Trip>) entity.getPublishedTrips()));
		dto.setStreet(entity.getStreet());
		dto.setSurname(entity.getSurname());
		dto.setZipCode(entity.getZipCode());
		return dto;
	}

}
