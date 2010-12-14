package de.rowbuddy.server.web;

import java.util.List;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.services.MemberRemoteService;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role.RoleName;
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
	public void importMembers(List<Member> members) throws RowBuddyException {
		getRowBuddyFacade().importMembers(members);
	}

	@Override
	public List<MemberDTO> getMembers() {
		return getRowBuddyFacade().getMembers();
	}

}
