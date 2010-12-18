package de.rowbuddy.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role.RoleName;
import de.rowbuddy.exceptions.NotLoggedInException;
import de.rowbuddy.exceptions.RowBuddyException;

public interface MemberRemoteService extends RemoteService {

	public Member addMember(Member addMember, RoleName... roles)
			throws RowBuddyException;

	public Member updateMember(Member member) throws RowBuddyException;

	public Integer importMembers(String importData) throws RowBuddyException;

	public List<MemberDTO> getMembers() throws NotLoggedInException;

}
