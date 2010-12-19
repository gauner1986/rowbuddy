package de.rowbuddy.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import de.rowbuddy.boundary.converter.MemberDTOConverter;
import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.business.MemberManagement;
import de.rowbuddy.entities.Member;

@Stateless
@LocalBean
public class MemberBoundary {

	@EJB
	private MemberManagement memberMgmt;
	private MemberDTOConverter memberConverter = new MemberDTOConverter();

	public List<MemberDTO> getMembers() {
		List<Member> members = memberMgmt.getMembers();
		return memberConverter.getList(members);
	}

	public List<MemberDTO> searchMember(String search) {
		List<Member> members = memberMgmt.searchMember(search);
		return memberConverter.getList(members);
	}

}
