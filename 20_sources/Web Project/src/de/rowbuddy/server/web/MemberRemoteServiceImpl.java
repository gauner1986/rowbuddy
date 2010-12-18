package de.rowbuddy.server.web;

import java.util.List;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.services.MemberRemoteService;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role.RoleName;
import de.rowbuddy.exceptions.NotLoggedInException;
import de.rowbuddy.exceptions.RowBuddyException;

public class MemberRemoteServiceImpl extends AbstractRemoteService implements
		MemberRemoteService {

	private static final long serialVersionUID = 634229104027874536L;

	@Override
	public Member addMember(Member addMember, RoleName... roles)
			throws RowBuddyException {
		try{
		return getRowBuddyFacade().addMember(addMember, roles);
		} catch(NullPointerException e){
			throw new NotLoggedInException();
		}
	}

	@Override
	public Member updateMember(Member member) throws RowBuddyException {
		try{
		return getRowBuddyFacade().updateMember(member);
		} catch(NullPointerException e){
			throw new NotLoggedInException();
		}
	}

	@Override
	public Integer importMembers(String importData) throws RowBuddyException {
		try{
		return getRowBuddyFacade().importMembers(importData);
		} catch(NullPointerException e){
			throw new NotLoggedInException();
		}
	}

	@Override
	public List<MemberDTO> getMembers() throws NotLoggedInException {
		try{
		return getRowBuddyFacade().getMembers();
		} catch(NullPointerException e){
			throw new NotLoggedInException();
		}
	}

}
