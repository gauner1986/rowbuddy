package de.rowbuddy.boundary.converter;

import java.util.LinkedList;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

public class MemberDTOConverter extends DtoConverter<Member, MemberDTO> {

	@Override
	public MemberDTO getDto(Member entity) {
		MemberDTO convMember = new MemberDTO();
		
		convMember.setId(entity.getId());
		
		try {
			convMember.setEmail(entity.getEmail());
			convMember.setBirthdate(entity.getBirthdate());
			convMember.setCity(entity.getCity());
			convMember.setDeleted(entity.getDeleted());
			convMember.setGivenname(entity.getGivenname());
			convMember.setMemberId(entity.getMemberId());
			convMember.setMobilePhone(entity.getMobilePhone());
			convMember.setPasswordHash(entity.getPasswordHash());
			convMember.setPhone(entity.getPhone());
			convMember.setStreet(entity.getStreet());
			convMember.setSurname(entity.getSurname());
			convMember.setZipCode(entity.getZipCode());
		} catch (RowBuddyException e) {
			e.printStackTrace();
		}
		
		LinkedList<Trip> trips = new LinkedList<Trip>();
		for(Trip t : entity.getPublishedTrips()){
			trips.add(t);
		}
		
		convMember.setPublishedTrips(trips);
		
		
		LinkedList<Role> roleList = new LinkedList<Role>();
		for(Role r: entity.getRoles()){
			roleList.add(r);
		}
		
		convMember.setRoles(roleList);
		return convMember;
	}
}
