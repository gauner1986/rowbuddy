package de.rowbuddy.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role.RoleName;

public interface MemberRemoteServiceAsync {

	void addMember(Member addMember, RoleName[] roles,
			AsyncCallback<Member> callback);

	void importMembers(String importData, AsyncCallback<Integer> callback);

	void updateMember(Member member, AsyncCallback<Member> callback);

	void getMembers(AsyncCallback<List<MemberDTO>> callback);

	void getMember(Long id, AsyncCallback<Member> callback);

}
