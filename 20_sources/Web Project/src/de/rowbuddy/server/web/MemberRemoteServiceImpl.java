package de.rowbuddy.server.web;

import java.util.ArrayList;
import java.util.List;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.services.MemberRemoteService;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role;
import de.rowbuddy.entities.Role.RoleName;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.NotLoggedInException;
import de.rowbuddy.exceptions.RowBuddyException;

public class MemberRemoteServiceImpl extends AbstractRemoteService implements
		MemberRemoteService {

	private static final long serialVersionUID = 634229104027874536L;

	@Override
	public Member addMember(Member addMember, RoleName... roles)
			throws RowBuddyException {
		return getRowBuddyFacade().addMember(addMember, roles);
	}

	@Override
	public Member updateMember(Member member) throws RowBuddyException {
		return getRowBuddyFacade().updateMember(member);
	}

	@Override
	public Integer importMembers(String importData) throws RowBuddyException {
		return getRowBuddyFacade().importMembers(importData);
	}

	@Override
	public List<MemberDTO> getMembers() throws NotLoggedInException {
		return getRowBuddyFacade().getMembers();
	}

	@Override
	public Member getMember(Long id) throws RowBuddyException {
		Member member = getRowBuddyFacade().getMember(id);
		member.setPublishedTrips(new ArrayList<Trip>());
		member.setRoles(new ArrayList<Role>());
		return member;
	}
}
